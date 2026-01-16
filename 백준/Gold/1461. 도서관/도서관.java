import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1461 도서관
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Integer> n = new PriorityQueue<>();
        Queue<Integer> p = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            int pos = Integer.parseInt(st.nextToken());
            if (pos < 0) n.add(pos);
            else p.add(pos);
        }

        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        while (!n.isEmpty()) {
            for (int i = 0; i < M && !n.isEmpty(); i++) {
                if(i==0) pq.add(n.peek()*-1);
                n.poll();
            }
        }

        while (!p.isEmpty()) {
            for (int i = 0; i < M && !p.isEmpty(); i++) {
                if(i==0) pq.add(p.peek());
                p.poll();
            }
        }

        int ans = pq.poll();
        while (!pq.isEmpty()) {
            ans += pq.poll() * 2;
        }

        System.out.println(ans);
    }
}