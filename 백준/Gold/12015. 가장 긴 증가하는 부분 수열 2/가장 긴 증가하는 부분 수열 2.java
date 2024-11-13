import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열 2
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		List<Integer> result = new ArrayList<>(); // 가장 긴 증가하는 부분 수열의 길이를 유지하는 List

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			int left = 0;
			int right = result.size() - 1;
			int idx = -1;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (result.get(mid) >= num) {
					idx = mid;
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			
			if (idx == -1) { // 하계를 찾을 수 없다면, 지금 넣으려는 수가 가장 큰 수
				result.add(num);
			} else { // 하계를 찾았다면, 지금 넣으려는 수가 더 작으니 가능성 있다고 판단해서 교체
				result.set(idx, num);
			}

		}

		System.out.println(result.size());
	}
}