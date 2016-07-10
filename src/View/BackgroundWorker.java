package View;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Domain.FileConfiguration;
import Excel.ExcelMerger;
import Exception.FileReadException;

public class BackgroundWorker extends SwingWorker<Object, Object>
{
	private final List<FileConfiguration> configurations;
	
	public BackgroundWorker(List<FileConfiguration> configurations) {
		super();
		this.configurations = configurations;
	}

	@Override
	protected Object doInBackground()  
	{
		MainWindow.getInstance().getProcessPanel().getProgressBar().setIndeterminate(true);
		MainWindow.getInstance().getProcessPanel().getStartButton().setEnabled(false);
		
		try 
		{
			ExcelMerger excelMerger = new ExcelMerger(configurations, 
					MainWindow.getInstance().getReferenceFileChooserPanel().getPath(),
					MainWindow.getInstance().getSimilarFileChooserPanel().getPath(),
					MainWindow.getInstance().getSPAndVATPanel().getSpread(),
					MainWindow.getInstance().getSPAndVATPanel().getVAT());
			excelMerger.process();
			SummeryDialog summeryDialog = new SummeryDialog(excelMerger);
		} 
		catch (InvalidFormatException ex) 
		{
			showErrorDialog(Labels.DIALOG_ERROR_TITLE, Labels.DIALOG_ERROR_MESSAGE_INVALID_FORMAT);
		}
		catch(FileReadException ex)
		{
			showErrorDialog(Labels.DIALOG_ERROR_TITLE, ex.getMessage());
		} 
		catch(Exception ex)
		{
			showErrorDialog(Labels.DIALOG_ERROR_TITLE, Labels.DIALOG_ERROR_MESSAGE_SOMETHING);
			ex.printStackTrace();
		}
		finally
		{
			stopProgressBarAndEnableStart();
		}
		
		return null;
	}

	@Override
	protected void done() {
		stopProgressBarAndEnableStart();
	}
	
	private void stopProgressBarAndEnableStart()
	{
		MainWindow.getInstance().getProcessPanel().getProgressBar().setIndeterminate(false);
		MainWindow.getInstance().getProcessPanel().getStartButton().setEnabled(true);
	}
	
	private void showErrorDialog(String title, String message)
	{
		JOptionPane.showMessageDialog(MainWindow.getInstance().getWindow(),
				message, title, JOptionPane.ERROR_MESSAGE);
	}

}
