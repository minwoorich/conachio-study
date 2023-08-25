package minwoo.bruteforce.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class 모든순열_10974 {
    static int[] array;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i+1;
        }
        System.out.println(Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        while (nextPermutaion()) {
            System.out.println(Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static boolean nextPermutaion() {
        int i = array.length - 2;
        while (i >= 0 && array[i] > array[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] < array[i]) {
            j--;
        }

        swap(i, j);
        reverse(i + 1, array.length - 1);
        return true;
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
