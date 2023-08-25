package minwoo.bruteforce.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 스타트와링크_14889 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] S;
    static List<Integer> aTeam = new ArrayList<>();
    static List<Integer> totalTeam;
    static int minDiff=9999999;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        S = new int[N+1][N+1];

        // totalTeam : 모든 멤버를 담고있는 리스트
        totalTeam = IntStream
                .rangeClosed(1,N)
                .boxed()
                .collect(Collectors.toList());

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                S[i][j] = Integer.parseInt(input[j-1]);
            }
        }
        dfs(1);
        System.out.println(minDiff);
    }

    public static void dfs(int start) {
        if (aTeam.size() == N/2) {

            /*
            * aTeam == 스타트 팀
            * bTeam == 링크 팀
            */

            List<Integer> bTeam = teamBuild(); // bTeam생성
            int aTeamStats=0; // 스타트팀 총 경험치
            int bTeamStats=0; // 링크팀 총 경험치

            // aTeam경험치, bTeam 경험치 계산
            for (int i = 0; i < N / 2; i++) {
                for (int j = i + 1; j < N / 2; j++) {
                    aTeamStats+=(S[aTeam.get(i)][aTeam.get(j)] + S[aTeam.get(j)][aTeam.get(i)]);
                    bTeamStats+=(S[bTeam.get(i)][bTeam.get(j)] + S[bTeam.get(j)][bTeam.get(i)]);
                }
            }

            // 두 팀간의 경험치 차이의 최솟값을 저장.
            int diff = Math.abs(aTeamStats - bTeamStats);
            minDiff = Math.min(minDiff, diff);
            return;
        }

        for (int i = start; i < N; i++) {
            if (!aTeam.contains(i)) {
                aTeam.add(i);
                dfs(i);
                aTeam.remove(aTeam.size() - 1);
            }
        }
    }

    // bTeam 생성 -> totalTeam 에서 aTeam멤버를 제외하는 로직
    private static List<Integer> teamBuild() {
        List<Integer> bTeam = totalTeam.stream()
                .filter(member -> !aTeam.contains(member))
                .collect(Collectors.toList());
        return bTeam;
    }
}
