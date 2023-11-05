package minwoo.삼성sw기출.BOJ_감시_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Point{
    int r;
    int c;
    int type;
    int dir;

    public Point(int r, int c, int type, int dir) {
        this.r = r;
        this.c = c;
        this.type = type;
        this.dir = dir;
    }
}

public class Main {
    static int N,M;
    static int[][] map;
    static List<Point> cctvs = new ArrayList<>();
    static List<Point> walls = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] >= 1 || map[i][j] <= 5) {
                    cctvs.add(new Point(i, j, map[i][j], -1));
                }
                if (map[i][j] == 6) {
                    walls.add(new Point(i, j, map[i][j], -1));
                }
            }
        }
    }
}
