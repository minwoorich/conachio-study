package minwoo.삼성sw기출.BOJ_드래곤커브_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Point{
    int x;
    int y;
    int d;
    int g;

    public Point(int x, int y, int d, int g) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.g = g;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map = new int[101][101];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    static int N, x, y, d, g;
    static List<Point> dragons = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            d = Integer.parseInt(input[2]);
            g = Integer.parseInt(input[3]);

            dragons.add(new Point(x, y, d, g));
            map[y][x] = 1;
            map[y+dy[d]][x+dx[d]] = 1;
        }

        start();
    }

    public static void start() {
        for (Point dragon : dragons) {
            dfs(dragon);
        }
    }

    public static void dfs(Point dragon) {

    }
}
