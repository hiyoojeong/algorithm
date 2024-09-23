import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 문제집
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 문제의 수
        int M = Integer.parseInt(st.nextToken()); // 정보의 수

        int[] in = new int[N + 1];
        List<Integer>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // A 문제는 B 문제보다 먼저 푼다.
            int B = Integer.parseInt(st.nextToken());
            in[B]++;
            adjList[A].add(B);
        }

        // 위상정렬
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int v = pq.poll();
            answer.append(v + " ");

            for (int u : adjList[v]) {
                in[u]--;
                if (in[u] == 0) {
                    pq.add(u);
                }
            }
        }
        System.out.println(answer);
    }
}