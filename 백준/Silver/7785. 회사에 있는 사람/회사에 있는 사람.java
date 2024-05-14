
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 회사에 있는 사람
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		HashSet<String> set = new HashSet<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String move = st.nextToken();
			if (move.equals("enter")) {
				set.add(name);
			} else if (move.equals("leave")) {
				set.remove(name);
			}
		}
		
		PriorityQueue<String> remains = new PriorityQueue<String>(Collections.reverseOrder());
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			remains.add(it.next());
		}
		
		int size = remains.size();
		for(int i=0; i<size; i++) {
			System.out.println(remains.poll());
		}

	}

}
