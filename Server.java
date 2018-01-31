package socket;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket socket=new ServerSocket(9999);//监听端口
        int Clientsnum=0;
		System.out.println("***The server is started, waiting for the client's connection ***");
        //循环监听接收各个客户端连接
        while (true) {
            Socket client = socket.accept();
            
            //开启多线程接受客户端信息
            ThreadServer ts=new ThreadServer(client);
            new Thread(ts).start();
            Clientsnum++;
			System.out.println("Count of clients:"+Clientsnum);
        }
        
    }
}