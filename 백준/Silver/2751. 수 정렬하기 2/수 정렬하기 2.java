import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// 수 정렬하기2
public class Main {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < N; i++)
			arr.add(sc.nextInt());
		sc.close();

		// 풀이
		Collections.sort(arr);

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int a : arr)
			sb.append(a).append("\n");
		System.out.println(sb);
	}

}