/*
 * ���ϸ� : Frame.java
 * �����ۼ��� : 2020�� 6�� 12��
 * �ۼ��� : 20142611 ���϶�
 */
public class Frame {
	public int first;
	public int second;
	public boolean isOpen() {		//���� �Ǻ� �Լ�
		if (first + second < 10)
			return true;
		else
			return false;
	}
	public boolean isSpare() {		//����� �Ǻ� �Լ�
		if (first + second == 10 && first != 10) 	
			return true;
		else
			return false;
	}
	public boolean isStrike() {		//��Ʈ����ũ �Ǻ� �Լ�
		if (first == 10)
			return true;
		else
			return false;
	}	
}