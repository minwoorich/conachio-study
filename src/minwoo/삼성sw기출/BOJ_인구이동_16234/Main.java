package minwoo.삼성sw기출.BOJ_인구이동_16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Country{
    int r;
    int c;

    public Country(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,L,R;
    static int[][] unionsMap;
    static int[][] map;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static List<int[]> unions = new ArrayList<>();
    static Queue<Country> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);
        map = new int[N][N]; // 일반 지도
        unionsMap = new int[N][N]; // 연합 지도 (visited 대용으로도 쓰임)

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int day = 0;
        while (true) {
            // 1. 연합국 판별
            determineUnions();

            // 2. 더이상 인구 이동을 할 수 없을 경우
            if (isEnd()) {
                break;
            }

            // 3. 인구 이동
            migrate();

            // 4. map, unionsMap 리셋
            reset();

            // 5. 날짜 증가
            day++;
        }

        // 6. 출력
        System.out.println(day);
    }

    // 연합국 판별
    public static void determineUnions() {
        int seq = 1; // 같은 연합끼리 사용되는 코드번호
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // 아직 소속된 연합이 없는 국가일 경우 getTerritory() 실행
                if (unionsMap[i][j] == 0) {
                    unionsMap[i][j] = seq;
                    unions.add(getTerritory(i,j,seq++));
                }
            }
        }
    }

    // 연합 생성 및 연합에 대한 정보 반환
    public static int[] getTerritory(int r, int c, int seq) {
        int countries = 1; // 연합의 총 국가 수
        int population = map[r][c]; // 연합의 총 인구 수

        queue.add(new Country(r, c));

        while (!queue.isEmpty()) {
            Country crnt = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = crnt.r + dr[i];
                int nc = crnt.c + dc[i];

                // 벽 경계 조건
                if (!(nr >= 0 && nc >= 0 && nr < N && nc < N)) {
                    continue;
                }

                // 이미 방문( 이미 다른 연합에 소속된 경우)
                if (unionsMap[nr][nc] != 0) {
                    continue;
                }

                // 현재 국가(crnt) 와 다음 국가가 서로 연합인지 아닌지
                if (!isUnion(map[crnt.r][crnt.c], map[nr][nc])) {
                    continue;
                }


                queue.add(new Country(nr, nc));
                unionsMap[nr][nc] = seq;
                countries++;
                population += map[nr][nc];
            }
        }
        return new int[]{countries, population};
    }

    // 인구 이동
    private static void migrate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (unionsMap[i][j] != 0) {
                    int[] arr = unions.get(unionsMap[i][j] - 1); // unionsMap은 연합의 코드번호(seq)를 갖고 있음
                                                                // 이 코드번호로 unions에 저장된 해당 연합의 정보를 추출
                    map[i][j] = arr[1] / arr[0]; // = 인구수 / 국가 수 를 맵에 저장
                }
            }
        }
    }

    // 서로 연합국인지 아닌지 판별
    public static boolean isUnion(int p1, int p2) {
        int result = Math.abs(p1 - p2);
        return result >= L && result <= R;
    }


    // 더이상 인구 이동이 있는지 없는지 판별
    public static boolean isEnd() {
        for (int[] arr : unions) {
            if (arr[0] != 1) { // arr[0] == 1 인것은 연합이 새로 변경된 사항이 없다는 뜻
                return false;
            }
        }
        return true;
    }

    // 맵 리셋
    public static void reset() {
        unions.clear();
        unionsMap = new int[N][N];
    }
}
