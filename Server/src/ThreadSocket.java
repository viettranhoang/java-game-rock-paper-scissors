import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import BEAN.Address;
import BEAN.User;
import DAO.UserDAO;

public class ThreadSocket extends Thread{
	private Socket socket;
	private int port;
	static BufferedReader br = null;
	static BufferedWriter bw = null;
	
	
	public ThreadSocket(Socket socket, int port) {
		this.socket = socket;
		this.port = port;
	}


	Address add = new Address();
	@Override
	public void run() {

        try {
        	System.out.println("------------" +port);
            //Tạo luồng nhận dữ liệu từ Client
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//Tạo luồng gửi dữ liệu về Client
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
          //Tạo luồng nhận dữ liệu từ Client
            DataInputStream inFromClient = new DataInputStream(socket.getInputStream());
            //Tạo luồng gửi dữ liệu về Client
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
	        while(true) {
	        	String readLine = br.readLine();
	        	String[] ss = readLine.split("-");
	        	
	        	if(ss[0].equals("1")) {
	        		System.out.println("user_login : " + ss[1]);
	        		if(UserDAO.checkLogin(ss[1], ss[2])) {
	        			
	        			System.out.println("Dang nhap thanh cong");
	        			bw.write("1"); 
	    				bw.newLine();
	    				bw.flush();
	        		}else {
	        			System.out.println("Dang nhap that bai");
	        			bw.write("0"); 
	    				bw.newLine();
	    				bw.flush();
	        		}
	        	}
	        	if(ss[0].equals("2")) {
	        		System.out.println("user_login : " + ss[1] + " || user_name : " + ss[2]);
	        		if(!UserDAO.checkUser(ss[1])) {
	        			User user = new User(ss[1], ss[2], ss[3]);
	        			UserDAO.creatUser(user);
	        			System.out.println("Dang ky thanh cong");
	        			bw.write("1"); 
	    				bw.newLine();
	    				bw.flush();
	        		}else {
	        			System.out.println("Dang ky that bai");
	        			bw.write("0"); 
	    				bw.newLine();
	    				bw.flush();
	        		}
	        	}
	        	if(ss[0].equals("3")) // tao server
                {

                    int port1 = port +1;
                    add.port1.add(port);
                    add.port2.add(port1);
                    add.ip.add("127.0.0.1");
                    add.name.add(ss[1]);
                    String _string = "127.0.0.1-"+ port + "-" + port1;
                    //gui address ve client
                    outToClient.writeBytes(_string + "\n");
//                    bw.newLine();
//                    bw.flush();
                }
                if(ss[0].equals("4")) // tao client
                {
                    
                    String _strPort1 = ""; //add.port1.get(0).toString();
                    //System.out.println(_strPort1);
                    String _strPort2 = ""; //add.port2.get(0).toString();
                    String _strIp =""; // add.ip.get(0);
                    String _strName =""; // add.name.get(0);
                    for(int i =0; i < add.port1.size(); i++)
                    {    System.out.println(add.name.get(i) + "|");
                        _strPort1 =_strPort1 + add.port1.get(i) + "-";
                        _strPort2 =_strPort2 + add.port2.get(i) + "-";
                        _strIp    =_strIp    + add.ip.get(i)    + "-";
                        _strName  =_strName  + add.name.get(i)  + "-";
                     }
                                        
                    //System.out.println( _strPort1 + _strPort2 + _strIp);
                    outToClient.writeBytes(_strPort1 + "\n");                  
                    outToClient.writeBytes(_strPort2 + "\n");                 
                    outToClient.writeBytes(_strIp + "\n");      
                    outToClient.writeBytes(_strName + "\n");
                   
//                    System.out.println( _strPort1);
//                    System.out.println( _strPort2);
//                    System.out.println( _strIp);
//                    System.out.println( _strName);
//                    String[] sss = _strName.split("-");
//                    System.out.println( sss.length);
//                    for(int i=0; i< sss.length; i++)
//                    {
//                        System.out.println( sss[i]);
//                    }
                 }
                if(ss[0].equals("5"))
                {	

                    int j = 0;
                    for(j =0; j < add.name.size(); j++)
                    {
                        if(ss[1].equals(add.name.get(j)))
                            break;
                    }
                    add.port1.remove(j);
                    add.port2.remove(j);
                    add.ip.remove(j);
                    add.name.remove(j);                    
                }
                if(ss[0].equals("6")) {
                	System.out.println("co chay vao 6");
                }
	        	if(ss[0].equals("8")) {
	        		Vector<User> list = UserDAO.getBXH();
	        		String bxh = "";
	        		for (User user : list) {
						bxh+= user; 
					}
	        		System.out.println("bxh ser : " + bxh);
	        		outToClient.writeBytes(bxh + "\n");
//	        		thử sout cái biến bxh ra r chạy thử xem. nếu ko thấy bên kia hiện ra bảng, mà biến bxh vẫn có thì xem lại
//	        		cách truyền socket của ông thôi @@
	        		//uhm cach truyen nay sida vai ra y
	        		
	        	}
	        	
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
        
	}
}
