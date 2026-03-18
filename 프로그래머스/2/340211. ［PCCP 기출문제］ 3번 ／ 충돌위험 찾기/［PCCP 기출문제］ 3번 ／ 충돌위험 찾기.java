import java.util.*;

class Solution {

    class Node {
        int i;
        int r;
        int c;
        int cnt;
        int time;
        Node(int i, int r, int c, int cnt, int time) {
            this.i = i;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.time = time;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        int[][][] map = new int[19801][101][101];
        int m = routes[0].length; // 방문해야하는 지점 개수

        Queue<Node> q = new ArrayDeque<>();
        for(int i=0; i<routes.length; i++) {
            int r = points[routes[i][0]-1][0];
            int c = points[routes[i][0]-1][1];
            Node node = new Node(i, r, c, 1, 1);

            if(map[0][r][c] == 1){
                answer++;       // 충돌 체크
            }
            map[0][r][c]++;

            q.add(node);
        }

        while(!q.isEmpty()) {
            Node node = q.poll();
            // 목표지점에 도착하면, 다음 목표지점 설정
            int p = routes[node.i][node.cnt]-1;
            if(points[p][0] == node.r && points[p][1] == node.c) {
                node.cnt++;
                if(node.cnt == m) {
                    continue;
                }
                p = routes[node.i][node.cnt]-1;
            }

            // r 이동
            if(points[p][0] != node.r) {
                if(node.r > points[p][0]) node.r--;
                else node.r++;
            }
            // c 이동
            else if (points[p][1] != node.c) {
                if(node.c > points[p][1]) node.c--;
                else node.c++;
            }

            node.time++;

            if(map[node.time][node.r][node.c] == 1){
                answer++;                 // 충돌 체크
            }
            map[node.time][node.r][node.c]++;

            q.add(node);
        }
        return answer;
    }
}