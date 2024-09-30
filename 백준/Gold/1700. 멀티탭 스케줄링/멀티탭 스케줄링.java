
// 멀티탭 스케줄링
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 개수
		int K = Integer.parseInt(st.nextToken()); // 전기 용품의 총 사용횟수

		int[] items = new int[K];
		int[] afters = new int[K]; // 동일한 전기용품이 다음에 나오는 위치 (Integer.MAX_VALUE이면 안나옴)
		Map<Integer, Integer> befores = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int item = Integer.parseInt(st.nextToken());
			items[i] = item;
			afters[i] = Integer.MAX_VALUE;
			if (befores.containsKey(item)) { // 이전에 나온적 있는 전기용품이라면 현재 순서에서 재등장했음을 표시
				afters[befores.get(item)] = i;
			}
			befores.put(item, i); // 현재 전기용품이 나온적 있음을 표시
		}
		
		Map<Integer, Integer> multitap = new HashMap<>();
		int remove = 0;
		for (int i = 0; i < K; i++) {
			// 뽑는다 = 멀티탭 자리는 꽉 찼고, 현재 꼽아햐 하는 전기용품은 꽂혀있지 않은 경우
			if (multitap.size() == N && !multitap.containsKey(items[i])) {
				int targetItem = -1;
				int targetAfter = -1;
				for (int item : multitap.keySet()) {
					int after = multitap.get(item);
					if (targetAfter < after) { // 가장 나중에 등장하는 전기용품 뽑기
						targetAfter = after;
						targetItem = item;
					}
				}
				multitap.remove(targetItem);
				remove++;
			}
			
			// 꼽는다
			multitap.put(items[i], afters[i]);
		}
		
		System.out.println(remove);
	}

}
