import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 선발 명단
public class Main {

    static int N = 11, power[][];
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            power = new int[N][N];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    power[i][j] = Integer.parseInt(st.nextToken()); // i번째 선수가 j번 포지션 뛸 떄의 능력
                }
            }

            ans = 0;
            dfs(0, 0);

            answer.append(ans + "\n");
        }

        System.out.println(answer);
    }

    public static void dfs(int pos, int sum) {
        if (pos == 11) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 11; i++) { // 포지션
            if (visited[i] || power[pos][i] == 0) {
                continue;
            }
            visited[i] = true;
            dfs(pos + 1, sum + power[pos][i]);
            visited[i] = false;
        }
    }
}