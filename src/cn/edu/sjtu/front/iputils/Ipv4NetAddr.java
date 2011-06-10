/**
 * 
 */
package cn.edu.sjtu.front.iputils;

/**
 * @author jianwen
 *
 */
public class Ipv4NetAddr {

	long netAddr;
	long maskLen;
	long netGroup;
	
	public Ipv4NetAddr() {
		netAddr = 0x00000000;
		maskLen = 1;
		netGroup = 0;
	}
	
	public Ipv4NetAddr(long NA, long ML, long NG) {
		netAddr = NA;
		maskLen = ML;
		netGroup = NG;
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
