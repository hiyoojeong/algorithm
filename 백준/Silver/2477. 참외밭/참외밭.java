import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	static class Info {
		int direct, len;

		public Info(int direct, int len) {
			this.direct = direct;
			this.len = len;
		}
	}

	static class Shape {
		String str;
		int w, h, n, m;

		public Shape(String str, int w, int h, int n, int m) {
			this.str = str;
			this.w = w;
			this.h = h;
			this.n = n;
			this.m = m;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();

		List<Shape> shapes = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		String str = "314142";
		map.put(str, 0);
		shapes.add(new Shape(str, 0, 5, 2, 3));
		str = "313142";
		map.put(str, 1);
		shapes.add(new Shape(str, 4, 5, 1, 2));
		str = "314242";
		map.put(str, 2);
		shapes.add(new Shape(str, 0, 1, 3, 4));
		str = "314232";
		map.put(str, 3);
		shapes.add(new Shape(str, 1, 2, 4, 5));

		List<Info> list = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			list.add(new Info(sc.nextInt(), sc.nextInt()));
		}

		for (int i = 0; i < 6; i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < 6; j++) {
				sb.append(list.get((j + i) % 6).direct);
			}
			String key = sb.toString();
			if (map.containsKey(key)) {
				Shape shape = shapes.get(map.get(key));
				int area = (list.get((shape.w + i) % 6).len * list.get((shape.h + i) % 6).len)
						- (list.get((shape.n + i) % 6).len * list.get((shape.m + i) % 6).len);
				int answer = area * C;
				System.out.println(answer);
				return;
			}
		}

		sc.close();

	}

}
