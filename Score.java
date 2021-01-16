/*
 * ���ϸ� : Score.java
 * �����ۼ��� : 2020�� 6�� 12��
 * �ۼ��� : 20142611 ���϶�
 */
public class Score {
	public int Index = 0;
	public int sum = 0;
	public void calcScore(Frame [] frame, int [] accumScore, int frameNum) {
		while (Index <= frameNum) {
			int basic = addBasic(frame[Index]);
			int bonus = 0;
			if (frame[Index].isStrike())	//��Ʈ����ũ�� ��� ���� �������� �� ���� ���ʽ��� ����
				bonus = strikeBonus(frame, Index, frameNum);
			if (frame[Index].isSpare())		//������� ��� ���� �������� ù ���� ���ʽ��� ����
				bonus = spareBonus(frame, Index, frameNum);
			if (bonus == -1)
				return;
			sum += basic + bonus;
			accumScore[Index] = sum;
			Index++;
		}
	}
	private int addBasic(Frame frame) {	//�⺻������ �ش� �������� �ʱ��� �ι�° ���� �� ���ϱ� 
		return frame.first + frame.second;
	}
	private int strikeBonus(Frame [] frame, int Index, int frameNum) {	//�ش� �������� ��Ʈ����ũ�� ��� ȣ��
		if (Index < 9 && Index + 1 > frameNum)
			return -1;
		if (Index < 8 && Index + 2 > frameNum)	
			return -1;
		if (!frame[Index + 1].isStrike())	//���� �������� ��Ʈ����ũ�� �ƴ� ���
			return addBasic(frame[Index + 1]);
		if (Index == 9)	//������ �������� ���
			return 10 + frame[Index + 1].second;
		return 10 + frame[Index + 2].first;
	}
	private int spareBonus(Frame [] frame, int Index, int frameNum) {	//�ش� �������� ������� ��� ȣ��
		if (Index < 9 && Index + 1 > frameNum)
			return -1;
		return frame[Index + 1].first;
	}
}