package main;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * 플레이어 정보 객체
 * 
 * @author 조경혜
 *
 */
public class Player extends JLabel {

	/**
	 * 플레이어의 이미지 구현
	 */
	private ImageIcon icPlayer;
	
	/**
	 * 플레이어의 그래픽상의 좌표</br>
	 */
	private int playerX;
	private int playerY;
	
	/**
	 * 플레이어가 움직일 좌표</br>
	 */
	private int destX;
	private int destY;
	
	/**
	 * 플레이어가 있는 타일의 번호
	 */
	private int position;
	
	/**
	 * 플레이어의 자산
	 */
	private int money;
	
	/**
	 * 플레이어의 상태</br>
	 * 0 : 무인도에 갇힘</br>
	 * 1 : 게임 진행 가능
	 */
	int playerState = 1; // 플레이어 상태 [0:무인도, 1:게임 가능]
	
	/**
	 * 게임 정보를 불러올 게임 객체 생성
	 */
	private static Game parentGame ;
	
	/**
	 * 타일정보를 불러올 타일 객체 생성
	 */
	Tile tile = new Tile();
	
//	/**
//	 * 게임 정보를 불러올 게임 객체 생성
//	 */
//	private Game game;
	
	/**
	 * 사운드Method를 불러올 객체 생성 
	 */
	private Login login;
	
	/**
	 * 플레이어 setting
	 * @param i 플레이어 번호
	 * @param x 플레이어가 움직이기 시작할 x좌표
	 * @param y 플레이어가 움직이기 시작할 y 좌표
	 * @param position 플레이어가 말 위치
	 */

	public Player(int i, int x, int y, int position) {

		this.playerX = x;
		this.playerY = y;
		
		icPlayer = new ImageIcon(String.format("img/piece_%d.png", i));
		
		pName(i); // 캐릭터 이름
		setIcon(icPlayer); // 기본이미지(오른쪽)
		setLocation(playerX, playerY); // 시작좌표 설정
		setPosition(position);
		setVisible(true);
		setMoney(100);

	}


//	public Player(int i, int x, int y) {
//		this(i, x, y, 0);
//	}
	
	/**
	 * 자산 증가
	 * @param money 증가할 금액
	 */
	public void plusMoney(int money) {
		this.money += money;
	}
	
	
	/**
	 * 자산 감소
	 * @param money 감소할 금액
	 * @return 자산에서 들어온 금액을 뺀 금액
	 */
	public int minusMoney(int money) {
		this.money -= money;
		return this.money;
	}
	
	/**
	 * 플레이어가 말 위치
	 * @return 플레이어 말 위치
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * 플레이어 움직일 말 위치
	 * @param position 움직일 곳
	 * @return 플레이어 말 위치
	 */
	public int setPosition(int position) {
		return this.position = position;

	}

	
	/**
	 * 플레이어 이미지
	 * @return 플레이어 이미지
	 */
	public ImageIcon getIcPlayer() {
		return icPlayer;
	}
	
	/**
	 * 플레이어 이미지
	 * @param icPlayer 플레이어 이미지
	 */
	public void setIcPlayer(ImageIcon icPlayer) {
		this.icPlayer = icPlayer;
	}
	
	/**
	 * 플레이어의 x좌표
	 * @return 플레이어의 x좌표
	 */
	public int getPlayerX() {
		return playerX;
	}
	
	/**
	 * 플레이어의 x좌표
	 * @param playerX 플레이어의 x좌표
	 */
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	
	/**
	 * 플레이어의 y좌표
	 * @return 플레이어의 y좌표
	 */
	public int getPlayerY() {
		return playerY;
	}
	
	/**
	 * 플레이어의 y좌표
	 * @param playerY 플레이어의 y좌표
	 */
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
	
	/**
	 * 플레이어의 자산
	 * @return 자산
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * 플레이어의 자산
	 * @param money 자산 setting
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	
	
	/**
	 * Game class의 정보 파악하기 위한 Method
	 * @param game
	 */
	public static void setparentGame(Game game) {
		if(Player.parentGame != null) return;
		Player.parentGame = game;
	} 
	
	
	/**
	 * 플레이어가 움직이는 애니메이션 Thread</br>
	 * @param tileNo 플레이어가 가야할 타일의 번호
	 */
	public void moveTo(int tileNo) {
		TileInfo tileInfo = Tile.tileList[tileNo];
		destX = tileInfo.getX();
		destY = tileInfo.getY();
		position = tileNo;
		
		/**
		 * Timer Thread를 이용해서 플레이어를 이동해야할 좌표로 이동시킨다</br>
		 * 목적 좌표에 도달하면 창 띄운후 Thread 종료
		 */
		
		final Timer timer = new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * 이동해야할 좌표와 현재 있는 좌표가 같다면 이동 정지
				 */
				if (destX == playerX && destY == playerY) {
					
					parentGame.showWindow(); // 목적 좌표에 도달하면 창 띄운후 Thread 종료
					((Timer) e.getSource()).stop();
				}
				
				/**
				 * 이동해야할 좌표와 현재 있는 좌표가 다르다면 이동
				 */
				if (playerX < destX) {
					++playerX;
				} else {
					--playerX;
				}

				if (playerY < destY) {
					++playerY;
				} else {
					--playerY;
				}
				setBounds(new Rectangle(playerX, playerY, 40, 40));
				
			}

		});
		timer.start();
		login.playSound("sound/rise_grow.wav", false);

	}
	
	/**
	 * 플레이어 별 이름
	 * @param playerNum 플레이할 인원
	 */
	public void pName(int playerNum) {
		if (playerNum == 0) {
			setName("피카츄");
		} else if (playerNum == 1) {
			setName("푸린");
		} else if (playerNum == 2) {
			setName("파이리");
		} else {
			setName("꼬부기");
		}
	}
}
