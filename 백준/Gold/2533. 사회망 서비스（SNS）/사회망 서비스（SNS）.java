import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 사회망 서비스(SNS)
public class Main {

    static List<Integer>[] tree;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        dp = new int[2][N + 1]; // dp[0][i] : i가 루트이고 얼리어답터가 아닐때, 최소 얼리어답터 수
        visited = new boolean[N + 1];
        getMin(1); // 모든 노드가 최상위 루트가 될 수 있으므로, 그냥 1로 시작

        int result = Math.min(dp[1][1], dp[0][1]);
        System.out.println(result);
    }

    static void getMin(int now) {
        visited[now] = true;
        dp[1][now] = 1; // 내가 얼리어답터인 경우, 얼리어답터 수를 1로 설정한다.
        for(int next : tree[now]) {
            if(visited[next]) {
                continue;
            }
            getMin(next);
            dp[0][now] += dp[1][next]; // 내가 얼리어답터가 아니면, 다음은 얼리어답터여야 한다.
            dp[1][now] += Math.min(dp[0][next], dp[1][next]); // 내가 얼리어답터라면, 다음은 얼리어답터여도 되고 아니어도 된다.
        }
    }
}