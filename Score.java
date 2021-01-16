/*
 * 파일명 : Score.java
 * 최종작성일 : 2020년 6월 12일
 * 작성자 : 20142611 이하람
 */
public class Score {
	public int Index = 0;
	public int sum = 0;
	public void calcScore(Frame [] frame, int [] accumScore, int frameNum) {
		while (Index <= frameNum) {
			int basic = addBasic(frame[Index]);
			int bonus = 0;
			if (frame[Index].isStrike())	//스트라이크인 경우 다음 프레임의 두 구를 보너스로 얻음
				bonus = strikeBonus(frame, Index, frameNum);
			if (frame[Index].isSpare())		//스페어인 경우 다음 프레임의 첫 구를 보너스로 얻음
				bonus = spareBonus(frame, Index, frameNum);
			if (bonus == -1)
				return;
			sum += basic + bonus;
			accumScore[Index] = sum;
			Index++;
		}
	}
	private int addBasic(Frame frame) {	//기본적으로 해당 프레임의 초구와 두번째 구의 합 구하기 
		return frame.first + frame.second;
	}
	private int strikeBonus(Frame [] frame, int Index, int frameNum) {	//해당 프레임이 스트라이크인 경우 호출
		if (Index < 9 && Index + 1 > frameNum)
			return -1;
		if (Index < 8 && Index + 2 > frameNum)	
			return -1;
		if (!frame[Index + 1].isStrike())	//다음 프레임이 스트라이크가 아닌 경우
			return addBasic(frame[Index + 1]);
		if (Index == 9)	//마지막 프레임인 경우
			return 10 + frame[Index + 1].second;
		return 10 + frame[Index + 2].first;
	}
	private int spareBonus(Frame [] frame, int Index, int frameNum) {	//해당 프레임이 스페어인 경우 호출
		if (Index < 9 && Index + 1 > frameNum)
			return -1;
		return frame[Index + 1].first;
	}
}