/**
 * 
 */
package cn.edu.sjtu.front.iputils;

import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import java.io.*;

/**
 * @author jianwen
 *
 */
public class IpMatcherLst implements IpMatcher {

	public static ArrayList<Ipv4NetAddr> ipv4NetAddrList = null;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public IpMatcherLst() throws IOException {
		// TODO Auto-generated constructor stub
		if (ipv4NetAddrList == null) {
			ipv4NetAddrList = new ArrayList<Ipv4NetAddr>(1000);

			File filename = new File(IpMatcherConf.NETFILE); 
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String readIn;
			String[] subReadIn;
			Ipv4NetAddr tempToAdd = new Ipv4NetAddr();
			
			readIn = in.readLine();
			subReadIn = readIn.split(" ");
			tempToAdd = new Ipv4NetAddr();
			tempToAdd.netAddr = Integer.parseInt(subReadIn[0].substring(2), 16);
			tempToAdd.maskLen = Integer.parseInt(subReadIn[1]);
			tempToAdd.netGroup = Integer.parseInt(subReadIn[2]);
			ipv4NetAddrList.add(tempToAdd);
       
			while((readIn = in.readLine()) != null){
				subReadIn = readIn.split(" ");
				tempToAdd = new Ipv4NetAddr();
				// TODO: Check if readLine is empty Line or if the split is valid
				tempToAdd.netAddr = (int) Long.parseLong(subReadIn[0].substring(2),16) & 0xFFFFFFFF;
				tempToAdd.maskLen = Integer.parseInt(subReadIn[1]);
				tempToAdd.netGroup = Integer.parseInt(subReadIn[2]);

				for(int i=0; i < ipv4NetAddrList.size(); i++){
					if (tempToAdd.maskLen >= ipv4NetAddrList.get(i).maskLen){
						ipv4NetAddrList.add(i, tempToAdd);
						break;
					}
					else if (i == (ipv4NetAddrList.size()-1))
						ipv4NetAddrList.add(tempToAdd);
				}
			}
		}
	}
	

	/* (non-Javadoc)
	 * @see cn.edu.sjtu.front.iputils.IpMatcher#ipMatch(int)
	 */

	public Ipv4NetAddr ipMatch(int ipv4Addr) {
		// Ipv4NetAddr result = new Ipv4NetAddr();
		Ipv4NetAddr result = null;

		for (Ipv4NetAddr addrInList : ipv4NetAddrList){
			int netMask = ((int)(-1)) << ((int)32-addrInList.maskLen);
			if ((ipv4Addr & netMask) == addrInList.netAddr){
				result = new Ipv4NetAddr(addrInList);
				break;
			}
		}
		
		// TODO: Add Exception Hadling Code
		return result;
	}

	/* (non-Javadoc)
	 * @see cn.edu.sjtu.front.iputils.IpMatcher#ipMatch(cn.edu.sjtu.front.iputils.Ipv4NetAddr)
	 */

	public Ipv4NetAddr ipMatch(Ipv4NetAddr v4NetAddr) {
		return ipMatch(v4NetAddr.netAddr);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		IpMatcher test = new IpMatcherLst();
		System.out.println(Integer.toHexString(test.ipMatch(0x97491205).netAddr).toUpperCase());
		System.out.println(test.ipMatch(0x97491205).maskLen);
		System.out.println(test.ipMatch(0x97491205).netGroup);
	}

}
