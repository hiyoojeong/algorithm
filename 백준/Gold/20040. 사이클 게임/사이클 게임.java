import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        int ans = 0;

        // 초기 최상단 부모는 자기 자신으로 설정
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 사이클 찾기
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (find(a) == find(b)) {
                ans = i;
                break;
            }
            union(a, b);
        }

        System.out.println(ans);
    }

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa > pb) {
            parent[pa] = pb;
        } else {
            parent[pb] = pa;
        }
    }
}