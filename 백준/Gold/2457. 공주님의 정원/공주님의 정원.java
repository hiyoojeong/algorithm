import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 공주님의 정원
public class Main {

	static class Flower implements Comparable<Flower> {
		int start, end;

		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Flower o) {
			if (this.start == o.start) {
				return o.end - this.end;
			}
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		Flower[] flowers = new Flower[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int startM = Integer.parseInt(st.nextToken());
			int startD = Integer.parseInt(st.nextToken());
			int endM = Integer.parseInt(st.nextToken());
			int endD = Integer.parseInt(st.nextToken());
			
			flowers[i] = new Flower(startM * 100 + startD, endM * 100 + endD);
		}

		Arrays.sort(flowers);
		
		int beforeEnd = 301;
		int maxEnd = 301;
		int end = 1201;
		int idx = 0;
		int answer = 0;

		while (maxEnd < end) {
			boolean pick = false;

			for (int i = idx; i < n; i++) {
				if (flowers[i].start > beforeEnd) {
					break;
				}

				if (flowers[i].end > maxEnd) {
					maxEnd = flowers[i].end;
					pick = true;
					idx = i + 1;
				}
			}

			if (pick) {
				beforeEnd = maxEnd;
				answer++;
			} else {
				break;
			}
		}
		
		if(maxEnd < end) {
			answer = 0;
		}
		
		System.out.println(answer);
	}

}
