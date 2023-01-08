package testGUI;

import java.awt.event.WindowEvent;

import javax.swing.*;

public class frame {
	String name;
	int vRes;
	int hRes;
	boolean isVisible;
	boolean isBorderless;
	JFrame f;
	
	public frame(String name, int x, int y, boolean isVisible){
		System.out.println("A new frame has been created!");
		this.name = name;
		this.vRes = x;
		this.hRes = y;
		this.isVisible = isVisible;
		this.f = new JFrame(name);
		this.f.setVisible(isVisible);
		this.f.setSize(x, y);
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void toggle() {
		if(this.isVisible) {
			this.f.setVisible(false);
			this.isVisible = false;
		}else {
			this.f.setVisible(true);
			this.isVisible = true;
		}
	}
	
	public void setRes(int x, int y) {
		this.vRes = x;
		this.hRes = y;
		this.f.setSize(x, y);
	}
	
	public void setBorderless(boolean val) {
		this.f.setUndecorated(true);
	}
	
	public void close() {
		f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
	}
}
