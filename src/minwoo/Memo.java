package minwoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.function.BinaryOperator;


public class Memo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(3);
        queue.add(2);
        queue.add(5);
        queue.add(1);

        for (int i = 0; i < 4; i++) {
            System.out.println(queue.remove());
        }
    }
}

