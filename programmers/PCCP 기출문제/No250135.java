// [PCCP 기출문제] 3번 / 아날로그 시계
public class No250135 {

	public static void main(String[] args) {
		System.out.println(solution(0, 5, 30, 0, 7, 0));
		System.out.println(solution(12, 0, 0, 12, 0, 30));
		System.out.println(solution(0, 6, 1, 0, 6, 6));
		System.out.println(solution(11, 59, 30, 12, 0, 0));
		System.out.println(solution(11, 58, 59, 11, 59, 0));
		System.out.println(solution(1, 5, 5, 1, 5, 6));
		System.out.println(solution(0, 0, 0, 23, 59, 59));
	}

	public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int result = getCount(h2, m2, s2) - getCount(h1, m1, s1);
        if (s1 == 0 && m1 == 0) result += 1;

        return result;
    }

    private static int getCount(int h, int m, int s) {
    	int result = 0;
        int mCount = 0, hCount = 0;

        hCount += h * 60;
        mCount += h * 59;

        hCount += m;
        mCount += m;
        result -= 1;

        int curMDegree = m * 6; // 분침 각도
        double curHDegree = 30 * (h % 12) + 0.5 * m; // 시침 각도
        boolean condition1 = curMDegree <= 5.9 * s; // 초침이 분침을 지나갔는지
        boolean condition2 = curHDegree <= (6 - 1 / 120.0) * s; // 초침이 시침을 지나갔는지

        if (condition1) mCount += 1;
        if (condition2) hCount += 1;

        if (h >= 12) {
            hCount -= 1;
            result -= 1;
        }

        result += mCount + hCount;

        return result;
    }
}
