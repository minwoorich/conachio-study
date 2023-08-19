package minwoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Memo {
    public static void main(String[] args) {
        int n = 4; // Number of rows

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                list.add(-1); // Add placeholders for empty spaces
            }
            for (int j = i; j < n; j++) {
                int value = i * n + j;
                list.add(value);
            }
        }

        // Remove placeholders (-1) from the list
        list.removeIf(value -> value == -1);

        // Print the list
        for (int value : list) {
            System.out.print(value + " ");
        }
    }
}
