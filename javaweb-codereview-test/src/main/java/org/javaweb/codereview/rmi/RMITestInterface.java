package org.javaweb.codereview.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMITestInterface extends Remote {

	String test(String str) throws RemoteException;

}