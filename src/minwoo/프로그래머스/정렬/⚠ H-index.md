> 문제 확인 하러 가기 : https://school.programmers.co.kr/learn/courses/30/lessons/42747#fn1

## 1. 코드

```java
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int h = 0;
        int size = citations.length;
        
        Arrays.sort(citations); // 오름 차순 정렬
        
        for(int i=0; i<size; i++){
            h = size - i;
            
            if(citations[i] >= h){
                answer = h;
                break;
            }
        }
        return answer;
    }
}
```
## 2. 풀이
### 1) 오름차순 정렬
```java
Arrays.sort(citations); // 오름차순 정렬
```

정렬된 배열에는 인용된 횟수가 오름차순으로 저장되어 있게된다.
### 2) 정렬된 논문 배열을 루프 탐색
```java
for(int i=0; i<size; i++){
	h = size - i;     
	...
}
```

이제부터 본격적으로 탐색을 시작하는데 h-index 값은``size - 현재인덱스`` 만큼 할당을 해준다. 

왜냐하면 ``citations[현재인덱스]`` 이상으로 인용된 논문의 개수는 자신을 포함해 현재 논문의 오른쪽에 놓여있을거기 때문이다.   

<img src="https://velog.velcdn.com/images/minwoorich/post/f4509219-8cba-49f5-8119-6dfa5f318824/image.png" style="margin:0;width:400px"/>

### 3) h-index 의 최대값을 찾은 경우

```java
if(citations[i] >= h){
	answer = h;
	break;
}
```

``citations[i] >= h`` 가 된 순간 그 다음을 탐색 할 필요가 없으므로 ``break`` 로 for문을 탈출한다.

<img src="https://velog.velcdn.com/images/minwoorich/post/acb0d7a7-850f-4d3f-af06-17a5ccb6d2c3/image.png" style="margin:0;width:500px"/>
