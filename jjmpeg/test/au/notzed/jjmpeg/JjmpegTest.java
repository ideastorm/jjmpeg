package au.notzed.jjmpeg;

import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFrame;

public class JjmpegTest extends JFrame
{
	public static void main(String[] args)
	{
		File file = new File(args[1]);
		if (file.exists())
			EventQueue.invokeLater(()->{
				new JjmpegTest().setVisible(true);
			});
	}
}
