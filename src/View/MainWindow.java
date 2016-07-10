package View;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import View.Impl.FileChooserPanel;
import View.Impl.FileOptionPanelContainer;
import View.Impl.FileOptionPanelContainerScroller;
import View.Impl.FolderChooserPanel;
import View.Impl.ProcessPanel;
import View.Impl.SpreadAndVATPanel;
import View.Interface.UIBuilder;

public class MainWindow {

	private static MainWindow instance = null;
	
	private JFrame window;
	private FileChooserPanel similarFileChooserPanel;
	private FileChooserPanel referenceFileChooserPanel;
	private FolderChooserPanel folderChooserPanel;
	private FileOptionPanelContainer fileOptionPanelContainer;
	private SpreadAndVATPanel spreadAndVATPanel;
	private FileOptionPanelContainerScroller fileOptionPanelContainerScroller;
	private ProcessPanel processPanel;

	protected MainWindow() 
	{
		createWindow();
	}
	
	public static MainWindow getInstance()
	{
		if (instance == null)
		{
			instance = new MainWindow();
		}
		return instance;
	}
	
	private void createWindow()
	{
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1100, 800);
		window.setJMenuBar(new MenuBar());
		window.setLocationRelativeTo(null);
		window.setTitle(Labels.APP_TITLE);
		window.getContentPane().setLayout(new MigLayout());
		
		Image i;
		try 
		{
			i = ImageIO.read(getClass().getClassLoader().getResource("icon.png"));
			window.setIconImage(i);
		} 
		catch (Exception e) 
		{

		}
		
		List<UIBuilder> uiElements = new ArrayList<>();
		
		similarFileChooserPanel = new FileChooserPanel(Labels.FC_SIMILAR_INFO);
		referenceFileChooserPanel = new FileChooserPanel(Labels.FC_REFERENCE_INFO);
		fileOptionPanelContainer = new FileOptionPanelContainer();
		spreadAndVATPanel = new SpreadAndVATPanel();
		fileOptionPanelContainerScroller = new FileOptionPanelContainerScroller(fileOptionPanelContainer);
		folderChooserPanel = new FolderChooserPanel(fileOptionPanelContainerScroller, Labels.FC_FOLDER_INFO);
		processPanel = new ProcessPanel();
		
		uiElements.add(similarFileChooserPanel);
		uiElements.add(referenceFileChooserPanel);
		uiElements.add(folderChooserPanel);
		uiElements.add(spreadAndVATPanel);
		uiElements.add(fileOptionPanelContainerScroller);
		uiElements.add(processPanel);
		
		uiElements.forEach((element)->{
			element.createPanel();
			window.getContentPane().add((Component) element, "wrap");
		});
	}
	
	public void setVisible()
	{
		instance.window.setVisible(true);
	}
	
	public void close()
	{
		instance.window.dispose();
	}
	
	public JFrame getWindow()
	{
		return instance.window;
	}
	
	public FileChooserPanel getReferenceFileChooserPanel()
	{
		return referenceFileChooserPanel;
	}
	
	public FileChooserPanel getSimilarFileChooserPanel()
	{
		return similarFileChooserPanel;
	}
	
	public FileOptionPanelContainer getFileOptionPanelContainer()
	{
		return fileOptionPanelContainer;
	}
	
	public ProcessPanel getProcessPanel()
	{
		return processPanel;
	}
	
	public SpreadAndVATPanel getSPAndVATPanel()
	{
		return spreadAndVATPanel;
	}
}
