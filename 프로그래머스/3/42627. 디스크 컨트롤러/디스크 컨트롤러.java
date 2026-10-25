import java.util.*;

class Solution {
    
    class Node {
        int i, s, t;
        Node(int i, int s, int t) {
            this.i = i;
            this.s = s;
            this.t = t;
        }
    }
    public int solution(int[][] jobs) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> { // 요청순서 
            return o1.s == o2.s ? o1.i - o2.i : o1.s - o2.s;
        });
        Queue<Node> wait = new PriorityQueue<>((o1, o2) -> { // 대기실
            return o1.t == o2.t ? (o1.s == o2.s ? o1.i - o2.i : o1.s - o2.s) : o1.t-o2.t;
        });
        for(int i=0; i<jobs.length; i++) {
            pq.add(new Node(i, jobs[i][0], jobs[i][1]));
        }
        
        int sum = 0;
        int time = 0;
        while(!pq.isEmpty() || !wait.isEmpty()) {
            while(!pq.isEmpty() && pq.peek().s <= time) {
                wait.add(pq.poll());
            }
            if(wait.isEmpty()) {
                time = pq.peek().s;
                continue;
            }
            Node n = wait.poll();
            time += n.t; // 작업 종료 시간
            sum += time - n.s;
        }
        
        int answer = sum / jobs.length;
        return answer;
    }
}