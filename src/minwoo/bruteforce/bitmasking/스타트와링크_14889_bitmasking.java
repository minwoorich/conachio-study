package minwoo.bruteforce.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 스타트와링크_14889_bitmasking {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int minDiff=9999999;
    static int N;
    static int[][] S;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];

        // 매트릭스 생성
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 1; i < (1 << N); i++) {
            // i를 2진수로 변환했을때 1의 개수가 N/2 개인 경우 (팀의 인원을 정확히 반으로 나눠야 하니깐)
            // ex) 3 -> 0011 (ok)
            if (bitCount(i) == (N/2)) {
                List<Integer> aTeam = new ArrayList<>();
                List<Integer> bTeam = new ArrayList<>();

                // 1이 몇번째 index에 있는지 찾는 for문
                // ex) i == 3일때, 0011 -> index == 0,1
                for (int index = 0; index < N; index++) {
                    if ((i & (1 << index)) != 0) {
                        aTeam.add((N-1)-index);// 실제 리스트는 인덱스가 왼쪽부터 오름차순이므로 (N-1) - index 가 실제 인덱스.
                    }else{
                        bTeam.add((N-1)-index);// 실제 리스트는 인덱스가 왼쪽부터 오름차순이므로 (N-1) - index 가 실제 인덱스.
                    }
                }
                int aTeamStats=0; // 스타트팀 총 경험치
                int bTeamStats=0; // 링크팀 총 경험치

                // aTeam경험치, bTeam 경험치 계산
                for (int a = 0; a < N / 2; a++) {
                    for (int b = a + 1; b < N / 2; b++) {
                        aTeamStats+=(S[aTeam.get(a)][aTeam.get(b)] + S[aTeam.get(b)][aTeam.get(a)]);
                        bTeamStats+=(S[bTeam.get(a)][bTeam.get(b)] + S[bTeam.get(b)][bTeam.get(a)]);
                    }
                }

                // 두 팀간의 경험치 차이의 최솟값을 저장.
                int diff = Math.abs(aTeamStats - bTeamStats);
                minDiff = Math.min(minDiff, diff);
            }
        }

        System.out.println(minDiff);
    }

    private static int bitCount(int num) {
        int count = 0;

         /* num의 각 비트를 1과 '&' 연산 함.
         ex) num == 3 == 0011
         0011 & 0001 == 1
         0001 & 0001 == 1
         ==>  총 2개의 1이 존재.*/
        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>= 1; // 다음 비트를 위해 num을 오른쪽으로 1비트 쉬프트함.
        }
        return count;
    }
}
