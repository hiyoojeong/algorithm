// [PCCP 기출문제] 1번 / 붕대 감기
public class No250137 {

	public static void main(String[] args) {
		System.out.println(
				solution(new int[] { 5, 1, 5 }, 30, new int[][] { { 2, 10 }, { 9, 15 }, { 10, 5 }, { 11, 5 } }));
	}

	public static int solution(int[] bandage, int health, int[][] attacks) {
		// bandage는 [시전 시간, 초당 회복량, 추가 회복량]
		// health는 최대 체력
		// attacks[i]는 [공격 시간, 피해량]

		int maxHealingTime = bandage[0];
		int healingPerSecond = bandage[1];
		int extraHealing = bandage[2];

		int maxHealth = health;

		int healingTime = 0;
		int time = 0;

		int attackOrder = 0;
		while (attackOrder < attacks.length) {
			// 시간 증가
			time++;

			// 공격을 받은 경우
			if (attacks[attackOrder][0] == time) {
				health -= attacks[attackOrder][1];
				if (health <= 0) {
					return -1;
				}
				
				healingTime = 0;

				attackOrder++;
				continue;
			}

			// 초당 회복
			health += healingPerSecond;
			health = (health > maxHealth) ? maxHealth : health;

			// 붕대 감기
			healingTime++;
			if (healingTime == maxHealingTime) {
				health += extraHealing;
				health = (health > maxHealth) ? maxHealth : health;
				
				healingTime = 0;
			}
		}

		return health;
	}

}
