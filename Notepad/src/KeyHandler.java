import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	GUI gui;
	
	public KeyHandler(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F)
			gui.menuFile.doClick();
		if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_E)
			gui.menuEdit.doClick();
		if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_O)
			gui.menuFormat.doClick();
		if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_V) {
			gui.view.wordCount();
			gui.view.characterCount();
			gui.menuView.doClick();
		}
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N)
			gui.file.newFile();
		if(e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_N)
			gui.file.newWindow();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O)
			gui.file.open();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S)
			gui.file.save();
		if(e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_S)
			gui.file.saveAs();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_P)
			gui.file.print();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q)
			gui.file.exit();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z)
			gui.edit.undo();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y)
			gui.edit.redo();
//		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X)
//			gui.iCut.doClick();
//		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C)
//			gui.iCopy.doClick();
//		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V)
//			gui.iPaste.doClick();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B)
			gui.iBold.doClick();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_I)
			gui.iItalic.doClick();
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_U)
			gui.iUnderline.doClick();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
