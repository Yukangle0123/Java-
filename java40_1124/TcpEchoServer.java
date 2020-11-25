package java40_1124;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpEchoServer {
    ServerSocket serverSocket=null;
    //初始化服务器
    TcpEchoServer(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器已启动");
        while(true) {
            Socket clientServer = serverSocket.accept();
            processConnection(clientServer);
        }
    }

    private void processConnection(Socket clientServer) throws IOException {
        System.out.printf("[%s|%d]客户端上线",clientServer.getInetAddress().toString(),clientServer.getPort());
        System.out.println();
        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(clientServer.getInputStream()));
            BufferedWriter bufferedwriter=new BufferedWriter(new OutputStreamWriter(clientServer.getOutputStream()))){
            String request=bufferedReader.readLine();

            //读到请求后进行响应操作
            String response=process(request);

            bufferedwriter.write(response);
            bufferedwriter.flush();
            System.out.printf("[%s|%d] req:%s   resp:%s",clientServer.getInetAddress().toString(),clientServer.getPort(),request,response);

        }catch (IOException e){
            System.out.printf("[%s|%d]客户端下线",clientServer.getInetAddress().toString(),clientServer.getPort());
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer tcpEchoServer=new TcpEchoServer(9090);
        tcpEchoServer.start();
    }

}
