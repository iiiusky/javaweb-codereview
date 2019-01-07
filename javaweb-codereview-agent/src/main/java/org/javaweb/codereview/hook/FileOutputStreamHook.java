/*
 * Copyright yz 2018-01-27 Email:admin@javaweb.org.
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
package org.javaweb.codereview.hook;

import org.javaweb.codereview.commons.CodeReviewMethodHookVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;

public class FileOutputStreamHook extends CodeReviewMethodHookVisitor {

	public FileOutputStreamHook(int api, MethodVisitor mv, int access, String name, String desc) {
		super(api, mv, access, name, desc);
	}

	@Override
	public void visitCode() {
		// 获取File对象
		mv.visitVarInsn(ALOAD, 1);

		// 调用对应的处理类处理
		mv.visitMethodInsn(
				Opcodes.INVOKESTATIC, FileOutputStreamHook.class.getName().replace(".", "/"),
				"writeFile", "(Ljava/io/File;)V", false
		);
	}

	public static void writeFile(File file) {
		System.err.println("Hook到写文件操作:" + file.getAbsolutePath());
//		System.err.println("---------------------------------FileOutputStream Hook-----------------------------------------");
//		System.err.println(file.getAbsolutePath());
//		System.err.println("---------------------------------调用链---------------------------------------");
//
//		ClassUtils.printStackTrace();
	}

}
