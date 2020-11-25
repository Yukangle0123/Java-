package java40_1124;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TcpEchoClient {
    Socket socket=null;
    TcpEchoClient(String serverIp,int serverPort) throws IOException {
        socket=new Socket(serverIp,serverPort);
    }
    public void start() throws IOException {
        System.out.println("客户端启动");
        Scanner scanner=new Scanner(System.in);
        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
            while(true) {
                System.out.print("->");
                String request=scanner.nextLine();
                if(request.equals("exit")){
                    break;
                }
                bufferedWriter.write(request+"\n");
                bufferedWriter.flush();

               String response= bufferedReader.readLine();
                System.out.println(response);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient tcpEchoClient=new TcpEchoClient("127.0.0.1",9090);
        tcpEchoClient.start();
    }
}
