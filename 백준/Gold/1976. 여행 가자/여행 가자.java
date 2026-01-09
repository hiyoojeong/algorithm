import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1976 여행 가자

/*

5
5
0 0 1 0 0
0 0 0 1 0
1 0 0 0 1
0 1 0 0 1
0 0 1 1 0
1 2 3 4 5

 */

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine()); // 전체 도시 수
        int M = Integer.parseInt(br.readLine()); // 여행할 도시 수

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) {
                    union(i, j);
                }
            }
        }

        String answer = "YES";
        st = new StringTokenizer(br.readLine());
        if (M > 0) {
            int p = Integer.parseInt(st.nextToken()) - 1;
            for (int i = 1; i < M; i++) {
                int v = Integer.parseInt(st.nextToken()) - 1;
                if (find(parent[v]) != find(p)) {
                    answer = "NO";
                    break;
                }
            }
        }

        System.out.println(answer);
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

        if (pa == pb) {
            return;
        }

        if (pa < pb) {
            parent[pa] = pb;
        } else {
            parent[pb] = pa;
        }
    }
}