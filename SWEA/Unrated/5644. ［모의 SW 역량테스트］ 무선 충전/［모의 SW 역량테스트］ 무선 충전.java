
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 5644. 무선 충전
public class Solution {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void move(int dir) {
			switch (dir) {
			case 1: // 상
				y--;
				break;
			case 2: // 우
				x++;
				break;
			case 3: // 하
				y++;
				break;
			case 4: // 좌
				x--;
				break;
			}
		}
	}

	static class BC {
		Pos pos;
		int c, p;

		public BC(Pos pos, int c, int p) {
			this.pos = pos;
			this.c = c;
			this.p = p;
		}
	}

	static int M, A;
	static int[] moveA, moveB;
	static BC[] bcs;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			M = sc.nextInt(); // 총 이동시간
			A = sc.nextInt(); // BC의 개수

			moveA = new int[M]; // 사용자 A의 이동 정보
			moveB = new int[M]; // 사용자 B의 이동 정보
			bcs = new BC[A]; // BC 정보

			for (int i = 0; i < M; i++) {
				moveA[i] = sc.nextInt();
			}
			for (int i = 0; i < M; i++) {
				moveB[i] = sc.nextInt();
			}
			for (int j = 0; j < A; j++) {
				bcs[j] = new BC(new Pos(sc.nextInt(), sc.nextInt()), sc.nextInt(), sc.nextInt());
			}

			// 초기 위치 설정
			Pos posA = new Pos(1, 1);
			Pos posB = new Pos(10, 10);

			// 초기 위치에서 충전한다.
			int maxCharge = charge(posA, posB);

			// 이동하며 충전한다.
			for (int i = 0; i < M; i++) {
				posA.move(moveA[i]);
				posB.move(moveB[i]);
				maxCharge += charge(posA, posB);
			}

			answer.append(String.format("#%d %d\n", test_case, maxCharge));
		}

		System.out.println(answer);

		sc.close();
	}

	public static int charge(Pos posA, Pos posB) {
		// 사용자 별로 충전 가능한 BC를 찾는다.
		List<Integer> chargeA = new ArrayList<>();
		List<Integer> chargeB = new ArrayList<>();

		for (int i = 0; i < A; i++) {
			if (Math.abs(posA.x - bcs[i].pos.x) + Math.abs(posA.y - bcs[i].pos.y) <= bcs[i].c) {
				chargeA.add(i);
			}
			if (Math.abs(posB.x - bcs[i].pos.x) + Math.abs(posB.y - bcs[i].pos.y) <= bcs[i].c) {
				chargeB.add(i);
			}
		}

		int maxCharge = 0;

		// 사용자 A의 BC만 존재
		if (chargeA.size() > 0 && chargeB.size() == 0) {
			for (int i = 0; i < chargeA.size(); i++) {
				maxCharge = Math.max(maxCharge, bcs[chargeA.get(i)].p);
			}
		}
		// 사용자 B의 BC만 존재
		else if (chargeA.size() == 0 && chargeB.size() > 0) {
			for (int i = 0; i < chargeB.size(); i++) {
				maxCharge = Math.max(maxCharge, bcs[chargeB.get(i)].p);
			}
		}
		// 충전할 수 있는 BC들의 조합으로, 현재 위치에서 최대 충전량을 구한다.
		else {
			for (int i = 0; i < chargeA.size(); i++) {
				for (int j = 0; j < chargeB.size(); j++) {
					// 한 BC에 두 명의 사용자가 접속한 경우, 접속한 사용자의 수만큼 충전 양을 균등하게 분배한다.
					if (chargeA.get(i) == chargeB.get(j)) {
						maxCharge = Math.max(maxCharge, bcs[chargeA.get(i)].p);
						continue;
					}

					// 두 명의 사용자가 다른 BC에서 충전하는 경우, 각각 충전량을 저해준다.
					maxCharge = Math.max(maxCharge, bcs[chargeA.get(i)].p + bcs[chargeB.get(j)].p);
				}
			}
		}

		return maxCharge;
	}

}
