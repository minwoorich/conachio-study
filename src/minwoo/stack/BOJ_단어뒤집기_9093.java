package minwoo.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ_단어뒤집기_9093 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 리스트 만들고 입력된 문자열 add()
        int testCase = Integer.parseInt(br.readLine());
        List<String> stringList = new ArrayList<>();
        for(int i=0; i<testCase ; i++){
            stringList.add(br.readLine());
        }

        //리스트에 있는 문자열 하나씩 stream을 이용해 파싱.
        //1. 공백(" ") 기준으로 단어들을 split하여 Arrays에 담고
        //2. Arrays에 나뉘어 담긴 단어(word)들을 각각 reverse()로 철자를 뒤집고,
        //3. 뒤집혀진 각 단어들을 다시 하나의 string으로 joining해준다.이때 연결자는 공백(" ")
        for(String str : stringList){
            String result = Arrays.stream(str.split(" "))
                    .map(word -> new StringBuilder(word).reverse().toString())
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        }

    }
}