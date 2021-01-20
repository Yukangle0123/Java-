package com.code.reflection;

import java.util.Scanner;

class Cat{
    String name;
    Cat(){
        this.name="小黄";
    }
    Cat(String name){
        this.name=name;
    }
   public void eat(String food){
       System.out.println(this.name+"正在吃"+food);
   }
}

public class Demo1 {
    //程序运行时，获取到某个类的/对象的更多详细信息
    // （类的属性，属性的名字，属性的类型，属性的访问权限，类的方法，方法的参数，有哪些构造方法，构造方法的参数）
    // 经过编译后的得到的.class中保存了，类的相关信息
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //1)通过字符串获取到类,参数是类的全限定类名
        //catClass 就是Cat 类的图纸，里头描述了Cat类的内部构造
//        Class catClass = Class.forName("com.code.reflection.Cat");
//        Cat cat=new Cat("小黑");
//        //2)通过类实例来获取，实例的getClass方法获取到类对象
//        Class catClass2= cat.getClass();
//        //3)通过类来获取
//        Class catClass3 = Cat.class;
//        System.out.println(catClass==catClass3);
        Scanner scanner = new Scanner(System.in);
        String className = scanner.next();
        Class catClass = Class.forName(className);
        Cat cat = (Cat)catClass.newInstance();
        cat.eat("鱼");
    }
}
