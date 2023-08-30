package minwoo.백준문제.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_차이를최대로_10819 {
    static int N;
    static int[] array;
    static boolean[] isVisited;
    static int[] result;
    static int maxValue;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        // 입력 및 배열 초기화
        N = Integer.parseInt(br.readLine());
        array = new int[N];
        result = new int[N];
        isVisited = new boolean[N];
        String[] tempArr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(tempArr[i]);
        }
        maxValue=calculate();
        Arrays.sort(array);
        dfs(0);
        System.out.println(maxValue);
    }

    private static int calculate() {
        int temp = 0;
        for (int i = 0; i < N - 1; i++) {
            temp += Math.abs(result[i] - result[i + 1]);
        }
        return temp;
    }
    private static void dfs(int depth) {
        if (depth == N) {
            maxValue = Math.max(calculate(), maxValue);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!isVisited[i]) {
                result[depth] = array[i];
                isVisited[i] = true;
                dfs(depth + 1);
                isVisited[i] = false;
            }
        }
    }
}
