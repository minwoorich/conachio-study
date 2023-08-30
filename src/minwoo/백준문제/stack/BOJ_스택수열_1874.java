package minwoo.백준문제.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BOJ_스택수열_1874 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder("");
        int start = 0;
        for (int i = 0; i < n; i++) {
            int currentNum = Integer.parseInt(br.readLine());

            if(!isValid(currentNum)){return;}

            if (stack.contains(currentNum)) {
                stack.pop();
                output.append("-\n");
                continue;
            }

            for (int j = start+1; j <= currentNum; j++) {
                stack.push(j);
                output.append("+\n");
            }
            stack.pop();
            start = Math.max(start, currentNum);
            output.append("-\n");
        }
        System.out.println(output);

        List<String> list = new ArrayList<>();
    }

    private static boolean isValid(int currentNum) {
        if (stack.contains(currentNum) && stack.peek() != currentNum) {
            System.out.println("NO");
            return false;
        }
        return true;
    }
}
