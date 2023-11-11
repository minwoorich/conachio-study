package minwoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Memo {
    static List<Integer> output = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        output.add(2);
        output.add(4);
        output.add(6);
        output.add(8);

        output = output.stream().filter(i -> !(i>6)).collect(Collectors.toList());
        for (Integer integer : output) {
            System.out.println(integer);
        }
    }
}

