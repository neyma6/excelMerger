package View.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;

import View.Labels;

public class FolderChooserPanel extends FileChooserPanel {

	private static final long serialVersionUID = 1L;
	private FileOptionPanelContainerScroller containerScroller;

	public FolderChooserPanel(FileOptionPanelContainerScroller containerScroller, String labelText)
	{
		super(labelText);
		this.containerScroller = containerScroller;
	}

	@Override
	protected JFileChooser createFileChooser() {
		JFileChooser folderChooser = new JFileChooser();
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		folderChooser.setAcceptAllFileFilterUsed(false);
		return folderChooser;
	}

	@Override
	protected void loadPanels() 
	{
		List<String> xlsFiles = walk(this.getPath());
		
		containerScroller.getFileOptionPanelContainer().createFileOptionPanels(xlsFiles);
	}
	
	private List<String> walk( String path ) 
	{
		List<String> pathList = new ArrayList<>();
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return Collections.emptyList();

        for ( File f : list ) 
        {
            if ( f.isDirectory() ) 
            {
            	pathList.addAll(walk( f.getAbsolutePath()));
            }
            else 
            {
            	String fileName = f.getAbsoluteFile().toString();
            	if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))
            	{
            		pathList.add(fileName);
            	}
            }
        }
        return pathList;
    }

}
