package minwoo.삼성sw기출.BOJ_치킨배달_15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


class Point  {
    int x;
    int y;
    int distance;

    public Point(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

public class Main {
    static List<Point> houses = new ArrayList<>();
    static List<Point> chickens = new ArrayList<>();
    static int minCityDist = Integer.MAX_VALUE;
    static List<Integer> output = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] == 1) {
                    houses.add(new Point(j, i, -1));
                    continue;
                }
                if (map[i][j] == 2) {
                    chickens.add(new Point(j, i, 0));
                }
            }
        }
        dfs(0,0);
        System.out.println(minCityDist);
    }

    public static void dfs(int depth, int last) {
        if (depth == M) {
            setDistance(output); // 각 하우스에 치킨거리 설정
            int cityDist = getTotalDistance(); // 도시 치킨거리 계산
            minCityDist = Math.min(minCityDist, cityDist); // 최소 도시 치킨거리 계산
            return;
        }
        for (int i=0;i<chickens.size();i++) {
            if (!output.contains(i) && i >= last) {
                output.add(i);
                dfs(depth + 1, i);
                output.remove(output.size() - 1);
            }
        }
    }

    // 각 하우스에 치킨거리 설정
    public static void setDistance(List<Integer> selected) {
        for (Point house : houses) {
            int minDist = Integer.MAX_VALUE;
            for (int i : selected) {
                int distance = calcDistance(house, chickens.get(i));
                minDist = Math.min(distance, minDist);
            }
            house.distance = minDist;
        }
    }

    // 두 포인트 간의 거리 구하기
    public static int calcDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    // 도시 치킨거리 계산
    public static int getTotalDistance() {
        int sum = 0;
        for (Point house : houses) {
            sum+=house.distance;
        }
        return sum;
    }
}
