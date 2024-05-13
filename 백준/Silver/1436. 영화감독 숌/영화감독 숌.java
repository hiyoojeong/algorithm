import java.util.Scanner;

// 영화감독 숌
public class Main {

	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		// 출력
		System.out.println(solution(N));
	}

	public static int solution(int N) {
		int movieCnt = 0; // 666이 들어간 영화 제목의 개수
		int movieName = 665; // 666이 들어간 영화 제목을 찾기 위한 변수

		while (movieCnt < N) {
			movieName++;
			if (Integer.toString(movieName).contains("666"))
				movieCnt++;
		}
		return movieName;
	}

}
