/*
 * 파일명 : Player.java
 * 최종작성일 : 2020년 6월 12일
 * 작성자 : 20142611 이하람
 */
public class Player {
	public Frame [] frame = new Frame[11];	//보너스 투구까지 고려할 프레임 배열
	public int [] accumScore = new int[11];	//각 플레이어의 점수 합계를 담을 배열
	public Score score = new Score();
	public String level;	//플레이어의 수준(초보자 / 중급자 / 상급자)를 기록할 변수
	public String name;	
	public Player() {
		name = "";
		level = "";
		for (int i = 0; i < 11; i++) {
			frame[i] = new Frame();
			accumScore[i] = -1;	//scoreboard를 효율적으로 만들기 위해 초기값-1. 
		}
	}
	public void calcScore(int frameNum) {
		score.calcScore(frame, accumScore, frameNum);
	}
}