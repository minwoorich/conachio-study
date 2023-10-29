package minwoo.삼성sw기출.BOJ_연구소_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int N,M;
    static List<Point> emptyList = new LinkedList<>();
    static List<Point> virusList = new LinkedList<>();
    static List<Integer> locations = new LinkedList<>();
    static boolean[][] visited;
    static Queue<Point> queue = new ArrayDeque<>();
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    static int max = 0;
    static int[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);

                // 빈칸 위치 저장
                if (map[i][j] == 0) {
                    emptyList.add(new Point(i, j));
                }
                //바이러스 위치 저장
                if (map[i][j] == 2) {
                    virusList.add(new Point(i, j));
                }
            }
        }

        searchLocations(0, 0);
        System.out.println(max);
    }

    // 벽을 세울 장소를 찾는 메서드
    public static void searchLocations(int depth, int last) {
        if (depth == 3) {
            // 골라낸 3개의 장소에 벽 설치
            setBarricades(locations);

            // 벽설치 끝내고 바이러스 살포
            spreadTheVirus();

            // 바이러스 살포후 안전지역 계산
            max = Math.max(max, calcSafeZones());

            // 벽 위치, 바이러스 위치, 방문내역 리셋
            reset();

            return;
        }
        for (int i = 0; i < emptyList.size(); i++) {
            if (!locations.contains(i) && i >= last) {
                locations.add(i);
                searchLocations(depth+1, i);
                locations.remove(locations.size() - 1);
            }
        }
    }

    private static void spreadTheVirus() {
        // virus 위치 미리 queue에 저장 및 visited 설정
        for (Point p : virusList) {
            visited[p.r][p.c] = true;
            queue.add(p);
        }

        while (!queue.isEmpty()) {
            Point crnt = queue.poll();

            int crntR = crnt.r;
            int crntC = crnt.c;

            for (int i = 0; i < 4; i++) {
                int nr = crntR + dr[i];
                int nc = crntC + dc[i];

                if (!(nr >= 0 && nc >= 0 && nr < N && nc < M)) {
                    continue;
                }
                if (map[nr][nc] == 1 || map[nr][nc] == 11) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                queue.add(new Point(nr, nc));
                visited[nr][nc] = true;
                map[nr][nc] = 22; // 새로 전파된 바이러스는 2가 아닌 22로 표시
                // 그래야 이따 reset할때 구분 할 수 있음
            }
        }

    }

    public static void setBarricades(List<Integer> locations) {
        for (int num : locations) {
            int r = emptyList.get(num).r;
            int c = emptyList.get(num).c;

            map[r][c] = 11;
        }
    }

    public static void reset() {
        visited = new boolean[N][M];
        removeBarricades();
        removeViruses();
    }

    public static void removeBarricades() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 11 은 새로 설치한 벽 이므로 0으로 초기화
                if (map[i][j] == 11) {
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void removeViruses() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // -1 은 새로 전파된 바이러스 이므로 0으로 초기화
                if (map[i][j] == 22) {
                    map[i][j] = 0;
                }
            }
        }
    }
    public static int calcSafeZones() {
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
}
