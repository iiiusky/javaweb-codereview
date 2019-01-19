package org.javaweb.codereview.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMITestImpl extends UnicastRemoteObject implements RMITestInterface {

	private static final long serialVersionUID = 1L;

	protected RMITestImpl() throws RemoteException {
		super();
	}

	public String test(String str) {
		System.out.println(str);

		return "Hello RMI.";
	}

}