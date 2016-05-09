
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public abstract class SDialog {
	
	protected JDialog dia = new JDialog();
	protected char[] venusBuilder = new char[8];
	private JTextField FNText = new JTextField(15);
	private JTextField LNText = new JTextField(15);
	private JTextField CUNYText = new JTextField(15);
	private JTextField GPAText = new JTextField(15);
	private JTextField VENUSText = new JTextField(15);
	private JTextField ROWText = new JTextField(15);
	private JButton cancelBtn;
	
	public SDialog()
	{			
			
			dia.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dia.addWindowListener(new WindowListener()
			{
				public void windowClosed(WindowEvent e) {nullifyTextFields();}
				public void windowOpened(WindowEvent e) {}
				public void windowClosing(WindowEvent e) {}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowActivated(WindowEvent e) {}
				public void windowDeactivated(WindowEvent e) {}
			});
			dia.setSize(300, 250);
			dia.setLocationRelativeTo(StudentGui.tblData);
			
			dia.setLayout(new FlowLayout());
			dia.setVisible(false);
			
			dia.add(new JLabel(" First Name :"));
			dia.add(FNText);
			dia.add(new JLabel(" Last Name :"));
			dia.add(LNText);
			dia.add(new JLabel("        Cuny ID :"));
			dia.add(CUNYText);
			dia.add(new JLabel("              GPA :"));
			dia.add(GPAText);
			dia.add(new JLabel("Venus Login:"));
			dia.add(VENUSText);
			dia.setModal(true);
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
				public void keyTyped(KeyEvent e) {}
				public void keyPressed(KeyEvent e) {}
				@Override
				public void keyReleased(KeyEvent e) {setChars(0,1,e,LNText);}
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

			cancelBtn = new JButton("Cancel");
			cancelBtn.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					nullifyTextFields();
					dia.dispose();
					dia.setVisible(false);
				}
			});
			//dia.add(cancelBtn); 
	}	
	
	//getters
	protected JTextField getFNText(){return FNText;}
	protected JTextField getLNText(){return LNText;}
	protected JTextField getCUNYText(){return CUNYText;}
	protected JTextField getGPAText(){return GPAText;}
	protected JTextField getVENUSText(){return VENUSText;}
	protected JTextField getROWText(){return ROWText;}
	protected JButton getCancelBtn(){return cancelBtn;}
	
	void nullifyTextFields()
	{
		FNText.setText(null);
		LNText.setText(null);
		CUNYText.setText(null);
		GPAText.setText(null);
		VENUSText.setText(null);
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
	void populateStudent(Object[] student)
	{
		student[0] = StudentGui.tblModel.getRowCount()+1;
		student[1] = getFNText().getText();
		student[2] = getLNText().getText();
		student[3] = getCUNYText().getText();
		student[4] = getGPAText().getText();
		student[5] = getVENUSText().getText();	
	}
	protected int getRow()
	{
		return StudentGui.tblData.convertRowIndexToModel(StudentGui.tblData.getSelectedRow());
	}
}
