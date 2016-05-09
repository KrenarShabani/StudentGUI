import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class replaceDialog extends SDialog{	


	replaceDialog() {
		super();
		dia.setTitle("Modify selected Student");
		JButton updateBtn = new JButton("Update existing Student");
		updateBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dia.setVisible(false);
				int n =JOptionPane.showConfirmDialog(dia, "Are you sure you want to modify this student?");
				if(n==0)
				{
					if(Utilities.CheckStudent(new String[] {getFNText().getText(), getLNText().getText(),
							getCUNYText().getText(),getGPAText().getText(),getVENUSText().getText()}))
					{
						Object[] newStudent = new Object[6];
						populateStudent(newStudent);
						StudentGui.tblData.setValueAt(getRow()-1, getRow(), 0); // all this does is keep the row ID the same
						StudentGui.tblData.setValueAt(newStudent[1], getRow(), 1);
						StudentGui.tblData.setValueAt(newStudent[2], getRow(), 2);
						StudentGui.tblData.setValueAt(newStudent[3], getRow(), 3);
						StudentGui.tblData.setValueAt(newStudent[4], getRow(), 4);
						StudentGui.tblData.setValueAt(newStudent[5], getRow(), 5);
						nullifyTextFields();
					}
					else
						dia.setVisible(true);
				}
			}
		});
		dia.add(new JLabel("       Row ID : "));
		dia.add(getROWText());
		dia.add(updateBtn);
		dia.add(getCancelBtn());
		//dia.setModal(true);

	}

	protected void setUp(int row)
	{
		populateReplaceDialog(row);
		dia.setVisible(true);
	}


	private void populateReplaceDialog(int row)
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
	}

}
