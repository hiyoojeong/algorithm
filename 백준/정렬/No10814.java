package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 나이순 정렬
class Member implements Comparable<Member> {
	int age;
	String name;

	public Member(int age, String name) {
		this.age = age;
		this.name = name;
	}

	@Override
	public int compareTo(Member o) {
		return this.age - o.age;
	}
}

public class No10814 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Member> members = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			members.add(new Member(age, name));
		}
		
		Collections.sort(members);

		for (int i = 0; i < N; i++) {
			System.out.println(members.get(i).age + " " + members.get(i).name);
		}

	}

}
