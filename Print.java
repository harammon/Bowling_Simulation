/*
 * ���ϸ� : Print.java
 * �����ۼ��� : 2020�� 6�� 12��
 * �ۼ��� : 20142611 ���϶�
 */
public class Print {
	public void frameBoard() {
		System.out.println("=====================================================================");
		System.out.println("|FRAME |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |   10  |");	
	}
	public String frameScore(int first, int second)	{
		if ( first == 10 )	//��Ʈ����ũ�� ���
			return " " + frameScore(first) + "   ";
		if ( first + second == 10 )		//������� ���
			return " " + frameScore(first) + " " + "/" + " ";
		return " " + frameScore(first) + " " + frameScore(second) + " ";	//������ ���
	}	
	public String frameScore(int num) {		//��Ʈ����ũ�� ���� ǥ�� �Լ�
		if (num == 0)
			return "-";
		else if (num == 10)
			return "X";
		else
			return Integer.toString(num);
	}
	public void scoreBoard(Player [] player, int playerNum, int frameNum) {
		for (int i = 0; i < player.length; i++) {
			StringBuffer sb = new StringBuffer();
			sb.append("|");
			sb.append((i+1)+"�� �մ�");
			sb.append("|");
			for ( int j = 0; j < 9; j++) {	//1~9�����ӱ��� ���� ���
				int first = player[i].frame[j].first;
				int second = player[i].frame[j].second;
				if (j < frameNum || (j == frameNum && i <= playerNum)) {
					sb.append(frameScore(first, second) + "|");
				}
				else {
					sb.append("     |");
				}
			}
			String first = " ";
			String second = " ";
			String third = " ";
			if (frameNum == 9 && i <= playerNum) {	//10������ ���
				if (player[i].frame[9].isOpen()) {	//���ʽ� ���� ����
					first = frameScore(player[i].frame[9].first);
					second = frameScore(player[i].frame[9].second);
					sb.append(" " + first + " " + second + "   |");
				}
				if (player[i].frame[9].isSpare()) {	//���ʽ� ���� �������� ���� ���� ǥ��
					int first1 = player[i].frame[9].first;
					int second1 = player[i].frame[9].second;
					third = frameScore(player[i].frame[10].first);
					sb.append(frameScore(first1, second1));
					sb.append(third + " |");
					}
				if (player[i].frame[9].isStrike()) { //���ʽ� ���� �������� ���� ���� ǥ�� (�ؿ��� ���� ���� �� ������ 10�����ӿ��� ��Ʈ����ũ : x , ����� : / , ���� : -�� ǥ���ϱ� ����.)
					if(player[i].frame[9].second!=10) {		//10�������� �ι�° ���� ��Ʈ����ũ�� �ƴѰ��
						first = frameScore(player[i].frame[9].first);
						int second2 = player[i].frame[9].second;
						int third2 = player[i].frame[10].first;
						sb.append(" " + first);
						sb.append(frameScore(second2, third2));
						sb.append("|");
					}
					else if(player[i].frame[9].second==10) {	//10�������� �ι�° ���� ��Ʈ����ũ�� ���
						first = frameScore(player[i].frame[9].first);
						second = frameScore(player[i].frame[9].second);
						third = frameScore(player[i].frame[10].first);
						sb.append(" " + first + " " + second + " " + third + " |");
					}
				}
			}
			else 
				sb.append("       |");
			System.out.println("---------------------------------------------------------------------");
			System.out.println(sb.toString());
			System.out.print("|SCORE |");
			for ( int f = 0; f < 9; f++ ) {		//�� �÷��̾��� �ش� �����ӱ����� ���� �հ� ���
				if ( player[i].accumScore[f] == -1 )
					System.out.print("     |");
				else
					System.out.printf(" %3d |", player[i].accumScore[f]);
			}
			if ( player[i].accumScore[9] == -1 )
				System.out.print("       |");
			else
				System.out.printf("  %3d  |", player[i].accumScore[9]);
			System.out.println();
		}
		System.out.println("=====================================================================");
	}
	public void resultScore(Player[] player, int playerNum) {	//���� ����� ��� �Լ�
		int winnerScore=0;	//���� �ְ� ������ ��� �ִ� ����
		int winnerIndex=0;	//�ش� ������ player�� �����ϱ� ���� index
		for(int i=0; i<playerNum; i++) {
			System.out.println((i+1)+"�� �մ� = "+player[i].name);
			if(winnerScore<player[i].accumScore[9]) {
				winnerScore = player[i].accumScore[9];
				winnerIndex = i;
			}
		}
		System.out.println("");
		for(int k=0; k<playerNum; k++) {
			System.out.println(player[k].name+"("+player[k].level+")"+"��"+"�� ���� ������ "+player[k].accumScore[9]);
			
		}
		System.out.println("");
		System.out.println("�ڡڡڡڡڡڡڡڡ������մϴ�. ���� ����ڴ� "+player[winnerIndex].name+"���Դϴ�.�ڡڡڡڡڡڡڡڡ�");
	}
}