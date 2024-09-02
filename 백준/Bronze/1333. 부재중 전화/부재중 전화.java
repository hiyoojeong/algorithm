import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int L = sc.nextInt(); // 안내멘트 시간
		int D = sc.nextInt(); // 고객 요청 쿨타임

		int time = -1;
		for (int i = 0; i < N; i++) {
			time += L;
			for (int j = 0; j < 5; j++) {
				time++;
				if (time % D == 0) {
					System.out.println(time);
					return;
				}
			}
		}
		while (true) {
			time++;
			if (time % D == 0) {
				System.out.println(time);
				return;
			}
		}
	}
}
