
// 저울
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<Integer>[] in = new ArrayList[N+1];
		List<Integer>[] out = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			in[i] = new ArrayList<>();
			out[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			in[to].add(from);
			out[from].add(to);
		}
		
		Queue<Integer> q = null;
		boolean[] visited = null;
		for(int i=1; i<=N; i++) {
			int cnt = 0;
			
			// check in
			q = new ArrayDeque<>();
			q.add(i);
			
			visited = new boolean[N+1];
			visited[i] = true;
			
			while(!q.isEmpty()) {
				int now = q.poll();
				for(int next: in[now]) {
					if(visited[next]) {
						continue;
					}
					q.add(next);
					visited[next] = true;
					cnt++;
				}
			}
			
			// check out
			q = new ArrayDeque<>();
			q.add(i);
			
			visited = new boolean[N+1];
			visited[i] = true;
			
			while(!q.isEmpty()) {
				int now = q.poll();
				for(int next: out[now]) {
					if(visited[next]) {
						continue;
					}
					q.add(next);
					visited[next] = true;
					cnt++;
				}
			}
			
			// save unknown number
			bw.write((N-1) - cnt + "\n");
		}
		
		bw.flush();
		br.close();
	}
}
