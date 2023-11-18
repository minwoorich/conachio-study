package minwoo.삼성sw기출.BOJ_사다리조작_15684;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Point{
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    @Override
    public boolean equals(Object obj) {
        return (((Point)obj).r == r && ((Point)obj).c == c);
    }

    @Override
    public String toString() {
        return "(r= "+r+", c= "+c+")";
    }
}
public class Main {
    static int[][] map;
    static int N,M,H;
    static int a,b;
    static int result = -1;
    static int[] dr = {0, 0, 1};
    static int[] dc = {-1, 1, 0};
    static List<Point[]> lines = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> output = new ArrayList<>();
    static boolean[] visited;
    static String[] input;
    public static void main(String[] args) throws IOException {
        input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);
        map = new int[H][N];
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]) - 1;
            b = Integer.parseInt(input[1]) - 1;
            map[a][b] = -1;
            map[a][b+1] = 1;
        }

        System.out.println(start());
    }

    public static int start() {
        addLines();
        visited = new boolean[lines.size()];
        for (int i = 0; i <= 3; i++) {
            chooseN(0, i);
            // result = -1 로 초기화 되어있음
            if (result == i) {
                return result;
            }
        }
        return result;
    }

    public static void addLines() {
        for (int i = 0; i < H; i++) {
            // N-1 까지해야함
            for (int j = 0; j < N-1; j++) {
                if (map[i][j] == 0) {
                    add(i,j);
                }
            }
        }
    }

    private static void add(int r, int c) {
        Point[] arr = new Point[2];
        for (int i = 0; i < 2; i++) {
            arr[i] = new Point(r, c + i);
        }

        if (isPlaceable(arr)) {
            lines.add(arr);
        }
    }

    private static boolean isPlaceable(Point[] arr) {
        Point left = arr[0];
        Point right = arr[1];
        return map[left.r][left.c] == 0 && map[right.r][right.c] == 0;
    }
    public static boolean chooseN(int start, int n) {
        if (output.size() == n) {
            for (int i : output) {
                if (!setLine(lines.get(i))) {
                    reset();
                    return false;
                }
            }

            if (areAllReturningToStartColumn()) {
                reset();
                result = n;
                return true;
            }
            reset();
            return false;
        }
        for (int i=start;i<lines.size();i++) {
            if (!visited[i]) {
                visited[i] = true;
                output.add(i);
                if (chooseN(i + 1, n)) {
                    return true;
                }
                output.remove(output.size()-1);
                visited[i] = false;
            }
        }
        return false;
    }


    public static boolean setLine(Point[] arr) {
        Point left = arr[0];
        Point right = arr[1];

        if (map[left.r][left.c] != 0 || map[right.r][right.c] != 0) {
            return false;
        }

        map[left.r][left.c] = -2;
        map[right.r][right.c] = 2;
        return true;
    }



    public static boolean areAllReturningToStartColumn() {
        for (int i = 0; i < N; i++) {
            if (!startFromNthCol(new Point(0, i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean startFromNthCol(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        Point end = null;
        int count = 0;
        while (true) {

            Point crnt = queue.poll();
            // 사다리 전부 내려간 경우 while문 탈출
            if (crnt.r == H) {
                end = crnt;
                break;
            }

            int val = map[crnt.r][crnt.c];

            int nr = 0;
            int nc = 0;

            if (val == 0) {
                // 가로선 만날 때까지 아래로 이동
                nr = crnt.r + 1;
                nc = crnt.c;

                queue.add(new Point(nr, nc));
                continue;
            }

            // 가로선 만난 경우
            count++;
            if (count == 2) { // 가로선을 다 건넜다면 다시 아래로 이동
                nr = crnt.r + 1;
                nc = crnt.c;
                queue.add(new Point(nr, nc));
                count = 0;
                continue;
            }

            // 현재값이 0 보다 크면 -> '좌' 로이동
            // 현재값이 0 보다 작으면 -> '우' 로이동
            nr = crnt.r;
            nc = (val > 0) ? crnt.c - 1 : crnt.c + 1;
            queue.add(new Point(nr, nc));
        }
        return end.c == start.c;
    }


    public static void reset() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(map[i][j]) == 2) {
                    map[i][j] = 0;
                }
            }
        }
        visited = new boolean[lines.size()];
    }
}

