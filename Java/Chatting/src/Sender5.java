import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
public class Sender5 extends Thread {
	
	Socket socket;
	PrintWriter out = null;
	String name;
	String name2;
	boolean ear = false;
	
	//생성자
	public Sender5(Socket socket, String name)
	{
		this.socket = socket;
		try 
		{
			out = new PrintWriter(socket.getOutputStream(), true);
			this.name = name;
		}
		catch(Exception e)
		{
			System.out.println("예외3:"+e);
		}
	}
	
	//run()메소드 재정의
	@Override
	public void run()
	{
		Scanner s= new Scanner(System.in);
		try
		{
			//서버에 입력한 사용자 이름을 보내준다
			out.println(name);
			while(out!=null)
			{
				try
				{
					String s2 = s.nextLine();
					if(s2.equals("") || s2.length() == 0)
					{
						continue;
					}
					StringTokenizer str = new StringTokenizer(s2," ");
					List<String> lst = new ArrayList<String>();
					while(str.hasMoreTokens())
					{
						lst.add(str.nextToken());
					}
					if(s2.equals("q") || s2.equals("Q"))
					{
						out.println(URLEncoder.encode(s2, "UTF-8"));
						break;
					}
					else if(lst.get(0).equals("/to") && lst.size() > 2)
					{
						out.println(URLEncoder.encode(s2, "UTF-8"));
						
					}
					
					else if(lst.get(0).equals("/to") &&lst.size() == 2 && ear == false)
					{
						ear = true;
						name2 = lst.get(1);
						this.name2 = name2;
						
					}
					else if(lst.get(0).equals("/to") &&lst.size() == 2 && ear == true)
					{
						ear = false;
					}
					
					else if(ear == true)
					{
						out.println(URLEncoder.encode("/to"+" "+name2+" "+s2, "UTF-8"));
					}
					else if(ear == false)
					{
						out.println(URLEncoder.encode(s2, "UTF-8"));
					}
					

					
				}
				catch(UnsupportedEncodingException e)
				{
					System.out.println("예외S1:"+e);
				}
			}
			
			out.close();
			socket.close();
			
		}
		catch(Exception e)
		{
			System.out.println("예외S2:"+e);
		}
	}

}
