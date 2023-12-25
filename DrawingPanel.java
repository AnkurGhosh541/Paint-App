import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {

	private int initialX, initialY;
	private Tool currentTool = Tool.PENCIL;
	private Color currentColor = Color.BLACK;
	private int lineThickness = 15;

	public DrawingPanel() {
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void setCurrentTool(Tool t) {
		currentTool = t;
	}
	
	public void setLineThickness(int t) {
		lineThickness = t;
	}
	
	public void setCurrentColor(Color c) {
		currentColor = c;
	}

	public Color getCurrentColor() {
		return currentColor;
	}
	
	public void clear() {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
	}

	private void draw(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setStroke(new BasicStroke(lineThickness));

		if (currentTool == Tool.PENCIL) {
			g.setColor(currentColor);
			g.drawLine(initialX, initialY, x, y);
			initialX = x;
			initialY = y;
		} else if (currentTool == Tool.ERASER) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, lineThickness, lineThickness);
		}
	}

	private void drawRect(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setStroke(new BasicStroke(lineThickness));
		g.setColor(currentColor);

		int width = x - initialX;
		int height = y - initialY;

		if (x < initialX) {
			initialX = x;
			width = width * -1;
		}

		if (y < initialY) {
			initialY = y;
			height = height * -1;
		}

		g.drawRect(initialX, initialY, width, height);
	}

	private void drawElipse(int x, int y) {
		Graphics2D g = (Graphics2D) getGraphics();
		g.setStroke(new BasicStroke(lineThickness));
		g.setColor(currentColor);

		int width = x - initialX;
		int height = y - initialY;

		if (x < initialX) {
			initialX = x;
			width = width * -1;
		}

		if (y < initialY) {
			initialY = y;
			height = height * -1;
		}

		g.drawOval(initialX, initialY, width, height);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int curX = e.getX();
		int curY = e.getY();

		draw(curX, curY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		initialX = e.getX();
		initialY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (currentTool == Tool.RECTANGLE) {
			drawRect(e.getX(), e.getY());
		} else if (currentTool == Tool.ELIPSE) {
			drawElipse(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
}
