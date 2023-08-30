package minwoo.백준문제.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ_Guess_1248 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] signs;
    static String[][] signMatrix;
    static int n;
    static List<Integer> output = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        signs = br.readLine().split(""); // 부호가 저장된 1차원 배열
        signMatrix = new String[n][n]; // 부호를 2차원 배열에 따로 저장


        // signs -> signMatrix 로 옮겨담는 중
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                signMatrix[i][j] = signs[idx++];
            }
        }

        // dfs 실행
        dfs(0);
    }

    public static void dfs(int depth) {
        if (depth == n) {
            // 리스트 -> String 으로 변환 후 출력
            System.out.println(output.stream().map(String::valueOf).collect(Collectors.joining(" ")));

            // 출력하고 바로 강제 종료
            System.exit(0);
        }

        for (int i = -10; i <= 10; i++) {
            output.add(i);

            // checkSign(현재뎁스)
            // -> signMatrix를 만들 수 있는지 없는지를 검사
            // -> 현재 depth까지의 output을 검사.
            if (checkSign(depth)) {
                dfs(depth+1);
            }
            output.remove(output.size() - 1);
        }
    }


    private static boolean checkSign(int length) {
        for (int i = 0; i <= length; i++) {
            // sum == 현재 행의 총 합
            int sum = 0; // 행이 바뀔 때 마다 0으로 초기화
            for (int j = i; j <= length; j++) {
                sum += output.get(j);
                if ("+".equals(signMatrix[i][j])) { // 현재 좌표의 signMatrxi값이 "+" 인 경우
                    if (!(sum > 0)) { // sum > 0 이 아니면 false출력
                        return false;
                    }
                }
                if ("-".equals(signMatrix[i][j])) {// 현재 좌표의 signMatrxi값이 "-" 인 경우
                    if (!(sum < 0)) {// sum < 0 이 아니면 false출력
                        return false;
                    }
                }
                if ("0".equals(signMatrix[i][j])) {// 현재 좌표의 signMatrxi값이 "0" 인 경우
                    if (sum != 0) {// sum == 0 이 아니면 false출력
                        return false;
                    }
                }
            }
        }

        // for문을 정상적으로 통과했으면 true 반환
        return true;
    }
}
