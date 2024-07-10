import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String init = br.readLine();
			int answer = solution(init);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}
	
	public static int solution(String init) {
		char ch = '0';
		int cnt = 0;
		
		for(int i=0; i<init.length(); i++) {
			if(init.charAt(i) != ch) {
				cnt++;
				
				if(ch == '0') {
					ch = '1';
				} else {
					ch = '0';
				}
			}
		}
		
		return cnt;
	}

}
