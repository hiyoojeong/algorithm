package 동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 알고리즘 수업 - 피보나치 수 1 
public class No24416 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] fib = new int[N + 1]; // 피보나치 수 재귀호출 의사 코드
		fib[1] = 1;
		fib[2] = 1;

		for (int i = 3; i <= N; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}

		System.out.println(fib[N] + " " + (N - 2));

	}

}
