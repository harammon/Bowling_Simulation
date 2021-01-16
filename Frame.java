/*
 * 파일명 : Frame.java
 * 최종작성일 : 2020년 6월 12일
 * 작성자 : 20142611 이하람
 */
public class Frame {
	public int first;
	public int second;
	public boolean isOpen() {		//오픈 판별 함수
		if (first + second < 10)
			return true;
		else
			return false;
	}
	public boolean isSpare() {		//스페어 판별 함수
		if (first + second == 10 && first != 10) 	
			return true;
		else
			return false;
	}
	public boolean isStrike() {		//스트라이크 판별 함수
		if (first == 10)
			return true;
		else
			return false;
	}	
}