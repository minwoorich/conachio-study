package minwoo.삼성sw기출.BOJ_경사로_14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


class Point{
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class Main {
    static int N, L;
    static int[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<Point> queue = new ArrayDeque<>();
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        System.out.println(passableCheck());
    }

    public static int passableCheck() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            isVisited = new boolean[N];
            if (isRowPassable(i)) {
                count++;
            }

            isVisited = new boolean[N];
            if (isColPassable(i)) {
                count++;
            }
        }

        return count;
    }

    public static boolean isRowPassable(int row) {
        queue.add(new Point(row, 0));
        while (!queue.isEmpty()) {
            Point crnt = queue.poll();
            int nc = crnt.c + 1;

            if (hasTouchedWall(nc)) {
                break;
            }

            // 상방 경사 발생
            if ( map[row][crnt.c] < map[row][nc]) {
                if(isDifferenceMoreThanTwo(map[row][nc], map[row][crnt.c])) {
                    return false;
                }

                if (isPrevCellUnplaceable(crnt.c)) {
                    return false;
                }
            }

            // 하방 경사 발생
            if (map[row][crnt.c] > map[row][nc]) {
                if(isDifferenceMoreThanTwo(map[row][nc], map[row][crnt.c])) {
                    return false;
                }
                if (isNextCellUnplaceableForRow(row, crnt.c)) {
                    return false;
                }
            }

            queue.add(new Point(row, nc));
        }

        return true;
    }

    public static boolean isColPassable(int col) {
        queue.add(new Point(0, col));
        while (!queue.isEmpty()) {
            Point crnt = queue.poll();
            int nr = crnt.r + 1;

            if (hasTouchedWall(nr)) {
                break;
            }

            // 상방 경사 발생
            if ( map[crnt.r][col] < map[nr][col]) {
                if(isDifferenceMoreThanTwo(map[nr][col], map[crnt.r][col])) {
                    return false;
                }
                if (isPrevCellUnplaceable(crnt.r)) {
                    return false;
                }
            }

            // 하방 경사 발생
            if (map[crnt.r][col] > map[nr][col]) {
                if(isDifferenceMoreThanTwo(map[nr][col], map[crnt.r][col])) {
                    return false;
                }
                if (isNextCellUnplaceableForCol(crnt.r, col)) {
                    return false;
                }
            }

            queue.add(new Point(nr, col));
        }

        return true;
    }

    public static boolean hasTouchedWall(int n) {
        return n >= N;
    }
    public static boolean isDifferenceMoreThanTwo(int n1, int n2) {
        return Math.abs(n1 - n2) >= 2;
    }

    public static boolean isPrevCellUnplaceable(int n) {
        for (int i = 0; i < L; i++) {
            if (n - i < 0) {
                return true;
            }
            if (isVisited[n - i]) {
                return true;
            }
            isVisited[n - i] = true;
        }
        return false;
    }

    public static boolean isNextCellUnplaceableForRow(int r, int c) {
        int level = map[r][c+1];
        for (int i = 1; i <= L; i++) {
            if (c + i >= N) {
                return true;
            }
            if (map[r][c + i] != level) {
                return true;
            }
            isVisited[c + i] = true;
        }
        return false;
    }
    public static boolean isNextCellUnplaceableForCol(int r, int c) {
        int level = map[r+1][c];
        for (int i = 1; i <= L; i++) {
            if (r + i >= N) {
                return true;
            }
            if (map[r + i][c] != level) {
                return true;
            }
            isVisited[r + i] = true;
        }
        return false;
    }
}

