package minwoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Memo {
    static int N,M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> output = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        dfs(0,0);
    }

    public static void dfs(int depth, int last) {
        if (depth == M) {
            for (int num : output) {
                System.out.print(num+" ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if ( !output.contains(i) && last <= i) {
                output.add(i);
                dfs(depth+1, i);
                output.remove(output.size() - 1);
            }
        }
    }
}

