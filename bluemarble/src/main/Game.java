package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 * 게임구동하는 메인 페이지
 * @author 조경혜
 *
 */



public class Game extends JPanel {
	/**
	 * 이미지 저장할 변수
	 */
	private BufferedImage img;
	private BufferedImage diceImage;
	
	/**
	 * 주사위 라벨과 주사위를 컨트롤할 버튼
	 */
	private JLabel dice;
	private JButton throwbtn;
	
	/**
	 * 타일 정보를 가져올 Tile class 선언
	 */
	private static Tile tile = new Tile();
	
	/**
	 * Login에서 받아올 플레이어 인원
	 */
	private int playersNum;
	
	/**
	 * Login에서 받아온 수만큼 플레이어 객체 생성
	 */
	private Player[] players;
	
	/**
	 * 주사위 눈금
	 */
	private static int num = 0;
	
	/**
	 * 현재 플레이어가 있는 좌표
	 */
	private static int[] coordinate; 
	
	/**
	 * 현재 게임을 진행할 플레이어
	 */
	private static int turn = 0;
	private int now = 0;

	/**
	 * 주사위의 상태
	 */
	private static final int PAUSE = 0;
	private static final int RUN = 1;
	private int diceStatus = PAUSE;

	/**
	 * 플레이어의 정보를 가지고 있는 라벨
	 */
	private JLabel[] infoPanels;
	private ImageIcon imageIcon;
	private JLabel imgLabel;
	private JLabel infoLabel;
	private JLabel moneyLabel;
	private JPanel infoPanel;
	private JLabel panel;

	private String message = "";
	
	/**
	 * 플레이어가 움직일 좌표
	 */
	private int position;
	
	/**
	 * 플레이어의 보유금액
	 */
	private int playerMoney;
	

	private Login login;
	
	

	public Player getPlayerByIndex(int idx) {
		return players[idx];
	}
	

	/**
	 * Game Layout setting
	 * @param playersNum 플레이할 인원수를 받아옴
	 */

	public Game(int playersNum) {
		
		// 레이아웃 설정
		super(true);
		this.playersNum = playersNum;

		JOptionPane.showMessageDialog(null, "게임을 시작합니다! \n  	GoodLuck!");
		
		/**
		 * 플레이어 객체에게 Game정보를 보냄
		 */
		Player.setparentGame(this);

		setSize(900, 600);
		this.setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 10, 600, 600);
		layeredPane.setLayout(null);
		
		

		/**
		 * 선택한 플레이어 인원수 만큼 플레이어객체, 상태창을 만들 배열 생성 
		 */

		players = new Player[playersNum];
		infoPanels = new JLabel[playersNum];
		coordinate = new int[playersNum];

		
		for (int i = 0; i < playersNum; i++) {
			
			/**
			 * 인원수 만큼 플레이어 객체 생성</br>
			 * new Player(플레이어번호, 플레이어가 움직이기 시작할 x좌표, y좌표, 플레이어의 말 위치)
			 */
			players[i]= new Player(i, tile.tileList[coordinate[i]].x, tile.tileList[coordinate[i]].y, coordinate[i]);
			
			
			/**
			 * 플레이어 정보창 setting
			 */
			infoPanels[i] = new JLabel();
			
			layeredPane.add(players[i]);
			
			
			/**
			 * 플레이어 이미지 setting
			 */
			imageIcon = new ImageIcon("img/piece_" + i + ".png"); // 이미지 불러오기
			Image image = imageIcon.getImage(); // 이미지 세팅
			Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // 라벨 크기에 맞춰서 세팅
			imageIcon = new ImageIcon(newimg); // 이미지 되돌리기

			imgLabel = new JLabel();
			imgLabel.setPreferredSize(new Dimension(100, 100));
			imgLabel.setIcon(imageIcon);
			imgLabel.setBorder(new LineBorder(new Color(254, 246, 213)));
			imgLabel.setOpaque(true);

			
			
			/**
			 * 플레이어 이름 setting
			 */
			infoLabel = new JLabel();
			infoLabel.setBackground(Color.WHITE);
			infoLabel.setFont(new Font("BusanFont_Provisional", Font.BOLD, 17));
			infoLabel.setText("    " +(i+1) + " P  : " + players[i].getName());
			infoLabel.setOpaque(true);

			
			/**
			 * 플레이어 자산 setting
			 */
			playerMoney = players[i].getMoney();
			moneyLabel = new JLabel();
			moneyLabel.setBackground(Color.GREEN);
			moneyLabel.setFont(new Font("BusanFont_Provisional", Font.BOLD, 14));
			moneyLabel.setText("     자 산 : " + playerMoney + " 만원");
			moneyLabel.setOpaque(true);
			
			
			/**
			 * 플레이어의 이름, 자산 합친 Panel
			 */
			infoPanel = new JPanel();
			infoPanel.setPreferredSize(new Dimension(210, 100));
			infoPanel.setLayout(new GridLayout(2, 1));
			infoPanel.add(infoLabel);
			infoPanel.add(moneyLabel);

			
			/**
			 * 이미지, 이름, 자산 합친 Panel
			 */
			panel = infoPanels[i];
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(572, i * 100, 310, 100);
			panel.setLayout(new BorderLayout());
			panel.add(imgLabel, BorderLayout.WEST);
			panel.add(infoPanel, BorderLayout.CENTER);
			add(panel);

		}

		/**
		 * 배경
		 */
		try {
			img = ImageIO.read(new File("img/gamemap_re2.png"));
		} catch (IOException e) {
			System.exit(0);
		}

		/**
		 * 주사위 이미지 setting
		 */
		try {
			diceImage = ImageIO.read(new File("img/dice_1.png"));
		} catch (IOException e) {
			System.exit(0);
		}
		dice = new JLabel();
		dice.setBounds(215, 105, 130, 120);
		dice.setLayout(null);
		dice.setIcon(new ImageIcon(diceImage));

		/**
		 * 던지기 버튼 setting
		 */
		throwbtn = new JButton("던지기");
		throwbtn.setBounds(240, 220, 90, 35);
		throwbtn.addActionListener(throwListener);
		throwbtn.setFocusPainted(false);
		throwbtn.setContentAreaFilled(false);
		throwbtn.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));

		layeredPane.add(dice);
		layeredPane.add(throwbtn);

		add(layeredPane, BorderLayout.CENTER);
		JFrame frame = new JFrame("신나는 부루마블");
		frame.setLayout(null);
		frame.setBounds(0, 0, 900, 600);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	/**
	 * 주사위 던졌을때의 액션리스너
	 */

	private ActionListener throwListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			/**
			 * 플레이어의 상태가 게임을 진행할 수 있는지 판별
			 */
			switch (players[turn].playerState) {
			case 0: {
				JOptionPane.showMessageDialog(dice, "무인도에 갇혔으므로 이번턴은 쉽니다!");
				players[turn].playerState = 1;
				turn = ++turn % playersNum;
				break;
			}
			case 1: {
				
				/**
				 * 주사위 애니메이션 Timer Thread를 조절함</br>
				 * 주사위의 상태에 따른 버튼 텍스트 삽입
				 */
				switch (diceStatus) {
				case PAUSE:

					timer.start();
					login.playSound("sound/dice_sound.wav", false);
					throwbtn.setText("스탑!");
					diceStatus = RUN;

					break;

				case RUN:

					timer.stop();
					throwbtn.setText("던지기!");

					playerSet(turn);
				
					turn = ++turn % playersNum;
					diceStatus = PAUSE;
					break;

				}

			}
			}
		}

	};

	
	/**
	 * 주사위 애니메이션 Timer Thread </br>
	 * 주사위의 랜덤한 숫자 출력
	 * 
	 */
	private Timer timer = new Timer(5, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			num = (int) ((Math.random() * 6) + 1);	
//			num =2;
			ImageIcon icon = new ImageIcon(String.format("img/dice_%d.png", num));
			dice.setIcon(icon);
			

		}
	});
	
	
	/**
	 * 게임판, 플레이어 말 이미지 생성
	 */

	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage(img, 0, 0, this); // see javadoc for more info on the parameters
		for (Player playerimg : players) {
			g.drawImage(playerimg.getIcPlayer().getImage(), playerimg.getX(), playerimg.getY(), 38, 38, null);
		}

	}
	
	
	/**
	 * 플레이어 setting</br>
	 * @param i
	 */

	private void playerSet(int i) {
		this.now = i;

		Player player = players[i];
		String message = "";
		
		switch (player.playerState) {
		
		/**
		 * 플레이어가 무인도에 갇혀있는 상황이면 주사위를 던지지 않는다.
		 */
		case 0: {
			break;
		}
		
		/**
		 * 플레이어가 게임 가능한 상태면 주사위를 던져서 나온 수만큼 칸을 움직인다.
		 */
		case 1: {

			position = (player.getPosition() + num) % 24;

			player.setPosition(position);
			player.moveTo(player.getPosition()); // 좌표

		}
		}
	}
	
	
	/**
	 * 플레이어가 도착한 타일의 종류 판별
	 */
	public void showWindow() {

		Player player = players[now];

			if (player.getPosition() == 0 || player.getPosition() == 6 || player.getPosition() == 12 || player.getPosition() == 18) { // 특수지역이니?
				specialTile();

			} else if (player.getPosition() == 3 || player.getPosition() == 9 || player.getPosition() == 15) { // 황금열쇠니?
				boolean b = goldKey();
				if (b && (player.getPosition() == 0 || player.getPosition() == 6 || player.getPosition() == 12 || player.getPosition() == 18)) { // 특수지역이니?
					specialTile();
				}
			} else if (Tile.tileList[player.getPosition()].getOwner() == null) { // 비어있는 땅이니?
				emptyTile();

			} else if (!Tile.tileList[player.getPosition()].getOwner().equals(players[now])) { // 다른사람 땅이니?
				otherTile();

			} else if (Tile.tileList[player.getPosition()].getOwner().equals(players[now])) { // 내땅이니?
				myTile();
			}
			
			/**
			 * 파산하는 플레이어가 나온다면 게임 종료
			 */
			gameEnd(player);
			
		}
	
	/**
	 * 플레이어가 도착한 타일이 특수지역 타일이라면 해야할 행동
	 */

	public void specialTile() { // 특수지역이니?
		
		Player player = players[now];

		if (player.getPosition() == 0) {
			login.playSound("sound/positive.wav", false);
			message = "월급을 받습니다!";
			System.out.println(players[now].getName() + " 출발점 ");
			players[now].plusMoney(10);
		} else if (player.getPosition() == 6) {
			login.playSound("sound/negative.wav", false);
			message = "무인도에 갇혔습니다ㅠㅠ \n다음턴은 쉽니다";
			System.out.println(players[now].getName() + " 무인도 ");
			players[now].playerState = 0;
		} else if (player.getPosition() == 12) {
			login.playSound("sound/positive.wav", false);
			message = "복권 당첨!! \n(축하금 20만원)";
			System.out.println(players[now].getName() + " 복권 ");
			players[now].plusMoney(20);
		} else if (player.getPosition() == 18) {
			login.playSound("sound/negative.wav", false);
			message = "병원 도착! 건강검진을 받으세요! \n(비용 20만원)";
			System.out.println(players[now].getName() + " 병원 ");
			players[now].minusMoney(20);
		}
		

		JOptionPane.showMessageDialog(null, message);
		changeMoney();
	}

	
	/**
	 * 플레이어가 도착한 타일이 주인이 없는 타일이라면 해야할 행동
	 */
	public void emptyTile() { // 비어있는 땅이니?
		Player player = players[now];

		if (players[now].getMoney() < Tile.tileList[player.getPosition()].toll) {
			JOptionPane.showMessageDialog(null, "돈이 부족해서 땅을 구매할 수 없습니다!");
			System.out.println(players[now].getName() + " 돈부족 ");
		} else {
			new PurchaseWindow(position, now, Tile.tileList[player.getPosition()], this);
			System.out.println(players[now].getName() + " 땅삼 ");
		}
		changeMoney();
	}
	
	
	/**
	 * 플레이어가 도착한 타일에 주인이 타일이라면 해야할 행동
	 */
	public void otherTile() { // 다른사람 땅이니?
		Player player = players[now];
		login.playSound("sound/negative.wav", false);
		message = Tile.tileList[player.getPosition()].getOwner().getName() + "의 땅입니다! \n통행료 " + Tile.tileList[player.getPosition()].toll
				+ "만원을 지불하세요";

		players[now].minusMoney(Tile.tileList[player.getPosition()].toll);
		Tile.tileList[player.getPosition()].getOwner().plusMoney(Tile.tileList[player.getPosition()].toll);
		System.out.println(player.getName() + " 이 " + Tile.tileList[player.getPosition()].getOwner().getName() + " 에게 통행료 납부");
		JOptionPane.showMessageDialog(null, message);
		changeMoney();
	}
	
	
	/**
	 * 플레이어가 도착한 타일이 자신의 타일이라면 해야할 행동
	 */
	public void myTile() { // 내땅이니?
		Player player = players[now];
		login.playSound("sound/positive.wav", false);
		message = "자신의 땅에 돌아왔습니다! 통행료가 2배가 됩니다!";
		Tile.tileList[player.getPosition()].toll = Tile.tileList[player.getPosition()].toll * 2;
		System.out.println(players[now].getName() + "의 땅 두번 밟음");
		JOptionPane.showMessageDialog(null, message);
		
		changeMoney();
	}

	/**
	 * 플레이어가 도착한 타일이 황금열쇠 타일이라면 해야할 행동
	 */
	public boolean goldKey() { // 황금열쇠니?
		login.playSound("sound/bonus.wav", false);
		JOptionPane.showMessageDialog(null, "쨔란 황금열쇠 도착! >_<");
		Player player = players[now];
		int gold = (int) ((Math.random() * 9) + 1);
		boolean b = false;
		switch (gold) {
		case 1: {
			message = "용돈 20만원을 받습니다!";
			login.playSound("sound/positive.wav", false);
			JOptionPane.showMessageDialog(null, message);
			players[now].plusMoney(20);
			b = false;
			break;
		}
		case 2: {
			message = "속도위반! 벌금을 냅니다! (비용 10만원)";
			login.playSound("sound/negative.wav", false);
			JOptionPane.showMessageDialog(null, message);
			players[now].minusMoney(10);
			b = false;
			break;
		}
		case 3: {
			message = "출발지으로 이동합니다!";
			JOptionPane.showMessageDialog(null, message);
			players[now].moveTo(0);
			players[now].setPosition(0);
			break;
		}
		case 4: {
			message = "무인도로 이동합니다!";
			JOptionPane.showMessageDialog(null, message);
			players[now].moveTo(6);
			players[now].setPosition(6);
			break;
		}
		case 5: {
			message = "복권으로 이동합니다!";
			JOptionPane.showMessageDialog(null, message);
			players[now].moveTo(12);
			players[now].setPosition(12);
			break;
		}
		case 6: {
			message = "병원으로 이동합니다!";
			JOptionPane.showMessageDialog(null, message);
			players[now].moveTo(18);
			players[now].setPosition(18);
			break;
		}
		case 7: {
			message = "신촌으로 이동합니다!";
			JOptionPane.showMessageDialog(null, message);
			players[now].setPosition(22);
			players[now].moveTo(22);
			break;
		}
		case 8: {
			message = "뒤로 2칸 이동 합니다!";
			JOptionPane.showMessageDialog(null, message);
			players[now].setPosition((player.getPosition()-1) % 24);
			players[now].moveTo((player.getPosition()-1) % 24);
			b = false;
			break;
		}
		case 9: {

			message = "생일축하합니다! \n다른 플레이어들에게 축하금을 받으세요! (비용 각 5만원)";
			login.playSound("sound/positive.wav", false);
			JOptionPane.showMessageDialog(null, message);
			players[now].plusMoney(5*(playersNum-1));
			
			for (int i = 0; i < playersNum; i++) {
				if (now == i) continue;
				players[i].minusMoney(5);
			}
					
			b = false;
			break;
		}
		}
		changeMoney();
		return b;
	}
	
	/**
	 * 플레이어의 보유금액 업데이트
	 */
	public void changeMoney() {
		for (int k = 0; k < playersNum; ++k) {
			((JLabel) ((JPanel) infoPanels[k].getComponent(1)).getComponent(1)).setText("보유 금액 : " + players[k].getMoney() + " 만원");}
	}
	
	/**
	 * 게임 종료 판별</br>
	 * @param player 현재 플레이어를 받아와 소유 금액 판별 후 0원 이하면 게임 종료
	 */
	public void gameEnd(Player player) {
		if (players[now].getMoney() <= 0) { // 게임 종료
			JOptionPane.showMessageDialog(null, players[now].getName() + "의 파산! \n게임이 종료되었습니다!");
			String endGame = "";
			int max = players[0].getMoney();
			String winner = players[0].getName();
			System.out.println("if 전 max : " +max);
			System.out.println("if 전 winner : " +winner);

			for (int j = 0; j < playersNum; ++j) {

				if (max < players[j].getMoney()) {
					max = players[j].getMoney();
					winner = players[j].getName();
					System.out.println("if 후 max : " +max);
					System.out.println("if 후 winner : " +winner);
				}

				endGame += players[j].getName() + "님의 보유금액 : " + players[j].getMoney() + "만원 \n";
			
			}

			endGame += "\n승자는 " + winner + "입니다!";
			JOptionPane.showMessageDialog(null, endGame);
			System.exit(1);// 게임 종료

		}
	}
	
}
