import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 특별상이라도 받고 싶어
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(rec(arr, 0, 0, N));
    }

    public static int rec(int[][] arr, int r, int c, int size) {
        if (size == 1) {
            return arr[r][c];
        }

        int half = size / 2;
        Queue<Integer> pq = new PriorityQueue<>();
        pq.add(rec(arr, r, c, half));
        pq.add(rec(arr, r + half, c, half));
        pq.add(rec(arr, r, c + half, half));
        pq.add(rec(arr, r + half, c + half, half));
        pq.poll();
        return pq.poll();
    }
}