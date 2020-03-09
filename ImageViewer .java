package MeinPacket;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class ImageViewer extends JFrame implements ActionListener {
	private ViewComponent viewComponent = new ViewComponent();
	
	public ImageViewer() {
		super ("Bildbetrachter");
		JMenuBar mbar = new JMenuBar();
		JMenu menu = new JMenu("Datei");
		JMenuItem item = new JMenuItem("öffnen");
		item.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK) );
		item.addActionListener(this);
		menu.add(item);
		mbar.add(menu);
		setJMenuBar(mbar);
		add(viewComponent);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
	}
	public void actionPerformed(ActionEvent e) {
		JFileChooser d = new JFileChooser();
		d.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith("jpg")
						|| f.getName().toLowerCase().endsWith(".gif");
			}
			public String getDescription() {
				return "*.jpg;*.gif";
			}
		});
		d.showOpenDialog(null);
		File file = d.getSelectedFile();
		viewComponent.setImage(file);
	}
	public static void main(String args[]) {
		new ImageViewer().setVisible( true );
	}
}

class ViewComponent extends JComponent
{
	private Image image;

  protected void paintComponent( Graphics g ) 
	{
		if ( image != null )
			g.drawImage( image, 0, 0, this );
	}

	public void setImage( File file )
	{
		image = Toolkit.getDefaultToolkit().getImage( file.getAbsolutePath() );
		if ( image != null )
			repaint();
	}
}
