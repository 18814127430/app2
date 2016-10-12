package com;

import java.net.*;
import java.util.Enumeration;

import utils.test;

public class Test6 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress ia = null;
		try {
			ia = ia.getLocalHost();
			
			String localname = ia.getHostName();
			String localip = ia.getHostAddress();
			System.out.println("本机名称是：" + localname);
			System.out.println("本机的ip是 ：" + localip);
			test.a(getLocalIP());
			test.a(getServerIp());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		//System.out.println(ipAddrStr);     
		return ipAddrStr;
	}
	
	public static String getServerIp() {
		String SERVER_IP = "";
		try {
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				ip = (InetAddress) ni.getInetAddresses().nextElement();
				SERVER_IP = ip.getHostAddress();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
					SERVER_IP = ip.getHostAddress();
					break;
				}
				else {
					ip = null;
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		
		return SERVER_IP;
	}
	
}
