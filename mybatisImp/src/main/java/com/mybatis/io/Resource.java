package com.mybatis.io;

import java.io.InputStream;

public class Resource {
    /**
     * 根据传入的参数获取一个字节输入流
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath){
        return Resource.class.getClassLoader().getResourceAsStream(filePath);
    }
}
