import javax.swing.plaf.basic.BasicOptionPaneUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartButtonActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton)(e.getSource());
		if (src.getText().equals("2 Player")) {
			System.out.println("btnClicked");
		}
	}
	
}
