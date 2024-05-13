import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수열
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] temp = new int[N + 1];
		
		// K개의 온도 저장
		int sum = 0;
		for (int i = 0; i < K; i++) {
			temp[i] = Integer.parseInt(st.nextToken());
			sum += temp[i];
		}

		// 앞을 지우고, 뒤를 더하면서 max 찾기
		int max = sum;
		for (int i = K; i < N; i++) {
			temp[i] = Integer.parseInt(st.nextToken());
			sum = sum - temp[i - K] + temp[i];
			max = (sum > max) ? sum : max;
		}

		System.out.println(max);

	}

}