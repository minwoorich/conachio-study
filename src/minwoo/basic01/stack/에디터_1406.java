package minwoo.basic01.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

public class 에디터_1406 {
    static Stack<String> leftStack = new Stack<>();
    static Stack<String> rightStack = new Stack<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List.of((br.readLine()).split(""))
                .forEach(str -> leftStack.push(str));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            executeCommand(br.readLine());
        }
        StringBuilder result = new StringBuilder("");
        while (!isLeftStackEmpty()) {
            result.append(leftStack.pop());
        }
        result = result.reverse();
        while (!isRightStackEmpty()) {
            result.append(rightStack.pop());
        }
        System.out.println(result);
    }

    private static void executeCommand(String command) {
        if(command.equals("L")){
            if(!isLeftStackEmpty()){
                rightStack.push(leftStack.pop());
            }
        } else if (command.equals("D")) {
            if(!isRightStackEmpty()){
                leftStack.push(rightStack.pop());
            }
        } else if (command.equals("B")) {
            if(!isLeftStackEmpty()){
                leftStack.pop();
            }
        } else {
            leftStack.push(command.substring(2));
        }
    }

    private static boolean isLeftStackEmpty() {
        if(leftStack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    private static boolean isRightStackEmpty() {
        if(rightStack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
