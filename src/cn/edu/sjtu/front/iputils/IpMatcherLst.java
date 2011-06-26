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
			ipv4NetAddrList = new ArrayList<Ipv4NetAddr>(200);

			File filename = new File(IpMatcherConf.NETFILE); 
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String readIn;
			String[] subReadIn;
			
			readIn = in.readLine();
			subReadIn = readIn.split(" ");
			Ipv4NetAddr tempToAdd = new Ipv4NetAddr();
			tempToAdd.netAddr = Long.parseLong(subReadIn[0].substring(2), 16);
			tempToAdd.maskLen = Long.parseLong(subReadIn[1]);
			tempToAdd.netGroup = Long.parseLong(subReadIn[2]);
			ipv4NetAddrList.add(tempToAdd);
       
			while((readIn = in.readLine()) != null){
				subReadIn = readIn.split(" ");
				tempToAdd = new Ipv4NetAddr();
				tempToAdd.netAddr = Long.parseLong(subReadIn[0].substring(2),16);
				tempToAdd.maskLen = Long.parseLong(subReadIn[1]);
				tempToAdd.netGroup = Long.parseLong(subReadIn[2]);

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
	
//	public void ListConstruction() throws IOException{
//		
//}

	/* (non-Javadoc)
	 * @see cn.edu.sjtu.front.iputils.IpMatcher#ipMatch(int)
	 */

	public Ipv4NetAddr ipMatch(long ipv4Addr) {
		Ipv4NetAddr result = null; 

		for (Ipv4NetAddr tempInList : ipv4NetAddrList){
//			System.out.println(Long.toHexString(tp.netAddr).toUpperCase()+" "+tp.maskLen+" "+tp.netGroup);
			long maskLen = (-1) << ((long)32-tempInList.maskLen);
			if ((ipv4Addr & maskLen) == tempInList.netAddr){
				result = new Ipv4NetAddr(tempInList);
				break;
			}
		}
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
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		IpMatcherLst test = new IpMatcherLst();
////		test.ListConstruction();
//		System.out.println(Long.toHexString(test.ipMatch(0x6FBA1807).netAddr).toUpperCase());
//		System.out.println(test.ipMatch(0x6FBA1809).maskLen);
//		System.out.println(test.ipMatch(0x6FBA1809).netGroup);
//	}

}
