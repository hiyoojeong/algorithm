
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 숫자 카드
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<N; i++) {
			if(set.contains(Integer.parseInt(st.nextToken()))) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			sb.append(" ");
		}
		
		System.out.println(sb);

	}

}
