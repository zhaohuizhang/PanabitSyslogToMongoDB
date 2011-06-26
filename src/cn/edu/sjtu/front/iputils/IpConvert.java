/**
 * 
 */
package cn.edu.sjtu.front.iputils;

/**
 * @author jianwen
 *
 */
public class IpConvert {

	/**
	 * @param args
	 * 
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

	public static int dottoInt(String strIp) {
		return 0;
	}
	//ip convert to long 
  

	public static long iptolong(String ip) {
		String[] ips = ip.split("[.]");  
        long num =   16777216L*Long.parseLong(ips[0]) + 65536L*Long.parseLong(ips[1]) + 256*Long.parseLong(ips[2]) + Long.parseLong(ips[3]);  
    	return num;
	}
}
