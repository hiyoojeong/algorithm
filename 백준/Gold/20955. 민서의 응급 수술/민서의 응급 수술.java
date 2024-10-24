import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 민서의 응급수술
public class Main {

    static int parents[], cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 뉴런 수
        int m = Integer.parseInt(st.nextToken()); // 시냅스 수

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        for (int i = 1; i <= n; i++) {
            if (parents[i] == i) { // 분리집합 수를 구하고, 분리집합끼리 연결하는 수 더함
                cnt++;
            }
        }
        System.out.println(cnt - 1);
    }

    public static int find(int v) {
        if (v == parents[v]) {
            return v;
        }
        return parents[v] = find(parents[v]);
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) { // 중복 연결 끊는 거 더함
            cnt++;
        }

        parents[pa] = pb;
    }
}