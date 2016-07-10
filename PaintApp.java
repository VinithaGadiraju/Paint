// Vinitha Gadiraju 
// MIT license

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// CPoint gets x,y coordinates of the point draw as well as color and size
class CPoint {
	private int m_x;
	private int m_y;
	private int m_size;
	private Color m_color;
	
	CPoint(int x, int y, int size, Color color) {
		m_x = x; m_y = y; m_size = size; m_color = color;
	}
	// Accessor functions
	public int getX() {
		return m_x;
	}
	
	public int getY() {
		return m_y;
	}
	
	public int getSize() {
		return m_size;
	}
	
	public Color getColor() {
		return m_color;
	}
}

// MyCanvas class implements Mouse and adds point to ArrayList 
class MyCanvas extends JPanel {
	private ArrayList<CPoint> m_points;
	private int m_size;
	private Color m_color;
	private static final long serialVersionUID=0;
	MyCanvas() {
		m_color = Color.BLACK;
		m_size = 4;
		m_points = new ArrayList<CPoint>();
		addMouseMotionListener(
				new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent event){
						m_points.add(new CPoint(event.getPoint().x,event.getPoint().y, m_size, m_color));
						repaint();
					}
				
				});
		addMouseListener(
				new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent event){
						m_points.add(new CPoint(event.getPoint().x,event.getPoint().y, m_size, m_color));
						repaint();
					}
				
				});
	}
	// Mutator Functions 
	public void setPenColor(Color new_color) {
		m_color = new_color;
	}
	
	public void setPenSize(int new_size) {
		m_size = new_size;
	}
	
	public void clear() {
		m_points.clear();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (CPoint point : m_points){
			g.setColor(point.getColor());
			g.fillOval(point.getX(), point.getY(), point.getSize(), point.getSize());
		}
	}
}
// Event Handler 
class ButtonHandler implements ActionListener {
	private MyCanvas m_canvas;
	private JButton m_Black;
	private JButton m_Blue;
	private JButton m_Pink;
	private JButton m_Red;
	private JButton m_Small;
	private JButton m_Medium;
	private JButton m_Large;
	private JButton m_Clear;
	
	ButtonHandler(MyCanvas canvas, JButton black, JButton blue, JButton pink, JButton red, JButton small, JButton medium, JButton large, JButton clear) {
		m_canvas = canvas;
		m_Black = black;
		m_Blue = blue;
		m_Pink = pink;
		m_Red = red;
		m_Small = small;
		m_Medium = medium;
		m_Large = large;
		m_Clear = clear;
	}
	// Chooses color/size based on which button is pressed 
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==m_Black) {
			m_canvas.setPenColor(Color.BLACK);
		}
		else if (ae.getSource()==m_Blue) {
			m_canvas.setPenColor(Color.BLUE);
		}
		else if (ae.getSource()==m_Pink) {
			m_canvas.setPenColor(Color.PINK);
		}
		else if (ae.getSource()==m_Red) {
			m_canvas.setPenColor(Color.RED);
		}
		else if (ae.getSource()==m_Small) {
			m_canvas.setPenSize(5);
		}
		else if (ae.getSource()==m_Medium) {
			m_canvas.setPenSize(10);
		}
		else if (ae.getSource()==m_Large) {
			m_canvas.setPenSize(15);
		}
		else if (ae.getSource()==m_Clear) {
			m_canvas.clear();
			m_canvas.repaint();
		}
		
	}
}
// Main Class
public class PaintApp {
	public static void main(String[] args) {

		JFrame App = new JFrame("Paint App");
		MyCanvas canvas = new MyCanvas();
		canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		App.add(canvas, BorderLayout.CENTER);
		// Creating Panel for Color Buttons
		JPanel westPanel = new JPanel();
		JButton btnBlack = new JButton("Black");
		JButton btnBlue = new JButton("Blue");
		JButton btnPink = new JButton("Pink");
		JButton btnRed = new JButton("Red");
		BoxLayout bl = new BoxLayout(westPanel, BoxLayout.Y_AXIS);
		westPanel.setLayout(bl);
		westPanel.add(btnBlack); westPanel.add(btnBlue); westPanel.add(btnPink); westPanel.add(btnRed);
		App.add(westPanel, BorderLayout.WEST);
		// Creating Panel for size buttons and clear button
		JPanel eastPanel = new JPanel();
		JButton btnSmall = new JButton("Small");
		JButton btnMedium = new JButton("Medium");
		JButton btnLarge = new JButton("Large");
		JButton btnClear = new JButton("Clear Frame");
		BoxLayout bl2 = new BoxLayout(eastPanel, BoxLayout.Y_AXIS);
		eastPanel.add(btnSmall); eastPanel.add(btnMedium); eastPanel.add(btnLarge); eastPanel.add(btnClear);
		eastPanel.setLayout(bl2);
		App.add(eastPanel, BorderLayout.EAST);
		
		//implementing event handler for each button 
		ButtonHandler b_handler = new ButtonHandler(canvas, btnBlack, btnBlue, btnPink, btnRed,
				btnSmall, btnMedium, btnLarge, btnClear);
		btnBlack.addActionListener(b_handler);
		btnBlue.addActionListener(b_handler);
		btnPink.addActionListener(b_handler);
		btnRed.addActionListener(b_handler);
		btnSmall.addActionListener(b_handler);
		btnMedium.addActionListener(b_handler);
		btnLarge.addActionListener(b_handler);
		btnClear.addActionListener(b_handler);
		
		// Setting Main frame size and visibility
		App.setSize(600, 600);
		App.setVisible(true);
		
		App.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
	}

}

	

