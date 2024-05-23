import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 축구
public class Main {

	static int N = 19;
	static int[] range = { 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18 };
	static int[] C = new int[N];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		init();

		double A = Double.parseDouble(br.readLine()) / 100;
		double B = Double.parseDouble(br.readLine()) / 100;

		double A_total = 0.0;
		double B_total = 0.0;
		for (int i = 0; i < 12; i++) {
			int r = range[i];
			A_total += (Math.pow(A, r) * Math.pow(1 - A, N - 1 - r) * C[r]);
			B_total += (Math.pow(B, r) * Math.pow(1 - B, N - 1 - r) * C[r]);
		}

		System.out.println(1 - (A_total * B_total));

	}

	public static void init() {
		int n = N;
		C[0] = 1;
		C[1] = 1;
		for (int i = 2; i < n; ++i) {
			C[0] = 1;
			C[i] = 1;
			for (int j = i - 1; j > 0; --j)
				C[j] += C[j - 1];
		}

	}

}
