import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 선 긋기
public class Main {

    static class Node {

        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Node> nodes = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodes.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        long answer = 0;
        Node startNode = nodes.poll();
        int preStart = startNode.start;
        int preEnd = startNode.end;
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node.start <= preEnd) { // 이전 선과 이어지는 경우
                preEnd = Math.max(preEnd, node.end);
            } if(preEnd < node.start) { // 이전 선과 이어지지 않는 경우
                answer += preEnd - preStart;
                preStart = node.start;
                preEnd = node.end;
            }
        }
        answer += preEnd - preStart;

        System.out.println(answer);
    }
}