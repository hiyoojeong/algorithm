import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 222-풀링
public class Main {

    static int N, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dfs(0, 0, N);
        System.out.println(result);

    }

    private static int dfs(int r, int c, int size) {
        if (size == 2) {
            Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = r; i < r + size; i++) {
                for (int j = c; j < c + size; j++) {
                    pq.add(map[i][j]);
                }
            }
            pq.poll(); // 첫번째로 큰수
            return pq.poll(); // 두번째로 큰수
        }

        int half = size / 2;
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(dfs(r, c, half));
        pq.add(dfs(r, c + half, half));
        pq.add(dfs(r + half, c, half));
        pq.add(dfs(r + half, c + half, half));
        pq.poll(); // 첫번째로 큰수
        return pq.poll(); // 두번째로 큰수
    }
}