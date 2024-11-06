
// 예산
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		int total = 0, min = 100_000, max = 1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}

		int m = Integer.parseInt(br.readLine());
		
		int upper = max;
		if(total > m) {
			upper = 0;
			int left = 1;
			int right = max;
			while(left <= right) {
				int mid = (left + right) / 2;
				
				int tmp = 0; // 현재 상한가에서 필요한 예산액
				for(int i=0; i<n; i++) {
					if(arr[i] > mid) {
						tmp += mid;
						continue;
					}
					tmp += arr[i];
				}
				
				if(m < tmp) { // 예산보다 많이 필요해서, 상한가를 줄여야 한다.
					right = mid - 1;
				} else { // 예산이랑 같거나 부족해서, 상한가를 늘려봐도 된다.
					left = mid + 1;
					upper = Math.max(mid, upper);
				}
			}
		}
		 
		System.out.println(upper);
		br.close();
	}
}
