/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcaro;

import com.client.screen.Login;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ltphong
 */
public class ClientCaro {
    public static Vector<String> listOnline = new Vector<String>();
    /**
     * @param args the command line arguments
     */
public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
            // TODO add your handling code here:
                    Socket ClientSocket = new Socket("localhost", 8888); 
            System.out.println("Kết nối thành công!");
            System.out.println(ClientSocket.getInetAddress());
            System.out.println(ClientSocket.getLocalSocketAddress());
            System.out.println(ClientSocket.getLocalPort());
            System.out.println(ClientSocket.getLocalAddress());
            
            
            
            //Tạo luồng nhận dữ liệu từ bàn phím
            DataInputStream inFromUser = new DataInputStream(System.in); 
            //Tạo luồng nhận dữ liệu từ Server
            DataInputStream inFromServer = new DataInputStream(ClientSocket.getInputStream()); 
            //Tạo luồng gửi dữ liệu lên Server
            DataOutputStream outToServer = new DataOutputStream(ClientSocket.getOutputStream()); 
        
            try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
            
        //Login at server
        Login logincaro = new Login(inFromServer, outToServer);

	}
}
