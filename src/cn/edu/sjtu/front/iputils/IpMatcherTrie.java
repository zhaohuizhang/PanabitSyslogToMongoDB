package cn.edu.sjtu.front.iputils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IpMatcherTrie {
	class TrieNode{
		Ipv4NetAddr leafNode;
		TrieNode leftNode;
		TrieNode rightNode;
		
		TrieNode(){
			leafNode = null;
			leftNode = null;
			rightNode = null;
		}
		
		TrieNode(Ipv4NetAddr in){
			leafNode = null;
			leftNode = null;
			leafNode = new Ipv4NetAddr(in);
		}
	}
	
	public static  TrieNode root = null;
	
	public void insert(Ipv4NetAddr v4NetAddr){
		TrieNode curNode = root; 
		int netMask;
		for (int i = 1; i < v4NetAddr.maskLen; i++){
			netMask = ((int)(1)) << ((int)32-i);
			if ( (v4NetAddr.netAddr & netMask) == 0 ) {
				if (curNode.leftNode == null) curNode.leftNode = new TrieNode();
				curNode = curNode.leftNode;
			}
			else {
				if (curNode.rightNode == null) curNode.rightNode = new TrieNode();
				curNode = curNode.rightNode;
			}			
		}			
		netMask = ((int)(1)) << ((int)32-v4NetAddr.maskLen);
		if ( (v4NetAddr.netAddr & netMask) == 0 ) {
			if (curNode.leftNode == null) curNode.leftNode = new TrieNode(v4NetAddr);
			else curNode.leftNode.leafNode = new Ipv4NetAddr(v4NetAddr);
		}
		else {
			if (curNode.rightNode == null) curNode.rightNode = new TrieNode(v4NetAddr);
			else curNode.rightNode.leafNode = new Ipv4NetAddr(v4NetAddr);
		}
	}
	
	public IpMatcherTrie() throws NumberFormatException, IOException{
		if (root == null) {
			root = new TrieNode();
			try{
			FileReader filename = new FileReader("F:/net.txt"); 
			BufferedReader in = new BufferedReader(filename);
			String readIn;
			String[] subReadIn;
			Ipv4NetAddr tempToAdd = new Ipv4NetAddr();
			
			while((readIn = in.readLine()) != null){
				subReadIn = readIn.split(" ");
				tempToAdd = new Ipv4NetAddr();
				tempToAdd.netAddr = (int) Long.parseLong(subReadIn[0].substring(2),16) & 0xFFFFFFFF;
				tempToAdd.maskLen = Integer.parseInt(subReadIn[1]);
				tempToAdd.netGroup = Integer.parseInt(subReadIn[2]);
				insert(tempToAdd);
		      }
		    }
			catch (FileNotFoundException e)
		    {
		         e.printStackTrace();
		     } 
		     catch (IOException e)
		     {
		         e.printStackTrace();
		     }
			 

	   }
	}
	
	public Ipv4NetAddr ipMatch(int ipv4Addr){
		Ipv4NetAddr result = null;
		int i = 1;
		int netMask;
		TrieNode curNode = root;
		while (curNode != null) {
			if (curNode.leafNode != null) result = new Ipv4NetAddr(curNode.leafNode);
			netMask = ((int)(1)) << ((int)32-i);
			if ( (ipv4Addr & netMask) == 0 ) curNode = curNode.leftNode;
			else curNode = curNode.rightNode;
			i++;
		}
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		IpMatcherTrie test = new IpMatcherTrie();
		System.out.println(Integer.toHexString(test.ipMatch(0xAB891204).netAddr).toUpperCase());
		System.out.println(test.ipMatch(0x97491205).maskLen);
		System.out.println(test.ipMatch(0x97491205).netGroup);
	}

}
