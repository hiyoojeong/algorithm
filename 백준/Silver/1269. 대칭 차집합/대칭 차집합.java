

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

// 대칭 차집합
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// A, B 정보 저장
		HashSet<Integer> A = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}

		HashSet<Integer> B = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}
		
		// 대칭 차집합 개수 구하기
		int count = 0;
		Iterator<Integer> it;
		
		it= A.iterator();
		while(it.hasNext()) {
			int num = it.next();
			if(!B.contains(num)) {
				count++;
			}
		}
		
		it = B.iterator();
		while(it.hasNext()) {
			int num = it.next();
			if(!A.contains(num)) {
				count++;
			}
		}
		
		System.out.println(count);
	}

}
