import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

// IF문 좀 대신 써줘
public class Main {

	static class Title {
		String name;
		int max;

		public Title(String name, int max) {
			this.name = name;
			this.max = max;
		}

	}

	static Map<Integer, String> caches;
	static List<Title> titles;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		titles = new ArrayList<>();
		caches = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int max = Integer.parseInt(st.nextToken());
			titles.add(new Title(name, max));
		}

		StringBuffer answer = new StringBuffer();
		for (int i = 0; i < M; i++) {
			int max = Integer.parseInt(br.readLine());
			if (caches.containsKey(max)) {
				String name = caches.get(max);
				answer.append(name).append("\n");
			} else {
				String name = lowerbound(max);
				answer.append(name).append("\n");
				caches.put(max, name);
			}
		}

		System.out.println(answer);
	}

	public static String lowerbound(int max) {
		int left = 0;
		int right = titles.size() - 1;

		// lower bound(하한)을 찾아야 한다.
		while (left <= right) {
			int mid = (left + right) / 2;

			if (titles.get(mid).max >= max) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return titles.get(left).name;
	}

}
