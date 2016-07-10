package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	public MenuBar()
	{
		createFileMenu();
	}
	
	private JMenu createMenu(String name)
	{
		JMenu menu=new JMenu(name);
	    this.add(menu);
	    return menu;
	}
	
	private JMenuItem createSubMenu(String name, JMenu menu)
	{
		JMenuItem submenu = new JMenuItem(name);
	    menu.add(submenu);
	    return submenu;
	}
	
	private void createFileMenu()
	{
		JMenu file = createMenu(Labels.MENU_FILE);
		JMenuItem exit = createSubMenu(Labels.MENU_EXIT, file);
		exit.addActionListener((event)->{MainWindow.getInstance().close();});
	}
	
}
