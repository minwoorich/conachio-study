package sanghee;

import java.util.Scanner;

public class Palindrome_10988 {
    public static void main(String[] args) {
        // 1. 입력받기 위해 스캐너를 생성한다.
        Scanner sc = new Scanner(System.in);

        // 2. 알파벳 소문자로만 이루어진 단어를 입력받는다.
        String word = sc.nextLine();

        if(word.length()<1 | word.length()>100) {
            return;
        }

        for(int i=0; i<word.length(); i++) {
            if(!Character.isLowerCase(word.charAt(i))) {
                return;
            }
        }

        // 3. 팰리드롬인지 아닌지 구한다.
        int palindrome = 1;
        for(int i=0; i<Math.abs(word.length()/2); i++) {
            if(word.charAt(i)!=word.charAt(word.length()-1-i)) {
                palindrome=0;
            }
        }

        // 4. 출력
        System.out.println(palindrome);
    }
}
