package socket;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Clients {
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket("localhost", 9999);//�����˿�
        Scanner sc=new Scanner(System.in);
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        InputStream in = socket.getInputStream();
       
        //socket.shutdownOutput();
	while(true) {
			
			
			System.out.println("Client,please input message:");
			String message=sc.next();
			
			 out.writeObject(message);
     		//socket.shutdownOutput();
			
			   byte[] b=new byte[1024];
		        in.read(b);
		        String inf=new String(b,0,b.length);
		        System.out.println(inf);
			if(message=="exit") {
				socket.shutdownOutput();
				break;
			}
		}
        //��ȡ����������������Ϣ
  
     
        
        //�ر���
        in.close();
        out.close();
        socket.close();
        
    }
}