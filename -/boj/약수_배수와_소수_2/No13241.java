package 약수_배수와_소수_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최소공배수
public class No13241 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		long d = gcd(a, b);

		System.out.println((a * b / d)); // 최소공배수
	}

	// 최대공약수
	public static long gcd(long a, long b) {

		while (b != 0) {
			long r = a % b; // 나머지를 구해준다.

			// GCD(a, b) = GCD(b, r)이므로 변환한다.
			a = b;
			b = r;
		}
		return a;
	}

}
