package org.javaweb.codereview.test;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * @author yz
 */
public class ReflectionTest {

	private static void exec() {
		try {
			System.out.println(Runtime.class.getMethod("exec", String.class).invoke(Runtime.class.getMethod("getRuntime").invoke(null), "curl -i localhost:8000"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			String str = "whoami";

			// java.lang.Runtime
			String runtime = new String(new byte[]{106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 82, 117, 110, 116, 105, 109, 101});

			// Runtime.class
			Class<?> c = Class.forName(runtime);

			// 获取getRuntime方法，Runtime.getRuntime()
			Method m1 = c.getMethod(new String(new byte[]{103, 101, 116, 82, 117, 110, 116, 105, 109, 101}));

			// 获取Runtime的exec方法，rt.exec(xxx)
			Method m2 = c.getMethod(new String(new byte[]{101, 120, 101, 99}), String.class);

			// Runtime.getRuntime().exec(str)
			Object obj2 = m2.invoke(m1.invoke(null), str);

			// 获取命令执行结果Process类的getInputStream()方法
			Method m = obj2.getClass().getMethod(new String(new byte[]{103, 101, 116, 73, 110, 112, 117, 116, 83, 116, 114, 101, 97, 109}));
			m.setAccessible(true);

			// process.getInputStream()
			InputStream in = (InputStream) m.invoke(obj2, new Object[]{});

			// 输出InputStream内容到
			Scanner scanner = new Scanner(in).useDelimiter("\\A");
			System.out.println(scanner.hasNext() ? scanner.next() : "");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
