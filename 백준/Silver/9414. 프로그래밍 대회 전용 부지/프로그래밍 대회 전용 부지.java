

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// 프로그래밍 대회 전용 부지
public class Main {

	static int MONEY = 5000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < T; i++) {
			int total = 0;

			// 땅값 정보 저장
			int ground = Integer.parseInt(br.readLine());
			while (ground != 0) {
				queue.add(ground);
				ground = Integer.parseInt(br.readLine());
			}

			// 모든 땅을 구입하는데 필요한 최소 금액 구하기
			int size = queue.size();
			for (int year = 1; year <= size; year++) {
				int min_ground = queue.poll();
				total += (2 * Math.pow(min_ground, year));
			}
			
			if(total <= MONEY) {
				answer.append(total + "\n");
			} else {
				answer.append("Too expensive\n");
			}
		}
		
		System.out.println(answer);
	}

}
