import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 겹치는 선분
public class Main {

    static class Node {

        int pos, type;

        public Node(int pos, int type) {
            this.pos = pos;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Math.toIntExact(
            o1.pos != o2.pos ? o1.pos - o2.pos : o1.type - o2.type));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Node(s, 1));
            pq.add(new Node(e, -1));
        }

        int cnt = 0, maxCnt = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.type == 1) {
                cnt++;
            } else {
                cnt--;
            }
            if (maxCnt < cnt) {
                maxCnt = cnt;
            }
        }

        System.out.println(maxCnt);
    }
}