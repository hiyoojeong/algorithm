package 약수_배수와_소수_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1735 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int a_up = Integer.parseInt(st.nextToken());
		int a_down = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int b_up = Integer.parseInt(st.nextToken());
		int b_down = Integer.parseInt(st.nextToken());

		int d_up = a_up * b_down + b_up * a_down;
		int d_down = a_down * b_down;

		int d_gcd = gcd(d_up, d_down);
		d_up /= d_gcd;
		d_down /= d_gcd;

		System.out.println(d_up + " " + d_down);

	}

	public static int gcd(int a, int b) {

		while (b != 0) {
			int r = a % b;

			a = b;
			b = r;
		}
		return a;
	}

}
