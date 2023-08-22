package minwoo.basic01.bruteforce.dfs.n_and_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class N과M_6_15655 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[] nArr;
    static int result[];
    static boolean[] visit;
    static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        nArr = new int[N];
        visit = new boolean[10001];
        result = new int[M];
        output = new StringBuilder();

        // nArr 에 값 저장
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(input2[i]);
        }

        // nArr을 먼저 오름차순으로 정렬 시켜야함
        Arrays.sort(nArr);

        dfs(0,nArr[0]);
        System.out.println(output);
    }

    public static void dfs(int depth, int last) {
        if (depth == M) {
            for (int num : result) {
                output.append(num+" ");
            }
            output.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            // 중복 회피 && 오름차순
            if (!(visit[nArr[i]]) && last <= nArr[i]) {
                result[depth] = nArr[i];
                visit[nArr[i]] = true;
                dfs(depth + 1, nArr[i]);
                visit[nArr[i]] = false;
            }
        }
    }
}
