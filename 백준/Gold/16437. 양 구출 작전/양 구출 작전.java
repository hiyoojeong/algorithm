import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 양 구출 작전
public class Main {

    static int N;
    static int[] count;
    static List<Integer>[] childs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        count = new int[N + 1];
        childs = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            childs[i] = new ArrayList<>();
        }
        count[1] = 0;
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            char who = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken());
            if (who == 'W') { // 늑대
                cnt *= -1;
            }
            int parent = Integer.parseInt(st.nextToken());
            count[i] = cnt;
            childs[parent].add(i);
        }

        long result = dfs(1);
        System.out.println(result);
    }

    private static long dfs(int cur) {
        long cnt = 0;
        for (int child : childs[cur]) {
            cnt += dfs(child);
        }

        cnt += Main.count[cur];
        if (cnt < 0) {
            cnt = 0;
        }
        return cnt;
    }
}