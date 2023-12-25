
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicIconFactory;

public class MyFrame extends JFrame implements ActionListener, ChangeListener {

	private DrawingPanel drawingArea;
	private JButton color;
	private JSlider lineWidth;

	public MyFrame() {
		setTitle("Paint App");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}

	private void init() {

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 50));

		ButtonGroup toolBtns = new ButtonGroup();
		JToggleButton pencil = new JToggleButton("pencil", true);
		JToggleButton eraser = new JToggleButton("eraser");
		JToggleButton rectangle = new JToggleButton("rectangle");
		JToggleButton elipse = new JToggleButton("elipse");
		
		JButton clear = new JButton("clear");
		
		lineWidth = new JSlider(5, 50, 15);
		lineWidth.setMajorTickSpacing(5);
		lineWidth.setPaintLabels(true);
		lineWidth.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

		JButton chooseColor = new JButton("color");
		color = new JButton();
		color.setBackground(Color.BLACK);
		color.setBorder(BorderFactory.createLineBorder(color.getBackground(), 10, true));
		color.setEnabled(false);
		
		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controlPanel.add(lineWidth);
		controlPanel.add(chooseColor);
		controlPanel.add(color);

		pencil.addActionListener(this);
		eraser.addActionListener(this);
		rectangle.addActionListener(this);
		elipse.addActionListener(this);
		
		clear.addActionListener(this);

		lineWidth.addChangeListener(this);
		chooseColor.addActionListener(this);

		toolBtns.add(pencil);
		toolBtns.add(eraser);
		toolBtns.add(rectangle);
		toolBtns.add(elipse);

		toolBar.add(pencil);
		toolBar.add(eraser);
		toolBar.add(rectangle);
		toolBar.add(elipse);
		toolBar.addSeparator();
		toolBar.addSeparator();
		toolBar.addSeparator();
		toolBar.add(clear);
		toolBar.addSeparator();
		toolBar.add(controlPanel);
		add(toolBar, BorderLayout.NORTH);

		drawingArea = new DrawingPanel();
		add(drawingArea, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		MyFrame f = new MyFrame();
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equalsIgnoreCase("pencil")) {
			drawingArea.setCurrentTool(Tool.PENCIL);
		} else if (cmd.equalsIgnoreCase("eraser")) {
			drawingArea.setCurrentTool(Tool.ERASER);
		} else if (cmd.equalsIgnoreCase("rectangle")) {
			drawingArea.setCurrentTool(Tool.RECTANGLE);
		} else if (cmd.equalsIgnoreCase("elipse")) {
			drawingArea.setCurrentTool(Tool.ELIPSE);
		} else if (cmd.equalsIgnoreCase("color")) {
			Color newColor = JColorChooser.showDialog(this, "Choose a color", drawingArea.getCurrentColor());
			if(newColor!=null) {
				color.setBackground(newColor);
				color.setBorder(BorderFactory.createLineBorder(color.getBackground(), 10, true));
				drawingArea.setCurrentColor(newColor);
			}
		} else if(cmd.equalsIgnoreCase("clear")) {
			drawingArea.clear();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int value = lineWidth.getValue();
		drawingArea.setLineThickness(value);
	}

}
