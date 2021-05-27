package main;

/** 
 * 타일의 이름, 좌표, 타일 요금, 타일의 소유주를 저장 하기 위한 객체
 * @author 조경혜
 */


public class TileInfo {
	
	/**
	 * 타일의 이름
	 */
	
	String name;
	
	/**
	 * 타일의 번호
	 */
	
	int tNum;
	
	/**
	 * x좌표	
	 */
	
	int x;
	
	/**
	 * y좌표
	 */
	
	int y;
	
	/**
	 * 타일 요금
	 */
	
	int toll;
	
	/**
	 * 타일 주인 소유여부
	 */
	
	private Player owner = null;
	
	public void buy(Player owner) {
		this.setOwner(owner);
		owner.setMoney(owner.minusMoney(toll));
	}
	
	
	
	/**
	 * 
	 * @param name 타일의 이름
	 * @param tNum 타일의 순서
	 * @param x X좌표
	 * @param y Y좌표
	 * @param toll 타일요금
	 */
	
	
	TileInfo(String name, int tNum, int x, int y, int toll){
		this.name = name;
		this.tNum = tNum;
		this.x = x;
		this.y = y;
		this.toll = toll;
	}
	
	
	/**
	 * owner 값를 반환
	 * @return owner
	 */


	public Player getOwner() {
		return owner;
	}
	
	
	/**
	 * owner에 주어진 값을 대입
	 * @param owner
	 * owner에 대입할 Player 객체
	 */


	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	/**
	 * name 값를 반환
	 * @return name
	 */



	public String getName() {
		return name;
	}

	
	/**
	 * name에 주어진 값을 대입
	 * @param name
	 * name에 대입할 String형 객체
	 */

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * tNum 값를 반환
	 * @return tNum
	 */


	public int gettNum() {
		return tNum;
	}
	
	
	/**
	 * tNum에 주어진 값을 대입
	 * @param tNum
	 * tNum에 대입할 int형 객체
	 */


	public void settNum(int tNum) {
		this.tNum = tNum;
	}
	
	/**
	 * x 값을 반환
	 * @return x
	 */


	public int getX() {
		return x;
	}
	
	/**
	 * x에 주어진 값을 대입
	 * @param x
	 * x에 대입할 int형 객체
	 */


	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * y 값을 반환
	 * @return y
	 */


	public int getY() {
		return y;
	}
	
	
	
	/**
	 * y에 주어진 값을 대입
	 * @param x
	 * y에 대입할 int형 객체
	 */
	


	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * toll 값을 반환
	 * @return toll
	 */


	public int getToll() {
		return toll;
	}
	
	/**
	 * toll에 주어진 값을 대입
	 * @param toll
	 * toll에 대입할 int형 객체
	 */


	public void setToll(int toll) {
		this.toll = toll;
	}
	
	
}

