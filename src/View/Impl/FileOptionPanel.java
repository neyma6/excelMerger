package View.Impl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import View.Labels;
import View.Interface.UIBuilder;

public class FileOptionPanel extends JPanel implements UIBuilder{

	private JLabel fileNameLabel;
	private JComboBox idSelector;
	private JComboBox descSelector;
	private JComboBox priceSelector;
	private JLabel fileLabel;
	private JLabel idLabel;
	private JLabel descLabel;
	private JLabel priceLabel;
	private String path;
	
	private static List<String> options;

	static {
		options = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
	}
	
	public FileOptionPanel(String path)
	{
		this.path = path;
	}
	
	@Override
	public void createPanel()
	{
		this.setLayout(new MigLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(300, 150));
		this.setBackground(Color.LIGHT_GRAY);
		
		fileLabel = new JLabel();
		fileLabel.setText(Labels.FO_FILE);
		this.add(fileLabel, "split 2");
		
		String fileName = path.substring(path.lastIndexOf("\\") + 1);
		fileNameLabel = new JLabel();
		fileNameLabel.setText(fileName);
		this.add(fileNameLabel, "wrap");
		
		idSelector = createOptionLine(idLabel, Labels.FO_ID, 0);
		descSelector = createOptionLine(descLabel, Labels.FO_DESC, 1);
		priceSelector = createOptionLine(priceLabel, Labels.FO_PRICE, 2);
	}
	
	private JComboBox createOptionLine(JLabel label, String text, int selectedIndex)
	{
		label = new JLabel();
		label.setText(text);
		this.add(label);
		
		JComboBox comboBox = new JComboBox(options.toArray());
		comboBox.setSelectedIndex(selectedIndex);
		comboBox.addActionListener((event)->{
			checkSameIndecies();
		});
		this.add(comboBox, "wrap");
		return comboBox;
	}
	
	public int getSelectedIdColumn()
	{
		return idSelector.getSelectedIndex();
	}
	
	public int getSelectedDescColumn()
	{
		return descSelector.getSelectedIndex();
	}
	
	public int getSelectedPriceColumn()
	{
		return priceSelector.getSelectedIndex();
	}
	
	public String getPath()
	{
		return path;
	}
	
	private void checkSameIndecies()
	{
		if (!isNotTheSameIndeciesSelected())
		{
			this.setBorder(BorderFactory.createLineBorder(Color.red));
		}
		else
		{
			this.setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}
	
	public boolean isNotTheSameIndeciesSelected()
	{
		return getSelectedIdColumn() != getSelectedDescColumn() &&
				getSelectedIdColumn() != getSelectedPriceColumn() &&
				getSelectedDescColumn() != getSelectedPriceColumn();
	}
}
