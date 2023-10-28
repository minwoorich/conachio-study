package minwoo.삼성sw기출.BOJ_주사위굴리기_14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //                      동 서 북  남
    static int[] dr = {0,   0, 0,-1, 1};
    static int[] dc = {0,   1,-1, 0, 0};
    static int[] dice = new int[6];
    static int N,M,x,y,K;
    static int[][] map;
    static Queue<Integer> commandQ = new ArrayDeque<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        // 각종 변수 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 지도 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            commandQ.add(Integer.parseInt(st.nextToken()));
        }

        // 주사위 이동 (명령큐가 텅 빌 때까지)
        while (!commandQ.isEmpty()) {
            int command = commandQ.poll();
            int nx = x + dr[command];
            int ny = y + dc[command];

            // 벽을 넘어가려고 할 경우
            if (!(nx >= 0 && ny >= 0 && nx < N && ny < M)) {
                continue;
            }
            x = nx;
            y = ny;

            move(command); // 주사위 한 칸 이동
            setValue(); // 맵 -> 주사위바닥 값 저장 (or 그 반대)
            System.out.println(dice[0]);
        }
    }

    public static void move(int command) {
        if (isEast(command)) {
            moveEast();
            return;
        }
        if (isWest(command)) {
            moveWest();
            return;
        }
        if (isNorth(command)) {
            moveNorth();
            return;
        }
        if (isSouth(command)) {
            moveSouth();
        }
    }

    /*
    *    [1]
    * [3][0][2]
    *    [4]
    *    [5]
    * 주사위의 인덱스는 고정 되고 값이 변경 됨
    *
    * 동/서 이동 시 => dice[0], dice[2], dice[5], dice[3] 값들만 한 칸씩 이동
    * 북/남 이동 시 => dice[1], dice[0], dice[4], dice[5] 값들만 한 칸씩 이동
    * */
    public static void moveEast() {
        // 이동방향  ---->
        int[] indexes = {0, 2, 5, 3};
        int temp = dice[3];
        for (int i = 3; i > 0; i--) {
            dice[indexes[i]] = dice[indexes[i - 1]];
        }
        dice[0] = temp;
    }

    public static void moveWest() {
        // 이동방향  <----
        int[] indexes = {0, 2, 5, 3};
        int temp = dice[0];
        for (int i = 0; i < 3; i++) {
            dice[indexes[i]] = dice[indexes[i + 1]];
        }
        dice[3] = temp;
    }

    public static void moveNorth() {
        // 이동방향  <----
        int[] indexes = {1, 0, 4, 5};
        int temp = dice[1];
        for (int i = 0; i < 3; i++) {
            dice[indexes[i]] = dice[indexes[i + 1]];
        }
        dice[5] = temp;
    }

    public static void moveSouth() {
        // 이동방향  ---->
        int[] indexes = {1, 0, 4, 5};
        int temp = dice[5];
        for (int i = 3; i > 0; i--) {
            dice[indexes[i]] = dice[indexes[i - 1]];
        }
        dice[1] = temp;
    }

    public static void setValue() {
        if (map[x][y] == 0) {
            map[x][y] = dice[5];
            return;
        }
        dice[5] = map[x][y];
        map[x][y] = 0;
    }

    public static boolean isEast(int command) {
        return command == 1;
    }
    public static boolean isWest(int command) {
        return command == 2;
    }
    public static boolean isNorth(int command) {
        return command == 3;
    }
    public static boolean isSouth(int command) {
        return command == 4;
    }
}
