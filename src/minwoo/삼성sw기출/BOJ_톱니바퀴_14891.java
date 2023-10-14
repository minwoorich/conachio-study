package minwoo.삼성sw기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Gear{
    int gear;
    int direction;

    public Gear(int gear, int direction) {
        this.gear = gear;
        this.direction = direction;
    }
}
// N = 0, S = 1
// 시계 = 1, 반시계 = -1
public class BOJ_톱니바퀴_14891 {
    static int[] dx = {-1, 1};
    static Queue<Gear> queue = new ArrayDeque<>();
    static boolean[] visited = new boolean[4];
    static int[][]gears = new int[4][8];
    static int K;
    static List<Gear> rotatableGearList;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                gears[i][j] = Integer.parseInt(input[j]);
            }
        }
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            visited = new boolean[4];
            rotatableGearList = new ArrayList<>();
            String[] input = br.readLine().split(" ");

            int gear = Integer.parseInt(input[0])-1;
            int direction = Integer.parseInt(input[1]);
            bfs(new Gear(gear, direction));

            for (Gear item : rotatableGearList) {
                rotate(item.gear, item.direction);
            }
        }
        System.out.println(getResult());
    }

    public static void bfs(Gear startGear) {
        rotatableGearList.add(startGear);
        visited[startGear.gear] = true;
        queue.add(startGear);

        while (!(queue.isEmpty())) {
            Gear v = queue.remove();
            int currentGear = v.gear;
            int currentDirection = v.direction;

            for (int i = 0; i < 2; i++) {
                int nextGear = currentGear + dx[i];
                int nextDirection = currentDirection * (-1);

                if (!(nextGear >= 0 && nextGear < 4)) {
                    continue;
                }
                if (visited[nextGear]) {
                    continue;
                }
                if (!isRotatable(nextGear,currentGear)) {
                    continue;
                }
                rotatableGearList.add(new Gear(nextGear, nextDirection));
                visited[nextGear] = true;
                queue.add(new Gear(nextGear, nextDirection));
            }
        }
    }
    public static void rotate(int gear, int direction) {
        if (direction == 1) {
            int end = gears[gear][7];
            for (int i = 6; i >= 0; i--) {
                gears[gear][i + 1] = gears[gear][i];
            }
            gears[gear][0] = end;
        }else {
            int start = gears[gear][0];
            for (int i = 0; i <= 6; i++) {
                gears[gear][i] = gears[gear][i+1];
            }
            gears[gear][7] = start;
        }
    }

    public static boolean isRotatable(int next, int current) {
        if (next > current) {// current(왼) - next(오)
            return gears[current][2] != gears[next][6];
        }
        // next(왼) - current(오)
        return gears[next][2] != gears[current][6];
    }

    public static int getResult() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == 1) {
                sum += Math.pow(2,i);
            }
        }
        return sum;
    }
}
