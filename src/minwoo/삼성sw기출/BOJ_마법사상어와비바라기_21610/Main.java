package minwoo.삼성sw기출.BOJ_마법사상어와비바라기_21610;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Cloud{
    int x;
    int y;

    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N,M;
    static int[][] A;
    static List<Cloud> clouds = new ArrayList<>();
    static boolean[][] visited;
//                         ←, ↖,  ↑, ↗, →, ↘, ↓, ↙
    static int[] dx = {0,  -1,-1, 0, 1, 1, 1, 0,-1};
    static int[] dy = {0,   0,-1,-1,-1,0,1,1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(input[j]);
            }
        }
        initClouds();
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int d = Integer.parseInt(input[0]);
            int s = Integer.parseInt(input[1]);
            visited = new boolean[N][N];

            bibaragi(d, s);
        }
        printTotalScore();
    }

    private static void initClouds() {
        clouds.add(new Cloud(0, N-1));
        clouds.add(new Cloud(1, N-1));
        clouds.add(new Cloud(0, N-2));
        clouds.add(new Cloud(1, N-2));
    }

    public static void bibaragi(int d, int s) {
        moveAndAddWater(d,s); // 구름 이동 + 비 내리기
        addWaterAndClearClouds(); // 물 충전 + 구름 소멸
        removeWaterAndCreateClouds(); // 물 감소 + 새 구름 생성
    }

    public static void moveAndAddWater(int d, int s) {
        for (Cloud cloud : clouds) {
            cloud.x = (cloud.x + (dx[d] * (s % N)) + N) % N;
            cloud.y = (cloud.y + (dy[d] * (s % N)) + N) % N;
            A[cloud.y][cloud.x]++; // 물 +1
        }
    }
    public static void addWaterAndClearClouds() {
        for (Cloud cloud : clouds) {
            visited[cloud.y][cloud.x] = true;
            for (int i = 0; i < 4; i++) {
                int nx = cloud.x + dx[2 * i + 2];
                int ny = cloud.y + dy[2 * i + 2];

                if (!(nx >= 0 && ny >= 0 && nx < N && ny < N)) {
                    continue;
                }
                if (A[ny][nx] <= 0) {
                    continue;
                }
                A[cloud.y][cloud.x]++;
            }
        }
        clouds.clear();
    }
    public static void removeWaterAndCreateClouds() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && A[i][j] >= 2) {
                    A[i][j] -= 2;
                    clouds.add(new Cloud(j,i));
                }
            }
        }
    }

    public static void printTotalScore() {
        int sum=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum+=A[i][j];
            }
        }
        System.out.println(sum);
    }
}
