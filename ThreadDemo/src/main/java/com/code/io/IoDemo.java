package com.code.io;

import java.io.File;

public class IoDemo {
    public static void listAllFiles(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if (files !=null){
                for(File file1 : files){
                    listAllFiles(file1);
                }
            }
        }else{
            System.out.println(file);
        }
    }
    public static void main(String[] args) {
        File file =new File("C:\\Users\\OS\\Desktop\\学习学习学习");
        listAllFiles(file);
    }
}
