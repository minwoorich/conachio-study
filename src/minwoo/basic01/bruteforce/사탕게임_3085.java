package minwoo.basic01.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 사탕게임_3085 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[][] matrix;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        matrix = new String[N][N];
        int maxCandies = 0;

        // 행 단위로 문자열을 입력받고 split()을 이용해 배열에 저장
        for (int row = 0; row < N; row++) {
            matrix[row] = br.readLine().split("");
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j + 1 < N) {
                    // 오른쪽과 교환 (가로 방향)
                    swap(i, j, i, j+1);
                    maxCandies = Math.max(maxCandies, rowCheck(i));
                    maxCandies = Math.max(maxCandies, colCheck(j));
                    maxCandies = Math.max(maxCandies, colCheck(j+1));

                    // 원상태 복구
                    swap(i, j+1, i, j);
                }
                if (i + 1 < N) {
                    // 아래쪽과 교환 (세로 방향)
                    swap(i, j, i+1, j);
                    maxCandies = Math.max(maxCandies, rowCheck(i));
                    maxCandies = Math.max(maxCandies, rowCheck(i+1));
                    maxCandies = Math.max(maxCandies, colCheck(j));

                    // 원 상태 복구
                    swap(i+1, j, i, j);
                }
            }
        }
        System.out.println(maxCandies);

    }

    // 점 좌표 두개를 전달 받은뒤 교환 해주는 메서드
    private static void swap(int x1, int y1, int x2, int y2) {
        String temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    // 행의 index를 전달 받으면, 해당 행의 연속된 사탕개수를 체크 해주는 메서드
    private static int rowCheck(int row) {
        int N = matrix.length;
        int result = 1; // 결과 저장
        int count = 1; // 카운트 용

        for (int j = 1; j < N; j++) {
            // 이전에 먹은 사탕과 색이 같으면 +1,
            if (matrix[row][j-1].equals(matrix[row][j])) {
                count++;
            } else { // 색이 다르면 1로 초기화
                result = Math.max(result, count); // result에 지금까지 카운트한(연속된) 사탕개수 저장
                count=1;
            }
        }
        // 한번 더 result와 count 의 값을 비교해서 반환해줘야함.
        return Math.max(result, count);
    }

    // 열의 index를 전달 받으면, 해당 열의 연속된 사탕개수를 체크 해주는 메서드
    private static int colCheck(int col) {
        int N = matrix.length;
        int result = 1; // 결과 저장용
        int count = 1; // 카운트 용

        for (int i = 1; i < N; i++) {
            // 이전에 먹은 사탕과 색이 같으면 +1을 해주고,
            if (matrix[i-1][col].equals(matrix[i][col])) {
                count++;
            } else {// 색이 다르면 1로 초기화
                result = Math.max(result, count); // result에 지금까지 카운트한(연속된) 사탕개수 저장
                count=1;
            }
        }
        // 한번 더 result와 count 의 값을 비교해서 반환해줘야함.
        return Math.max(result, count);
    }
}
