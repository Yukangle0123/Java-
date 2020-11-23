package socket;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

// 客户端的主要流程分成四步.
// 1. 从用户这里读取输入的数据.
// 2. 构造请求发送给服务器
// 3. 从服务器读取响应
// 4. 把响应写回给客户端.
public class UdpEchoClient {
    DatagramSocket socket=null;
    private int severPort;
    private String severIp;

    // 需要在启动客户端的时候来指定需要连接哪个服务器
    UdpEchoClient(String severIp,int severPort) throws SocketException {
        this.severIp=severIp;
        this.severPort=severPort;
        //不需要指定端口号  在运行的时候操作系统会自动的分配一个端口号
        //为什么不需要指定端口号？
        //一个端口号同常情况下只能被一个进程绑定
        //如果客户端绑定了的话，那么一个主机就只能运行一个客户端了
        socket=new DatagramSocket();
    }
    public void start() throws IOException {
        while (true) {
            Scanner scanner=new Scanner(System.in);
            System.out.print("->");
            String request=scanner.nextLine();
            if(request.equals("exit")){
                break;
            }
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length,
                                                            InetAddress.getByName(severIp),severPort);
            //向服务器发送请求
            socket.send(requestPacket);

            //从服务器获取响应

            DatagramPacket responsePacket=new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);

            String response=new String(responsePacket.getData(),0,responsePacket.getLength()).trim();

            System.out.println(response);
        }
    }
    public static void main(String[] args) throws IOException {
        UdpEchoClient udpEchoClient=new UdpEchoClient("127.0.0.1",9090);
        udpEchoClient.start();
    }

}
