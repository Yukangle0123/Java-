package java40_1125;

import java.util.Stack;

public class Parenthesis {
    public boolean chkParenthesis(String A, int n) {
        if(n%2!=0){
            return false;
        }
        Stack <Character>stack=new Stack<>();
        char[] chars = A.toCharArray();
        for(char c:chars){
            if(c=='('){
                stack.push(c);
            }else if(c==')'&&!stack.isEmpty()){
                stack.pop();
            }else{
                return false;
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}
