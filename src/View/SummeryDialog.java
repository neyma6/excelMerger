package View;

import java.awt.Dimension;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import Domain.ProcessedValues;
import Excel.ExcelMerger;

public class SummeryDialog extends JDialog {

	private JButton saveButton;
	private JTextArea summery;
	private JScrollPane scrollPane;
	private ExcelMerger excelMerger;
	
	public SummeryDialog(ExcelMerger excelMerger)
	{
		this.excelMerger = excelMerger;
		createDialog();
	}
	
	private void createDialog()
	{
		this.setSize(new Dimension(900, 500));
		this.setLayout(new MigLayout());
		this.setTitle(Labels.SUMMERY_TITLE);
		this.setLocationRelativeTo(null);
		
		summery = new JTextArea(getEditedText(excelMerger.getLog()));
		scrollPane = new JScrollPane (summery, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(850, 450));
		this.add(scrollPane, "wrap");
		
		saveButton = new JButton();
		saveButton.setText(Labels.SUMMERY_SAVE);
		//need to refactor
		saveButton.addActionListener((event)->{
			saveButton.setEnabled(false);
			JFileChooser save = new JFileChooser(){
				@Override
			    public void approveSelection(){
			        File f = getSelectedFile();
			        if(f.exists() && getDialogType() == SAVE_DIALOG){
			            int result = JOptionPane.showConfirmDialog(this,Labels.DIALOG_WARNING_EXISTS_MESSAGE,
			            		Labels.DIALOG_WARNING_EXISTS_TITLE,JOptionPane.YES_NO_OPTION);
			            switch(result){
			                case JOptionPane.YES_OPTION:
			                    super.approveSelection();
			                    return;
			                default:
			                    return;	
			            }
			        }
			        super.approveSelection();
			    }  
			};
			int returnVal = save.showSaveDialog(MainWindow.getInstance().getWindow());
			checkAndSave(returnVal, save);
		});
		this.add(saveButton);
		this.setVisible(true);
	}
	
	private void checkAndSave(int option, JFileChooser save)
	{
		if (option == JFileChooser.APPROVE_OPTION)
		{
			File file = save.getSelectedFile();
            String path = file.getAbsolutePath();
            try 
            {
            	if (!path.endsWith("xlsx"))
            	{
            		path = path.concat(".xlsx");
            	}
				excelMerger.writeToFile(path);
				JOptionPane.showMessageDialog(MainWindow.getInstance().getWindow(),
						Labels.DIALOG_SUCCESS_WRITE_MESSAGE, Labels.DIALOG_SUCCESS_WRITE_TITLE, JOptionPane.INFORMATION_MESSAGE);
			} 
            catch (Exception e) 
            {
				JOptionPane.showMessageDialog(MainWindow.getInstance().getWindow(),
						Labels.EXCEPTION_WRITE_READ, Labels.DIALOG_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
			}
            finally
            {
            	saveButton.setEnabled(true);
            }
		}
	}
	
	private String getEditedText(List<String> values)
	{
		String edited = "";
		for (String str : values)
		{
			edited += str.toString() + "\n";
		}
		return edited;
	}
}
