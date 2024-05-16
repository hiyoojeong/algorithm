import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 게으른 백곰
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 양동이 수
		int k = Integer.parseInt(st.nextToken()); // 백곰이 움직일 수 있는 좌우 위치

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int ice = Integer.parseInt(st.nextToken());
			int icePos = Integer.parseInt(st.nextToken());
			map.put(icePos, ice);
		}

		int start = 0, end = 0;
		long sum = 0, maxSum = 0;
		int size = 0, maxSize = 2 * k + 1;
		while (!map.isEmpty()) {
			if(size == maxSize) {
				if(map.containsKey(start)) {
					sum -= map.get(start);
					map.remove(start);
				}
				start++;
				size--;
			}
			
			if(map.containsKey(end)) {
				sum += map.get(end);
			}
			end++;
			size++;
			
			maxSum = Math.max(sum, maxSum);
		}
		
		System.out.println(maxSum);

	}

}
