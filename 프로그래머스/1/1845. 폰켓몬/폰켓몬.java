import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Integer[] integers = new Integer[nums.length];
        for (int i =0; i<nums.length;i++) {
            integers[i] = nums[i];
        }
        int answer = 0;
        int N = nums.length;
        HashSet<Integer> setNums = new HashSet<Integer>(Arrays.asList(integers));
        int size = setNums.size();
        if(N/2>size){
            answer = size;
        }else{
            answer = N/2;
        }

        return answer;
    }
}