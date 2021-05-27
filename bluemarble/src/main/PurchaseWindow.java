package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * 땅에 도착 했을 때 구매창을 띄우기 위한 JFrame
 * @author 조경혜
 */

class PurchaseWindow extends JFrame implements ActionListener {
	private JRadioButton yes, no;
	private JLabel message;
	private JPanel bigPanel, smallPanel;
	private ButtonGroup size;
	private TileInfo tileInfo;
	private Game map;
	private Login login;

	private Player player;
	private int position;


	/**
	 * PurchaseWindow class 제작
	 */
	
	public PurchaseWindow(int position, int playerNum, TileInfo tile, Game root) {
		
		this.map = root;
		this.player = map.getPlayerByIndex(playerNum);
		this.tileInfo = tile;
		this.position = position;
		setSize(300, 300);
		setTitle("구매창");
		setLocationRelativeTo(null);
		
		
		/**
		 * 구매 의사를 물어보기 위한 panel </br>
		 * 메시지를 띄우기 위한 label 	</br>
		 * panel을 추가				
		 */


		bigPanel = new JPanel();
		message = new JLabel(tileInfo.name +"을 구입하시겠습니까?");
		bigPanel.add(message);
		
		
		/**
		 * radiobutton을 추가하기 위한 패널 </br>
		 */
		
		smallPanel = new JPanel();
		yes = new JRadioButton("예");
		no = new JRadioButton("아니오");
		
		
		/**
		 * 패널에 yes, no 버튼을 추가
		 */
		smallPanel.add(yes);
		smallPanel.add(no);
		
		
		/**
		 * 버튼을 누를시 다른 동작 발생
		 */
		yes.addActionListener(this);
		no.addActionListener(this);
		

		/**
		 * 생성한 패널의 위치 설정
		 */
		this.add(bigPanel, BorderLayout.CENTER);
		this.add(smallPanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		
		
	}

	/**
	 * 버튼을 누를 시 동작
	 */

	@Override

	public void actionPerformed(ActionEvent e) {
		
		/**
		 * yes 버튼을 누를시 구매하는 창이뜸 </br>
		 * 타일의 가격만큼 플레이어의 보유 현금이 삭감 </br>
		 * 플레이어의 보유금액 업데이트
		 * 메시지창으로 땅 주인 확인
		 */
		
		if (e.getSource() == yes) {

			JOptionPane.showMessageDialog(null, tileInfo.name + "을(를) 구매하였습니다");
			tileInfo.buy(player);
			map.changeMoney();
			login.playSound("sound/cash.wav", false);
			JOptionPane.showMessageDialog(null, tileInfo.name + "은(는) "+player.getName()+"의 땅입니다.");
			
			
		/**
		 * no 버튼을 누를시 취소 메시지가 나옴  
		 */
		} else if (e.getSource() == no) {
			JOptionPane.showMessageDialog(null, "취소하였습니다");
		}
		
		/**
		 * 창 닫기
		 */
		setVisible(false);
		dispose();
	}	
	
	
}
