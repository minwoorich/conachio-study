package minwoo.bruteforce.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_로또_6603 {
    static int[] result = new int[6];;
    static StringBuilder output;
    static boolean[] visited;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int k = -1;
    static int[] array;
    public static void main(String[] args) throws IOException {

        while (true) {
            String input = br.readLine();
            if (input.equals("0")) {
                break;
            }
            String[] tempArr = input.split(" ");

            k = Integer.parseInt(tempArr[0]);
            if (k == 0) {
                break;
            }
            array = new int[k];
            visited = new boolean[k];
            output = new StringBuilder();
            for (int i = 0; i < k; i++) {
                array[i] = Integer.parseInt(tempArr[i+1]);
            }
            dfs(0, array[0]);
            System.out.println(output);
        }
    }


    private static void dfs(int depth, int last) {
        if (depth == 6) {
            for (int num : result) {
                output.append(num + " ");
            }
            output.append("\n");
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i] && last <= array[i]) {
                visited[i] = true;
                result[depth] = array[i];
                dfs(depth + 1, array[i]);
                visited[i] = false;
            }
        }
    }
}
