package minwoo.삼성sw기출.BOJ_감시_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class CCTV  {
    int r;
    int c;
    int type;
    int d;

    public CCTV(int r, int c, int type, int d) {
        this.r = r;
        this.c = c;
        this.type = type;
        this.d = d;
    }
}
public class Main {
    static int N,M;
    static int[][] map;
    //                 동 남  서  북 (반드시 90도 간격으로 배치해야함) (시작방향 및 시계/반시계는 상관없음)
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;
    static List<CCTV> cctvs = new ArrayList<>();
    static List<Integer> output = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);

                // cctv 리스트 추가
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j], -1));
                }
            }
        }

        setCamAngle();
        System.out.println(min);
    }

    public static void setCamAngle() {
        if (output.size() == cctvs.size()) {
            // 모든 cctv에 각 카메라 각도를 설정해줌
            for (int i = 0; i < output.size(); i++) {
                cctvs.get(i).d = output.get(i);
            }

            // 모든 카메라의 감시 경로를 체크함
            checkAllCamsCoverage();

            // 사각지대가 최소인 경우를 저장
            min = Math.min(min, calcBlindSpots());

            // map 리셋
            reset();
            return;
        }

        for (int i = 0; i < 4; i++) {
            output.add(i);
            setCamAngle();
            output.remove(output.size() - 1);
        }
    }

    public static void checkAllCamsCoverage() {
        for (CCTV p : cctvs) {

            // 1번 CCTV -> [1방향] for문 없음
            if (p.type == 1) {
                checkCoverage(p.r, p.c, p.d);
                continue;
            }

            // 2번 CCTV -> [2방향, 180도간격] p.d, p.d+2 방향을 감시
            if (p.type == 2) {
                for (int i = 0; i < 3; i += 2) {
                    checkCoverage(p.r, p.c, (p.d + i) %4);
                }
                continue;
            }

            // 3번 CCTV -> [2방향, 90도 간격], p.d, p.d+1 방향 감시
            if (p.type == 3) {
                for (int i = 0; i < 2; i++) {
                    checkCoverage(p.r, p.c, (p.d + i) %4);
                }
                continue;
            }

            // 4번 CCTV -> [3방향, 90도 간격], p.d, p.d+1, p.d+2 방향 감시
            if (p.type == 4) {
                for (int i = 0; i < 3; i++) {
                    checkCoverage(p.r, p.c, (p.d + i) %4);
                }
                continue;
            }

            // 5번 CCTV -> [4방향, 90도 간격], p.d, p.d+1, p.d+2, p.d+3 방향 감시
            for (int i = 0; i < 4; i++) {
                checkCoverage(p.r, p.c, (p.d + i) %4);
            }
        }
    }

    public static void checkCoverage(int r, int c, int d) {
        int nr = r;
        int nc = c;

        while (true) {
            nr += dr[d];
            nc += dc[d];

            // 벽 또는 map바깥 나갈경우 아예 종료
            if (!(nr >= 0 && nc >= 0 && nr < N && nc < M) ||
                    map[nr][nc] == 6) {
                break;
            }

            // 이미 방문한 곳일 경우 스킵
            if (map[nr][nc] == -1) {
                continue;
            }

            // 카메라가 위치해 있는 곳일 경우 스킵
            if (map[nr][nc] >= 1 && map[nr][nc] <= 5) {
                continue;
            }


            map[nr][nc] = -1;
        }
    }

    public static int calcBlindSpots() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void reset() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                }
            }
        }
    }
}
