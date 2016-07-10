package View.Impl;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import net.miginfocom.swing.MigLayout;
import View.Labels;
import View.StartActionListener;
import View.Interface.UIBuilder;

public class ProcessPanel extends JPanel implements UIBuilder
{
	private JProgressBar progressBar;
	private JButton startButton;
	
	@Override
	public void createPanel() {
		
		this.setLayout(new MigLayout());
		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(600, 30));
		this.add(progressBar, "split 2");
		
		startButton = new JButton();
		startButton.setText(Labels.MW_START);
		StartActionListener actionListener = new StartActionListener();
		startButton.addActionListener(actionListener);
		this.add(startButton, "wrap");	
	}

	public JProgressBar getProgressBar() 
	{
		return progressBar;
	}

	public JButton getStartButton() 
	{
		return startButton;
	}

	
}
