import java.util.Arrays;
import java.util.Scanner;

// 수 정렬하기
public class Main {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();
		sc.close();

		// 풀이
		Arrays.sort(arr);
		
		// 출력
		for (int i = 0; i < N; i++)
			System.out.println(arr[i]);
	}
}
