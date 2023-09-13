package 정렬;

import java.util.Arrays;
import java.util.Scanner;

// 대표값2
public class No2587 {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[5];
		for (int i = 0; i < 5; i++)
			arr[i] = sc.nextInt();
		sc.close();
		
		// 출력
		System.out.println(avg(arr));
		System.out.println(median(arr));
	}

	public static int avg(int[] arr) {
		int sum = 0;
		for (int i = 0; i < 5; i++)
			sum += arr[i];
		return sum / 5;
	}

	public static int median(int[] arr) {
		Arrays.sort(arr);
		return arr[2];
	}
}
