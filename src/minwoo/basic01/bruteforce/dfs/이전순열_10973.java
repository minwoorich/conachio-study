package minwoo.basic01.bruteforce.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class 이전순열_10973 {
    static int[] array;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        array = new int[N];
        String[] tempArr = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(tempArr[i]);
        }

        nextPermutaion();
    }

    private static void nextPermutaion() {
        int i = array.length - 2;
        while (i >= 0 && array[i] < array[i + 1]) {
            i--;
        }
        if (i < 0) {
            System.out.println(-1);
            return;
        }
        int j = array.length - 1;
        while (array[j] > array[i]) {
            j--;
        }
        swap(i, j);
        reverse(i + 1, array.length - 1);

        String result = Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(result);
    }
    private static void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private static void reverse(int start, int end) {
        while (start < end) {
            swap(start, end);
            start++;
            end--;
        }
    }
}
