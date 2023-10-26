package minwoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Memo {
    public static void main(String[] args) throws IOException {
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.size());
        list.remove(list.size() - 1);
        System.out.println(list.size());
        list.add(4);
        System.out.println(list.size());
    }
}

