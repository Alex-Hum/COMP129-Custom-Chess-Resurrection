package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesPane extends GraphicsPane implements ActionListener {
private MainApplication program;
	
public RulesPane(MainApplication app) {
		program = app;
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideContents() {
		program.removeAll();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}