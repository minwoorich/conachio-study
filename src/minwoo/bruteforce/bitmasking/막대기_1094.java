package minwoo.bruteforce.bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 막대기_1094 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int x = Integer.parseInt(br.readLine());
        int count = 0;

        while (x > 0) {
            // x와 1을 비트 연산(and)
            // ex) x ==3 일때, x & 1 => 0011 & 0001 => 0001
            if ((x & 1) == 1) {
                count++;
            }
            // if문 통과하면 오른쪽으로 1비트 이동
            // ex) 0011 >>= 1 == 0001
            x >>= 1;
        }
        System.out.println(count);

    }
}
