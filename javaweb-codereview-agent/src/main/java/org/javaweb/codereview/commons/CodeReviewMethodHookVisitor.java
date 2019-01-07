package org.javaweb.codereview.commons;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author yz
 */
public class CodeReviewMethodHookVisitor extends AdviceAdapter {

	protected String className;

	protected String methodDesc;

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
	protected CodeReviewMethodHookVisitor(int api, MethodVisitor mv, int access, String name, String desc) {
		super(api, mv, access, name, desc);

		this.className = name;
		this.methodDesc = desc;
	}

}
