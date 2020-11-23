package meiriyiti;

import java.util.Scanner;

//问题描述：有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”答案是5瓶，
// 方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。
// 然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。
// 如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int num=0;
        while(scanner.hasNext()){
            num=scanner.nextInt();
            int count=0;//能换多少平水;
            int remainder=num;//空的瓶子个数;
            while(remainder>=3){
                count=remainder/3+count;
                remainder=remainder%3+remainder/3;
            }
            if(remainder==2){
                count++;
            }
            if(count==0){
                return;
            }
            System.out.println(count);
        }
    }
}
