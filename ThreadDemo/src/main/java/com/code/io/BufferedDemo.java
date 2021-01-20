package com.code.io;

import java.io.*;

public class BufferedDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new FileReader("E:\\测试.txt"));
        BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\ceshi.txt"));
        String read = null;
        while((read=bf.readLine())!=null){
            bw.write(read);
            bw.newLine();//写完一行后，写入一个回车（换行）
            bw.flush();
        }
        bf.close();
        bw.close();
    }
}
