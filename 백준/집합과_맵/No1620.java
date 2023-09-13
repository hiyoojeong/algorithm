package 집합과_맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 나는야 포켓몬 마스터 이다솜
public class No1620 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<String, Integer> pokemons = new HashMap<>();
		HashMap<Integer, String> numbers = new HashMap<>();

		for (int i = 1; i <= N; i++) {
			String name = br.readLine();
			pokemons.put(name, i);
			numbers.put(i, name);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			String quiz = br.readLine();
			try {
				int number = Integer.parseInt(quiz);
				sb.append(numbers.get(number) + "\n");
			} catch (Exception e) {
				sb.append(pokemons.get(quiz) + "\n");
			}
		}

		System.out.println(sb);

	}

}
