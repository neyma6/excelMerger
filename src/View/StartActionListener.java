package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Domain.FileConfiguration;
import View.Impl.FileOptionPanel;

public class StartActionListener implements ActionListener
{	
	public StartActionListener() 
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!isValidData())
		{
			showDialog(Labels.DIALOG_WARNING_TITLE, Labels.DIALOG_WARNING_FILL, JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (!checkNotTheSameIndeciesSelected(MainWindow.getInstance().getFileOptionPanelContainer().getOptionPanels()))
		{
			showDialog(Labels.DIALOG_WARNING_TITLE, Labels.DIALOG_WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
			return;
		}
		List<FileConfiguration> configurations = new ArrayList<>();
		MainWindow.getInstance().getFileOptionPanelContainer().getOptionPanels().forEach((panel)->{
			configurations.add(new FileConfiguration(panel.getPath(), 
					panel.getSelectedIdColumn(), 
					panel.getSelectedDescColumn(), 
					panel.getSelectedPriceColumn()));
		});
		
		try
		{
			BackgroundWorker backgroundWorker = new BackgroundWorker(configurations);
			backgroundWorker.execute();		
		}
		catch(Exception ex)
		{			
			showDialog(Labels.DIALOG_ERROR_TITLE, Labels.DIALOG_ERROR_MESSAGE_SOMETHING,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean isValidData()
	{
		return (MainWindow.getInstance().getSimilarFileChooserPanel().getPath() != null &&
				!MainWindow.getInstance().getSimilarFileChooserPanel().getPath().isEmpty() &&
				MainWindow.getInstance().getReferenceFileChooserPanel().getPath() != null &&
				!MainWindow.getInstance().getReferenceFileChooserPanel().getPath().isEmpty())
				&& !MainWindow.getInstance().getFileOptionPanelContainer().getOptionPanels().isEmpty();
	}
	
	private boolean checkNotTheSameIndeciesSelected(List<FileOptionPanel> fileOptionPanels)
	{
		List<FileOptionPanel> invalidPanels = fileOptionPanels.stream()
				.filter( (panel)->{
					return !panel.isNotTheSameIndeciesSelected();
				} ).collect(Collectors.<FileOptionPanel>toList());
		return invalidPanels.size() == 0;
	}
	private void showDialog(String title, String message, int type)
	{
		JOptionPane.showMessageDialog(MainWindow.getInstance().getWindow(),
				message, title, type);
	}

}
