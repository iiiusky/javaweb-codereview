package org.javaweb.codereview.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author yz
 */
public class RMIRegistryTest {

	public static void createRegistry(int port) throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(port);
	}

	public static void main(String[] args) throws Exception {
		String portStr = args.length > 0 && args[0] != null ? args[0] : "1099";
		int    port    = Integer.parseInt(portStr);
		createRegistry(port);

		System.out.println("RMI启动成功,端口:" + port);

		while (true) {
			Thread.sleep(1000);
		}
	}

}
