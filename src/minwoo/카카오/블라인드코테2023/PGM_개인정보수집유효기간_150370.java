package minwoo.카카오.블라인드코테2023;

import java.util.*;

public class PGM_개인정보수집유효기간_150370 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.solution(
                "2022.01.19",
                new String[]{"A 24", "B 12", "C 3"},
                new String[]{"2020.1.19 A"});

        for (int i : result) {
            System.out.print(i+" ");
        }
    }
}

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        // terms에 있는거 map으로 옮겨 담음
        Map<String, Integer> termsMap = new HashMap<>();
        for (String term : terms) {
            termsMap.put(term.split(" ")[0], Integer.parseInt(term.split(" ")[1]));
        }
        List<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] timestamp = privacies[i].split(" ")[0].split("\\.");
            String[] todayDate = today.split("\\.");
            String cond  = privacies[i].split(" ")[1];

            int timeStampInDays = Integer.parseInt(timestamp[0])*12*28
                    + Integer.parseInt(timestamp[1])*28
                    + Integer.parseInt(timestamp[2]);

            int todayDateInDays = Integer.parseInt(todayDate[0])*12*28
                    + Integer.parseInt(todayDate[1])*28
                    + Integer.parseInt(todayDate[2]);

            int validDays = termsMap.get(cond)*28;
            if (todayDateInDays - timeStampInDays >= validDays) {
                answerList.add(i+1);
            }
        }
        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}