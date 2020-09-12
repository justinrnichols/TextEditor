import java.awt.Color;

public class ViewFunctions {
	GUI gui;
	
	public ViewFunctions(GUI gui) {
		this.gui = gui;
	}
	
	public void appearance() {
		if(gui.appearance == false) {
			gui.appearance = true;
			gui.window.getContentPane().setBackground(Color.white);
			gui.textPane.setBackground(Color.white);
			gui.textPane.setForeground(Color.black);
			gui.iAppearance.setText("Appearance: LIGHT");
		}
		else if(gui.appearance == true) {
			gui.appearance = false;
			gui.window.getContentPane().setBackground(Color.DARK_GRAY);
			gui.textPane.setBackground(Color.DARK_GRAY);
			gui.textPane.setForeground(Color.white);
			gui.iAppearance.setText("Appearance: DARK");
		}
	}
	
	public void wordCount() {
		String text = gui.textPane.getText();
		int wordCount = 0;
		if(text.length() == 0)
			gui.iWordCount.setText("Word Count: " + wordCount);
		else {
			wordCount = text.split("\\s+").length;
			gui.iWordCount.setText("Word Count: " + wordCount);
		}
	}
	
	public void characterCount() {
		String text = gui.textPane.getText();
		int characterCount = 0;
		if(text.length() == 0)
			gui.iCharacterCount.setText("Character Count: " + characterCount);
		else {
			characterCount = text.length();
			gui.iCharacterCount.setText("Character Count: " + characterCount);
		}
	}
}
