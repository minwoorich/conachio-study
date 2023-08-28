package minwoo.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ_오큰수_17298 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        // 스택에 초기값(시작 인덱스) 넣어줌
        stack.push(0);

        // 반복문을 통해 각 자리에 들어갈 오큰수를 구함
        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty()) {
                // 오큰수 발견시 해당 자리의 값을 오큰수(arr[i]) 로 교체
                if (arr[i] > arr[stack.peek()]) {
                    arr[stack.pop()] = arr[i];
                }else{
                    break;
                }
            }
            // while문 끝나면 다음 인덱스를 stack에 push
            stack.push(i);
        }

        // 오큰수가 없는 인덱스들이 pop되지 못한채 stack에 남아있음
        // -> 이 자리에는 전부 -1로 채워넣어줌
        while (!stack.isEmpty()) {
            arr[stack.pop()] = -1;
        }

        // streamAPI를 이용해 배열 -> 문자열 로 출력.
        System.out.println(Arrays.stream(arr)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(" ")));

    }
}
