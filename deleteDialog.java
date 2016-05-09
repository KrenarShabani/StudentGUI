import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class deleteDialog extends SDialog{

	public deleteDialog()
	{
		super();
		dia.setLocationRelativeTo(null);
		dia.setTitle("Remove Student Record");
		dia.add(new JLabel("       Row ID : "));
		JButton delBtn = new JButton("Delete");
		delBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int n = JOptionPane.showConfirmDialog(dia, "Are you sure you want to delete " + getFNText().getText() + "?");
				if(n == JOptionPane.OK_OPTION)
				{
					StudentGui.tblModel.removeRow(StudentGui.tblData.convertRowIndexToModel(StudentGui.tblData.getSelectedRow()));
					resetRowID();
					dia.setVisible(false);
				}
			}
		});
		getFNText().setEditable(false);
		getLNText().setEditable(false);
		getCUNYText().setEditable(false);
		getGPAText().setEditable(false);
		dia.add(getROWText());
		dia.add(delBtn);
		dia.add(getCancelBtn());

	}
	
	void resetRowID()
	{
		for(int i = 0; i < StudentGui.tblData.getModel().getRowCount(); i++)
		{
			StudentGui.tblData.getModel().setValueAt(i+1, i, 0);
		}
	}
	protected void setUp(int row)
	{
		populateDeleteDialog(row);
		dia.setVisible(true);
	}
	private void populateDeleteDialog(int row)
	{
		getFNText().setText((String) StudentGui.tblData.getValueAt(row, 1));
		getLNText().setText((String) StudentGui.tblData.getValueAt(row, 2));
		getCUNYText().setText( (String) StudentGui.tblData.getValueAt(row, 3));
		getGPAText().setText( (String) StudentGui.tblData.getValueAt(row, 4));
		getVENUSText().setText((String) StudentGui.tblData.getValueAt(row, 5));
		
		venusBuilder[0] = getLNText().getText().charAt(0);
		venusBuilder[1] = getLNText().getText().charAt(1);
		venusBuilder[2] = getFNText().getText().charAt(0);
		venusBuilder[3] = getFNText().getText().charAt(1);
		for(int i = 4; i < 8; i++)
		{
			if(getCUNYText().getText().length() > i)
			{
				venusBuilder[i] = getCUNYText().getText().charAt(i);
			}
		}
		
		try{ 
		getROWText().setText((String) (StudentGui.tblData.getValueAt(row,0)));
		}catch(ClassCastException e){
			String text = Integer.toString((int) StudentGui.tblData.getValueAt(row,0));
			getROWText().setText(text);
		}
		getROWText().setEditable(false);
		String test = Utilities.BuildString(venusBuilder);
		getVENUSText().setText(test);
		//dia.setModal(true);
	}
}
