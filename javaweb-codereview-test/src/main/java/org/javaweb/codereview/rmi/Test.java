package org.javaweb.codereview.rmi;

import java.rmi.Naming;

public class Test {

	public static void main(String[] args) {
		try {
			int    port = 8001;
			String url  = "rmi://192.168.0.104:" + port + "/test";

			RMITestInterface rt = (RMITestInterface) Naming.lookup(url);

			String result = rt.test("Hello World~");// 调用远程方法

			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
