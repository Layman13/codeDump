package testGUI;
import javax.swing.*;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;

public class Main {
	
	int dumppp = (int)Math.random();
	static boolean mainWindowBorder = true;
	static boolean printedError = false;
	
	public static void main(String[] args) {
		System.out.println("hi mom");
		secondaryWindow();
		
	}
	
	public static void secondaryWindow() {
		frame secondary = new frame("Sudoku parameters", 800, 600, false);
		secondary.f.setResizable(false);
		
		Font font = new Font("Verdana", Font.BOLD, 24);
		JPanel panel = new JPanel();
		
		JLabel welcomeLabel = new JLabel("Welcome to my first gui program");
		JLabel welcomeLabel2 = new JLabel("This is an infinite sudoku app      ");
		JLabel xResolution = new JLabel("Window horizontal resolution");
		JLabel yResolution = new JLabel("Window Vertical Resolution");
		JLabel badResolution = new JLabel("The resolution you chose is too small");
		
		welcomeLabel.setFont(font);
		welcomeLabel2.setFont(font);
		xResolution.setFont(font);
		yResolution.setFont(font);
		badResolution.setFont(font);
		
		JTextField xField = new JTextField(4);
		JTextField yField = new JTextField(4);
		
		JCheckBox borderlessCheckBox = new JCheckBox("Click for borderless fullscreen");
		borderlessCheckBox.setFont(font);
		
		JButton recommended = new JButton("Play with recommended settings");
		JButton custom = new JButton("Play with custom settings");
		
		recommended.setFont(font);
		custom.setFont(font);
		
		ActionListener getCustom = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = 0;
				int y = 0;
				if(isNumeric(xField.getText()) && (isNumeric(yField.getText()))) {
					x = Integer.valueOf(xField.getText());
					y = Integer.valueOf(yField.getText());
				}
				if(x < 600 || y < 400) {
					if(printedError == false) {
						panel.add(badResolution);
						printedError = true;
						SwingUtilities.updateComponentTreeUI(secondary.f);
					}
				}else {
					boolean flag = false;
					if(borderlessCheckBox.isSelected()) {
						flag = true;
					}
					secondary.toggle();
					mainWindow(x, y, flag);
					//secondary.close();
				}
			}
		};
		
		ActionListener getRecommended = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
				secondary.toggle();
				mainWindow(size.width, size.height, true);
			}
		};
		
		recommended.addActionListener(getRecommended);
		custom.addActionListener(getCustom);

		panel.add(welcomeLabel);
		panel.add(Box.createHorizontalStrut(40));
		//panel.add(Box.createVerticalStrut(100));
		panel.add(welcomeLabel2);
		panel.add(Box.createHorizontalStrut(40));
		panel.add(xResolution);
		panel.add(xField);
		panel.add(Box.createHorizontalStrut(30));
		panel.add(yResolution);
		panel.add(yField);
		panel.add(Box.createHorizontalStrut(53));
		panel.add(borderlessCheckBox);
		panel.add(Box.createHorizontalStrut(50));
		panel.add(recommended);
		panel.add(custom);
		panel.add(Box.createHorizontalStrut(90));
		
		secondary.f.add(panel);
		secondary.toggle();
		
	}
	
	public static void mainWindow(int x, int y, boolean borderless) {
		int count = 0;
		frame primary = new frame("Sudoku", x, y, false);
		JPanel panel = new JPanel();
		System.out.println("Does this work?");
		sudoku susdoku = new sudoku("sudoku name");
		susdoku.generate(20);
		primary.f.setLocationRelativeTo(null);
		if(borderless) {
			primary.f.setUndecorated(true);
		}else {
			primary.f.setResizable(false);
		}
		
	//	primary.f.add(panel);
		primary.f.setLayout(new GridLayout(10, 9, 0, 0));
		primary.toggle();
		System.out.println("Do we get ehre?");
		susdoku.printBoard(susdoku.board);
		System.out.println();
		susdoku.printBoard(susdoku.unsolved);
		
		
		ArrayList<button> buttons = new ArrayList<button>();
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				button b = new button(i, j, susdoku.board, susdoku.unsolved);
				buttons.add(b);
				primary.f.add(b.bttn);
				count = count + b.retVal;
			}
		}
		
		
		SwingUtilities.updateComponentTreeUI(primary.f);
		
		KeyListener getKey = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				System.out.println("this works");
				
			}
			
			public void keyReleased(KeyEvent e) {
				System.out.println("this works too");
			}

			public void keyTyped(KeyEvent e) {
				SwingUtilities.updateComponentTreeUI(primary.f);
				System.out.println("this works too trtotoot o");
			}
		};
		
		primary.f.addKeyListener(getKey);
		primary.f.setFocusable(true);
		primary.f.requestFocus();
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Verdana", Font.BOLD, 24));
	//	primary.f.add(submit);
	}
	
	public static int getVals(ArrayList<button> arr) {
		int ct = 0;
		for(int i = 0; i < arr.size(); i++) {
			ct = ct + arr.get(i).retVal;
		}
		return ct;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
	//Nothing 
}

