package minwoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class MyNum implements Comparable<MyNum>{
    int val;
    int freq;

    public MyNum(int val, int freq) {
        this.val = val;
        this.freq = freq;
    }

    @Override
    public int compareTo(MyNum o) {
        if (this.freq > o.freq) {
            return 1;
        } else if (this.freq == o.freq) {
            if (this.val > o.val) {
                return 1;
            } else if (this.val == o.val) {
                return 0;
            }
            return -1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "("+val+", "+freq+")";
    }
}
public class Memo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<List<Integer>> list = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        List<Integer> newList = new ArrayList<>();
        newList.add(1);
        newList.add(0);
        newList.add(2);
        newList.add(2);
        newList.add(4);

        List<MyNum> myNums = convertToMyNumList(newList);
        myNums.forEach(System.out::println);

    }
    public static List<MyNum> convertToMyNumList(List<Integer> intList) {
        List<MyNum> myList = new ArrayList<>();
        intList = intList.stream().filter(i->i!=0).collect(Collectors.toList());
        int max = intList.stream().max(Integer::compareTo).get();
        int[] freq = new int[100];
        for (int num : intList) {
            freq[num]++;
        }
        for (int i = 1; i<=max; i++) {
            if (freq[i] != 0) {
                myList.add(new MyNum(i, freq[i]));
            }
        }
        return myList;
    }
    public static void print() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j)+" ");
            }
            System.out.println();
        }
        System.out.println("===================");
    }

}

