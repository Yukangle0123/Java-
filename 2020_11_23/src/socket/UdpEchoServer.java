package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpEchoServer {
    // 对于一个服务器程序来说, 核心流程也是要分成两步.
    // 1. 进行初始化操作 (实例化 Socket 对象)
    // 2. 进入主循环, 接受并处理请求. (主循环就是一个 "死循环")
    //   a) 读取数据并解析
    //   b) 根据请求计算响应
    //   c) 把响应结果写回到客户端.
    DatagramSocket socket=null;
    UdpEchoServer(int port) throws SocketException {
        //当不传入Ip是会默认给定一个特殊的0.0.0.0 ip
        //服务器必须绑定端口号
        socket=new DatagramSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true){
            //读取请求并解析
            DatagramPacket requestPacket=new DatagramPacket(new byte[4096],4096);
            //尝试从网卡中读数据，如果没有数据，就阻塞
            socket.receive(requestPacket);
            //获取请求信息
            String request=new String(requestPacket.getData(),0,requestPacket.getLength()).trim();
            String response=process(request);

            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),
                                                       request.getBytes().length,
                                                 requestPacket.getSocketAddress());
            socket.send(requestPacket);
            System.out.printf("[%s:%d] req: %s; resp: %s\n", requestPacket.getAddress().toString(),
                    requestPacket.getPort(), request, response);
        }
    }
    //为后期不同需求的服务器做准备
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer udpEchoServer=new UdpEchoServer(9090);
        udpEchoServer.start();
    }
}
