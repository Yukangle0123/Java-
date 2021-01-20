package com.code.io;

import java.io.FileReader;
import java.io.FileWriter;

public class ReaderAndWriterDemo {
    public static void main(String[] args) {
        try(FileReader fr =new FileReader("E:\\测试.txt");
            FileWriter fw =new FileWriter("D:\\ceshi.txt")){
            char[] buffer = new char[1024];
            int readCount = -1;
            while((readCount=fr.read(buffer))!=-1){
                fw.write(buffer,0,readCount);
                fw.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
