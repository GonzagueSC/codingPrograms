import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OnePlayerRound extends JPanel{
	JPanel t;
	JLabel[][] TicTacBoard = new JLabel[3][3];
	String[][] ComputerBoard = new String[3][3];
	JButton[][] ButtonChart = new JButton[3][3];
	boolean CompStart = ((int)(Math.round(Math.random())))==1;
	boolean CanPlay = true;
	public OnePlayerRound() {
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				ComputerBoard[i][j] = "N";
			}
		}
		t = this;
		this.setBounds(0, 0, 800, 800);
		this.setVisible(true);
		this.setBackground(new Color(152, 185, 243));
		this.repaint();
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				JButton b = new JButton("");
				b.setBounds(147+167*i, 147+167*j, 172, 172);
				b.setOpaque(false);
				b.setContentAreaFilled(false);
				b.setBorderPainted(false);
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (CanPlay) {
							JLabel l = new JLabel("X", SwingConstants.CENTER);
							l.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
							l.setBounds(b.getX(), b.getY(), 172, 172);
							TicTacBoard[(l.getX()-147)/167][(l.getY()-147)/167] = l;
							ComputerBoard[(l.getX()-147)/167][(l.getY()-147)/167] = "X";
							System.out.println("     " + ComputerBoard[0][0] + "     "+ ComputerBoard[0][1] + "     "+ ComputerBoard[0][2] + "     ");
							System.out.println("     " + ComputerBoard[1][0] + "     "+ ComputerBoard[1][1] + "     "+ ComputerBoard[1][2] + "     ");
							System.out.println("     " + ComputerBoard[2][0] + "     "+ ComputerBoard[2][1] + "     "+ ComputerBoard[2][2] + "     ");
							System.out.println();
							System.out.println();
							t.add(l);
							t.repaint();
							t.remove(b);
							CanPlay = false;
							if (!checkMatch().equals("Match Goes On")) {
								JButton n = new JButton("Continue");
								n.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
								n.setBounds(300, 600, 200, 100);
								n.setOpaque(false);
								n.setContentAreaFilled(false);
								n.setBorderPainted(false);
								n.setBackground(Color.gray);
								n.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										Main.PlayerWon(checkMatch(), "OnePlayer");
										
									}
									
								});
								t.add(n);
							} else {
								CompPlay();
								CanPlay = true;
								t.repaint();
								//System.out.println(checkMatch());
								if (!checkMatch().equals("Match Goes On")) {
									JButton n = new JButton("Continue");
									n.setFont(new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 20));
									n.setBounds(300, 650, 150, 80);
									n.setOpaque(false);
									n.setContentAreaFilled(false);
									n.setBorderPainted(false);
									n.setBackground(Color.gray);
									n.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											Main.PlayerWon(checkMatch(), "OnePlayer");
											
										}
										
									});
									t.add(n);
								}
							}
						}
					}
					
				});
				this.add(b);
				ButtonChart[i][j] = b;
			}
		}
		if (CompStart) {
			CompPlayStart();
			t.repaint();
			t.repaint();
			
			//System.out.println(checkMatch());
			if (!checkMatch().equals("Match Goes On")) {
				Main.PlayerWon(checkMatch(), "OnePlayer");
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
	public void CompPlay() {
		int X = -1;
		int Y = -1;
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					if (ComputerBoard[i][j].equals("O")) {
						if (ComputerBoard[0][0].equals("O") && ComputerBoard[2][2].equals("N")) {
							X = 2;
							Y = 2;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 138");
							return;
						}
						if (ComputerBoard[0][2].equals("O") && ComputerBoard[2][0].equals("N")) {
							X = 2;
							Y = 0;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 145");
							return;
						}
						if (ComputerBoard[2][2].equals("O") && ComputerBoard[0][0].equals("N")) {
							X = 0;
							Y = 0;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 152");
							return;
						}
						if (ComputerBoard[2][0].equals("O") && ComputerBoard[0][2].equals("N")) {
							X = 0;
							Y = 2;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 159");
							return;
						}
					}
				}
				if ((i == 0 || i == 2) && (j == 0 || j == 2)) {
					if (ComputerBoard[i][j].equals("O") && ComputerBoard[(i+2)%4][(j+2)%4].equals("O") && ComputerBoard[1][1].equals("N")) {
						X = 1;
						Y = 1;
						if (ComputerBoard[X][Y].equals("N")) build(X,Y);
						else System.err.println("Error on line 169");
						return;
					}
				}
				if (ComputerBoard[i][j].equals("O")) {
					if (i > 0) {
						if (ComputerBoard[i-1][j].equals("O") && ComputerBoard[2][j].equals("N")) {
							if (i == 1) {
								X = 2;
								Y = j;
								if (ComputerBoard[X][Y].equals("N")) build(X,Y);
								else System.err.println("Error on line 180");
								return;
							} else if (ComputerBoard[0][j].equals("N")) {
								X = 0;
								Y = j;
								if (ComputerBoard[X][Y].equals("N")) build(X,Y);
								else System.err.println("Error on line 186");
								return;
							}
						}
					} else {
						if (ComputerBoard[i+1][j].equals("O")  && ComputerBoard[2][j].equals("N")) {
							X = 2;
							Y = j;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 195");
							return;
						}
						if (ComputerBoard[i+2][j].equals("O")  && ComputerBoard[1][j].equals("N")) {
							X = 1;
							Y = j;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 202");
							return;
						}
					}
					if (j > 0) {
						if (ComputerBoard[i][j-1].equals("O")) {
							if (i == 1 && ComputerBoard[i][2].equals("N")) {
								X = i;
								Y = 2;
								if (ComputerBoard[X][Y].equals("N")) build(X,Y);
								else System.err.println("Error on line 212");
								return;
							} else if (ComputerBoard[i][0].equals("N")) {
								X = i;
								Y = 0;
								if (ComputerBoard[X][Y].equals("N")) build(X,Y);
								else System.err.println("Error on line 218");
								return;
							}
						}
					}else {
						if (ComputerBoard[i][j+1].equals("O") && ComputerBoard[i][2].equals("N")) {
							X = i;
							Y = 2;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 227");
							return;
						}
						if (ComputerBoard[i][j+2].equals("O") && ComputerBoard[i][1].equals("N")) {
							X = i;
							Y = 1;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 234");
							return;
						}
					}
				}
			}
		}
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					if (ComputerBoard[i][j].equals("X")) {
						if (ComputerBoard[0][0].equals("X") && !ComputerBoard[2][2].equals("O")) {
							X = 2;
							Y = 2;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 249");
							return;
						}
						if (ComputerBoard[0][2].equals("X") && !ComputerBoard[2][0].equals("O")) {
							X = 2;
							Y = 0;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 256");
							return;
						}
						if (ComputerBoard[2][2].equals("X") && !ComputerBoard[0][0].equals("O")) {
							X = 0;
							Y = 0;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 263");
							return;
						}
						if (ComputerBoard[2][0].equals("X")  && !ComputerBoard[0][2].equals("O")) {
							X = 0;
							Y = 2;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 270");
							return;
						}
					}
				}
				if ((i == 0 || i == 2) && (j == 0 || j == 2)) {
					if (ComputerBoard[i][j].equals("X") && ComputerBoard[(i+2)%4][(j+2)%4].equals("X") && ComputerBoard[1][1].equals("N")) {
						X = 1;
						Y = 1;
						if (ComputerBoard[X][Y].equals("N")) build(X,Y);
						else System.err.println("Error on line 280");
						return;
					}
				}
				if (ComputerBoard[i][j].equals("X")) {
					if (i > 0) {
						if (ComputerBoard[i-1][j].equals("X")  && !ComputerBoard[2][j].equals("O")) {
							if (i == 1) {
								X = 2;
								Y = j;
								if (ComputerBoard[X][Y].equals("N")) build(X,Y);
								else System.err.println("Error on line 291");
								return;
							} else if (!ComputerBoard[0][j].equals("O")) {
								X = 0;
								Y = j;
								if (ComputerBoard[X][Y].equals("N")) build(X,Y);
								else System.err.println("Error on line 297");
								return;
							}
						}
					} else {
						if (ComputerBoard[i+1][j].equals("X")  && !ComputerBoard[2][j].equals("O")) {
							X = 2;
							Y = j;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 306");
							return;
						}
						if (ComputerBoard[i+2][j].equals("X") && !ComputerBoard[1][j].equals("O")) {
							X = 1;
							Y = j;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 313");
							return;
						}
					}
					if (j > 0) {
						if (ComputerBoard[i][j-1].equals("X") && !ComputerBoard[i][2].equals("O") && !ComputerBoard[i][2].equals("X")) {
							if (i == 1) {
								X = i;
								Y = 2;
								if (ComputerBoard[X][Y].equals("N")) build(X,Y);
								else System.err.println("Error on line 323");
								return;
							} else if (!ComputerBoard[i][0].equals("O")){
								X = i;
								Y = 0;
								if (ComputerBoard[X][Y].equals("N")) build(X,Y);
								else System.err.println("Error on line 329");
								return;
							}
						}
					}else {
						if (ComputerBoard[i][j+1].equals("X") && !ComputerBoard[i][2].equals("O")) {
							X = i;
							Y = 2;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 338");
							return;
						}
						if (ComputerBoard[i][j+2].equals("X") && !ComputerBoard[i][1].equals("O")) {
							X = i;
							Y = 1;
							if (ComputerBoard[X][Y].equals("N")) build(X,Y);
							else System.err.println("Error on line 345");
							return;
						}
					}
				}
			}
		}
		int Os = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (ComputerBoard[i][j].equals("O")) {
					Os++;
				}
			}
		}
		if (Os <= 1) {
			int n = 9;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (checkAround(i,j) < n && ComputerBoard[i][j].equals("N")) {
						X = i;
						Y = j;
						n = checkAround(i,j);
					}
				}
			}
			if (ComputerBoard[X][Y].equals("N")) build(X,Y);
			else System.err.println("Error on line 372");
			return;
		} else {
			int n = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (checkAroundFor(i,j, "O") > n && ComputerBoard[i][j].equals("N")) {
						X = i;
						Y = j;
						n = checkAroundFor(i,j,"O");
					}
				}
			}
		}
		ComputerBoard[X][Y] = "O";
		JLabel l = new JLabel("O", SwingConstants.CENTER);
		l.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		l.setBounds(147+X*167, 147+Y*167, 172, 172);
		TicTacBoard[X][Y] = l;
		t.add(l);
		t.remove(ButtonChart[X][Y]);
	}
	public String checkMatch() {
		for (int i = 0; i < 3; i ++) { 
			if (!ComputerBoard[i][0].equals("N") && !ComputerBoard[i][1].equals("N") && !ComputerBoard[i][2].equals("N")) {
				//System.out.println("column not empty");
				if (ComputerBoard[i][0].equals(ComputerBoard[i][1]) && ComputerBoard[i][1].equals(ComputerBoard[i][2])) {
					return ComputerBoard[i][0].equals("X")?"You Won!!!":"You Lost!!!";
				}
			}
			if (!ComputerBoard[0][i].equals("N") && !ComputerBoard[1][i].equals("N") && !ComputerBoard[2][i].equals("N")) {
				//System.out.println("row not empty");
				if (ComputerBoard[0][i].equals(ComputerBoard[1][i]) && ComputerBoard[1][i].equals(ComputerBoard[2][i])) {
					return ComputerBoard[0][i].equals("X")?"You Won!!!":"You Lost!!!";
				}
			}
			if (!ComputerBoard[0][0].equals("N") && !ComputerBoard[1][1].equals("N") && !ComputerBoard[2][2].equals("N")) {
				if (ComputerBoard[0][0].equals(ComputerBoard[1][1]) && ComputerBoard[1][1].equals(ComputerBoard[2][2])) {
					return ComputerBoard[0][0].equals("X")?"You Won!!!":"You Lost!!!";
				}
			}
			if (!ComputerBoard[0][2].equals("N") && !ComputerBoard[1][1].equals("N") && !ComputerBoard[2][0].equals("N")) {
				if (ComputerBoard[0][2].equals(ComputerBoard[1][1]) && ComputerBoard[1][1].equals(ComputerBoard[2][0])) {
					return ComputerBoard[0][2].equals("X")?"You Won!!!":"You Lost!!!";
				}
			}
		}
		int OpenBoxes = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (ComputerBoard[i][j].equals("N")) {
					OpenBoxes++;
				}
			}
		}
		return OpenBoxes == 0? "Draw!!!": "Match Goes On";
	}
	public boolean isSame(JLabel l1, JLabel l2) {
		return l1.getText().equals(l2);
	}
	public boolean isSame (JLabel l1, String s) {
		return l1.getText().equals(s);
	}
	public int checkAround(int i, int j) {
		int n = 0;
		for (int o = 0; o < 3; o++) {
			if (!ComputerBoard[o][j].equals("N") && o != i && ComputerBoard[i][j].equals("N")) {
				n++;
			}
			if (!ComputerBoard[i][o].equals("N") && o != j && ComputerBoard[i][j].equals("N")) {
				n++;
			}
			if (i == 0 && j == 0) {
				if (!ComputerBoard[o][o].equals("N") && o != j && ComputerBoard[i][j].equals("N")) {
					n++;
				}
			}
			if (i == 2 && j == 2) {
				if (!ComputerBoard[2-o][2-o].equals("N") && 2-o != j && ComputerBoard[i][j].equals("N")) {
					n++;
				}
			}
			if (i == 2 && j == 0) {
				if (!ComputerBoard[2-o][o].equals("N") && o != j && (2-o) != i && ComputerBoard[i][j].equals("N")) {
					n++;
				}
			}
			if (i == 0 && j == 2) {
				if (!ComputerBoard[o][2-o].equals("N") && 2-o != j && (o) != i && ComputerBoard[i][j].equals("N")) {
					n++;
				}
			}
		}
		return n;
	}
	public int checkAroundFor(int i, int j, String spec) {
		int n = 0;
		for (int o = 0; o < 3; o++) {
			if (ComputerBoard[o][j].equals(spec) && o != i) {
				n++;
			}
			if (ComputerBoard[i][o].equals(spec) && o != j) {
				n++;
			}
			if (i == 0 && j == 0) {
				if (ComputerBoard[o][o].equals(spec) && o != j) {
					n++;
				}
			}
			if (i == 2 && j == 2) {
				if (ComputerBoard[2-o][2-o].equals(spec) && 2-o != j) {
					n++;
				}
			}
			if (i == 2 && j == 0) {
				if (ComputerBoard[2-o][o].equals(spec) && o != j && (2-o) != i) {
					n++;
				}
			}
			if (i == 0 && j == 2) {
				if (ComputerBoard[o][2-o].equals(spec) && 2-o != j && (o) != i) {
					n++;
				}
			}
		}
		return n;
	}
	public void build(int X, int Y) {
		ComputerBoard[X][Y] = "O";
		JLabel l = new JLabel("O", SwingConstants.CENTER);
		l.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		l.setBounds(147+X*167, 147+Y*167, 172, 172);
		TicTacBoard[X][Y] = l;
		t.add(l);
		t.remove(ButtonChart[X][Y]);
		System.out.println("     " + ComputerBoard[0][0] + "     "+ ComputerBoard[0][1] + "     "+ ComputerBoard[0][2] + "     ");
		System.out.println("     " + ComputerBoard[1][0] + "     "+ ComputerBoard[1][1] + "     "+ ComputerBoard[1][2] + "     ");
		System.out.println("     " + ComputerBoard[2][0] + "     "+ ComputerBoard[2][1] + "     "+ ComputerBoard[2][2] + "     ");
		System.out.println();
		System.out.println();
	}
	public void CompPlayStart() {
		int X = (int)(Math.round(Math.random()*2));
		int Y = (int)(Math.round(Math.random()*2));
		if (ComputerBoard[X][Y].equals("N")) build(X,Y);
		return;
	}
}
