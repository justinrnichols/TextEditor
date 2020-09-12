import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultEditorKit.CopyAction;
import javax.swing.text.DefaultEditorKit.CutAction;
import javax.swing.text.DefaultEditorKit.PasteAction;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.text.StyledEditorKit.FontFamilyAction;
import javax.swing.text.StyledEditorKit.FontSizeAction;
import javax.swing.text.StyledEditorKit.ItalicAction;
import javax.swing.text.StyledEditorKit.UnderlineAction;
import javax.swing.undo.UndoManager;

public class GUI implements ActionListener{

	JFrame window;
	JTextPane textPane;
	JScrollPane scrollPane;
	
	JMenuBar menuBar;
	JMenu menuFile, menuEdit, menuFormat, menuView;
	
	FileFunctions file = new FileFunctions(this);
	JMenuItem iNew, iNewWindow, iOpen, iSave, iSaveAs, iPrint, iExit;
	
	EditFunctions edit = new EditFunctions(this);
	JMenuItem iUndo, iRedo, iCut, iCopy, iPaste;
	
	FormatFunctions format = new FormatFunctions(this);
	JMenu menuFont, menuFontSize, menuStylize;
	JMenuItem iFont[] = new JMenuItem[8];
	String[] names = {"Arial", "Comic Sans MS", "Courier", "Georgia", "Impact", "Tahoma", "Times New Roman", "Verada"};
	JMenuItem iFontSize[] = new JMenuItem[8];
	String[] sizes = {"10", "12", "14", "16", "24", "36", "48", "64"};
	JMenuItem iBold, iItalic, iUnderline;
	JMenuItem iFontColor;
	
	ViewFunctions view = new ViewFunctions(this);
	boolean appearance = false;
	JMenuItem iAppearance, iWordCount, iCharacterCount;

	UndoManager um = new UndoManager();
	KeyHandler kh = new KeyHandler(this);
	
	public static void main(String[] args) {
		new GUI();
	}
	
	public GUI() {
		createWindow();
		createTextArea();
		createMenuBar();
		createFileMenu();
		createEditMenu();
		createFormatMenu();
		textPane.setFont(new Font("Arial", Font.PLAIN, 16));
		createViewMenu();
		view.wordCount();
		view.characterCount();
		view.appearance();
		window.setVisible(true);
	}
	
	public void createWindow() {
		window = new JFrame("Untitled - Notepad");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void createTextArea() {
		textPane = new JTextPane();
		textPane.addKeyListener(kh);
		textPane.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				um.addEdit(e.getEdit());
			}
		});
		scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		window.add(scrollPane);
	}
	
	public void createMenuBar() {
		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		menuFormat = new JMenu("Format");
		menuBar.add(menuFormat);
		menuView = new JMenu("View");
		menuBar.add(menuView);
		menuView.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            jMenuMouseClicked(evt);
	        }
		});
	}
        
    private void jMenuMouseClicked(java.awt.event.MouseEvent evt) {                                    
        if(evt.getButton() == MouseEvent.BUTTON1)
        	view.wordCount();
        	view.characterCount();
    }
	
	public void createFileMenu() {
		iNew = new JMenuItem("New");
		iNew.addActionListener(this);
		iNew.setActionCommand("New");
		menuFile.add(iNew);
		
		iNewWindow = new JMenuItem("New Window");
		iNewWindow.addActionListener(this);
		iNewWindow.setActionCommand("NewWindow");
		menuFile.add(iNewWindow);
		
		iOpen = new JMenuItem("Open");
		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		menuFile.add(iOpen);
		
		iSave = new JMenuItem("Save");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		menuFile.add(iSave);
		
		iSaveAs = new JMenuItem("Save As");
		iSaveAs.addActionListener(this);
		iSaveAs.setActionCommand("SaveAs");
		menuFile.add(iSaveAs);
		
		iPrint = new JMenuItem("Print");
		iPrint.addActionListener(this);
		iPrint.setActionCommand("Print");
		menuFile.add(iPrint);
		
		iExit = new JMenuItem("Exit");
		iExit.addActionListener(this);
		iExit.setActionCommand("Exit");
		menuFile.add(iExit);
	}
	
	public void createEditMenu() {
		iUndo = new JMenuItem("Undo");
		iUndo.addActionListener(this);
		iUndo.setActionCommand("Undo");
		menuEdit.add(iUndo);
		
		iRedo = new JMenuItem("Redo");
		iRedo.addActionListener(this);
		iRedo.setActionCommand("Redo");
		menuEdit.add(iRedo);
		
		iCut = new JMenuItem(new CutAction());
		iCut.addActionListener(this);
		iCut.setActionCommand("Cut");
		iCut.setText("Cut");
		menuEdit.add(iCut);
		
		iCopy = new JMenuItem(new CopyAction());
		iCopy.addActionListener(this);
		iCopy.setActionCommand("Copy");
		iCopy.setText("Copy");
		menuEdit.add(iCopy);

		iPaste = new JMenuItem(new PasteAction());
		iPaste.addActionListener(this);
		iPaste.setActionCommand("Paste");
		iPaste.setText("Paste");
		menuEdit.add(iPaste);
	}
	
	public void createFormatMenu() {
		menuFont = new JMenu("Font");
		menuFormat.add(menuFont);
		
		for(int i = 0; i < iFont.length; i++) {
			iFont[i] = new JMenuItem(names[i]);
			iFont[i].addActionListener(this);
			iFont[i].setActionCommand(names[i]);
			menuFont.add(iFont[i]);
		}
		
		menuFontSize = new JMenu("Font Size");
		menuFormat.add(menuFontSize);
		
		for(int i = 0; i < iFontSize.length; i++) {
			iFontSize[i] = new JMenuItem(sizes[i]);
			iFontSize[i].addActionListener(this);
			iFontSize[i].setActionCommand(sizes[i]);
			menuFontSize.add(iFontSize[i]);
		}
		
		iFontColor = new JMenuItem("Font Color");
		iFontColor.addActionListener(this);
		iFontColor.setActionCommand("TextColor");
		menuFormat.add(iFontColor);
		
		menuStylize = new JMenu("Stylize");
		menuFormat.add(menuStylize);
		
		iBold = new JMenuItem(new BoldAction());
		iBold.addActionListener(this);
		iBold.setActionCommand("Bold");
		iBold.setText("Bold");
		menuStylize.add(iBold);
		
		iItalic = new JMenuItem(new ItalicAction());
		iItalic.addActionListener(this);
		iItalic.setActionCommand("Italic");
		iItalic.setText("Italic");
		menuStylize.add(iItalic);
		
		iUnderline = new JMenuItem(new UnderlineAction());
		iUnderline.addActionListener(this);
		iUnderline.setActionCommand("Underline");
		iUnderline.setText("Underline");
		menuStylize.add(iUnderline);
	}
	
	public void createViewMenu() {
		iAppearance = new JMenuItem("Appearance: LIGHT");
		iAppearance.addActionListener(this);
		iAppearance.setActionCommand("Appearance");
		menuView.add(iAppearance);
		
		iWordCount = new JMenuItem();
		menuView.add(iWordCount);
		
		iCharacterCount = new JMenuItem();
		menuView.add(iCharacterCount);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "New": file.newFile(); break;
			case "NewWindow": file.newWindow(); break;
			case "Open": file.open(); break;
			case "Save": file.save(); break;
			case "SaveAs": file.saveAs(); break;
			case "Print": file.print(); break;
			case "Exit": file.exit(); break;
			case "Undo": edit.undo(); break;
			case "Redo": edit.redo(); break;
			case "Arial": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "Comic Sans MS": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "Courier": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "Georgia": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "Gotham": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "Impact": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "Tahoma": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "Times New Roman": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "Veranda": new FontFamilyAction(command, command).actionPerformed(e); break;
			case "10": new FontSizeAction(command, 10).actionPerformed(e); break;
			case "12": new FontSizeAction(command, 12).actionPerformed(e); break;
			case "14": new FontSizeAction(command, 14).actionPerformed(e); break;
			case "16": new FontSizeAction(command, 16).actionPerformed(e); break;
			case "24": new FontSizeAction(command, 24).actionPerformed(e); break;
			case "36": new FontSizeAction(command, 36).actionPerformed(e); break;
			case "48": new FontSizeAction(command, 48).actionPerformed(e); break;
			case "64": new FontSizeAction(command, 64).actionPerformed(e); break;
			case "TextColor": 
				Color newColor = JColorChooser.showDialog(textPane, "Text Color", Color.BLACK); 
				SimpleAttributeSet attr = new SimpleAttributeSet();
				StyleConstants.setForeground(attr, newColor);
				textPane.setCharacterAttributes(attr, true);
				textPane.requestFocusInWindow(); break;
			case "Appearance": view.appearance(); break;
			case "View": view.wordCount(); view.characterCount(); break;
		}
	}

}