> 문제 확인 하러 가기 : https://www.acmicpc.net/problem/3613

```java

import java.util.*;
import java.lang.*;
import java.io.*;
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Deque<Character> dq = new ArrayDeque<>();
    static Deque<Character> stack = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException {

        char[] inputs = br.readLine().toCharArray();
        int len = inputs.length;
        for(int i=0; i<len; i++){
            // 괄호가 열리면
            if (inputs[i] == '<') {
                // 스택에 담겼던 문자들을 전부 꺼내서 sb 에 저장
                while (!stack.isEmpty()) {
                    sb.append(stack.pollLast());
                }
                int idx = i;
                // 괄호가 닫힐 때까지 sb에 괄호안에 있는 문자들을 저장한다.
                while (true) {
                    if (inputs[idx] == '>') {
                        sb.append(inputs[idx]);
                        break;
                    }
                    sb.append(inputs[idx]);
                    idx++;
                }
                i = idx; // 괄호안에 있던 문자개수만큼 for문 뛰어넘기
                continue;
            }

            // 공백을 만난경우
            if (inputs[i] == ' ') {
                // 공백 만나기 전까지 스택에 담겼던 문자들을 전부 꺼내서 sb 에 저장
                while (!stack.isEmpty()) {
                    sb.append(stack.pollLast());
                }
                // sb에 공백(현재위치) 저장
                sb.append(inputs[i]);
                continue;
            }

            // 공백 만나기 전까지 스택에 문자들을 저장한다.
            stack.add(inputs[i]);
        }

        // 위 for문에서 미쳐 꺼내지 못 한 경우 여기서 꺼낸다
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        System.out.println(sb);
    }
}

```
