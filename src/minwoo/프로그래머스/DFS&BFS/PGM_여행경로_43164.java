package minwoo.프로그래머스.dfs;

import java.util.*;

class PGM_여행경로_43164 {
    static boolean[] visited;
    static List<String> output = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs(0, "ICN","ICN",tickets);
        Collections.sort(output);

        return output.get(0).split(" ");
    }

    public static void dfs(int depth, String current, String path, String[][] tickets) {
        if (depth == tickets.length) {
            output.add(path);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && current.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(depth+1, tickets[i][1],path + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        PGM_여행경로_43164 sol = new PGM_여행경로_43164();
        String[] list = sol.solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}});
        Arrays.stream(list).forEach(System.out::println);

    }
}
