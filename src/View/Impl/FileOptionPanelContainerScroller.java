package View.Impl;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import View.Interface.UIBuilder;

public class FileOptionPanelContainerScroller extends JScrollPane implements UIBuilder
{
	private static final long serialVersionUID = 5881165872547589811L;
	private FileOptionPanelContainer fileOptionPanelContainer;
	
	public FileOptionPanelContainerScroller(FileOptionPanelContainer fileOptionPanelContainer) 
	{
		this.fileOptionPanelContainer = fileOptionPanelContainer;
	}

	@Override
	public void createPanel() {
		fileOptionPanelContainer.createPanel();
		this.getViewport().add(fileOptionPanelContainer);
		this.setAutoscrolls(true);
		this.setPreferredSize(new Dimension(1050, 700));
		//this.setVisible(false);
	}

	public FileOptionPanelContainer getFileOptionPanelContainer() {
		return fileOptionPanelContainer;
	}
}
