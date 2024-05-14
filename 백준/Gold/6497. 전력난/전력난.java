import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 전력난
public class Main {

	static class Node implements Comparable<Node> {
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// 거리가 작은 순
			return this.cost - o.cost;
		}
	}

	static int v;
	static int e;
	static List<Node> list;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());

			if (v == 0 && e == 0) {
				break;
			}

			list = new ArrayList<>();
			parents = new int[v];

			int allCost = 0;
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				allCost += cost;

				list.add(new Node(from, to, cost));
			}

			// 거리가 작은 순으로 정렬
			Collections.sort(list, new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o1.cost - o2.cost;
				}
			});

			for (int i = 0; i < v; i++) {
				parents[i] = i;
			}

			int sum = 0;
			int cnt = 0;

			for (Node n : list) {
				// 사이클이 생기지 않는다면, 선택한다.
				if (union(n.from, n.to)) {
					sum += n.cost;
					cnt++;

					if (cnt == e - 1)
						break;
				}
			}
			
			answer.append(allCost - sum).append("\n");
		}
		
		System.out.println(answer);
	}

	private static boolean union(int from, int to) {

		// 각 노드와 연결된 시작노드를 찾는다
		int fromRoot = findSet(from);
		int toRoot = findSet(to);

		// 사이클이 생기는 경우
		if (fromRoot == toRoot) {
			return false;
		}

		// 사이클이 생기지 않는 경우, 연결
		parents[toRoot] = fromRoot;
		return true;
	}

	private static int findSet(int v) {
		if (parents[v] == v) {
			return v;
		} else {
			return parents[v] = findSet(parents[v]);
		}
	}

}
