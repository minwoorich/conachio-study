package minwoo.삼성sw기출.BOJ_이차원배열과연산_17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


class MyNum implements Comparable<MyNum>{
    int val; // 값
    int freq; // 개수

    public MyNum(int val, int freq) {
        this.val = val;
        this.freq = freq;
    }

    @Override
    public int compareTo(MyNum o) {
        if (this.freq > o.freq) {
            return 1;
        } else if (this.freq == o.freq) {
            if (this.val > o.val) {
                return 1;
            } else if (this.val == o.val) {
                return 0;
            }
            return -1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "("+val+", "+freq+")";
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int r,c,k;
    static int[][] map = new int[101][101];
    static int maxRow = 3;
    static int maxCol = 3;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");

        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        for (int i = 0; i < 3; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int sec = 0;
        while (sec <= 100) { // 탈출 조건 - 1
            // 탈출 조건 - 2
            if (map[r-1][c-1] == k) {
                System.out.println(sec);
                return;
            }

            if (maxRow >= maxCol) {// 행 개수 >= 열 개수
                operationR(); // R 연산
            } else { // 열 개수 > 행 개수
                operationC(); // C 연산
            }
            sec++;
        }

        // 탈출 조건 1 을 통해 탈출 한 경우 '-1' 출력
        System.out.println(-1);
    }



    // R 연산
    public static void operationR() {
        int col = maxCol; // 현재 열 개수
        for (int i = 0; i < maxRow; i++) {
            // getRow(i) -> i 번째 행
            // mySort() -> 현재 행을 조건에 맞춰 정렬
            List<Integer> newRow = mySort(getRow(i), col);

            // 행렬의 가로(행) 길이는 가장 긴 행 기준
            maxCol = Math.max(maxCol, newRow.size());

            // 새로 정렬된 행으로 갈아끼우기
            int j = 0;
            while (j < newRow.size()) {
                map[i][j] = newRow.get(j++);
            }
        }
    }

    // C 연산
    public static void operationC() {
        int row = maxRow;
        for (int i = 0; i < maxCol; i++) {
            // getCol(i) -> i 번째 열
            // mySort() -> 현재 열을 조건에 맞춰 정렬
            List<Integer> newCol = mySort(getCol(i), row);

            // 행렬의 세로(열) 길이는 가장 긴 열 기준
            maxRow = Math.max(maxRow, newCol.size());


            // 새로 정렬된 열 로 갈아끼우기
            int j = 0;
            while (j < newCol.size()) {
                map[j][i] = newCol.get(j++);
            }
        }
    }

    // 행렬(map)에서 n번째 행을 반환
    public static List<Integer> getRow(int n) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < maxCol; j++) {
            list.add(map[n][j]);
        }
        return list;
    }

    // 행렬(map)에서 n번째 열을 반환
    public static List<Integer> getCol(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < maxRow; i++) {
            list.add(map[i][n]);
        }
        return list;
    }

    // 조건에 맞춰 정렬 하는 메서드
    public static List<Integer> mySort(List<Integer> list, int size) {
        //                                     (값, 개수)
        // convertToMyNumList() : [1,2,1] ->  [(1,2), (2,1)]
        // converToIntList() : [(1,2), (2,1)] -> [2,1,1,2]
        List<Integer> newLine = convertToIntList(convertToMyNumList(list));

        // 남는부분은 0 채워 넣기
        int rep = size - newLine.size();
        for (int i = 0; i < rep; i++) {
            newLine.add(0);
        }
        return newLine;
    }

    //                                     (값, 개수)
    // convertToMyNumList() : [1,2,1] ->  [(1,2), (2,1)]
    public static List<MyNum> convertToMyNumList(List<Integer> intList) {
        List<MyNum> myList = new ArrayList<>();

        // 리스트에서 0 전부 제거
        intList = intList.stream().filter(i->i!=0).collect(Collectors.toList());

        // max : intList 에서 가장 큰 숫자, 하지만 100 보다 클 경우 그냥 100까지만 저장.
        int max = Math.min(100, intList.stream().max(Integer::compareTo).get());

        // 각 숫자별로 몇 개가 있는지 카운트하는 배열
        int[] freq = new int[101];
        for (int num : intList) {
            freq[num]++;
        }

        // freq[] 정보를 토대로 MyNum(값, 개수) 리스트 생성 리스트
        for (int i = 1; i<=max; i++) {
            if (freq[i] != 0) {
                myList.add(new MyNum(i, freq[i]));
            }
        }
        return myList;
    }

    // converToIntList() : [(1,2), (2,1)] -> [2,1,1,2]
    public static List<Integer> convertToIntList(List<MyNum> myList) {
        Queue<MyNum> queue = new PriorityQueue<>(); // 우선순위 큐 사용 (MyNum 클래스에서 compareTo() 정의
        List<Integer> intList = new ArrayList<>();

        // MyNum 리스트에 있는 모든 값들을 우선순위 큐에 저장
        // -> compareTo()에 정의한 기준을 토대로 자동으로 정렬해줌
        queue.addAll(myList);

        while (!queue.isEmpty()) {
            MyNum myNum = queue.poll();
            intList.add(myNum.val);
            intList.add(myNum.freq);
        }
        return intList;
    }
}
