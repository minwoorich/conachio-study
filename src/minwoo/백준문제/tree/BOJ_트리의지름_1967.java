package minwoo.백준문제.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Node1{
    int neighbor;
    int weight;

    public Node1(int neighbor, int weight) {
        this.neighbor = neighbor;
        this.weight = weight;
    }
}

public class BOJ_트리의지름_1967 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Node1>[] tree;
    static int n;
    static int[] distance;
    static boolean[] visited;
    static int max=0;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        distance = new int[n+1];
        tree = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }


        for (int i = 0; i < n-1; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            tree[x].add(new Node1(y, weight));
            tree[y].add(new Node1(x, weight));
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.println(max);
    }

    public static void dfs(int current, int distance) {
        if (max < distance) {
            max = distance;
        }

        for (Node1 node : tree[current]) {
            if (!visited[node.neighbor]) {
                visited[node.neighbor] = true;
                dfs(node.neighbor, distance + node.weight);
            }
        }
    }
}
