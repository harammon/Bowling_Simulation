/*
 * 파일명 : RandomBowling.java
 * 최종작성일 : 2020년 6월 12일
 * 작성자 : 20142611 이하람
 */
import java.util.Scanner;
import java.math.*;
public class RandomBowling {
	public Player [] player;
	public int[] beginer= {0, 1, 2, 3, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10};	//초보자의 점수 배열(실력 반영)
	public int[] intermediate = {4, 5, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10};	//중급자의 점수 배열(실력 반영)
	public int[] senior = {7, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10};	//상급자의 점수 배열(실력 반영)
	int playerCnt;
	String s2;
	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("★★★★★★★★★Random Bowling 게임입니다.★★★★★★★★★");
		while(true) {	//자연수가 아닐시 다시 입력받도록(정수 이외의 문자 입력시 예외발생)
			System.out.print("인원 수를 입력하세요 : ");
			playerCnt = scanner.nextInt();	//게임에 참여하는 인원수 입력 받기.
			if(!(playerCnt>0)) {
				System.out.println("인원수(자연수)를 입력하세요.");
			}
			if(playerCnt>0) {
				break;
			}
		}
		makePlayer(playerCnt);	//입력된 인원수만큼 플레이어 생성
		for(int j=0; j<playerCnt; j++) {	//플레이어의 이름과 수준 입력받기
			System.out.print("player"+(j+1)+"의 이름을 입력하세요 : ");
			String s1 = scanner.next();
			player[j].name=s1;
			while(true) {	//초보자, 중급자, 상급자 문자열 입력 받을때까지
				System.out.print("player"+(j+1)+"의 수준을 입력하세요(초보자 / 중급자 / 상급자) : ");
				s2 = scanner.next();
				if(!s2.equals("초보자")&&!s2.equals("중급자")&&!s2.equals("상급자")) {
					System.out.println("");
					System.out.println("잘못 입력하셨습니다. 초보자 / 중급자 / 상급자 중 하나를 입력해주세요.");			
				}
				if(s2.equals("초보자")||s2.equals("중급자")||s2.equals("상급자")) {
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
	private void makePlayer(int playerCnt) {	//플레이어 생성 함수
		player = new Player [playerCnt];
		for ( int i = 0; i < playerCnt; i++ ) {
			player[i] = new Player();
		}
	}
	private void inputScore(int playerNum, int frameNum) {	//점수 입력 함수
		Frame frame = player[playerNum].frame[frameNum];
		if(player[playerNum].level.equals("상급자")) {	//상급자인경우
			int q = (int)(Math.random()*senior.length);	//상급자 점수 배열 원소 중 하나 선택되도록
			frame.first=senior[q];
		}
		if(player[playerNum].level.equals("중급자")) {	//중급자인경우
			int q = (int)(Math.random()*intermediate.length);	//중급자 점수 배열 원소 중 하나 선택되도록
			frame.first=intermediate[q];
		}
		if(player[playerNum].level.equals("초보자")) {	//초보자인경우
			int q = (int)(Math.random()*beginer.length);	//초보자 점수 배열 원소 중 하나 선택되도록
			frame.first=beginer[q];
		}
		if (frame.first != 10) {	//첫투구 스트라이크 아니면 두번째 투구 시행
			if(player[playerNum].level.equals("상급자")) {	//상급자인경우
				for(int z=0; z<3; z++) {	//최대 3번 반복하며 스페어 처리를 유도
					frame.second=(int)(Math.random()*(10-frame.first)+1);
					if(frame.isSpare())	//스페어인경우
						break;	//for loop 빠져나감
				}
			}
			if(player[playerNum].level.equals("중급자")) {	//중급자인경우
				for(int z=0; z<2; z++) {	//최대 2번 반복하며 스페어 처리를 유도
					frame.second=(int)(Math.random()*(10-frame.first)+1);
					if(frame.isSpare())	//스페어인경우
						break;	//for loop 빠져나감
				}
			}
			else if(player[playerNum].level.equals("초보자")) {	//초보자인경우 스페어 유도 과정 없음.
				frame.second=(int)(Math.random()*(10-frame.first)+1);
			}
		}
		if (frameNum == 9){	//10프레임인경우 (보너스 투구 고려하기 위함)
			if (frame.isSpare()) {	//스페어인경우 10프레임의 세번째 구는 해당 배열에서 랜덤
				if(player[playerNum].level.equals("상급자")) {		
					int q = (int)(Math.random()*senior.length);
					player[playerNum].frame[10].first = senior[q];
				}
				if(player[playerNum].level.equals("중급자")) {	
					int q = (int)(Math.random()*intermediate.length);
					player[playerNum].frame[10].first = intermediate[q];
				}
				if(player[playerNum].level.equals("초보자")) {	
					int q = (int)(Math.random()*beginer.length);
					player[playerNum].frame[10].first = beginer[q];
				}
			}
			else if (frame.isStrike()) {	//스페어인 경우 10프레임의 두번째 구는 해당 배열에서 랜덤
				if(player[playerNum].level.equals("상급자")) {	
					int q = (int)(Math.random()*senior.length);
					frame.second = senior[q];
					if(frame.second!=10) {	//두번째 구가 스트라이크가 아니면, 세번째구의 점수는 (10-두번째 구)
						player[playerNum].frame[10].first=(int)(Math.random()*(10-frame.second)+1);
					}
					else if(frame.second==10) {		//두번째 구도 스트라이크인 경우, 세번째 구는 랜덤
						int q1 = (int)(Math.random()*senior.length);
						player[playerNum].frame[10].first = senior[q1];
					}			
				}
				if(player[playerNum].level.equals("중급자")) {	
					int q = (int)(Math.random()*intermediate.length);
					frame.second = intermediate[q];
					if(frame.second!=10) {	//두번째 구가 스트라이크가 아니면, 세번째구의 점수는 (10-두번째 구)
						player[playerNum].frame[10].first=(int)(Math.random()*(10-frame.second)+1);
					}
					else if(frame.second==10) {	//두번째 구도 스트라이크인 경우, 세번째 구는 랜덤
						int q1 = (int)(Math.random()*intermediate.length);
						player[playerNum].frame[10].first = intermediate[q1];
					}			
				}
				if(player[playerNum].level.equals("초보자")) {	
					int q = (int)(Math.random()*beginer.length);
					frame.second = beginer[q];
					if(frame.second!=10) {	//두번째 구가 스트라이크가 아니면, 세번째구의 점수는 (10-두번째 구)
						player[playerNum].frame[10].first=(int)(Math.random()*(10-frame.second)+1);
					}
					else if(frame.second==10) {	//두번째 구도 스트라이크인 경우, 세번째 구는 랜덤
						int q1 = (int)(Math.random()*beginer.length);
						player[playerNum].frame[10].first = beginer[q1];
					}			
				}
			}
		}
	}
}
