import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Queue<Integer> asc = new PriorityQueue<>(); // 오른차순 정렬
        Queue<Integer> desc = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 정렬
        
        for(String operation : operations) {
            String[] arr = operation.split(" ");
            String operator = arr[0];
            int number = Integer.parseInt(arr[1]);
             
            // 추가 명령어
            if(operator.equals("I")) {
                asc.add(number);
                desc.add(number);
            } 
            // 삭제 명령어
            else if(operator.equals("D")) {
                // 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
                if(asc.isEmpty() && desc.isEmpty()) {
                    continue;
                }
                
                int value = 0;
                // 최댓값
                if(number == 1) {
                    value = desc.peek();
                }
                // 최솟값
                else if(number == -1) {
                    value = asc.peek();
                }
                
                // 삭제해야하는 값을 모든 큐에서 삭제해야 한다.
                desc.remove(value);
                asc.remove(value);
            }   
        }
        
        int max = 0, min = 0;
        if(!asc.isEmpty()) {
            min = asc.poll();
        }
        if(!desc.isEmpty()) {
            max = desc.poll();
        }
        
        return new int[] {max, min};
    }
}