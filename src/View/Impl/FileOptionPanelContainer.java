package View.Impl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import View.MainWindow;
import View.Interface.UIBuilder;

public class FileOptionPanelContainer extends JPanel implements UIBuilder
{
	private List<FileOptionPanel> optionPanels;
	private int columnIndex = 0;
	private int rowIndex = 0;

	public FileOptionPanelContainer() 
	{
		optionPanels = new ArrayList<>();
	}

	@Override
	public void createPanel() 
	{
		this.setLayout(new MigLayout());
		this.setBackground(Color.darkGray);
		this.setPreferredSize(new Dimension(1000, (rowIndex + 1) * 150));
	}
	
	public void createFileOptionPanels(List<String> pathList)
	{
		optionPanels.forEach((panel)->{
			this.remove(panel);
		});
		optionPanels.clear();
		columnIndex = rowIndex = 0;
		
		pathList.forEach((name)->{
			FileOptionPanel fileOptionPanel = new FileOptionPanel(name);
			fileOptionPanel.createPanel();
			this.add(fileOptionPanel, getCellIndex());
			optionPanels.add(fileOptionPanel);
		});
		this.setPreferredSize(new Dimension(1000, (rowIndex + 1) * 150));
		MainWindow.getInstance().getWindow().validate();
		MainWindow.getInstance().getWindow().repaint();
	}

	public List<FileOptionPanel> getOptionPanels() {
		return optionPanels;
	}
	
	private String getCellIndex()
	{
		String indexString = "cell " + columnIndex + " " + rowIndex;
		columnIndex++;
		if (columnIndex == 4)
		{
			rowIndex ++;
			columnIndex = 0;
		}
		return indexString;
	}
	
}
