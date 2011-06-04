package cn.edu.sjtu.front.panabitsyslog;
/*
       this part can find new application 
 * 
 * */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CheckoutApp {
   
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File f=new File("syslog.txt");
		// TODO Auto-generated method stub
		BufferedReader re = new BufferedReader(new FileReader(f));
		try { String d="";
			
			while(re.readLine()!=null){
				String a=re.readLine();
			    String b=a.substring(6, a.indexOf("."));
			   
	            if(d.indexOf(b)==-1){
	            	System.out.println(b+",");
	            }		
	            d+=b;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
