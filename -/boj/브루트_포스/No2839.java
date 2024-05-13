package 브루트_포스;

import java.util.Scanner;

// 설탕 배달
public class No2839 {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		// 출력
		System.out.println(solution(N));
	}

	public static int solution(int N) {
		// i: 5kg 설탕봉지 개수
		// j: 3kg 설탕봉지 개수
		for (int i = N / 5; i >= 0; i--)
			for (int j = (N - i * 5) / 3; j >= 0; j--)
				if ((N - i * 5) % 3 == 0)
					return i + j;
		return -1;
	}

}
