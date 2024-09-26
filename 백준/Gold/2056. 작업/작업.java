import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 작업
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] in = new int[N + 1];
        int[] time = new int[N + 1];
        List<Integer>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            time[i] = t;

            int M = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M; j++) {
                int pre = Integer.parseInt(st.nextToken());
                in[i]++;
                adjList[pre].add(i); // pre를 먼저 수행하고, i를 수행한다.
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] endTime = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.add(i);
                endTime[i] = time[i];
            }
        }

        int totalEndTime = 0;
        while (!q.isEmpty()) {
            int v = q.poll();

            totalEndTime = Math.max(totalEndTime, endTime[v]);

            for (int u : adjList[v]) {
                in[u]--;
                if (endTime[u] < endTime[v] + time[u]) { // 각 작업 완료최대시간 업데이트
                    endTime[u] = endTime[v] + time[u];
                }
                if (in[u] == 0) {
                    q.add(u);
                }
            }
        }

        System.out.println(totalEndTime);

    }
}