import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static final int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st;

        // 숫자 저장
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> visited = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visited.put(Integer.parseInt(st.nextToken()), i);
        }

        // 승패 정보 저장
        int[] score = new int[N];
        List<Integer> adj[] = new ArrayList[MAX + 1];
        for (int i = 1; i <= MAX; i++) {
            adj[i] = new ArrayList<>();
        }

        Set<Integer> keySet = visited.keySet();
        Iterator<Integer> it = keySet.iterator();
        while(it.hasNext()) {
            int num = it.next();
            int idx = visited.get(num);
            int val = 1;

            int next = 0;
            while((next = num * ++val) <= MAX) {
                if(visited.containsKey(next)) {
                    adj[next].add(num);
                    score[idx]++; // 승리 수 ( 현재 수의 배수 개수만큼 승리 )
                }
            }
        }

        keySet = visited.keySet();
        it = keySet.iterator();
        while(it.hasNext()) {
            int num = it.next();
            int idx = visited.get(num);
            score[idx] -= adj[num].size(); // 패배 수 ( 현재 수를 배수로 가지는 수의 개수만큼 패배 )
        }

        // 점수 출력
        for (int i = 0; i < N; i++) {
            sb.append(score[i] + " ");
        }

        System.out.println(sb);

    }
}
