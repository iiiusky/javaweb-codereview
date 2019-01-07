/*
 * Copyright yz 2018-01-20 Email:admin@javaweb.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.javaweb.codereview.agent;

import org.javaweb.codereview.commons.CodeReviewAsm;
import org.javaweb.codereview.commons.CodeReviewMethodHookDesc;
import org.javaweb.codereview.hook.ExpressionHook;
import org.javaweb.codereview.hook.FileInputStreamHook;
import org.javaweb.codereview.hook.FileOutputStreamHook;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.Instrumentation;
import java.util.*;
import java.util.logging.Logger;

public class CodeReviewAgent implements Opcodes {

	private static Set<String> retransformClasses = new HashSet<String>();

	private static List<CodeReviewMethodHookDesc> methodHookList = Collections.synchronizedList(new ArrayList<CodeReviewMethodHookDesc>());

	private static final Logger LOG = Logger.getLogger("info");

	static {
		// MVEL2 Hook
		methodHookList.add(
				new CodeReviewMethodHookDesc(
						CodeReviewAsm.MVEL_INTERPRETED_RUNTIME_CLASS, "parse",
						"()Ljava/lang/Object;", ExpressionHook.class
				)
		);

		// OGNL Hook
		methodHookList.add(
				new CodeReviewMethodHookDesc(
						CodeReviewAsm.OGNL_CLASS, "parseExpression",
						"(Ljava/lang/String;)Ljava/lang/Object;",
						ExpressionHook.class
				)

		);

		// SpEL Hook
		methodHookList.add(
				new CodeReviewMethodHookDesc(
						CodeReviewAsm.SPEL_EXPRESSION_CLASS, "<init>",
						"(Ljava/lang/String;Lorg/springframework/expression/spel/ast/SpelNodeImpl;" +
								"Lorg/springframework/expression/spel/SpelParserConfiguration;)V",
						ExpressionHook.class
				)
		);

		// FileInputStream Hook
		methodHookList.add(
				new CodeReviewMethodHookDesc(
						CodeReviewAsm.FILE_INPUTSTREAM_CLASS, "<init>", "(Ljava/io/File;)V",
						FileInputStreamHook.class
				)
		);

		// FileOutputStream Hook
		methodHookList.add(
				new CodeReviewMethodHookDesc(
						CodeReviewAsm.FILE_OUTPUTSTREAM_CLASS, "<init>", "(Ljava/io/File;Z)V",
						FileOutputStreamHook.class
				)
		);

		// 初始化需要被retransform的类列表
		retransformClasses.add(CodeReviewAsm.FILE_INPUTSTREAM_CLASS);// FileInputStream
		retransformClasses.add(CodeReviewAsm.FILE_OUTPUTSTREAM_CLASS);// FileOutputStream
		retransformClasses.add(CodeReviewAsm.FILE_CHANNEL_IMPL_CLASS);// FileChannelImpl
		retransformClasses.add(CodeReviewAsm.PROCESS_BUILDER_CLASS);// ProcessBuilder
		retransformClasses.add(CodeReviewAsm.UNIX_PROCESS_CLASS);// UNIXProcess
		retransformClasses.add(CodeReviewAsm.PROCESS_IMPL_CLASS);// ProcessImpl
		retransformClasses.add(CodeReviewAsm.RANDOM_ACCESS_FILE_CLASS);// RandomAccessFile
	}

	public static void premain(String args, final Instrumentation inst) {
		inst.addTransformer(new DefaultTransform(methodHookList), true);

		// 设置retransformClasses
		setRetransformClasses(inst);
	}

	/**
	 * 设置需要retransformClasses的类
	 *
	 * @param inst
	 */
	private static void setRetransformClasses(Instrumentation inst) {
		List<Class> classList     = new ArrayList<Class>();
		Class[]     loadedClasses = inst.getAllLoadedClasses();

		for (Class loadedClass : loadedClasses) {
			String className = loadedClass.getName();

			// 检查类是否需要被retransformClasses且支持retransformClasses修改
			if (inst.isModifiableClass(loadedClass) && retransformClasses.contains(className)) {
				classList.add(loadedClass);
			}
		}

		if (classList.size() > 0) {
			Class[] classes = classList.toArray(new Class[classList.size()]);

			try {
				inst.retransformClasses(classes);
			} catch (Exception e) {
				LOG.info("LingXe 设置点retransformClasses异常:" + e);
			}
		}
	}

}