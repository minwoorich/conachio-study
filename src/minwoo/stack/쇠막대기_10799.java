package minwoo.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 쇠막대기_10799 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Stack<Character> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push('(');
            }else{
                stack.pop();
                // 레이저였으면 stack 사이즈만큼 값을 더함
                if (isLaser(i-1,input)) {
                    result+=stack.size();
                // 레이저가 아니면 그냥 +1
                }else{
                    result++;
                }
            }
        }
        System.out.println(result);
    }


    // 레이저를 만났는지 안 맞났는지 확인하는 함수
    public static boolean isLaser(int i,String input){
        if (input.charAt(i) == '(') {
            return true;
        }
        return false;
    }
}
