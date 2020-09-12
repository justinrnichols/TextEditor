
public class EditFunctions {
	GUI gui;
	
	public EditFunctions(GUI gui) {
		this.gui = gui;
	}
	
	public void undo() {
		try {
			gui.um.undo();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Nothing to undo");
		}
	}
	
	public void redo() {
		try {
			gui.um.redo();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Nothing to redo");
		}
	}
}
