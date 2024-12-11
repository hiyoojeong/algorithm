import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 전깃줄 - 2
public class Main {

    static class Conn {

        int left, right;

        public Conn(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] conn = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            conn[i][0] = Integer.parseInt(st.nextToken());
            conn[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(conn, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<Integer> list = new ArrayList<>();
        int[] idx = new int[N];
        for (int i = 0; i < N; i++) {
            int key = conn[i][1];
            int left = 0;
            int right = list.size();
            while (left < right) { // 하계
                int mid = (left + right) / 2;
                if (list.get(mid) >= key) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            idx[i] = right;
            if (right >= list.size()) {
                list.add(right, key);
            } else {
                list.set(right, key);
            }
        }

        // 뒤에서부터 LIS를 구성하지 않는 전깃줄 저장
        int maxIdx = list.size() - 1;
        Queue<Integer> answer = new PriorityQueue<>();
        for (int i = N - 1; i >= 0; i--) {
            if (idx[i] == maxIdx) {
                maxIdx--;
                continue;
            }
            answer.add(conn[i][0]);
        }

        sb.append(answer.size() + "\n");
        while (!answer.isEmpty()) {
            sb.append(answer.poll() + "\n");
        }
        System.out.println(sb);
    }
}