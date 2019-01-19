package org.javaweb.codereview.rmi;

import sun.rmi.registry.RegistryImpl_Stub;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author yz
 */
public class RMILookupTest {

	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
		String url = "rmi://localhost:8003";

		RegistryImpl_Stub rt = (RegistryImpl_Stub) Naming.lookup(url);

		// 获取localhost 8081端口注册的RMI服务名称
		String[] names = rt.list();

		for (String rmiName : names) {
			System.out.println(rmiName);
		}
	}

}
