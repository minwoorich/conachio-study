package minwoo.삼성sw기출.BOJ_뱀_3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int x;
    int y;
    int d;

    public Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {
    static int N, K, L;
    // 반드시 북동남서 순으로 세팅 해놔야함 (시계 방향)
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map;
    static List<Point> snake = new LinkedList<>();
    static Queue<Map<Integer, String>> directionQueue = new ArrayDeque<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int time = 1;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        // 1. 사과 정보
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[1]) - 1;
            int y = Integer.parseInt(input[0]) - 1;
            map[y][x] = 1;
        }
        
        // 2. 방향 정보
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            String[] input = br.readLine().split(" ");
            int t = Integer.parseInt(input[0]);
            String d = input[1];
            directionQueue.add(new HashMap<Integer, String>(){{put(t,d);}});
        }

        // 3. 뱀 초기화
        snake.add(new Point(0, 0, 1));
        int head = snake.size()-1; // 머리 위치
        int tail = 0; // 꼬리 위치

        // 4. 루프 시작
        while (true) {

            // 4-1) 뱀 이동
            // 이동 전
            int x = snake.get(head).x;
            int y = snake.get(head).y;
            int prevd = snake.get(head).d;

            // 이동 후
            int nx = x + dx[prevd];
            int ny = y + dy[prevd];
            int nd = getDirection(prevd); // getDirection() : 방향 전환 체크

            // 새로운 머리 추가
            snake.add(new Point(nx, ny, nd));
            head++;// 머리 추가 됐으니 +1 증가

            // 4-2) 벽 or 자기 몸 부딪히기 체크
            if (isFinish(head)) {
                break;
            }

            // 4-3) 사과를 먹었는지 안먹었는지 체크
            if (!hasEatenApple(head)) {
                snake.remove(tail); // 안 먹었으면 꼬리 지워줌
                head--; // 머리 위치도 갱신
            }

            // 4-4) 시간 상승
            time++;
        }
        // 5. 결과 출력
        System.out.println(time);
    }

    private static int getDirection(int d) {
        if (hasDirectionChanged()) {
            Map<Integer, String> direction = directionQueue.remove();
            // 왼쪽 = -1
            if (isLeft(direction)) {
                return (d + (4-1)) % 4;
            }
            // 오른쪽 = +1
            return (d + (4+1)) % 4;
        }
        return d;
    }

    public static boolean hasDirectionChanged() {
        if (directionQueue.peek() == null) {
            return false;
        }
        return directionQueue.peek().containsKey(time);
    }

    private static boolean isLeft(Map<Integer,String> direction) {
        return "L".equals(direction.get(time));
    }



    public static boolean isFinish(int head) {
        return hasTouchedBody(head) || hasTouchedWall(head);
    }

    private static boolean hasTouchedWall(int head) {
        int x = snake.get(head).x;
        int y = snake.get(head).y;

        return !(x >= 0 && y >= 0 && x < N && y < N);
    }

    private static boolean hasTouchedBody(int head) {
        int x = snake.get(head).x;
        int y = snake.get(head).y;

        // head 전까지 체크해야함
        for (int i = 0; i < head; i++) {
            if (snake.get(i).x == x && snake.get(i).y == y) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasEatenApple(int head) {
        int x = snake.get(head).x;
        int y = snake.get(head).y;

        if (map[y][x] == 1) {
            map[y][x] = 0; // 사과 먹었으니 사과 지워야함
            return true;
        }
        return false;
    }
}
