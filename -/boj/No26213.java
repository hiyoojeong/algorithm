

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class No26213 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		Map<Integer, Long> buildings = new HashMap<>();
		int maxHeight = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			maxHeight = (height > maxHeight) ? height : maxHeight;
			buildings.put(height, buildings.getOrDefault(height, (long) 0) + 1);
		}

		long answer = 0;
		for (int h = maxHeight; h > 0; h--) {
			if (buildings.containsKey(h)) {
				long number = buildings.get(h);
				answer += number;

				buildings.remove(h);
				buildings.put(h - 1, buildings.getOrDefault(h - 1, (long) 0) + number);

				D--;
			}

			if (D == 0) {
				break;
			}
		}

		System.out.println(answer);
	}

}
