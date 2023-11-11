package minwoo.삼성sw기출.BOJ_이차원배열과연산_17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


class MyNum implements Comparable<MyNum>{
    int val;
    int freq;

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
        while (sec <= 100) {
            if (map[r-1][c-1] == k) {
                System.out.println(sec);
                return;
            }
            sec++;
            if (maxRow >= maxCol) {
                operationR();
                continue;
            }
            operationC();
        }
        System.out.println(-1);
    }



    public static void operationR() {
        int col = maxCol;
        for (int i = 0; i < maxRow; i++) {
            List<Integer> newRow = mySort(getRow(i), col);
            maxCol = Math.max(maxCol, newRow.size());

            int j = 0;
            while (j < newRow.size()) {
                map[i][j] = newRow.get(j++);
            }
        }
    }

    public static void operationC() {
        int row = maxRow;
        for (int i = 0; i < maxCol; i++) {
            List<Integer> newCol = mySort(getCol(i), row);
            maxRow = Math.max(maxRow, newCol.size());

            int j = 0;
            while (j < newCol.size()) {
                map[j][i] = newCol.get(j++);
            }
        }
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < maxCol; j++) {
            list.add(map[rowIndex][j]);
        }
        return list;
    }

    public static List<Integer> getCol(int colIndex) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < maxRow; i++) {
            list.add(map[i][colIndex]);
        }
        return list;
    }

    public static List<Integer> mySort(List<Integer> list, int size) {
        List<Integer> newLine = convertToIntList(convertToMyNumList(list));
        int rep = size - newLine.size();
        for (int i = 0; i < rep; i++) {
            newLine.add(0);
        }
        return newLine;
    }

    public static List<MyNum> convertToMyNumList(List<Integer> intList) {
        List<MyNum> myList = new ArrayList<>();
        intList = intList.stream().filter(i->i!=0).collect(Collectors.toList());
        int max = Math.min(100, intList.stream().max(Integer::compareTo).get());
        int[] freq = new int[101];
        for (int num : intList) {
            freq[num]++;
        }
        for (int i = 1; i<=max; i++) {
            if (freq[i] != 0) {
                myList.add(new MyNum(i, freq[i]));
            }
        }
        return myList;
    }

    public static List<Integer> convertToIntList(List<MyNum> myList) {
        Queue<MyNum> queue = new PriorityQueue<>();
        List<Integer> intList = new ArrayList<>();
        queue.addAll(myList);

        while (!queue.isEmpty()) {
            MyNum myNum = queue.poll();
            intList.add(myNum.val);
            intList.add(myNum.freq);
        }
        return intList;
    }
}
