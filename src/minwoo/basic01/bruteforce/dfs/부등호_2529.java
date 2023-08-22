package minwoo.basic01.bruteforce.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class 부등호_2529 {
    static long maxVal=0;
    static long minVal= 9999999999l;
    static StringBuilder result = new StringBuilder();
    static int k;
    static String[] signArr;
    static List<Integer> output = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        signArr = br.readLine().split(" ");

        dfs();

        // "%0"+(k+1)+"d" -> 빈 자리를 0으로 채워넣는다
        System.out.println(String.format("%0"+(k+1)+"d",maxVal));
        System.out.println(String.format("%0"+(k+1)+"d",minVal));
    }

    public static void dfs() {
        if (output.size() == k+1) {
            for (int i = 0; i < output.size()-1; i++) {
                // check()는 ouput에 있는 숫자가 부등호를 만족하는지 검사하는 메서드
                // check()가 false일 경우 그냥 return
                if (!check(i)) {
                    return;
                }
            }

            // for문을 전부 도는동안 모든 부등호를 만족했다면, result에 저장
            // 최소, 최대를 각각 따로 저장
            long result = Long.parseLong(output.stream().map(Object::toString).collect(Collectors.joining()));
            maxVal = Math.max(maxVal, result);
            minVal = Math.min(minVal, result);
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
            return output.get(i) < output.get(i + 1);
        }
        return false;
    }
}
