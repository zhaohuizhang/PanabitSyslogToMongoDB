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
		netGroup = 0;
	}
	
	public Ipv4NetAddr(Ipv4NetAddr _v4netAddr) {
		this();
		netAddr = _v4netAddr.netAddr;
		maskLen = _v4netAddr.maskLen;
		netGroup = _v4netAddr.netGroup;
	}
	
	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
