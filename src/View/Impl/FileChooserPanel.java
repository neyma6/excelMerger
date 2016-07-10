package View.Impl;

import java.awt.Color;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import View.Labels;
import View.MainWindow;
import View.Interface.UIBuilder;


public class FileChooserPanel extends JPanel implements UIBuilder{

	private static final long serialVersionUID = 1L;
	private JTextField inputFileName;
	private JButton openFileChooser;
	private String path = null;
	private String labelText;
	
	public FileChooserPanel(String labelText)
	{
		this.labelText = labelText;
	}
	
	@Override
	public void createPanel()
	{
		this.setLayout(new MigLayout());
		
		JLabel infoLabel = new JLabel();
		infoLabel.setText(labelText);
		this.add(infoLabel, "wrap");
		
		inputFileName = new JTextField(100);
		inputFileName.setEnabled(false);
		inputFileName.setDisabledTextColor(Color.black);
		this.add(inputFileName, "width 100:600:600");
		
		openFileChooser = new JButton(Labels.FC_OPEN_FILE);
		openFileChooser.addActionListener((event)-> {
			JFileChooser fileChooser = createFileChooser();
			if (path != null && !path.isEmpty())
			{
				fileChooser.setCurrentDirectory(new File(path));
			}
			int returnValue = fileChooser.showOpenDialog(MainWindow.getInstance().getWindow());
			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				path = fileChooser.getSelectedFile().getAbsolutePath();
				inputFileName.setText(path);
				loadPanels();
			}
		});
		this.add(openFileChooser, "width 110:110:110");
	}
	
	protected JFileChooser createFileChooser()
	{
		FileFilter filter = new FileNameExtensionFilter("Excel File","xls", "xlsx");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		return fileChooser;
	}
	
	protected void loadPanels()
	{
		
	}
	
	public String getPath()
	{
		return path;
	}
}
