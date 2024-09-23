import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


// 장난감 조립
public class Main {

    static class Toy {

        int subToy, cnt;

        public Toy(int subToy, int cnt) {
            this.subToy = subToy;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] in = new int[N + 1];
        int[] out = new int[N + 1];
        List<Toy>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            adj[X].add(new Toy(Y, K));
            in[Y]++;
            out[X]++;
        }

        // 위상정렬
        int[] cnt = new int[N + 1];
        cnt[N] = 1;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        Queue<Toy> result = new PriorityQueue<>((o1, o2) -> o1.subToy - o2.subToy);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (out[v] == 0) { // 진출차수가 0 = 기본 부품
                result.add(new Toy(v, cnt[v]));
            }

            for (Toy toy : adj[v]) {
                in[toy.subToy]--; // 진입차수 감소
                cnt[toy.subToy] += cnt[v] * toy.cnt; // 필요한 하위부품 수 = 상위부품의 개수 * 하위부품의 개수
                if (in[toy.subToy] == 0) {
                    q.add(toy.subToy);
                }
            }
        }

        StringBuffer output = new StringBuffer();
        while (!result.isEmpty()) {
            Toy toy = result.poll();
            output.append(toy.subToy + " " + toy.cnt + "\n");
        }
        System.out.println(output);
    }

}
