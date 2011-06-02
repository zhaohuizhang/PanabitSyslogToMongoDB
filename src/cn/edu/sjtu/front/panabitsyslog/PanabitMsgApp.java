/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;

/**
 * @author jianwen
 *
 */
public class PanabitMsgApp extends PanabitMsg {

	public PanabitAppEnum appType;
	public PanabitConnEnum connType;
	public long startTime;
	public long endTime;
	public int	srcIpv4;
	public short srcPort;
	public int	dstIpv4;
	public short dstPort;
	public int	outByte;	// Traffic from src to dst, in bytes
	public int	inByte;		// Traffic from dst to src, in bytes
	
	public PanabitMsgApp() {
		msgType = PanabitMsgEnum.MSGAPP;
	}
	
	public PanabitMsgApp(PanabitAppEnum _appT, PanabitConnEnum _connType, long _startT, long _endT, int _srcIpv4, short _srcPort, int _dstIpv4, short _dstPort, int _outB, int _inB) {
		this();
		appType	= _appT;
		connType = _connType;
		startTime = _startT;
		endTime = _endT;
		srcIpv4 = _srcIpv4;
		srcPort = _srcPort;
		dstIpv4 = _dstIpv4;
		dstPort = _dstPort;
		outByte = _outB;
		inByte = _inB;
	}
	
	/* (non-Javadoc)
	 * @see cn.edu.sjtu.front.panabitsyslog.PanabitMsg#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
