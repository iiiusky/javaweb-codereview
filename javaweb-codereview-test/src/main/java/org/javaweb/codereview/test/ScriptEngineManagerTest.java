package org.javaweb.codereview.test;

import sun.jvm.hotspot.memory.SystemDictionary;
import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.oops.Klass;
import sun.jvm.hotspot.runtime.VM;

import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * @author yz
 */
public class ScriptEngineManagerTest {

	public static void main(String[] args) {
//		ScriptEngineManager	 sem = new ScriptEngineManager();
		final ArrayList<InstanceKlass> klasses = new ArrayList<InstanceKlass>(128);

		SystemDictionary dict = VM.getVM().getSystemDictionary();
		dict.classesDo(new SystemDictionary.ClassVisitor() {
			@Override
			public void visit(Klass k) {
				if (k instanceof InstanceKlass) {
					System.out.println(k);
					klasses.add((InstanceKlass) k);
				}
			}

//			public void visit(Klass k) {
//				if (k instanceof InstanceKlass) {
//					klasses.add((InstanceKlass) k);
//				}
//			}
		});
	}

}
