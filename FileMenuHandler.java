


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileMenuHandler implements ActionListener {
   JFrame jframe;
   JFileChooser jfc;
   JLabel lblOutput;
   JTextArea txtFileOutput;
   DefaultTableModel tblModel;
   public FileMenuHandler (JFrame jf, JFileChooser jfilechooser) {
      jframe = jf;
      jfc = jfilechooser;
   }
   public void setTable(DefaultTableModel tbl)
   {
	   tblModel = tbl;
   }
   public void actionPerformed(ActionEvent event) {
	   switch(event.getActionCommand())
	   {
		case "Open":
			jfc.setDialogTitle("Choose a file to read: ");
			int jfcResult = jfc.showOpenDialog(null); //Pass null to center the dialog
			if(jfcResult == JFileChooser.CANCEL_OPTION)
			{
				return;
			}
			if(jfcResult == JFileChooser.APPROVE_OPTION)
			{
				System.out.println(("Selected filename is " + jfc.getSelectedFile().getAbsolutePath()));
				
				try
				{
					FileReader fr = new FileReader(jfc.getSelectedFile());
					Scanner read = new Scanner (fr);

					//String x = StudentRaw;
					//int size = StudentRaw.length() - x.replaceAll(",", "").length() + 1; //this is so i can adjust the size of my array without "hardcoding" it
					int rowID = tblModel.getRowCount();
					while(read.hasNextLine()){
						String StudentRaw = read.nextLine();
						Object[] StudentData = new Object[6]; // i hardcoded anyways
						int index =1;
						for(String token: StudentRaw.split(",")) 
						{
							if(token.charAt(0) == ' ')
								token = token.substring(1);
							StudentData[index] = token;
							//System.out.println(token);
							//System.out.println(StudentData.length);
							index++;
						}
						rowID++;
						StudentData[0] = rowID;
						tblModel.addRow(StudentData);
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
	   		case ("Find"):
	   			JOptionPane.showMessageDialog(jframe, "You clicked on Find",
	   					"Edit",
	   					JOptionPane.DEFAULT_OPTION);
	   			break;
	   		case ("Export"):
				jfc.setDialogTitle("Choose a file to read: ");
				int jfcResult1 = jfc.showSaveDialog(jframe); //Pass null to center the dialog
				if(jfcResult1 == JFileChooser.CANCEL_OPTION)
				{
					return;
				}
				if(jfcResult1 == JFileChooser.APPROVE_OPTION)
				{
					System.out.println(("Selected filename is " + jfc.getSelectedFile().getAbsolutePath()));
					
					try
					{
						FileWriter write = new FileWriter (jfc.getSelectedFile());
						for(int i = 0; i < StudentGui.tblData.getRowCount(); i++)
						{
							for(int j = 1; j < StudentGui.tblData.getColumnCount()-1; j++)
							{
								try{
								write.write((String) StudentGui.tblData.getModel().getValueAt(i, j));
								}catch(ClassCastException e){
									write.write((int) StudentGui.tblData.getModel().getValueAt(i, j));
								}
								write.write(", ");
							}
							write.write((String) StudentGui.tblData.getModel().getValueAt(i, 5));
							write.write(System.lineSeparator());
						}
						write.close();
					}
					
					catch (FileNotFoundException ex)
					{
						lblOutput.setText("File is not found");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						lblOutput.setText("There was an IO Exception that was caught. Error: " + e1.getMessage());
						e1.printStackTrace();
					}
				}
				else
				{
					System.out.println("DSJIDS");
				}
	   			break;
	   		case("About"):
	   			JOptionPane.showMessageDialog(jframe, "a \"\"simple\"\" gui i made",
	   					"About", JOptionPane.QUESTION_MESSAGE);
	   			break;
      }
   } //actionPerformed
}