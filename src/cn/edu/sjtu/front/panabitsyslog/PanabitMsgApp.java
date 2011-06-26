/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;

/**
 * @author jianwen，zhangzhaohui
 *
 */
public class PanabitMsgApp extends PanabitMsg {

	public  String appType;
	public  String connType;
	public  long srcGroup;
	public  long dstGroup;
	public  long startTime;
	public  long endTime;
	public  long	srcIpv4;
	public  int srcPort;
	public  long	dstIpv4;
	public  int dstPort;
	public  int	outByte;	// Traffic from src to dst, in bytes
	public  int	inByte;		// Traffic from dst to src, in bytes
	
	public PanabitMsgApp() {
		msgType = PanabitMsgEnum.MSGAPP;
	}

	public String toString() {
		// TODO Auto-generated method stub
		
		String s="Application:"+this.getAppType()+"  "+"Protocol:"+this.getConnType()+"  "+"StartTime:"+this.getStartTime()+"  "+"EndTime:"+this.getEndTime()+"  "+"Srcip:"+this.getSrcIpv4()+"  "+"Srcport:"+this.getSrcPort()+"  "+"SrcGroup"+this.getSrcGroup()+"  "+"Desip:"+this.getDstIpv4()+"  "+"Desport:"+this.getDstPort()+"  "+"DstGroup"+this.getDstGroup()+"  "+"Inbyte:"+this.getInByte()+"  "+"Outbyte:"+this.getOutByte();
		return s;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getConnType() {
		return connType;
	}

	public void setConnType(String connType) {
		this.connType = connType;
	}

	public long getSrcGroup() {
		return srcGroup;
	}

	public void setSrcGroup(long srcGroup) {
		this.srcGroup = srcGroup;
	}

	public long getDstGroup() {
		return dstGroup;
	}

	public void setDstGroup(long dstGroup) {
		this.dstGroup = dstGroup;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getSrcIpv4() {
		return srcIpv4;
	}

	public void setSrcIpv4(long srcIpv4) {
		this.srcIpv4 = srcIpv4;
	}

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public long getDstIpv4() {
		return dstIpv4;
	}

	public void setDstIpv4(long dstIpv4) {
		this.dstIpv4 = dstIpv4;
	}

	public int getDstPort() {
		return dstPort;
	}

	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	public int getOutByte() {
		return outByte;
	}

	public void setOutByte(int outByte) {
		this.outByte = outByte;
	}

	public int getInByte() {
		return inByte;
	}

	public void setInByte(int inByte) {
		this.inByte = inByte;
	}



	
}
