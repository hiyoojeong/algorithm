import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

// 트리 순회
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] tree = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree[node][0] = left;
            tree[node][1] = right;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        int cnt = 0;
        Set<Integer> visit = new HashSet<>();
        while (!stack.isEmpty()) {
            int node = stack.peek();

            if (tree[node][0] != -1) { // 왼쪽 자식
                stack.push(tree[node][0]);
                tree[node][0] = -1;
                cnt++;
                continue;
            }

            visit.add(node); // 방문 노드 체크(왼쪽 자식 방문 이후에 체크)
            if (visit.size() == N) { // 순회의 끝은 중위 순회할 때 마지막 노드
                break;
            }

            if (tree[node][1] != -1) { // 오른쪽 자식
                stack.push(tree[node][1]);
                tree[node][1] = -1;
                cnt++;
                continue;
            }

            // 방문할 자식 없음
            stack.pop();
            cnt++;
        }

        System.out.println(cnt);
    }
}