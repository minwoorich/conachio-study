> 문제 확인 하러 가기 : https://school.programmers.co.kr/learn/courses/30/lessons/42748
```java
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0; i<commands.length; i++){
            int num = getAnswer(array, commands[i]);
            answer[i]=num;    
        }
        
        return answer;
    }
    
    public int getAnswer(int[] array, int[] command){
        List<Integer> list = new ArrayList<>();
        for(int i=command[0]-1; i<=command[1]-1; i++){
            list.add(array[i]);
        }
        
        Collections.sort(list);
        
        return list.get(command[2]-1);
    }
}
```
