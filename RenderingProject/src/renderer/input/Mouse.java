package renderer.input;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private int mouseX = -1; // x position
	private int mouseY = -1; // y position
	private int mouseB = -1; // mouse button pressed (varies)
	private int scroll = 0; // scroll
	
	public int getX() {
		return this.mouseX;
	}
	
	public int getY() {
		return this.mouseY;
	}
	
	public boolean isScrollingUp() {
		return this.scroll == -1;
	}
	
	public boolean isScrollingDown() {
		return this.scroll == 1;
	}
	
	public void resetScroll() {
		this.scroll = 0;
	}
	
	public ClickType getButton() {
		switch(this.mouseB) {
			case 1:
				return ClickType.LeftClick;
			case 2:
				return ClickType.ScrollClick;
			case 3:
				return ClickType.RightClick;
			case 4:
				return ClickType.ForwardPage;
			case 5:
				return ClickType.BackPage;
			default:
				return ClickType.Unknown;
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		this.scroll = event.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		this.mouseX = event.getX();
		this.mouseY = event.getY();
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		this.mouseX = event.getX();
		this.mouseY = event.getY();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		this.mouseB = event.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.mouseB = -1;
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		
	}

}
