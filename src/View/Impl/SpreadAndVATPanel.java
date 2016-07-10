package View.Impl;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import View.Labels;
import View.Interface.UIBuilder;

public class SpreadAndVATPanel extends JPanel implements UIBuilder 
{
	private String spread = "1.1";
	private String VAT = "1.27";
	
	private JLabel spreadLabel;
	private JLabel VATLabel;
	
	private JFormattedTextField spreadTextField;
	private JFormattedTextField VATTextField;
	
	@Override
	public void createPanel() 
	{
		this.setLayout(new MigLayout());
		NumberFormat amountFormat = NumberFormat.getNumberInstance();
		createAndAddLabel(spreadLabel, Labels.SPREAD);
		spreadTextField = new JFormattedTextField(amountFormat);
		createAndAddTextField(spreadTextField, spread);
		createAndAddLabel(VATLabel, Labels.VAT);
		VATTextField = new JFormattedTextField(amountFormat);
		createAndAddTextField(VATTextField, VAT);
	}
	
	private void createAndAddLabel(JLabel label, String text)
	{
		label = new JLabel(text);
		this.add(label, "split 2");
		
	}
	
	private void createAndAddTextField(JFormattedTextField textField, String value)
	{
		textField.setValue(new Float(Float.parseFloat(value)));
		textField.setColumns(10);
		this.add(textField);
	}
	
	public float getSpread()
	{
		String value = String.valueOf(spreadTextField.getValue());
		return Float.valueOf(value);
	}
	
	public float getVAT()
	{
		String value = String.valueOf(VATTextField.getValue());
		return Float.valueOf(value);
	}

}
