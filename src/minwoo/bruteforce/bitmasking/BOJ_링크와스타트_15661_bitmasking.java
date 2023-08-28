package minwoo.bruteforce.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_링크와스타트_15661_bitmasking {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] matrix;
    static StringTokenizer st;
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> aTeam = new ArrayList<>();
        List<Integer> bTeam = new ArrayList<>();
        int min = 999999999;
        for (int i =1; i < (1 << N); i++) {
            aTeam.clear();
            bTeam.clear();
            for (int k = 0; k < N; k++) {
                if ((i & (1 << k)) != 0) {
                    aTeam.add((N-1)-k);
                }else{
                    bTeam.add((N-1)-k);
                }
            }
            int aTeamStats = 0;
            int bTeamStats = 0;
            int aTeamSize = aTeam.size();
            int bTeamSize = bTeam.size();
            for (int p = 0; p < aTeamSize; p++) {
                for (int q = p+1; q < aTeamSize; q++) {
                    aTeamStats += (matrix[aTeam.get(p)][aTeam.get(q)] + matrix[aTeam.get(q)][aTeam.get(p)]);
                }
            }
            for (int p = 0; p < bTeamSize; p++) {
                for (int q = p+1; q < bTeamSize; q++) {
                    bTeamStats += (matrix[bTeam.get(p)][bTeam.get(q)] + matrix[bTeam.get(q)][bTeam.get(p)]);
                }
            }
            min = Math.min(min, Math.abs(aTeamStats - bTeamStats));
        }

        System.out.println(min);
    }
}
