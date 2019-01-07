package org.javaweb.codereview.agent;

import org.javaweb.codereview.commons.CodeReviewMethodHookDesc;
import org.javaweb.codereview.commons.CodeReviewMethodHookVisitor;
import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.reflect.Constructor;
import java.security.ProtectionDomain;
import java.util.List;

/**
 * @author yz
 */
public class DefaultTransform implements ClassFileTransformer {

	private List<CodeReviewMethodHookDesc> methodHookList;

	public DefaultTransform(List<CodeReviewMethodHookDesc> methodHookList) {
		this.methodHookList = methodHookList;
	}

	@Override
	public byte[] transform(ClassLoader loader, String name, Class<?> classBeingRedefined,
	                        ProtectionDomain protectionDomain, byte[] classfileBuffer) {

		final String className = name.replace("/", ".");

		for (final CodeReviewMethodHookDesc hookDesc : methodHookList) {
			if (hookDesc.getHookClassName().equals(className)) {
				final ClassReader cr = new ClassReader(classfileBuffer);

				// 忽略接口类
				ClassWriter cw  = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
				final int   api = Opcodes.ASM5;

				try {
					ClassVisitor classVisitor = new ClassVisitor(api, cw) {

						@Override
						public void visit(int version, int access, String name, String signature,
						                  String superName, String[] interfaces) {

							super.visit(version, access, name, signature, superName, interfaces);
						}

						@Override
						public MethodVisitor visitMethod(final int access, final String methodName,
						                                 final String argTypeDesc, final String signature,
						                                 final String[] exceptions) {

							final MethodVisitor methodVisitor = super.visitMethod(
									access, methodName, argTypeDesc, signature, exceptions
							);

							// 检查Hook方法名、方法描述符是否一致
							if (hookDesc.getHookMethodName().equals(methodName)
									&& hookDesc.getHookMethodArgTypeDesc().equals(argTypeDesc)) {

								// 检查Hook处理类是否设置正确
								if (hookDesc.getMethodVisitorClass() != null) {
									Class hooClass = hookDesc.getMethodVisitorClass();

									// 检测当前HookClass是否是CodeReviewMethodHookVisitor的子类
									if (CodeReviewMethodHookVisitor.class.isAssignableFrom(hooClass)) {
										try {
											Constructor hookConstructor = hooClass.getDeclaredConstructor(
													int.class, MethodVisitor.class,
													int.class, String.class, String.class
											);

											return (MethodVisitor) hookConstructor.newInstance(
													api, methodVisitor, access, className, argTypeDesc
											);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							}

							return methodVisitor;
						}
					};

					cr.accept(classVisitor, ClassReader.EXPAND_FRAMES);
					classfileBuffer = cw.toByteArray();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}

		return classfileBuffer;
	}

}
