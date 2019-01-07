package org.javaweb.codereview.test;

import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Scanner;

/**
 * @author yz
 */
public class MethodHandlesTest {

	public static void main(String[] args) {
		try {
			String               str          = "ping p2j.cn -c 1";
			Class                runtimeClass = Runtime.class;
			MethodHandles.Lookup lookup       = MethodHandles.lookup();

			// Runtime rt = Runtime.getRuntime()
			MethodHandle methodHandle = lookup.findStatic(
					runtimeClass, "getRuntime", MethodType.methodType(runtimeClass)
			);

			// 获取Runtime的exec方法
			MethodHandle execMethod = lookup.findVirtual(
					runtimeClass, "exec", MethodType.methodType(Process.class, new Class[]{
							String.class
					})
			);

			// 获取Process的getInputStream方法
			MethodHandle inputStreamMethod = lookup.findVirtual(
					Process.class, "getInputStream", MethodType.methodType(InputStream.class)
			);

			// 调用Runtime.getRuntime().exec(xxx).getInputStream()
			InputStream in = (InputStream) inputStreamMethod.invoke(
					execMethod.invoke(methodHandle.invoke(), str)
			);

			// 输出InputStream内容到
			Scanner scanner = new Scanner(in).useDelimiter("\\A");
			System.out.println(scanner.hasNext() ? scanner.next() : "");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
