import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// LCA
public class Main {

    static class Conn {

        int v, u;

        public Conn(int v, int u) {
            this.v = v;
            this.u = u;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        int[] parents = new int[N + 1];
        int[] depth = new int[N + 1];
        parents[1] = -1; // root
        depth[1] = 1;
        Queue<Conn> conns = new ArrayDeque<>(); // 보류용

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            if (connect(parents, depth, v, u)) { // 트리에 연결된 노드가 없으면 연결을 보류한다.
                continue;
            }
            conns.add(new Conn(v, u));
        }

        while (!conns.isEmpty()) { // 보류한 연결을 다시 연결한다.
            Conn conn = conns.poll();
            if (connect(parents, depth, conn.v, conn.u)) {
                continue;
            }
            conns.add(conn);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());

            // depth가 동일하게 맞춰줌
            if (depth[v] > depth[u]) { // v를 올려야 함
                int cnt = depth[v] - depth[u];
                for (int j = 0; j < cnt; j++) {
                    v = parents[v];
                }
            } else if (depth[v] < depth[u]) { // u를 올려야 함
                int cnt = depth[u] - depth[v];
                for (int j = 0; j < cnt; j++) {
                    u = parents[u];
                }
            }

            // depth가 동일해졌음
            while (v != u) {
                v = parents[v];
                u = parents[u];
            }

            answer.append(v + "\n");
        }

        System.out.println(answer);
    }

    private static boolean connect(int[] parents, int[] depth, int v, int u) {
        if (parents[v] == 0 && parents[u] == 0) { // 두 노드 모두 부모가 없음
            return false;
        }

        if (parents[v] != 0) {
            parents[u] = v;
            depth[u] = depth[v] + 1;
        } else if (parents[u] != 0) {
            parents[v] = u;
            depth[v] = depth[u] + 1;
        }
        return true;
    }
}