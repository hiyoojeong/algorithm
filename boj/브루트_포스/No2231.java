package 브루트_포스;

import java.util.Scanner;

// 분해합
public class No2231 {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();

		// 풀이
		int answer = 0;
		for (int i = 0; i < num; i++) {
			if(ds(i) == num) {
				answer = i;
				break;
			}
		}

		// 출력
		System.out.println(answer);
	}

	// 숫자 i에 대한 분해합을 구하는 함수
	public static int ds(int i) {
		int sum = i;
		while (i != 0) {
			sum += i % 10;
			i /= 10;
		}
		return sum;
	}

}
