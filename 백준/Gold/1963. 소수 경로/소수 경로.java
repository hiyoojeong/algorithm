import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 소수 경로
public class Main {

    static Set<Integer> notPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        notPrime = new HashSet<>();
        notPrime.add(0);
        notPrime.add(1);
        for (int i = 2; i <= 9999; i++) {
            for (int j = i * i; j <= 9999; j += i) {
                notPrime.add(j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String origin = st.nextToken();
            String target = st.nextToken();
            int result = bfs(origin, target);
            sb.append((result == -1 ? "impossible" : result) + "\n");
        }

        System.out.println(sb);
    }

    private static int bfs(String origin, String target) {
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.add(origin);
        visited.add(origin);
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String num = q.poll();
                if (num.equals(target)) {
                    return cnt;
                }

                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 10; k++) {
                        if (j == 0 && k == 0) {
                            continue;
                        }
                        String newNum = num.substring(0, j) + k + num.substring(j + 1, 4);
                        if (!visited.contains(newNum) && !notPrime.contains(Integer.parseInt(newNum))) { // 방문하지 않고, 소수여야 한다.
                            q.add(newNum);
                            visited.add(newNum);
                        }
                    }
                }
            }
            cnt++;
        }
        return -1;
    }

}