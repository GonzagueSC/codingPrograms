import javax.swing.*;

public class Main {
	static JPanel mainPanel;
	static JFrame mainFrame;
	public static void main(String[] args) {
		mainPanel = new Panel();
		mainFrame = new Frame();
		((Frame) mainFrame).changePanel(mainPanel);
	}
	public static void TwoPlayer() {
		mainFrame.remove(mainPanel);
		mainPanel = new TwoPlayerRound();
		((Frame) mainFrame).changePanel(mainPanel);
		((Frame) mainFrame).removeCurr();
		mainPanel.repaint();
	}
	public static void OnePlayer() {
		mainFrame.remove(mainPanel);
		mainPanel = new OnePlayerRound();
		((Frame) mainFrame).changePanel(mainPanel);
		((Frame) mainFrame).removeCurr();
		mainPanel.repaint();
	}
	public static void PlayerWon(String t, String rem) {
		mainFrame.remove(mainPanel);
		mainPanel = new PlayerWonPage(t, rem);
		((Frame) mainFrame).changePanel(mainPanel);
		((Frame) mainFrame).removeCurr();
		mainPanel.repaint();
	}
	public static void MainMenu() {
		mainFrame.remove(mainPanel);
		mainPanel = new Panel();
		((Frame) mainFrame).changePanel(mainPanel);
		((Frame) mainFrame).removeCurr();
		mainPanel.repaint();
	}
}
