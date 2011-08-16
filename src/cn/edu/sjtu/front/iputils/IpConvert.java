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

	public static int dottoInt(String strIp) {
		return 0;
	}

	public static int iptoInt(String ip) {
		String[] ips = ip.split("[.]");  
    	int num = (Integer.parseInt(ips[0])<<24) | (Integer.parseInt(ips[1])<<16) | (Integer.parseInt(ips[2])<<8) | (Integer.parseInt(ips[3]));
		return num;
	}
	   public static String getLongIpToString(long ipLong) {   
	        
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

}
