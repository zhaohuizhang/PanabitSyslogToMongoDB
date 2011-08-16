/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author jianwen，zhangzhaohui
 *
 */
public class PanabitMsgApp extends PanabitMsg {

	public  String appType;
	public  String connType;
	public  long startTime;
	public  long endTime;
	public  int	srcIpv4;
	public  int srcPort;
	public  int	dstIpv4;
	public  int dstPort;
	public  int srcGroup;
	public  int dstGroup;
	public  long	outByte;	// Traffic from src to dst, in bytes
	public  long	inByte;		// Traffic from dst to src, in bytes
	
	public PanabitMsgApp() {
		msgType = EnumPanabitMsg.MSGAPP;
	}
	
	public PanabitMsgApp(PanabitMsgApp msgApp) {
		this();
		this.appType = msgApp.appType;
		this.connType = msgApp.connType;
		this.srcGroup = msgApp.srcGroup;
		this.dstGroup = msgApp.dstGroup;
		this.startTime = msgApp.startTime;
		this.endTime = msgApp.endTime;
		this.srcIpv4 = msgApp.srcIpv4;
		this.srcPort = msgApp.srcPort;
		this.outByte = msgApp.outByte;
		this.inByte = msgApp.inByte;
	}

	public String toString() {
		String s="Application:"+this.getAppType()+"  "+"Protocol:"+this.getConnType()+"  "+"StartTime:"+this.getStartTime()+"  "+"EndTime:"+this.getEndTime()+"  "+"Srcip:"+this.getSrcIpv4()+"  "+"Srcport:"+this.getSrcPort()+"  "+"SrcGroup:"+this.getSrcGroup()+"  "+"Desip:"+this.getDstIpv4()+"  "+"Desport:"+this.getDstPort()+"  "+"DstGroup:"+this.getDstGroup()+"  "+"Inbyte:"+this.getInByte()+"  "+"Outbyte:"+this.getOutByte();
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

	public int getSrcGroup() {
		return srcGroup;
	}

	public void setSrcGroup(int srcGroup) {
		this.srcGroup = srcGroup;
	}

	public int getDstGroup() {
		return dstGroup;
	}

	public void setDstGroup(int dstGroup) {
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

	public int getSrcIpv4() {
		return srcIpv4;
	}

	public void setSrcIpv4(int srcIpv4) {
		this.srcIpv4 = srcIpv4;
	}

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public int getDstIpv4() {
		return dstIpv4;
	}

	public void setDstIpv4(int dstIpv4) {
		this.dstIpv4 = dstIpv4;
	}

	public int getDstPort() {
		return dstPort;
	}

	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	public long getOutByte() {
		return outByte;
	}

	public void setOutByte(long outByte) {
		this.outByte = outByte;
	}

	public long getInByte() {
		return inByte;
	}

	public void setInByte(long inByte) {
		this.inByte = inByte;
	}
	
}
