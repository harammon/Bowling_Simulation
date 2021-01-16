/*
 * 파일명 : Print.java
 * 최종작성일 : 2020년 6월 12일
 * 작성자 : 20142611 이하람
 */
public class Print {
	public void frameBoard() {
		System.out.println("=====================================================================");
		System.out.println("|FRAME |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |   10  |");	
	}
	public String frameScore(int first, int second)	{
		if ( first == 10 )	//스트라이크인 경우
			return " " + frameScore(first) + "   ";
		if ( first + second == 10 )		//스페어인 경우
			return " " + frameScore(first) + " " + "/" + " ";
		return " " + frameScore(first) + " " + frameScore(second) + " ";	//오픈인 경우
	}	
	public String frameScore(int num) {		//스트라이크와 거터 표시 함수
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
			sb.append((i+1)+"번 손님");
			sb.append("|");
			for ( int j = 0; j < 9; j++) {	//1~9프레임까지 점수 출력
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
			if (frameNum == 9 && i <= playerNum) {	//10프레임 출력
				if (player[i].frame[9].isOpen()) {	//보너스 투구 없음
					first = frameScore(player[i].frame[9].first);
					second = frameScore(player[i].frame[9].second);
					sb.append(" " + first + " " + second + "   |");
				}
				if (player[i].frame[9].isSpare()) {	//보너스 투구 시행으로 인한 점수 표기
					int first1 = player[i].frame[9].first;
					int second1 = player[i].frame[9].second;
					third = frameScore(player[i].frame[10].first);
					sb.append(frameScore(first1, second1));
					sb.append(third + " |");
					}
				if (player[i].frame[9].isStrike()) { //보너스 투구 시행으로 인한 점수 표기 (밑에와 같이 나눠 준 이유는 10프레임에서 스트라이크 : x , 스페어 : / , 거터 : -를 표시하기 위함.)
					if(player[i].frame[9].second!=10) {		//10프레임의 두번째 구가 스트라이크가 아닌경우
						first = frameScore(player[i].frame[9].first);
						int second2 = player[i].frame[9].second;
						int third2 = player[i].frame[10].first;
						sb.append(" " + first);
						sb.append(frameScore(second2, third2));
						sb.append("|");
					}
					else if(player[i].frame[9].second==10) {	//10프레임의 두번째 구가 스트라이크인 경우
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
			for ( int f = 0; f < 9; f++ ) {		//각 플레이어의 해당 프레임까지의 접수 합계 출력
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
	public void resultScore(Player[] player, int playerNum) {	//최종 우승자 출력 함수
		int winnerScore=0;	//현재 최고 점수를 담고 있는 변수
		int winnerIndex=0;	//해당 점수의 player를 저장하기 위한 index
		for(int i=0; i<playerNum; i++) {
			System.out.println((i+1)+"번 손님 = "+player[i].name);
			if(winnerScore<player[i].accumScore[9]) {
				winnerScore = player[i].accumScore[9];
				winnerIndex = i;
			}
		}
		System.out.println("");
		for(int k=0; k<playerNum; k++) {
			System.out.println(player[k].name+"("+player[k].level+")"+"님"+"의 최종 점수는 "+player[k].accumScore[9]);
			
		}
		System.out.println("");
		System.out.println("★★★★★★★★★축하합니다. 최종 우승자는 "+player[winnerIndex].name+"님입니다.★★★★★★★★★");
	}
}