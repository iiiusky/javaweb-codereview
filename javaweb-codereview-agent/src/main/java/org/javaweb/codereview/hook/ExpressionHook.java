package org.javaweb.codereview.hook;

import org.javaweb.codereview.commons.CodeReviewMethodHookVisitor;
import org.javaweb.codereview.utils.ClassUtils;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author yz
 */
public class ExpressionHook extends CodeReviewMethodHookVisitor {

	/**
	 * Creates a new {@link AdviceAdapter}.
	 *
	 * @param api    the ASM API version implemented by this visitor. Must be one
	 *               of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
	 * @param mv     the method visitor to which this adapter delegates calls.
	 * @param access the method's access flags (see {@link Opcodes}).
	 * @param name   the method's name.
	 * @param desc   the method's descriptor (see {@link Type Type}).
	 */
	public ExpressionHook(int api, MethodVisitor mv,
	                         int access, String name, String desc) {

		super(api, mv, access, name, desc);
	}

	@Override
	public void visitCode() {
		if ("ognl.Ognl".equals(className)) {
			mv.visitVarInsn(Opcodes.ALOAD, 0);// 传入参数名
		} else {
			mv.visitVarInsn(Opcodes.ALOAD, 1);// 传入参数名
		}

		// 调用对应的处理类处理
		mv.visitMethodInsn(
				Opcodes.INVOKESTATIC, ExpressionHook.class.getName().replace(".", "/"),
				"expression", "(Ljava/lang/String;)V", false
		);
	}

	/**
	 * SpEL、OGNL、MVEL2表达式处理
	 *
	 * @param exp
	 */
	public static void expression(String exp) {
		System.err.println("---------------------------------Expression-----------------------------------------");
		System.err.println(exp);
		System.err.println("---------------------------------调用链---------------------------------------");

		ClassUtils.printStackTrace();
	}

}
