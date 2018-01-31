package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 多线程接受用户登陆,把客户端连接对象传入作为成员变量
 * @author 徐景洋
 */
public class ThreadServer implements Runnable {

    private Socket client;

    public ThreadServer(Socket client) {
        this.client=client;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in=new ObjectInputStream(client.getInputStream());
            OutputStream out = client.getOutputStream();
            String info;
            String res;
        	int result=0;
			while(true) {	
			   info = (String) in.readObject();
				 res=info;
				for(int i=0;i<info.length()-1;i++) {
					if(info.substring(i,i+1).equals("+")) {
						result=Integer.parseInt((info.substring(0, i)).trim())+Integer.parseInt((info.substring(i+1, info.length())).trim());
					}
					if(info.substring(i,i+1).equals("-")) {
						result=Integer.parseInt((info.substring(0, i)).trim())-Integer.parseInt((info.substring(i+1, info.length())).trim());
					}
					if(info.substring(i,i+1).equals("*")) {
						result=Integer.parseInt((info.substring(0, i)).trim())*Integer.parseInt((info.substring(i+1, info.length())).trim());
					}
					if(info.substring(i,i+1).equals("/")) {
						result=Integer.parseInt((info.substring(0, i)).trim())/Integer.parseInt((info.substring(i+1, info.length())).trim());
					}
				}
//			socket.shutdownInput();
				
				String ss="515OK "+res+" = "+result;
				out.write(ss.getBytes());
			
		     }
  
           
           
            
//            client.shutdownOutput();
//            out.close();
//            in.close();
//            client.close();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        
    }
    
}