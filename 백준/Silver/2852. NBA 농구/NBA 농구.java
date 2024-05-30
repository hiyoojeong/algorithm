import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 기상캐스터
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] score = new int[2 + 1];
		int[] time = new int[2 + 1];

		// 득점 정보를 받아, 각 팀이 이기고 있던 시간을 계산한다.
		int beforeTime = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int team = Integer.parseInt(st.nextToken());
			String[] timeInfo = st.nextToken().split(":");
			int currentTime = Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);

			// 직전 체크포인트부터 현재 체크포인트까지 이기고 있던 팀에게 이기고 있던 시간을 추가해준다.
			if (score[1] > score[2]) {
				time[1] += currentTime - beforeTime;
			} else if (score[1] < score[2]) {
				time[2] += currentTime - beforeTime;
			}

			// 현재 체크포인트에서 득점한 팀의 점수를 증가시켜준다.
			score[team]++;

			// 체크포인트를 바꿔준다.
			beforeTime = currentTime;
		}

		// 직전 체크포인트부터 게임 종료 시점(48:00)까지 이기고 있던 팀에게 이기고 있던 시간을 추가해준다.
		int currentTime = 48 * 60;
		if (score[1] > score[2]) {
			time[1] += currentTime - beforeTime;
		} else if (score[1] < score[2]) {
			time[2] += currentTime - beforeTime;
		}

		StringBuffer answer = new StringBuffer();
		for (int i = 1; i <= 2; i++) {
			int minute = time[i] / 60;
			int second = time[i] % 60;

			answer.append(minute < 10 ? "0" : "").append(minute);
			answer.append(":");
			answer.append(second < 10 ? "0" : "").append(second);
			answer.append("\n");
		}
		System.out.println(answer);
	}

}
