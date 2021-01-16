/*
 * ���ϸ� : Player.java
 * �����ۼ��� : 2020�� 6�� 12��
 * �ۼ��� : 20142611 ���϶�
 */
public class Player {
	public Frame [] frame = new Frame[11];	//���ʽ� �������� ����� ������ �迭
	public int [] accumScore = new int[11];	//�� �÷��̾��� ���� �հ踦 ���� �迭
	public Score score = new Score();
	public String level;	//�÷��̾��� ����(�ʺ��� / �߱��� / �����)�� ����� ����
	public String name;	
	public Player() {
		name = "";
		level = "";
		for (int i = 0; i < 11; i++) {
			frame[i] = new Frame();
			accumScore[i] = -1;	//scoreboard�� ȿ�������� ����� ���� �ʱⰪ-1. 
		}
	}
	public void calcScore(int frameNum) {
		score.calcScore(frame, accumScore, frameNum);
	}
}