package minwoo.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_종이조각_14391 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);
        matrix = new int[N][M];
        int result = 0;

        // 매트릭스 채워넣기
        for (int i = 0; i < N; i++) {
            String[] input2 = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(input2[j]);
            }
        }

        // 0 ~ 2^(NxM) 까지 반복
        for (int S = 0; S < (1 << (N * M)); S++) {
            int sum = 0;// 모든 종이조각에 적힌 수를 더한 값을 저장

            // 가로 check! (0 찾기)
            for (int i = 0; i < N; i++) {
                int scrapValue = 0;// 종이 조각 하나에 적힌 숫자 값을 저장
                for (int j = 0; j < M; j++) {
                    int k = i*M + j; // 사각형을 일렬로 쭉 늘어뜨린다음, i & k(k는 계속 증가) 연산을 실시

                    if ((S & (1 << k)) == 0) {// 0이 나올때마다 해당 위치(i,j)의 실제값을 scrapValue에 저장.
                        scrapValue*=10; // 0이 연속적으로 나올경우 10을 곱해줌(자릿수올림)
                        scrapValue += matrix[i][j];
                    }else{// 0이 더 이상 안 나오면,
                        sum+=scrapValue;// scrapValue 값을 sum에 저장
                        scrapValue = 0; // 다음 조각에 적혀있을 숫자값을 위해 0으로 초기화.
                    }
                }
                sum+=scrapValue; // 마지막이 0으로 끝날경우 sum+=scrapValue가 생략되기때문에 한번 더 실시해줌
            }

            // 세로 check! (1 찾기) --> 가로check와 동일한데 이번엔 j에 대한 for문이 먼저나옴(세로 방향으로 탐색)
            for (int j = 0; j < M; j++) {
                int scrapValue = 0;
                for (int i = 0; i < N; i++) {
                    int k = i * M + j;
                    if ((S & (1 << k)) != 0) {
                        scrapValue*=10;
                        scrapValue += matrix[i][j];
                    }else{
                        sum+=scrapValue;
                        scrapValue = 0;
                    }
                }
                sum+=scrapValue;
            }
            result = Math.max(result, sum);
        }
        System.out.println(result);
    }
}
