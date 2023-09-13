package 집합과_맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 듣보잡
public class No1764 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashSet<String> hear = new HashSet<>();
		for (int i = 0; i < N; i++) {
			hear.add(br.readLine());
		}

		PriorityQueue<String> hearAndSee = new PriorityQueue<String>();
		for (int i = 0; i < M; i++) {
			String see = br.readLine();
			if (hear.contains(see)) {
				hearAndSee.add(see);
			}
		}

		int size = hearAndSee.size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			System.out.println(hearAndSee.poll());
		}

	}

}
