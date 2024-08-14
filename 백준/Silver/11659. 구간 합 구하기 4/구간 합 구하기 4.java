import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간 합 구하기 
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

		int[] sum = new int[N + 1]; // 누적합 정보
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			sum[i] = sum[i - 1] + num;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sIdx = Integer.parseInt(st.nextToken()); // 시작 인덱스
			int eIdx = Integer.parseInt(st.nextToken()); // 끝 인덱스
			
			int prefixSum = sum[eIdx] - sum[sIdx-1]; // 구간합
			answer.append(prefixSum).append("\n");
		}

		System.out.println(answer);
	}

}
