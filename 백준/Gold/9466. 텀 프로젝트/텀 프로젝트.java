import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 9466 텀 프로젝트

public class Main {

    static int[] visit;
    static int[] arr;

    static final int IS_EMPTY = 0; // 현재 지나온 경로인 경우
    static final int IS_VISIT = 1; // 현재 지나온 경로인 경우
    static final int IS_CYCLE = 2; // 이미 사이클을 이룬 경우
    static final int IS_CYCLE_END = 3; // 사이클 끝 체크
    static final int IS_NOT_CYCLE = 4; // 사이클이 아닌 경우

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            visit = new int[n];
            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            for (int i = 0; i < n; i++) {
                if (visit[i] == IS_EMPTY) {
                    dfs(i);
                }
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (visit[i] == IS_NOT_CYCLE) {
                    ans++;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    public static void dfs(int i) {
        int next = arr[i];
        if (visit[next] == IS_CYCLE || visit[next] == IS_CYCLE_END || visit[next] == IS_NOT_CYCLE) { // 다음 노드가 이미 사이클 판정이 난 경우라면 현재 노드 사이클 형성 불가
            visit[i] = IS_NOT_CYCLE;
            return;
        }
        if (visit[next] == IS_VISIT) { // 다음 노드가 지나온 경로라면 사이클이 만들어진 것
            visit[next] = IS_CYCLE_END;
            visit[i] = i == next ? IS_CYCLE_END : IS_CYCLE;
            return;
        }

        visit[i] = IS_VISIT;
        dfs(next);

        if (visit[i] == IS_CYCLE_END) { // 사이클이 형성된 경우이므로, 현재 노드가 사이클 끝지점이라면 바로 return
            return;
        }

        if (visit[next] == IS_CYCLE) { // 사이클이 형성된 경우이므로, 현재 노드 사이클 표시
            visit[i] = IS_CYCLE;
            return;
        }

        visit[i] = IS_NOT_CYCLE; // 사이클 형성되지 않음
    }
}