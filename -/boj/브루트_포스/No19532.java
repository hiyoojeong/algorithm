package 브루트_포스;

import java.util.Scanner;

// 수학은 비대면 강의입니다.
public class No19532 {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int e = sc.nextInt();
		int f = sc.nextInt();
		sc.close();

		// 풀이
		// 가감법 사용
		int x = (c * e - b * f) / (a * e - b * d);
		int y = (c * d - a * f) / (b * d - a * e);

		// 출력
		System.out.println(x + " " + y);
	}

}
