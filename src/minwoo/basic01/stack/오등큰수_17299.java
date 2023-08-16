package minwoo.basic01.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class 오등큰수_17299 {
    static Stack<Integer> stack = new Stack<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        // N : 수열 사이즈
        int N = Integer.parseInt(br.readLine());
        // input[] : 입력 수열
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // numberCounts[] : 각 자리에 숫자가 얼만큼 등장했는지 저장하는 배열(수열))
        // -> input배열의 각 원소를 인덱스로 삼아서 해당 인덱스에 등장 횟수를 저장
        int[] numberCounts = new int[1000001];
        for (int num : input) {
            numberCounts[num]++;
        }

        // 스택에 초기값(시작 인덱스) 넣어줌
        stack.push(0);
        // 반복문을 통해 각 자리에 들어갈 오등큰수를 구함
        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty()) {
                // 오등큰수 발견시 해당 자리의 값을 오등큰수(input[i]) 로 교체
                // 오큰수와는 다르게 값의 크기가 아닌, 값의 등장횟수로 비교하기 때문에 numberCounts의 원소들을 비교함
                if (numberCounts[input[i]] > numberCounts[input[stack.peek()]]) {
                    input[stack.pop()] = input[i];
                } else {
                    break;
                }
            }
            // while문 끝나면 다음 인덱스를 stack에 push
            stack.push(i);
        }

        // 오등큰수가 없는 인덱스들이 pop되지 못한채 stack에 남아있음
        // -> 이 자리에는 전부 -1로 채워넣어줌
        while (!stack.isEmpty()) {
            input[stack.pop()] = -1;
        }

        // 출력하기전 int[] -> String 으로 변환
        String result = Arrays.stream(input)
                        .mapToObj(Integer::toString)
                                .collect(Collectors.joining(" "));

        // 출력
        System.out.println(result);
    }
}
