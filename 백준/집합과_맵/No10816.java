package 집합과_맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 숫자 카드 2
public class No10816 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		HashMap<Integer, Integer> cards = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int card = Integer.parseInt(st.nextToken());
			cards.put(card, cards.getOrDefault(card, 0) + 1);
		}

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int card = Integer.parseInt(st.nextToken());
			sb.append(cards.getOrDefault(card, 0) + " ");
		}

		System.out.println(sb);

	}

}
