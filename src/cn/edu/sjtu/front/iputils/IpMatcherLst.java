/**
 * 
 */
package cn.edu.sjtu.front.iputils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianwen
 *
 */
public class IpMatcherLst implements IpMatcher {

	public static List<Ipv4NetAddr> ipv4NetAddrList = null;
	
	/**
	 * 
	 */
	public IpMatcherLst() {
		// TODO Auto-generated constructor stub
		if (ipv4NetAddrList == null) {
			ipv4NetAddrList = new ArrayList<Ipv4NetAddr>(null);
			// TODO: Read IpMatchConf.NETFILE and initialize the List
			// Step 1: Read file net.txt to ipv4NetAddrList
			// Step 2: Sort according to masklen from big to small
		}
	}
	

	/* (non-Javadoc)
	 * @see cn.edu.sjtu.front.iputils.IpMatcher#ipMatch(int)
	 */
	@Override
	public Ipv4NetAddr ipMatch(int ipv4Addr) {
//		Ipv4NetAddr netAddr = new Ipv4NetAddr();
		// TODO
		// LOOP
		// if((ipv4Addr && masklen_1111) == Ipv4NetAddr.netaddr)
		// 		return new Ipv4NetAddr(Ipv4Addr.netAddr, Ipv4Addr.masklen, Ipv4Addr.group);
		// LOOP END
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.edu.sjtu.front.iputils.IpMatcher#ipMatch(cn.edu.sjtu.front.iputils.Ipv4NetAddr)
	 */
	@Override
	public Ipv4NetAddr ipMatch(Ipv4NetAddr v4NetAddr) {
		return ipMatch(v4NetAddr.netAddr);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
