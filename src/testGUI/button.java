package testGUI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;



public class button {
	int retVal;
	int unsolvedValue;
	int solvedValue;
	boolean immuttable;
	boolean keyActive = false;
	JButton bttn = new JButton();
	Font font = new Font("Verdana", Font.BOLD, 34);
	
	KeyListener getKey = new KeyListener() {
		public void keyPressed(KeyEvent e) {
			if(immuttable) {
				System.out.println(e.getExtendedKeyCode());
				System.out.println("pppppppppppppppppppppppppppp");
				if(e.getKeyCode() == KeyEvent.VK_1){
					System.out.println("Do we get here???");
					bttn.setText(String.valueOf(1));
					unsolvedValue = 1;
					checkChange();
				}else if(e.getKeyCode() == 50){
					bttn.setText(String.valueOf(2));
					unsolvedValue = 2;
					checkChange();
				}
				else if(e.getKeyCode() == 51){
					bttn.setText(String.valueOf(3));
					unsolvedValue = 3;
					checkChange();
				}
				else if(e.getKeyCode() == 52){
					bttn.setText(String.valueOf(4));
					unsolvedValue = 4;
					checkChange();
				}
				else if(e.getKeyCode() == 53){
					bttn.setText(String.valueOf(5));
					unsolvedValue = 5;
					checkChange();
				}
				else if(e.getKeyCode() == 54){
					bttn.setText(String.valueOf(6));
					unsolvedValue = 6;
					checkChange();
				}
				else if(e.getKeyCode() == 55){
					bttn.setText(String.valueOf(7));
					unsolvedValue = 7;
					checkChange();
				}
				else if(e.getKeyCode() == 56){
					bttn.setText(String.valueOf(8));
					unsolvedValue = 8;
					checkChange();
				}
				else if(e.getKeyCode() == 57){
					bttn.setText(String.valueOf(9));
					unsolvedValue = 9;
					checkChange();
				}
				SwingUtilities.updateComponentTreeUI(bttn);
			}
		}
		
		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
			
		}
	};
	
	ActionListener getClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			bttn.requestFocus();
			if(keyActive == false) {
				bttn.addKeyListener(getKey);
			}else {
				bttn.removeKeyListener(getKey);
			}
		}
	};
	
	button(int x, int y, int[][] board, int[][] unsolved){
		this.solvedValue = board[x][y];
		this.unsolvedValue = unsolved[x][y];
		checkChange();
		
		if(this.unsolvedValue == 0) {
			bttn = new JButton();
			bttn.setFont(font);
			this.immuttable = true;
		}else {
			bttn = new JButton(String.valueOf(this.unsolvedValue));
			bttn.setFont(font);
			this.immuttable = false;
		}
		
		bttn.addActionListener(getClick);
		
	}
	
	void changeVal(int newVal) {
		this.unsolvedValue = newVal;
		checkChange();
		bttn = new JButton(String.valueOf(this.unsolvedValue));
	}
	
	void checkChange() {
		if(this.solvedValue == this.unsolvedValue) {
			retVal = 1;
		}else {
			retVal = 0;
		}
	}
	
	
}
