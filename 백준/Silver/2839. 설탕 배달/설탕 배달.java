import java.util.Scanner;

// 설탕 배달
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		int fiveCnt = N / 5, threeCnt = -1;
		while (fiveCnt >= 0) {
			int five = 5 * fiveCnt; // 5kg 설탕봉지의 총 무게
			int three = N - five; // 3kg 설탕봉지의 총 무게 = 필요한 설탕 무게 - 5kg 설탕봉지의 총 무게

			if (three % 3 == 0) { // 3kg 설탕봉지로 해당 무게를 맞출 수 있다면, 선택한다.
				threeCnt = three / 3;
				break;
			}

			fiveCnt--; // 3kg 설탕봉지로 해당 무게를 맞출 수 없다면, 5kg 설탕봉지 수를 줄인다.
		}

		int result = fiveCnt != -1 ? fiveCnt + threeCnt : -1;
		System.out.println(result);
	}

}
