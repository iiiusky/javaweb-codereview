package org.javaweb.codereview.utils;

/**
 * @author yz
 */
public class ClassUtils {

	public static void printStackTrace() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		for (StackTraceElement element : elements) {
			System.err.println(element);
			System.err.println("--------------------------------------------------------------------------");
		}
	}

}
