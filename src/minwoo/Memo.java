package minwoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Memo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static List<Integer> output = new ArrayList<>();
    static int N,M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];

        dfs();
    }
    public static void dfs(){
        if (output.size() == M) {
            for (int num : output) {
                System.out.print(num+" ");
            }
            System.out.println();

            return;
        }

        for (int i = 0; i < N; i++) {
            output.add(i);
            dfs();
            output.remove(output.size() - 1);

        }
    }
}
