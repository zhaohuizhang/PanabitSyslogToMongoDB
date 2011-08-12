/**
 * 
 */
package cn.edu.sjtu.front.iputils;

/**
 * @author jianwen
 *
 */
public class Ipv4NetAddr {

	public int netAddr;
	public int maskLen;
	public int netGroup;
	
	public Ipv4NetAddr() {
		netAddr = 0x00000000;
		maskLen = 1;
		netGroup = IpMatcherConf.UNKOWNGROUP;
	}
	
	public Ipv4NetAddr(int _netAddr, int _maskLen, int _netGroup) {
		this.netAddr = _netAddr;
		this.maskLen = _maskLen;
		this.netGroup = _netGroup;
	}

	public Ipv4NetAddr(Ipv4NetAddr _v4netAddr) {
		this();
		netAddr = _v4netAddr.netAddr;
		maskLen = _v4netAddr.maskLen;
		netGroup = _v4netAddr.netGroup;
	}
	
	/*
	 * @overide equals
	 */
	public Boolean equals(Ipv4NetAddr _v4netAddr) {
		if ((this.netAddr == _v4netAddr.netAddr) &&
			(this.maskLen == _v4netAddr.maskLen) &&
			(this.netGroup == _v4netAddr.netGroup)) 
		{
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		Ipv4NetAddr ipv4Local = new Ipv4NetAddr(0x00ffeecc, 24, 999);
		Ipv4NetAddr ipv4Remote = new Ipv4NetAddr(0x00ffeecc, 24, 999);
		
		// Check if they are "=="
		if (ipv4Local == ipv4Remote) {
			System.out.println(ipv4Local.netAddr + " == " + ipv4Remote.netAddr);
		} else {
			System.out.println(ipv4Local.netAddr + " != " + ipv4Remote.netAddr);
		}
		
		// Check if tey are "equal"
		if (ipv4Local.equals(ipv4Remote) == true) {
			System.out.println(ipv4Local.netAddr + " equals " + ipv4Remote.netAddr);
		} else {
			System.out.println(ipv4Local.netAddr + " not equals " + ipv4Remote.netAddr);
		}		
		
	}
	
}
