import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2304 창고 다각형
public class Main {

    static class Node implements Comparable<Node> {
        int l, h;

        Node(int l, int h) {
            this.l = l;
            this.h = h;
        }

        public int compareTo(Node o) {
            return o.h-this.h;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Queue<Node> pq = new PriorityQueue<>();
        int min = 1_002, max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            pq.add(new Node(L, H));

            min = Math.min(L, min);
            max = Math.max(L + 1, max);
        }

        Node top = pq.poll();
        int area = top.h;
        int l = top.l, r = top.l + 1;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (l == min && r == max) {
                break;
            }
            if (node.l < l) {
                area += (l - node.l) * node.h;
                l = node.l;
            } else if (r < node.l + 1) {
                area += (node.l + 1 - r) * node.h;
                r = node.l + 1;
            }
        }

        System.out.println(area);
    }
}