package minwoo.bruteforce.dfs.n_and_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class N과M_1_15649 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        result = new ArrayList<>();

        backtracking(0);
    }

    // 재귀 함수
    // N가지 숫자중 M개를 고르는 메서드
    public static void backtracking(int depth) {
        // 만약 List의 길이가 M인 경우 수열 출력
        if (depth == M) {
            System.out.println(
                    // List -> Stream 으로 객체 변환 후,
                    result.stream()
                    // result에 있는 item들<Integer>을 String으로 mapping,
                    .map(String::valueOf)
                    // 각 원소들을 joining해서 하나의 문자열로 합침.(가운데 띄어쓰기)
                    .collect(Collectors.joining(" "))
            );
            return;
        }

        // 1~N 까지 for문을 돌려가며 수열에 숫자를 집어 넣음.
        for (int i = 1; i <= N; i++) {
            if (!result.contains(i)) {// 숫자 i가 result에 없다면(중복 회피),
                result.add(i); // result에 i를 넣고
                backtracking(depth+1); // backtracking() 재귀 호출
                result.remove(result.size()-1); // return문을 만나 backtracking()이 종료된 경우,
                                                      // result의 마지막 원소를 제거
            }
        }
    }
}
