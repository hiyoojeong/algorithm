package 정렬;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// 수 정렬하기2
public class No2751 {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < N; i++)
			arr.add(sc.nextInt());
		sc.close();

		// 풀이
		// Arrays.sort(arr); <-- 시간초과
		Collections.sort(arr);

		// 출력
		// 단순 출력 <-- 시간초과
		StringBuilder sb = new StringBuilder();
		for (int a : arr)
			sb.append(a).append("\n");
		System.out.println(sb);
	}

}
