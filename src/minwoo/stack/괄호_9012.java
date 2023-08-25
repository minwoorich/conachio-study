package minwoo.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 괄호_9012 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        boolean isVPS = true;
        Stack<String> stack = new Stack<>();
        List<String> vpsList = new ArrayList<>();
        int testCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCase; i++) {
            vpsList.add(br.readLine());
        }

        for(String str : vpsList){
            List<String> list = List.of(str.split(""));
            for(String bracket : list){
                if ("(".equals(bracket)) {
                    stack.push(bracket);
                } else {
                    if(stack.isEmpty()){
                        isVPS = false;
                        break;
                    }
                    stack.pop();
                }
            }
            if(isVPS && stack.isEmpty()){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
            stack.clear();
            isVPS = true;
        }
    }
}
