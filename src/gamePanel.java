import javax.swing.JFrame;
import javax.swing.JPanel;

public class gamePanel extends JPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("game");
		frame.setSize(600,500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		frame.setVisible(true);
	}

}
