package com.code.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        byte[] buffer = new byte[1024];//将内容读入一个byte中
        int count = 0;//每次读到的字节数
        try(FileInputStream fileInputStream = new FileInputStream("E:\\壁纸\\preview.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\fuzhi.jpg")) {
            //当read()返回-1时说明没有内容可读了
            while((count = fileInputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer,0,count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}