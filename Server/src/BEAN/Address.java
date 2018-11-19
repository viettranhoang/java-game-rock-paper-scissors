package BEAN;

import java.util.Vector;

public class Address {
	public static Vector<Integer> port1 = new Vector<Integer>();
	public static Vector<Integer> port2 = new Vector<Integer>();
	public static Vector<String> ip = new Vector<String>();
	public static Vector<String> name = new Vector<String>();
	
	public Address(){
	}
	public Address(int port1,int port2, String ip, String name) {
		Address.port1.add(port1);
		Address.port1.add(port2);
		Address.ip.add(ip);
		Address.name.add(name);
	}
}
