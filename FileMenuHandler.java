import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileMenuHandler implements ActionListener {
   JFrame jframe;
   JLabel lblOutput;
   public FileMenuHandler (JFrame jf) {
      jframe = jf;
   }
   public void actionPerformed(ActionEvent event) {
	   switch(event.getActionCommand())
	   {
		case "Open":
			StudentGui.jfc.setDialogTitle("Choose a file to read: ");
			int jfcResult = StudentGui.jfc.showOpenDialog(null); //Pass null to center the dialog
			if(jfcResult == JFileChooser.CANCEL_OPTION)
			{
				return;
			}
			if(jfcResult == JFileChooser.APPROVE_OPTION)
			{
				System.out.println(("Selected filename is " + StudentGui.jfc.getSelectedFile().getAbsolutePath()));
				
				try
				{
					FileReader fr = new FileReader(StudentGui.jfc.getSelectedFile());
					Scanner read = new Scanner (fr);

					int rowID = StudentGui.tblModel.getRowCount();
					while(read.hasNextLine()){
						String StudentRaw = read.nextLine();
						Object[] StudentData = new Object[StudentGui.tblData.getColumnCount()];
						int index =1;
						for(String token: StudentRaw.split(",")) 
						{
							if(token.charAt(0) == ' ')
								token = token.substring(1);
							StudentData[index] = token;
							index++;
						}
						rowID++;
						StudentData[0] = (int)rowID;
						StudentGui.tblModel.addRow(StudentData);
					}
					read.close();
				}
				
				catch (FileNotFoundException ex)
				{
					lblOutput.setText("File is not found");
				} catch (@SuppressWarnings("hiding") IOException e1) {
					// TODO Auto-generated catch block
					lblOutput.setText("There was an IO Exception that was caught. Error: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
			else
			{
				lblOutput.setText("No file selected.");
			}
		break;
	   		case ("Quit"):
	   			System.exit(0);
	   			break;
	   		case ("Export"):
	   			if(StudentGui.tblData.getRowCount() != 0)
		   		{
					StudentGui.jfc.setDialogTitle("Choose a file to : ");
					int jfcResult1 = StudentGui.jfc.showSaveDialog(null); 
					if(jfcResult1 == JFileChooser.CANCEL_OPTION)
					{
						return;
					}
					if(jfcResult1 == JFileChooser.APPROVE_OPTION)
					{
						System.out.println(("Selected filename is " + StudentGui.jfc.getSelectedFile().getAbsolutePath()));
						
						try
						{
							FileWriter write = new FileWriter (StudentGui.jfc.getSelectedFile());
							for(int i = 0; i < StudentGui.tblData.getRowSorter().getViewRowCount(); i++)
							{
								for(int j = 1; j < StudentGui.tblData.getColumnCount()-1; j++)
								{
									try{
									write.write((String) StudentGui.tblData.getModel().getValueAt(StudentGui.tblData.convertRowIndexToModel(i), j));
									}catch(ClassCastException e){
										write.write((int) StudentGui.tblData.getModel().getValueAt(StudentGui.tblData.convertRowIndexToModel(i), j));
									}
									write.write(", ");
								}
								write.write((String) StudentGui.tblData.getModel().getValueAt(StudentGui.tblData.convertRowIndexToModel(i), StudentGui.tblData.getColumnCount()-1));
								write.write(System.lineSeparator());
							}
							write.close();
						}
						
						catch (FileNotFoundException ex)
						{
							lblOutput.setText("File is not found");
						} catch (IOException e1) {
							lblOutput.setText("There was an IO Exception that was caught. Error: " + e1.getMessage());
							e1.printStackTrace();
						}
					}
	   			}
	   			else 
	   				JOptionPane.showMessageDialog(jframe, "Nothing to export");
	   			break;
	   		case("About"):
	   			JOptionPane.showMessageDialog(jframe, "a \"\"simple\"\" gui i made",
	   					"About", JOptionPane.QUESTION_MESSAGE);
	   			break;
      }
   } 
}