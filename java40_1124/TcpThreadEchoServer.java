package java40_1124;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpThreadEchoServer {
    ServerSocket serverSocket=null;
    TcpThreadEchoServer(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true) {
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread(){
                @Override
                public void run() {
                    processConnection(clientSocket);
                }
            };
            t.start();
        }
    }

    private void processConnection(Socket clientSocket) {
        System.out.printf("[%s|%d]客户端上线了！\n",clientSocket.getInetAddress().toString(),clientSocket.getPort());
        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))){
            while(true){
                String request=bufferedReader.readLine();

                String response=process(request);

                bufferedWriter.write(response);
                bufferedWriter.flush();
            }
        }catch (IOException e){
            e.printStackTrace();

        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpThreadEchoServer tcpThreadEchoServer=new TcpThreadEchoServer(9090);
        tcpThreadEchoServer.start();
    }

}



