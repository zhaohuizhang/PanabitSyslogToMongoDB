/**
 * 
 */
package cn.edu.sjtu.front.iputils;

/**
 * @author jianwen
 *
 */
public interface IpMatcher {

	public abstract Ipv4NetAddr ipMatch(long ipv4Addr);
	public abstract Ipv4NetAddr ipMatch(Ipv4NetAddr v4NetAddr);
	
}
