package minwoo.basic01.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 암호만들기_1759 {
    static List<String> vowels = List.of("a", "e", "i", "o", "u");
    static String[] list;
    static List<String> output = new ArrayList<>();
    static int L,C;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input1 = br.readLine().split(" ");
        L = Integer.parseInt(input1[0]);
        C = Integer.parseInt(input1[1]);
        list = br.readLine().split(" ");

        Arrays.sort(list);

        dfs(0);
    }

    private static void dfs(int start) {
        if (output.size() == L
                && hasMoreThan1Vowels()
                && hasMoreThan2Consonants()) {
            String result = output.stream().collect(Collectors.joining(""));
            System.out.println(result);
            return;
        }

        for (int i = start; i < C; i++) {
            if (!output.contains(list[i])) {
                output.add(list[i]);
                dfs(i+1);
                output.remove(output.size() - 1);
            }
        }
    }

    // output이 1개 이상의 모음을 갖고있는지 판별
    private static boolean hasMoreThan1Vowels() {
        int result =
                (int)output.stream()
                        .filter(i -> vowels.contains(i))
                        .count();
        if (result >= 1) {
            return true;
        }
        return false;
    }

    // output이 2개 이상의 자음을 갖고있는지 판별
    private static boolean hasMoreThan2Consonants() {
        int result =
                (int)output.stream()
                        .filter(i -> !vowels.contains(i))
                        .count();
        if (result >= 2) {
            return true;
        }
        return false;
    }
}
