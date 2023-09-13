package 정렬;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// 커트라인
public class No25305 {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int k = sc.nextInt();
		ArrayList<Integer> scores = new ArrayList<>();
		for (int i = 0; i < N; i++)
			scores.add(sc.nextInt());
		sc.close();

		// 풀이
		Collections.sort(scores, Collections.reverseOrder());

		// 출력
		System.out.println(scores.get(k - 1));
	}

}
