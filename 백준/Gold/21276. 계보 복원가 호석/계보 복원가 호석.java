import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 계보 복원가 호석
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<String> sortedName = new ArrayList<>();
        Map<String, List<String>> parents = new HashMap<>(); // 자식의 부모 정보 저장
        Set<String> isChild = new HashSet<>(); // 자식인 경우가 있으면 저장

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();
            parents.put(name, new ArrayList());
            sortedName.add(name);
        }
        Collections.sort(sortedName);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken(); // 자식
            String parent = st.nextToken(); // 부모
            parents.get(child).add(parent);
            isChild.add(child);
        }

        // 조상 출력
        Queue<String> grandParents = new PriorityQueue<>();
        for (String p : parents.keySet()) {
            if (!isChild.contains(p)) {
                grandParents.add(p);
            }
        }
        answer.append(grandParents.size() + "\n");
        while (!grandParents.isEmpty()) {
            answer.append(grandParents.poll() + " ");
        }
        answer.append("\n");

        // 직속자식 출력
        Map<String, Queue<String>> directChilds = new HashMap<>();
        for (int i = 0; i < N; i++) {
            directChilds.put(sortedName.get(i), new PriorityQueue<>());
        }
        for (String c : sortedName) {
            int ccnt = parents.get(c).size(); // 자식의 부모 수
            for (String p : parents.get(c)) {
                int pcnt = parents.get(p).size(); // 부모의 부모 수
                if (pcnt + 1 == ccnt) { // 1 차이 >> c의 직속 부모는 p이다!!
                    directChilds.get(p).add(c);
                }
            }
        }

        for (String p : sortedName) {
            answer.append(p + " " + directChilds.get(p).size() + " ");
            while (!directChilds.get(p).isEmpty()) {
                answer.append(directChilds.get(p).poll() + " ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}