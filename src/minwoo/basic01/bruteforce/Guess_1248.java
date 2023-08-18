package minwoo.basic01.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Guess_1248 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] signs;
    static String[][] signMatrix;
    static int n;
    static List<Integer> output = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        signs = br.readLine().split("");
        signMatrix = new String[n][n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                signMatrix[i][j] = signs[idx++];
            }
        }
        dfs(0);
    }

    public static void dfs(int idx) {
        if (output.size() == n) {
            System.out.println(output.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            System.exit(0);
        }

        for (int i = -10; i <= 10; i++) {
            output.add(i);
            if (checkSign(idx + 1)) {
                dfs(idx+1);
            }
            output.remove(output.size() - 1);
        }
    }
    private static boolean checkSign(int length) {
        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += output.get(j);
                if ("+".equals(signMatrix[i][j])) {
                    if (!(sum > 0)) {
                        return false;
                    }
                }
                if ("-".equals(signMatrix[i][j])) {
                    if (!(sum < 0)) {
                        return false;
                    }
                }
                if ("0".equals(signMatrix[i][j])) {
                    if (sum != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
