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
public class IpMatcherLst  {

	public static ArrayList<Ipv4NetAddr> ipv4NetAddrList = null;
	
	/**
	 * 
	 */
	public IpMatcherLst() {
		// TODO Auto-generated constructor stub
	}
	
	public void ListConstruction() throws IOException{
		if (ipv4NetAddrList == null) {
			ipv4NetAddrList = new ArrayList<Ipv4NetAddr>(200);

			File fn = new File("D:/Users/zys/PanabitSyslogJava/net.txt");
			BufferedReader in = new BufferedReader(new FileReader(fn));
			String s;
			String[] ss;
			int i=0;
			
			s=in.readLine();
			ss=s.split(" ");
			Ipv4NetAddr temp = new Ipv4NetAddr();
			temp.netAddr=Long.parseLong(ss[0].substring(2),16);
			temp.maskLen=Long.parseLong(ss[1]);
			temp.netGroup=Long.parseLong(ss[2]);
			ipv4NetAddrList.add(temp);
       
			while((s=in.readLine())!=null){
				ss=s.split(" ");
				temp=new Ipv4NetAddr();
				temp.netAddr=Long.parseLong(ss[0].substring(2),16);
				temp.maskLen=Long.parseLong(ss[1]);
				temp.netGroup=Long.parseLong(ss[2]);

				for(i=0;i<ipv4NetAddrList.size();i++){
					if (temp.maskLen >= ipv4NetAddrList.get(i).maskLen){
						ipv4NetAddrList.add(i,temp);
						break;
					}
					else if (i==(ipv4NetAddrList.size()-1))
						ipv4NetAddrList.add(temp);
				}
			}
		}
}

	/* (non-Javadoc)
	 * @see cn.edu.sjtu.front.iputils.IpMatcher#ipMatch(int)
	 */

	public Ipv4NetAddr ipMatch(long ipv4Addr) {
		Ipv4NetAddr tp = null; 
		Ipv4NetAddr result = null; 
		int i=0;
		for (i=0;i<ipv4NetAddrList.size();i++){
			tp = ipv4NetAddrList.get(i);
//			System.out.println(Long.toHexString(tp.netAddr).toUpperCase()+" "+tp.maskLen+" "+tp.netGroup);
			long MaskLen=(-1)<<((long)32-tp.maskLen);
			if ((ipv4Addr & MaskLen) == tp.netAddr){
				result=new Ipv4NetAddr(tp);
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
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		IpMatcherLst test = new IpMatcherLst();
		test.ListConstruction();
		System.out.println(Long.toHexString(test.ipMatch(0x6FBA1807).netAddr).toUpperCase());
		System.out.println(test.ipMatch(0x6FBA1809).maskLen);
		System.out.println(test.ipMatch(0x6FBA1809).netGroup);
	}

}
