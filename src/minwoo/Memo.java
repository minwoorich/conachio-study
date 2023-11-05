package minwoo;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Memo {
    static List<Integer> output = new LinkedList<>();
    static int[] list = {0, 2, 4, 5, 9, 5, 61};
    public static void main(String[] args) throws IOException {
        dfs(0,list[0]);
    }

    public static void dfs(int depth, int last) {
        if (depth == 3) {
            for (Integer integer : output) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }

        for (int i = 0; i < list.length; i++) {
            if (!output.contains(list[i]) &&
                    list[i] >= last) {
                output.add(list[i]);
                dfs(depth + 1, list[i]);
                output.remove(output.size() - 1);
            }
        }
    }

}

