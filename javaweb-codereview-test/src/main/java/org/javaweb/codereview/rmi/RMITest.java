package org.javaweb.codereview.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Logger;

public class RMITest {

	private static final Logger LOG = Logger.getLogger("info");

	public static void main(String[] args) {
		try {
			int    port = 8001;
			String url  = "rmi://192.168.0.104:" + port + "/test";
			LocateRegistry.createRegistry(port);

			// 绑定Remote对象
			Naming.bind(url, new RMITestImpl());

			LOG.info("RMI服务启动成功,服务地址:" + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}