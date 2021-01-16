/*
 * ���ϸ� : RandomBowling.java
 * �����ۼ��� : 2020�� 6�� 12��
 * �ۼ��� : 20142611 ���϶�
 */
import java.util.Scanner;
import java.math.*;
public class RandomBowling {
	public Player [] player;
	public int[] beginer= {0, 1, 2, 3, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10};	//�ʺ����� ���� �迭(�Ƿ� �ݿ�)
	public int[] intermediate = {4, 5, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10};	//�߱����� ���� �迭(�Ƿ� �ݿ�)
	public int[] senior = {7, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10};	//������� ���� �迭(�Ƿ� �ݿ�)
	int playerCnt;
	String s2;
	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�ڡڡڡڡڡڡڡڡ�Random Bowling �����Դϴ�.�ڡڡڡڡڡڡڡڡ�");
		while(true) {	//�ڿ����� �ƴҽ� �ٽ� �Է¹޵���(���� �̿��� ���� �Է½� ���ܹ߻�)
			System.out.print("�ο� ���� �Է��ϼ��� : ");
			playerCnt = scanner.nextInt();	//���ӿ� �����ϴ� �ο��� �Է� �ޱ�.
			if(!(playerCnt>0)) {
				System.out.println("�ο���(�ڿ���)�� �Է��ϼ���.");
			}
			if(playerCnt>0) {
				break;
			}
		}
		makePlayer(playerCnt);	//�Էµ� �ο�����ŭ �÷��̾� ����
		for(int j=0; j<playerCnt; j++) {	//�÷��̾��� �̸��� ���� �Է¹ޱ�
			System.out.print("player"+(j+1)+"�� �̸��� �Է��ϼ��� : ");
			String s1 = scanner.next();
			player[j].name=s1;
			while(true) {	//�ʺ���, �߱���, ����� ���ڿ� �Է� ����������
				System.out.print("player"+(j+1)+"�� ������ �Է��ϼ���(�ʺ��� / �߱��� / �����) : ");
				s2 = scanner.next();
				if(!s2.equals("�ʺ���")&&!s2.equals("�߱���")&&!s2.equals("�����")) {
					System.out.println("");
					System.out.println("�߸� �Է��ϼ̽��ϴ�. �ʺ��� / �߱��� / ����� �� �ϳ��� �Է����ּ���.");			
				}
				if(s2.equals("�ʺ���")||s2.equals("�߱���")||s2.equals("�����")) {
					System.out.println("");
					break;
				}
			}
			player[j].level = s2;
		}
		for ( int frameNum = 0; frameNum < 10; frameNum++) {
			System.out.println((frameNum+1)+"FRAME");
			for ( int playerNum = 0; playerNum < playerCnt; playerNum++) {
				inputScore(playerNum, frameNum);				
				player[playerNum].calcScore(frameNum);	
				Print print = new Print();
				print.frameBoard();
				print.scoreBoard(player, playerNum, frameNum);
				System.out.println("");
			}			
		}	
		Print print = new Print();
		print.resultScore(player, playerCnt);
		scanner.close();
	}
	private void makePlayer(int playerCnt) {	//�÷��̾� ���� �Լ�
		player = new Player [playerCnt];
		for ( int i = 0; i < playerCnt; i++ ) {
			player[i] = new Player();
		}
	}
	private void inputScore(int playerNum, int frameNum) {	//���� �Է� �Լ�
		Frame frame = player[playerNum].frame[frameNum];
		if(player[playerNum].level.equals("�����")) {	//������ΰ��
			int q = (int)(Math.random()*senior.length);	//����� ���� �迭 ���� �� �ϳ� ���õǵ���
			frame.first=senior[q];
		}
		if(player[playerNum].level.equals("�߱���")) {	//�߱����ΰ��
			int q = (int)(Math.random()*intermediate.length);	//�߱��� ���� �迭 ���� �� �ϳ� ���õǵ���
			frame.first=intermediate[q];
		}
		if(player[playerNum].level.equals("�ʺ���")) {	//�ʺ����ΰ��
			int q = (int)(Math.random()*beginer.length);	//�ʺ��� ���� �迭 ���� �� �ϳ� ���õǵ���
			frame.first=beginer[q];
		}
		if (frame.first != 10) {	//ù���� ��Ʈ����ũ �ƴϸ� �ι�° ���� ����
			if(player[playerNum].level.equals("�����")) {	//������ΰ��
				for(int z=0; z<3; z++) {	//�ִ� 3�� �ݺ��ϸ� ����� ó���� ����
					frame.second=(int)(Math.random()*(10-frame.first)+1);
					if(frame.isSpare())	//������ΰ��
						break;	//for loop ��������
				}
			}
			if(player[playerNum].level.equals("�߱���")) {	//�߱����ΰ��
				for(int z=0; z<2; z++) {	//�ִ� 2�� �ݺ��ϸ� ����� ó���� ����
					frame.second=(int)(Math.random()*(10-frame.first)+1);
					if(frame.isSpare())	//������ΰ��
						break;	//for loop ��������
				}
			}
			else if(player[playerNum].level.equals("�ʺ���")) {	//�ʺ����ΰ�� ����� ���� ���� ����.
				frame.second=(int)(Math.random()*(10-frame.first)+1);
			}
		}
		if (frameNum == 9){	//10�������ΰ�� (���ʽ� ���� ����ϱ� ����)
			if (frame.isSpare()) {	//������ΰ�� 10�������� ����° ���� �ش� �迭���� ����
				if(player[playerNum].level.equals("�����")) {		
					int q = (int)(Math.random()*senior.length);
					player[playerNum].frame[10].first = senior[q];
				}
				if(player[playerNum].level.equals("�߱���")) {	
					int q = (int)(Math.random()*intermediate.length);
					player[playerNum].frame[10].first = intermediate[q];
				}
				if(player[playerNum].level.equals("�ʺ���")) {	
					int q = (int)(Math.random()*beginer.length);
					player[playerNum].frame[10].first = beginer[q];
				}
			}
			else if (frame.isStrike()) {	//������� ��� 10�������� �ι�° ���� �ش� �迭���� ����
				if(player[playerNum].level.equals("�����")) {	
					int q = (int)(Math.random()*senior.length);
					frame.second = senior[q];
					if(frame.second!=10) {	//�ι�° ���� ��Ʈ����ũ�� �ƴϸ�, ����°���� ������ (10-�ι�° ��)
						player[playerNum].frame[10].first=(int)(Math.random()*(10-frame.second)+1);
					}
					else if(frame.second==10) {		//�ι�° ���� ��Ʈ����ũ�� ���, ����° ���� ����
						int q1 = (int)(Math.random()*senior.length);
						player[playerNum].frame[10].first = senior[q1];
					}			
				}
				if(player[playerNum].level.equals("�߱���")) {	
					int q = (int)(Math.random()*intermediate.length);
					frame.second = intermediate[q];
					if(frame.second!=10) {	//�ι�° ���� ��Ʈ����ũ�� �ƴϸ�, ����°���� ������ (10-�ι�° ��)
						player[playerNum].frame[10].first=(int)(Math.random()*(10-frame.second)+1);
					}
					else if(frame.second==10) {	//�ι�° ���� ��Ʈ����ũ�� ���, ����° ���� ����
						int q1 = (int)(Math.random()*intermediate.length);
						player[playerNum].frame[10].first = intermediate[q1];
					}			
				}
				if(player[playerNum].level.equals("�ʺ���")) {	
					int q = (int)(Math.random()*beginer.length);
					frame.second = beginer[q];
					if(frame.second!=10) {	//�ι�° ���� ��Ʈ����ũ�� �ƴϸ�, ����°���� ������ (10-�ι�° ��)
						player[playerNum].frame[10].first=(int)(Math.random()*(10-frame.second)+1);
					}
					else if(frame.second==10) {	//�ι�° ���� ��Ʈ����ũ�� ���, ����° ���� ����
						int q1 = (int)(Math.random()*beginer.length);
						player[playerNum].frame[10].first = beginer[q1];
					}			
				}
			}
		}
	}
}
