import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ButtonHandler implements ActionListener {
	
	static char[] venusBuilder = new char[8];
	static boolean isEmptyFn = true;
	String VenusText;
	JDialog addDialog = new JDialog();
	DefaultTableModel tblModel;
	JFrame jframe;
	JTable tblData;
	JTextField FNText = new JTextField(15);
	JTextField LNText = new JTextField(15);
	JTextField CUNYText = new JTextField(15);
	JTextField GPAText = new JTextField(15);
	JTextField VENUSText = new JTextField(15);
	Component[] comps;
	JButton cancelBtn;
	JButton addBtn;
	public void setTable(DefaultTableModel tbl)
	{
		tblModel = tbl;
	}
	public ButtonHandler(JFrame jf) {
		jframe = jf;
		addDialog.setTitle("Create new Student Record");
		addDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addDialog.setSize(300, 250);
		addDialog.setLayout(new FlowLayout());
		addDialog.add(new JLabel(" First Name :"));
		addDialog.add(FNText);
		addDialog.add(new JLabel(" Last Name :"));
		addDialog.add(LNText);
		addDialog.add(new JLabel("        Cuny ID :"));
		addDialog.add(CUNYText);
		addDialog.add(new JLabel("              GPA :"));
		addDialog.add(GPAText);
		addDialog.add(new JLabel("Venus Login:"));
		addDialog.add(VENUSText);
		
		

		VENUSText.setEditable(false);
		FNText.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}	
			@Override
			public void keyReleased(KeyEvent e) {setChars(2,3,e,FNText);}
		});
		LNText.addKeyListener(new KeyListener()
		{
			@Override
			public void keyReleased(KeyEvent e) {setChars(0,1,e,LNText);}
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e){}		
		});
		CUNYText.addKeyListener(new KeyListener()


		{
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
				{
						for(int i =4 ; i < 8; i++)
						{
							if(CUNYText.getText().length() < 8)
								venusBuilder[i] = ' ';
						}
				}
				for(int i = 4; i < 8; i++)
				{
					if(CUNYText.getText().length() > i)
					{
						venusBuilder[i] = CUNYText.getText().charAt(i);
					}
				}
				String test = Utilities.BuildString(venusBuilder);
				VENUSText.setText(test);
			}
		});
		
		JButton addBtn = new JButton("Add new Student");
		addBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				int n;
				addDialog.setVisible(false);
				//need to make my own confirm button
				n = JOptionPane.showConfirmDialog(jframe, "Are you sure you want to add this student?");
				if(n==0)
				{
					if(Utilities.CheckStudent(new String[] {FNText.getText(), LNText.getText(),
							CUNYText.getText(),GPAText.getText(),VENUSText.getText()}))
					{
					Object[] newStudent = new Object[6];
					newStudent[0] = tblModel.getRowCount()+1;
					newStudent[1] = FNText.getText();
					newStudent[2] = LNText.getText();
					newStudent[3] = CUNYText.getText();
					newStudent[4] = GPAText.getText();
					newStudent[5] = VENUSText.getText();
					tblModel.addRow(newStudent);
					addDialog.dispose();
					addDialog.setVisible(false);
					FNText.setText(null);
					LNText.setText(null);
					CUNYText.setText(null);
					GPAText.setText(null);
					VENUSText.setText(null);
					addDialog.dispose();
					addDialog.setVisible(false);
					}
					else
						addDialog.setVisible(true);
				}
			}
		});
		addDialog.add(addBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				FNText.setText(null);
				LNText.setText(null);
				CUNYText.setText(null);
				GPAText.setText(null);
				VENUSText.setText(null);
				addDialog.dispose();
				addDialog.setVisible(false);
			}
		});
		addDialog.add(cancelBtn);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
		case ("Add"):
			for(int i = 0; i < venusBuilder.length; i++)
			{
				venusBuilder [i] = ' ';
			}
		addDialog = new JDialog();
		addDialog.setTitle("Create new Student Record");
		addDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addDialog.setSize(300, 250);
		addDialog.setLayout(new FlowLayout());
		addDialog.add(new JLabel(" First Name :"));
		addDialog.add(FNText);
		addDialog.add(new JLabel(" Last Name :"));
		addDialog.add(LNText);
		addDialog.add(new JLabel("        Cuny ID :"));
		addDialog.add(CUNYText);
		addDialog.add(new JLabel("              GPA :"));
		addDialog.add(GPAText);
		addDialog.add(new JLabel("Venus Login:"));
		addDialog.add(VENUSText);

			JButton addBtn = new JButton("Add new Student");
			addBtn.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					int n;
					addDialog.setVisible(false);
					//need to make my own confirm button
					n = JOptionPane.showConfirmDialog(jframe, "Are you sure you want to add this student?");
					if(n==0)
					{
						if(Utilities.CheckStudent(new String[] {FNText.getText(), LNText.getText(),
								CUNYText.getText(),GPAText.getText(),VENUSText.getText()}))
						{
						Object[] newStudent = new Object[6];
						newStudent[0] = tblModel.getRowCount()+1;
						newStudent[1] = FNText.getText();
						newStudent[2] = LNText.getText();
						newStudent[3] = CUNYText.getText();
						newStudent[4] = GPAText.getText();
						newStudent[5] = VENUSText.getText();
						tblModel.addRow(newStudent);
						addDialog.dispose();
						addDialog.setVisible(false);
						FNText.setText(null);
						LNText.setText(null);
						CUNYText.setText(null);
						GPAText.setText(null);
						VENUSText.setText(null);
						addDialog.dispose();
						addDialog.setVisible(false);
						}
						else
							addDialog.setVisible(true);
					}
				}
			});
			addDialog.add(addBtn);
			
			JButton cancelBtn = new JButton("Cancel");
			cancelBtn.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					FNText.setText(null);
					LNText.setText(null);
					CUNYText.setText(null);
					GPAText.setText(null);
					VENUSText.setText(null);
					addDialog.dispose();
					addDialog.setVisible(false);
				}
			});
			addDialog.add(cancelBtn);
			addDialog.setLocationRelativeTo(jframe);
			addDialog.setModal(true);
			addDialog.setVisible(true);
			break;
		case ("Delete"):
			if(tblData != null)
			if(tblData.getSelectedRow() >=0)
			{
				StringBuilder sb = new StringBuilder();
				int rowID = tblData.convertRowIndexToModel(tblData.getSelectedRow());
				tblModel.getColumnCount();
				for(int i = 0; i < tblModel.getColumnCount(); i++)
				{
					
					sb.append("\r\n" + tblModel.getColumnName(i) + " : " +  tblModel.getValueAt(rowID, i) + " ");
				}
				int result = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to delete this person's data?" + sb.toString()
						);
				if(result == JOptionPane.OK_OPTION)
				{
					tblModel.removeRow(tblData.getSelectedRow());
					resetRowID();
				}
			}
			break;
		}
	}
	void setChars(int index1, int index2, KeyEvent e, JTextField text)
	{
		if(e!= null)
		if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
		{
			if(text.getText().length() == 1)
				venusBuilder[index2] = ' ';
			else if (text.getText().length() == 0)
			{
				venusBuilder[index2] = ' ';
				venusBuilder [index1] = ' ';
			}
		}
		if(text.getText().length() == 1)
			venusBuilder[index1] = text.getText().charAt(0);
		else if(text.getText().length() >= 2)
		{
			venusBuilder[index1] = text.getText().charAt(0);
			venusBuilder[index2] = text.getText().charAt(1);
		}
		String test = Utilities.BuildString(venusBuilder);
		VENUSText.setText(test);
		
	}
	void castMessage(String message) {
		JOptionPane.showMessageDialog(jframe, "you clicked on " + message);
	}
	void SetJTable(JTable jt)
	{
		tblData = jt;
	}
	void updateStudentDialog(int row)
	{
		JDialog replace = new JDialog();
		FNText.setText((String) tblData.getValueAt(row, 1));
		System.out.println((String) tblData.getValueAt(row, 1));
		LNText.setText((String) tblData.getValueAt(row, 2));
		CUNYText.setText( (String) tblData.getValueAt(row, 3));
		GPAText.setText( (String) tblData.getValueAt(row, 4));
		VENUSText.setText((String) tblData.getValueAt(row, 5));
		replace.setTitle("Create new Student Record");
		replace.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		replace.setSize(300, 250);
		replace.setLayout(new FlowLayout());
		replace.add(new JLabel("       Row ID : "));
		JTextField ROWText = new JTextField(15);
		try{ 
		ROWText.setText((String) (tblData.getValueAt(row,0)));
		}catch(ClassCastException e){
			String text = Integer.toString((int) tblData.getValueAt(row,0));
			ROWText.setText(text);
		}
		ROWText.setEditable(false);
		replace.add(ROWText);
		
		replace.add(new JLabel(" First Name :"));
		replace.add(FNText);
		replace.add(new JLabel(" Last Name :"));
		replace.add(LNText);
		replace.add(new JLabel("        Cuny ID :"));
		replace.add(CUNYText);
		replace.add(new JLabel("              GPA :"));
		replace.add(GPAText);
		replace.add(new JLabel("Venus Login:"));
		replace.add(VENUSText);
		replace.setLocationRelativeTo(jframe);
		replace.setVisible(true);
		CUNYText.addKeyListener(new KeyListener()



		{
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
				{
						for(int i =4 ; i < 8; i++)
						{
							if(CUNYText.getText().length() < 8)
								venusBuilder[i] = ' ';
						}
				}
				for(int i = 4; i < 8; i++)
				{
					if(CUNYText.getText().length() > i)
					{
						venusBuilder[i] = CUNYText.getText().charAt(i);
					}
				}
				String test = Utilities.BuildString(venusBuilder);
				VENUSText.setText(test);
			}
		});
		setChars(2,3,null,FNText);
		setCunyText(CUNYText);
		setChars(0,1,null,LNText);
		JButton updateBtn = new JButton("Update existing Student");
		updateBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				replace.setVisible(false);
				int n =JOptionPane.showConfirmDialog(jframe, "Are you sure you want to modify this student?");
				if(n==0)
				{
					if(Utilities.CheckStudent(new String[] {FNText.getText(), LNText.getText(),
							CUNYText.getText(),GPAText.getText(),VENUSText.getText()}))
					{
					Object[] newStudent = new Object[6];
					newStudent[0] = tblModel.getRowCount()+1;
					newStudent[1] = FNText.getText();
					newStudent[2] = LNText.getText();
					newStudent[3] = CUNYText.getText();
					newStudent[4] = GPAText.getText();
					newStudent[5] = VENUSText.getText();
					//tblModel.removeRow(row);
					tblData.setValueAt(row-1, row, 1);
					tblData.setValueAt(newStudent[1], row, 1);
					tblData.setValueAt(newStudent[2], row, 2);
					tblData.setValueAt(newStudent[3], row, 3);
					tblData.setValueAt(newStudent[4], row, 4);
					tblData.setValueAt(newStudent[5], row, 5);
					tblModel.addRow(newStudent);
					FNText.setText(null);
					LNText.setText(null);
					CUNYText.setText(null);
					GPAText.setText(null);
					VENUSText.setText(null);
					}
					else
						replace.setVisible(true);
				}
			}
			
		});
		replace.add(updateBtn);
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				FNText.setText(null);
				LNText.setText(null);
				CUNYText.setText(null);
				GPAText.setText(null);
				VENUSText.setText(null);
				replace.dispose();
				replace.setVisible(false);
			}
		});
		replace.add(cancelBtn);
	}
	void setCunyText(JTextField text)
	{
		for(int i = 4; i < 8; i++)
		{
			if(CUNYText.getText().length() > i)
			{
				venusBuilder[i] = CUNYText.getText().charAt(i);
			}
		}
		String test = Utilities.BuildString(venusBuilder);
		VENUSText.setText(test);
	}
	void resetRowID()
	{
		for(int i = 0; i < tblData.getModel().getRowCount(); i++)
		{
			tblData.getModel().setValueAt(i+1, i, 0);
		}
	}
}
