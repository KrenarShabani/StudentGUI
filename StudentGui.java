import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Comparator;
public class StudentGui extends JFrame {
     
	private static final long serialVersionUID = 1L;
	protected static JTable tblData;
	protected DefaultTableModel tblModel;
    protected JMenuItem   item;
    protected JMenuBar    menuBar  = new JMenuBar();
    protected JMenu		  helpMenu = new JMenu("Help");
    protected JMenu       fileMenu = new JMenu("File");
    protected Component[] comps = {this, tblData};
    protected JTextField tInput = new JTextField(10);
    protected JComboBox<String> searchOptions;
    JFileChooser jfc = new JFileChooser();
    FileMenuHandler fmh  = new FileMenuHandler(this,jfc);
    ButtonHandler bh = new ButtonHandler(this);

	public StudentGui(String title, int height, int width) {
	    setTitle(title);
	    setSize(height,width);
	    setLocation  (400,200);
	    createFileMenu();
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
   } //SSNGUI

   private void createFileMenu( ) {

	  
      setLayout(new FlowLayout());

     // JTextField tInput = new JTextField(10);
      //TextArea 	  text = new TextArea();
      JLabel search = new JLabel("Search by:");
      add(search);
      String[] options = {"Row ID", "First Name", "Last Name", "Cuny ID", "GPA", "Venus Login" };
      searchOptions = new JComboBox<String>(options);
      

      add(searchOptions);
      
      add (tInput);
      
      JButton JBtn = new JButton("Add");
      JBtn.addActionListener(bh);
      add(JBtn);
      
      JBtn = new JButton("Delete");
      JBtn.addActionListener(bh);
      add(JBtn);
     
      item = new JMenuItem("About");
      KeyStroke keyToAbout = KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
      item.setAccelerator(keyToAbout);
      item.addActionListener(fmh);
      helpMenu.add(item);

      item = new JMenuItem("Open");    //Open...
      KeyStroke keyToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
      item.setAccelerator(keyToOpen);
      item.addActionListener( fmh );
      fileMenu.add( item );

      item = new JMenuItem("Export");
      KeyStroke keyToExport = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
      item.setAccelerator(keyToExport);
      item.addActionListener( fmh );
      fileMenu.add( item );
      fileMenu.addSeparator();           //add a horizontal separator line
    
      item = new JMenuItem("Quit"); //Quit
      KeyStroke keyToQuit = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
      item.setAccelerator(keyToQuit);
      item.addActionListener( fmh );
      fileMenu.add( item );
      menuBar.add(fileMenu);
      menuBar.add(helpMenu);

      setJMenuBar(menuBar);

      createDataMenu(options);
      JBtn = new JButton ("Export");
      JBtn.addActionListener(bh);
      add(JBtn);

   } //createMenu

   protected void createDataMenu(String[] Data)
   {
	   tblData = new JTable() //i swear java is such a hackey language
	   {
		private static final long serialVersionUID = 1L;

		@Override
		   public boolean isCellEditable(int row, int column)
		   {
			   return false;
		   }
	   };
	   JScrollPane jspData = new JScrollPane(tblData);
	   /////
	   tblModel = (DefaultTableModel)tblData.getModel();
	   fmh.setTable(tblModel);
	   bh.setTable(tblModel);
	   for(int i = 0; i < Data.length; i++)
	   {
		   tblModel.addColumn(Data[i]);
	   }
	   TableRowSorter<TableModel> sorter = 
			   new TableRowSorter<TableModel>(tblData.getModel());
	   tblData.setRowSorter(sorter);
	   
	   sorter.addRowSorterListener(new RowSorterListener()
	   {
		   @Override
			public void sorterChanged(RowSorterEvent e) {}
	   });

	      tInput.addKeyListener(new KeyListener()
	      {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {newFilter(sorter);}  
	      });
	   bh.SetJTable(tblData);
	   
	   tblData.addMouseListener(new MouseListener()
	   {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
	    	{
	    		if(e.getClickCount() == 2)
	    		if(tblData.getSelectedRow() != -1)
	    		{
	    			bh.updateStudentDialog(tblData.getSelectedRow());
	    		}
	    	}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
	    

			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		   
		   
	   });
	   add(jspData);
	   
   }
    void newFilter(TableRowSorter<TableModel> sor)
   {
	   RowFilter<? super TableModel, ? super Integer> rf = null; 
	   try
	   {
		   int index = searchOptions.getSelectedIndex();
		   rf = RowFilter.regexFilter(tInput.getText(),index);
		   sor.setRowFilter(rf);
		   //resetRowIndex();
	   }catch(java.util.regex.PatternSyntaxException e)
	   {
		   return;
	   }
   }

    
} //SSNGUI