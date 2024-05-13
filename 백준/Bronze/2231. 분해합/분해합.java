import java.util.Scanner;

// 분해합
public class Main {

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

	public static int ds(int i) {
		int sum = i;
		while (i != 0) {
			sum += i % 10;
			i /= 10;
		}
		return sum;
	}

}
