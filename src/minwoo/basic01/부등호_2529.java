package minwoo.basic01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class 부등호_2529 {
    static StringBuilder result = new StringBuilder();
    static int k;
    static String[] signArr;
    static List<Integer> output = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        signArr = br.readLine().split(" ");

        dfs();
//        System.out.println(result);
    }

    public static void dfs() {
        if (output.size() == k+1) {
            for (int i = 0; i < output.size()-1; i++) {
                if (!check(i)) {
                    return;
                }
            }
            String result = output.stream().map(Object::toString).collect(Collectors.joining());
            System.out.println(result);
//            for (int num : output) {
//                result.append(num);
//            }
//            result.append("\n");
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!output.contains(i)) {
                output.add(i);
                dfs();
                output.remove(output.size() - 1);
            }
        }
    }

    private static boolean check(int i) {
        if (">".equals(signArr[i])) {
            return output.get(i) > output.get(i + 1);
        }
        if("<".equals(signArr[i])){
            return output.get(i) > output.get(i + 1);
        }
        return false;
    }
}
