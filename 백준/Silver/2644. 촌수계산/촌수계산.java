import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 촌수계산
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());

		// 자식들의 부모 정보를 저장한다.
		int M = Integer.parseInt(br.readLine());
		Map<Integer, Integer> parents = new HashMap<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());

			parents.put(child, parent);
		}

		// n1에서 최고 조상까지 올라가며, 촌수를 저장한다.
		Map<Integer, Integer> n1_degrees = new HashMap<>();
		int parent = n1;
		int n1_degree = 0;
		while (true) {
			// 촌수 저장!!
			n1_degrees.put(parent, n1_degree);

			int child = parent;

			// 현재 child가 최고 조상이다.
			if (!parents.containsKey(child)) {
				break;
			}

			parent = parents.get(child);
			n1_degree++;
		}

		// n2에서 최고 조상까지 올라가며, n1과의 공통 조상을 확인한다.
		int answer = -1;
		parent = n2;
		int n2_degree = 0;
		while (true) {
			// 공통 조상 발견!!
			if (n1_degrees.containsKey(parent)) {
				n1_degree = n1_degrees.get(parent);
				answer = n1_degree + n2_degree;
				break;
			}
			
			int child = parent;
			
			// 현재 child가 최고 조상이다.
			if (!parents.containsKey(child)) {
				break;
			}

			parent = parents.get(child);
			n2_degree++;

		}

		System.out.println(answer);

	}

}
