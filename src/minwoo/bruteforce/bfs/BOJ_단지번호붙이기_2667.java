package minwoo.bruteforce.bfs;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;


public class BOJ_단지번호붙이기_2667 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<Point> queue = new ArrayDeque<>();
    static int[][] map;
    static int N;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split("");

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        List<Integer> output = new ArrayList<>();

        // (0,0) 부터 전부 조회 하면서 값이 '1' 일 경우에만 bfs() 탐색!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    output.add(bfs(i, j));
                }
            }
        }
        // 총 단지 개수 출력
        System.out.println(output.size());

        // 각 단지의 집 개수(오름차순) 출력
        output.stream().sorted().forEach(System.out :: println);
    }

    public static int bfs(int startX, int startY) {
        // 맨 처음 (0,0) 을 전달 받은 다음 queue에 저장 (Point객체 사용)
        queue.add(new Point(startX, startY));

        // map에는 0으로 바꿈
        map[startX][startY] = 0;

        // count : 집 개수 저장
        int count = 1;

        while (!queue.isEmpty()) {

            // queue에서 현재 위치 꺼냄
            Point currentPoint = queue.remove();
            int currentX = currentPoint.getLocation().x;
            int currentY = currentPoint.getLocation().y;

            // currnentPoint의 동,서,남,북에 존재하는 이웃을 순서대로 방문
            for (int i = 0; i < 4; i++) {
                int neighborX = currentX + dx[i];
                int neighborY = currentY + dy[i];

                // 만일 이웃의 위치가 map의 바깥으로 벗어난경우,
                if (!(neighborX >= 0 && neighborY >= 0 && neighborX < N && neighborY < N)) {
                    continue;
                }

                // 이미 방문한 경우,
                if (map[neighborX][neighborY] == 0) {
                    continue;
                }

                // 1. queue에 이웃 위치 저장 후
                // 2. 해당 위치 방문 표시
                // 3. 집 개수 count
                queue.add(new Point(neighborX, neighborY));
                map[neighborX][neighborY] = 0;
                count++;
            }
        }

        // 단지를 전부 순회했으면 최종 집개수를 반환
        return count;
    }
}
