import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 반도체 설계
public class Main {

	static class Port {
		int left, right;

		public Port(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

	static List<Integer> lis;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 데이터를 입력받는다.
		List<Port> ports = new ArrayList<>();

		int n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			ports.add(new Port(i, Integer.parseInt(st.nextToken())));
		}

		// 왼쪽 포트 번호를 기준으로 정렬한다.
		Collections.sort(ports, new Comparator<Port>() {
			public int compare(Port p1, Port p2) {
				return p1.left - p2.left;
			}
		});

		// 오른쪽 포트 번호에서 '가장 긴 증가하는 수열'을 찾는다.
		lis = new ArrayList<>();
		lis.add(ports.get(0).right);
		
		for(int i=0; i<n; i++) {
			int key = ports.get(i).right;
			int index = upperbound(key);
			
			if(lis.get(index) < key) {
				lis.add(key);
			} else {
				lis.set(index, key);
			}
			
		}
		
		// 최대로 연결할 수 있는 포트 수 = 가장 긴 증가하는 수열의 길이
		int maxConnection = lis.size();
		
		System.out.println(maxConnection);

	}

	public static int upperbound(int key) {
		int left = 0;
		int right = lis.size() - 1;

		while (left < right) { // 만일 큰 값이 없으면, 가장 오른쪽 인덱스가 반환될 것임

			int mid = (left + right) / 2;
			
			if(lis.get(mid) <= key) // 동일한 값도 넘겨버림
				left = mid + 1;
			else
				right = mid;
		}
		
		return right;
	}
}
