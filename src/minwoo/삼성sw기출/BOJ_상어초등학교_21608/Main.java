package minwoo.삼성sw기출.BOJ_상어초등학교_21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Seat implements Comparable<Seat> {
    int x;
    int y;
    int friends;
    int empty;

    public Seat(int x, int y, int friends, int empty) {
        this.x = x;
        this.y = y;
        this.friends = friends;
        this.empty = empty;
    }

    @Override
    public int compareTo(Seat other) {
        // 인접 좋아하는 학생 수로 비교
        if (friends != other.friends) return -(friends - other.friends);

        // 인접 빈칸 수로 비교
        if (empty != other.empty) return -(empty - other.empty);

        // 행으로 비교
        if (y != other.y) return y - other.y;

        // 열로 비교
        return x - other.x;
    }
}

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;
    static int[][] map;
    static Map<Integer, List<Integer>> friends;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        friends = new HashMap<>();
        for (int i = 0; i < N * N; i++) {
            String[] input = br.readLine().split(" ");
            int student = Integer.parseInt(input[0]);
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j < 5; j++) {
                list.add(Integer.parseInt(input[j]));
            }
            friends.put(student, list);
            // 자리 할당
            assignSeats(student);
        }
        // 만족도 출력
        printScore();

    }

    public static void printScore() {
        int score=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = step1(j, i,map[i][j]);

                if (step1(j, i,map[i][j]) != 0) {
                    score+=Math.pow(10,count-1);
                }
            }
        }
        System.out.println(score);
    }

    public static void assignSeats(int student) {
        PriorityQueue<Seat> queue = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    int friends = step1(j, i,student);
                    int empty = step2(j, i);
                    queue.add(new Seat(j, i, friends, empty));
                }
            }
        }
        Seat seat = queue.remove();
        map[seat.y][seat.x] = student;
    }

    // 인접한 친구 수 구하기
    public static int step1(int x, int y, int student) {
        int count = 0;
        List<Integer> list = friends.get(student);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(nx >= 0 && ny >= 0 && nx < N && ny < N)) {
                continue;
            }
            if (list.contains(map[ny][nx])) {
                count++;
            }
        }
        return count;
    }

    // 인접한 빈칸 수 찾기
    public static int step2(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(nx >= 0 && ny >= 0 && nx < N && ny < N)) {
                continue;
            }
            if (map[ny][nx] == 0) {
                count++;
            }
        }
        return count;
    }
}
