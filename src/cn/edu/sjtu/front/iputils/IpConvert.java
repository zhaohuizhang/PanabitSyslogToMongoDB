/**
 * 
 */
package cn.edu.sjtu.front.iputils;

import java.beans.Introspector;

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
  

	public static int iptoInt(String ip) {
		String[] ips = ip.split("[.]");  
        
//		long num =   16777216L*Long.parseLong(ips[0]) | 65536L*Long.parseLong(ips[1]) | 256*Long.parseLong(ips[2]) | Long.parseLong(ips[3]);  
    	int num = (Integer.parseInt(ips[0])<<24) | (Integer.parseInt(ips[1])<<16) | (Integer.parseInt(ips[2])<<8) | (Integer.parseInt(ips[3]));
		return num;
	}
	   public String getLongIpToString(long ipLong) {   
	        
	        long mask[] = {0x000000FF,0x0000FF00,0x00FF0000,0xFF000000};  
	        long num = 0;  
	        StringBuffer ipInfo = new StringBuffer();  
	        for(int i=0;i<4;i++){  
	            num = (ipLong & mask[i])>>(i*8);  
	            if(i>0) ipInfo.insert(0,".");  
	            ipInfo.insert(0,Long.toString(num,10));  
	        }  
	        return ipInfo.toString();  
	    }  
//	   public static void main(String args[]){
//		   IpConvert ipC = new IpConvert();
//		   System.out.println(iptoInt("218.193.191.132"));
//		   System.out.println(iptoInt("67.92.212.116"));
//		   System.out.println(Integer.toHexString(1130157172));
//	   }

}
