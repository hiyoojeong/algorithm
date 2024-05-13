import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Info {
		int start, end;

		public Info(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		Queue<Info> q = new PriorityQueue<>(new Comparator<Info>() {
			public int compare(Info o1, Info o2) {
				return o1.end == o2.end ? o1.start - o2.start : o1.end - o2.end;
			}
		});
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			q.add(new Info(start, end));
		}
		
		int beforeEnd = 0;
		int answer = 0;
		while(!q.isEmpty()) {
			Info info = q.poll();
			if(beforeEnd <= info.start) {
				answer++;
				beforeEnd = info.end;
			}
		}
		
		System.out.println(answer);

	}

}