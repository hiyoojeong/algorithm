package 조합론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 베라의 펜션
public class No15439 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int answer = N * (N - 1);
		System.out.println(answer);
	}

}
