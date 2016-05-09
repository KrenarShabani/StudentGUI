import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class addDialog extends SDialog{

	addDialog() {
		super();
		JButton addBtn = new JButton("Add new Student");
		dia.setTitle("Add a new Student");
		addBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				int n;
				dia.setVisible(false);
				//need to make my own confirm button
				n = JOptionPane.showConfirmDialog(dia, "Are you sure you want to add this student?");
				if(n==0)
				{
					if(Utilities.CheckStudent(new String[] {getFNText().getText(), getLNText().getText(),
							getCUNYText().getText(),getGPAText().getText(),getVENUSText().getText()}))
					{
					Object[] newStudent = new Object[6];
					populateStudent(newStudent);
					StudentGui.tblModel.addRow(newStudent);
					dia.dispose();
					dia.setVisible(false);
					nullifyTextFields();
					dia.dispose();
					dia.setVisible(false);
					}
					else
						dia.setVisible(true);
				}
				else
					dia.setVisible(true);

			}

		});
		dia.add(addBtn);
		dia.add(getCancelBtn());
		//dia.setModal(true);
	}

}
