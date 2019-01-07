package org.javaweb.codereview.commons;

public class CodeReviewMethodHookDesc {

	/**
	 * Hook类名
	 */
	private String hookClassName;

	/**
	 * Hook方法名
	 */
	private String hookMethodName;

	/**
	 * Hook方法参数描述符
	 */
	private String hookMethodArgTypeDesc;

	/**
	 * 处理Hook点的类对象
	 */
	private Class methodVisitorClass;

	/**
	 * ASM方法Hook
	 *
	 * @param hookClassName         hook类名
	 * @param hookMethodName        hook方法名
	 * @param hookMethodArgTypeDesc hook方法描述符
	 * @param methodVisitorClass    处理Hook点的类对象
	 */
	public CodeReviewMethodHookDesc(String hookClassName, String hookMethodName,
	                                String hookMethodArgTypeDesc, Class methodVisitorClass) {

		this.hookClassName = hookClassName;
		this.hookMethodName = hookMethodName;
		this.hookMethodArgTypeDesc = hookMethodArgTypeDesc;
		this.methodVisitorClass = methodVisitorClass;
	}

	public String getHookClassName() {
		return hookClassName;
	}

	public void setHookClassName(String hookClassName) {
		this.hookClassName = hookClassName;
	}

	public String getHookMethodName() {
		return hookMethodName;
	}

	public void setHookMethodName(String hookMethodName) {
		this.hookMethodName = hookMethodName;
	}

	public String getHookMethodArgTypeDesc() {
		return hookMethodArgTypeDesc;
	}

	public void setHookMethodArgTypeDesc(String hookMethodArgTypeDesc) {
		this.hookMethodArgTypeDesc = hookMethodArgTypeDesc;
	}

	public Class getMethodVisitorClass() {
		return methodVisitorClass;
	}

	public void setMethodVisitorClass(Class methodVisitorClass) {
		this.methodVisitorClass = methodVisitorClass;
	}

}