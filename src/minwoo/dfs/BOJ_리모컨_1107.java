package minwoo.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_리모컨_1107 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[] brokenNums;
    public static void main(String[] args) throws IOException {
        String N = br.readLine();
        String M = br.readLine();
        brokenNums = new boolean[10];
        int result = 500001;

        // brokenNums[고장난버튼숫자] -> true로 초기화.
        if (!M.equals("0")) {// 고장난 버튼이 있을경우에만 실행
            String[] broken = br.readLine().split(" ");
            for (int i = 0; i < Integer.parseInt(M); i++) {
                brokenNums[Integer.parseInt(broken[i])] = true;
            }
        }

        // 0~100000 까지 반복문 돌림 (채널이 500000 까지 주어지니깐, 그 2배인 1000000 까지 검증해줘야함)
        for (int i = 0; i <= 1000000; i++) {
            if(hasBrokenNums(i)){ // 만약 i에 고장난 숫자가 포함되어있으면 continue
                continue;
            }
            // diff -> | 목표 채널 - 현재 입력한 채널 |
            int diff = Math.abs(Integer.parseInt(N) - i);
            // result -> for문이 돌아가면서 계속 최소값을 갱신하여 저장
            result = Math.min(
                    result,
                    diff + Integer.toString(i).length() // Integer.toString(i).length() -> 숫자버튼 누른 횟수
            );
        }

        // (+,- 버튼) vs (숫자 버튼) 뭐가 더 최솟값인지 비교
        int minResult = Math.min(
                Math.abs(Integer.parseInt(N) - 100),
                result
                );

        // 출력
        System.out.println(minResult);
    }


    public static boolean hasBrokenNums(int i){
        // 전달 받은 숫자(i) 를 자리수 별로 배열에 저장
        String[] numArr = Integer.toString(i).split("");

        // for문 돌려서 각 자릿수가 brokenNums[] 에 있는지 없는지 확인
        for (String num : numArr) {
            if(brokenNums[Integer.parseInt(num)]){
                return true;
            }
        }
        return false;
    }

}
