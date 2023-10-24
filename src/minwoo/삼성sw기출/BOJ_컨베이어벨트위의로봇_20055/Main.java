package minwoo.삼성sw기출.BOJ_컨베이어벨트위의로봇_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N,K, count;
    static int belt[];
    static boolean isOccupied[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        belt = new int[2*N];
        isOccupied = new boolean[2 * N];

        input = br.readLine().split(" ");
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(input[i]);
        }

        do  {
            circle(); // 벨트 한 칸 이동
            move(); // 로봇 한 칸 이동
            count++;
        }while(!isEnd());
        System.out.println(count);

    }
    // 컨베이어 벨트 순환
    public static void circle() {
        // 벨트 이동 -> for문은 i=0이 아닌 뒤에서부터 반복 해야함. (그림 그려보면 이해 됨)
        int end = belt[belt.length-1];
        for (int i = belt.length-2; i > -1; i--) {
            belt[i+1] = belt[i];
        }
        belt[0] = end;

        // 로봇 이동 (내구도는 move()에서 처리 할거니 여기서는 로봇 위치만 변경)
        for (int i = N-2; i > -1; i--) {
            isOccupied[i + 1] = isOccupied[i];
        }
        isOccupied[0] = false;
    }

    // 로봇이 한 칸 이동
    public static void move() {
        // 내리는 위치에 로봇이 있으면 로봇 내리기
        if (isOccupied[N - 1]) {
            isOccupied[N - 1] = false;
        }

        // 로봇 이동 + 내구도 감점
        for (int i = N-2; i > 0; i--) {
            // if(
            // 현재칸에 로봇이 있고 and
            // 다음칸의 내구도가 0보다 크고 and
            // 다음칸에 로봇이 없고)
            if (isOccupied[i] && belt[i+1]>0 && !isOccupied[i+1]) {
                isOccupied[i+1] = true;
                isOccupied[i] = false;
                belt[i+1]--;
            }
        }
        // 맨앞칸에 로봇 올리기
        if (belt[0] > 0 && !isOccupied[0]) {
            isOccupied[0] = true;
            belt[0]--;
        }

    }

    // 내구도 체크
    public static boolean isEnd() {
        int count = 0;
        for (int i = 0; i < belt.length; i++) {
            if (belt[i] == 0) {
                count++;
            }
        }
        if (count >= K) {
            return true;
        }
        return false;
    }
}
