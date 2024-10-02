import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TwoPlayerRound extends JPanel{
	static boolean P1 = true;
	JPanel t;
	JLabel[][] TicTacBoard = new JLabel[3][3];
	public TwoPlayerRound() {
		t = this;
		this.setBounds(0, 0, 800, 800);
		this.setVisible(true);
		this.setBackground(new Color(152, 185, 243));
		this.repaint();
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				JButton b = new JButton("");
				b.setBounds(147+167*j, 147+167*i, 172, 172);
				b.setOpaque(false);
				b.setContentAreaFilled(false);
				b.setBorderPainted(false);
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JLabel l = new JLabel(P1?"X":"O", SwingConstants.CENTER);
						l.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
						l.setBounds(b.getX(), b.getY(), 172, 172);
						TicTacBoard[(l.getX()-147)/167][(l.getY()-147)/167] = l;
						t.add(l);
						t.remove(b);
						changePlay();
						System.out.println(checkMatch());
						if (!checkMatch().equals("Match Goes On")) {
							Main.PlayerWon(checkMatch(), "TwoPlayer");
						}
						checkMatch();
						t.repaint();
					}
					
				});
				this.add(b);
			}
		}
	}
	public void paintComponent(Graphics g) 
    { 
		this.setBackground(new Color(152, 185, 243));
        g.fillRect(150,315,500,4); 
        g.fillRect(150,482,500,4);
        g.fillRect(315,150,4,500); 
        g.fillRect(482,150,4,500);
    }
	public void changePlay() {
		P1 = !P1;
		System.out.println(P1?"Player 1 is playing":"Player 2 is playing");
	}
	public String checkMatch() {
		for (int i = 0; i < 3; i ++) { 
			if (TicTacBoard[i][0] != null && TicTacBoard[i][1] != null && TicTacBoard[i][2] != null) {
				System.out.println("column not empty");
				if (TicTacBoard[i][0].getText().equals(TicTacBoard[i][1].getText()) && TicTacBoard[i][1].getText().equals(TicTacBoard[i][2].getText())) {
					return TicTacBoard[i][0].getText().equals("X")?"Player 1 Won!!!":"Player 2 Won!!!";
				}
			}
			if (TicTacBoard[0][i] != null && TicTacBoard[1][i] != null && TicTacBoard[2][i] != null) {
				System.out.println("row not empty");
				if (TicTacBoard[0][i].getText().equals(TicTacBoard[1][i].getText()) && TicTacBoard[1][i].getText().equals(TicTacBoard[2][i].getText())) {
					return TicTacBoard[0][i].getText().equals("X")?"Player 1 Won!!!":"Player 2 Won!!!";
				}
			}
			if (TicTacBoard[0][0] != null && TicTacBoard[1][1] != null && TicTacBoard[2][2] != null) {
				if (TicTacBoard[0][0].getText().equals(TicTacBoard[1][1].getText()) && TicTacBoard[1][1].getText().equals(TicTacBoard[2][2].getText())) {
					return TicTacBoard[0][0].getText().equals("X")?"Player 1 Won!!!":"Player 2 Won!!!";
				}
			}
			if (TicTacBoard[0][2] != null && TicTacBoard[1][1] != null && TicTacBoard[2][0] != null) {
				if (TicTacBoard[0][2].getText().equals(TicTacBoard[1][1].getText()) && TicTacBoard[1][1].getText().equals(TicTacBoard[2][0].getText())) {
					return TicTacBoard[0][2].getText().equals("X")?"Player 1 Won!!!":"Player 2 Won!!!";
				}
			}
		}
		int OpenBoxes = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (TicTacBoard[i][j] == null) {
					OpenBoxes++;
				}
			}
		}
		return OpenBoxes == 0? "Draw!!!": "Match Goes On";
	}
}
