package minwoo.bruteforce.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BOJ_다음순열_10972 {
    static int[] array;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        array = new int[N];
        String[] temp = br.readLine().split(" ");

        // 입력 순열
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(temp[i]);
        }
        nextPermutation();
    }

    public static void nextPermutation() {
        // 가장 긴 내림차순 순열을 찾는 과정
        // (대신 도중에 끊기지 않고 마지막까지 이어져야함)
        int i = array.length - 2;
        while (i >= 0 && array[i] > array[i + 1]) {
            i--;
        }

        // 더이상 다음 순열이 없으면 "-1" 출력 하고 종료
        if (i < 0) {
            System.out.println(-1);
            return;
        }


        // 위에서 찾은 내림차순 순열중 array[i] 보다 큰 수중 가장 최소값을 찾는 로직
        int j = array.length - 1;
        while (array[j] < array[i]) {
            j--;
        }
         /*
         i => 내림차순 순열의 시작 하는 지점의 바로 전 인덱스가 저장되어있음
         (ex, array=[1,2,8,7,6] -> arr[i]==2)

         j => 내림차순 순열중에서 array[i] 보다 큰 숫자 중 최솟값의 인덱스를 저장
         (ex, array = [1,2,8,7,6] -> arr[j] == 6)*/

        // i와 j에 위치한 값을 서로 변경
        swap(i, j);

        // 내림차순 순열을 다시 오름차순으로 변경해줘야함
        reverse(i+1, array.length-1);

        // 결과 출력
        String result = Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(result);
    }

    public static void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void reverse(int start, int end) {
        while (start < end) {
            swap(start, end);
            start++;
            end--;
        }
    }
}
