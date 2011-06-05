/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;

/**
 * @author jianwen，zhangzhaohui
 *
 */
public class PanabitMsgApp extends PanabitMsg {

	public static String appType;
	public static String connType;
	public static long startTime;
	public static long endTime;
	public static long	srcIpv4;
	public static int srcPort;
	public static long	dstIpv4;
	public static int dstPort;
	public static int	outByte;	// Traffic from src to dst, in bytes
	public static int	inByte;		// Traffic from dst to src, in bytes
	
	

	public String toString() {
		// TODO Auto-generated method stub
		
		String s="Application:"+PanabitMsgApp.getAppType()+"  "+"Protocol:"+PanabitMsgApp.getConnType()+"  "+"StartTime:"+PanabitMsgApp.getStartTime()+"  "+"EndTime:"+PanabitMsgApp.getEndTime()+"  "+"Srcip:"+PanabitMsgApp.getSrcIpv4()+"  "+"Srcport:"+PanabitMsgApp.getSrcPort()+"  "+"Desip:"+PanabitMsgApp.getDstIpv4()+"  "+"Desport:"+PanabitMsgApp.getDstPort()+"  "+"Inbyte:"+PanabitMsgApp.getInByte()+"  "+"Outbyte:"+PanabitMsgApp.getOutByte();
		return s;
	}



	public static String getAppType() {
		return appType;
	}



	public static void setAppType(String appType) {
		PanabitMsgApp.appType = appType;
	}



	public static String getConnType() {
		return connType;
	}



	public static void setConnType(String connType) {
		PanabitMsgApp.connType = connType;
	}



	public static long getStartTime() {
		return startTime;
	}



	public static void setStartTime(long startTime) {
		PanabitMsgApp.startTime = startTime;
	}



	public static long getEndTime() {
		return endTime;
	}



	public static void setEndTime(long endTime) {
		PanabitMsgApp.endTime = endTime;
	}



	public static long getSrcIpv4() {
		return srcIpv4;
	}



	public static void setSrcIpv4(long srcIpv4) {
		PanabitMsgApp.srcIpv4 = srcIpv4;
	}



	public static int getSrcPort() {
		return srcPort;
	}



	public static void setSrcPort(int srcPort) {
		PanabitMsgApp.srcPort = srcPort;
	}



	public static long getDstIpv4() {
		return dstIpv4;
	}



	public static void setDstIpv4(long dstIpv4) {
		PanabitMsgApp.dstIpv4 = dstIpv4;
	}



	public static int getDstPort() {
		return dstPort;
	}



	public static void setDstPort(int dstPort) {
		PanabitMsgApp.dstPort = dstPort;
	}



	public static int getOutByte() {
		return outByte;
	}



	public static void setOutByte(int outByte) {
		PanabitMsgApp.outByte = outByte;
	}



	public static int getInByte() {
		return inByte;
	}



	public static void setInByte(int inByte) {
		PanabitMsgApp.inByte = inByte;
	}

}
