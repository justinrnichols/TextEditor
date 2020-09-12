import java.awt.FileDialog;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileFunctions {
	GUI gui;
	String fileName, fileAddress, oldText;
	
	public FileFunctions(GUI gui) {
		this.gui = gui;
	}
	
	public void newFile() {
		gui.textPane.setText("");
		gui.window.setTitle("Untitled - Notepad");
		fileName = null;
		fileAddress = null;
	}
	
	public void newWindow() {
		new GUI();
	}
	
	public void open() {
		FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
		fd.setVisible(true);
		if(fd.getFile() != null) {
			fileName = fd.getFile();
			fileAddress = fd.getDirectory();
			gui.window.setTitle(fileName + " -  Notepad");
		}
		try {
			FileReader file = new FileReader(fileAddress + fileName);
			gui.textPane.read(file, null);
			file.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(fileName + " not opened");
		}
	}
	
	public void save() {
		if(fileName == null)
			saveAs();
		else {
			try {
				FileWriter fw = new FileWriter(fileAddress + fileName);
				fw.write(gui.textPane.getText());
				gui.window.setTitle(fileName + " -  Notepad");
				fw.close();
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println(fileName + " not saved");
			}
		}
		oldText = gui.textPane.getText();
	}
	
	public void notSaved() {
		String newText = gui.textPane.getText();
		if(!newText.equals(oldText))
			gui.window.setTitle("*" + fileName + " -  Notepad");
		else 
			gui.window.setTitle(fileName + " -  Notepad");
	}
	
	public void saveAs() {
		FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
		fd.setVisible(true);
		if(fd.getFile() != null) {
			fileName = fd.getFile();
			fileAddress = fd.getDirectory();
			gui.window.setTitle(fileName + " -  Notepad");
			try {
				FileWriter fw = new FileWriter(fileAddress + fileName);
				fw.write(gui.textPane.getText());
				fw.close();
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println(fileName + " not saved");
			}
		}	
	}
	
	public void print() {
		PrinterJob pj = PrinterJob.getPrinterJob();
		if(pj.printDialog()) {
			try {
				pj.print();
			}
		    catch(PrinterException exc) {
		    	System.out.println(exc);
		    }
		}   
	}
	
	public void exit() {
		String newText = gui.textPane.getText();
		if(!newText.equals(oldText))
			
		System.exit(0);
	}
}