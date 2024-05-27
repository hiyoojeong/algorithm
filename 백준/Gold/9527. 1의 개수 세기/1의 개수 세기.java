import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1의 개수 세기
public class Main {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		System.out.println(getCount(B) - getCount(A - 1));
	}

	public static long getCount(long x) {
		if (x == 0 || x == 1) {
			return x;
		}

		int n = 0;
		long value = 1; // 2의 n제곱
		while (value * 2 <= x) {
			value *= 2;
			n++;
		}

		long diff = x - value;

		// 2의 (n-1)제곱까지 1의 개수 + 어중간하게 남아있는 수의 가장 앞자리 1의 개수 + 어중간하게 남아있는 수의 뒷자리
		return (n * value / 2) + (diff + 1) + getCount(diff);

	}

}
