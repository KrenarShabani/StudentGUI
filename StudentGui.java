import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.KeyEvent;
public class StudentGui extends JFrame {
     
	private static final long serialVersionUID = 1L;
	protected JTable tblData;
	protected DefaultTableModel tblModel;
    protected JMenuItem   item;
    protected JMenuBar    menuBar  = new JMenuBar();
    protected JMenu		  helpMenu = new JMenu("Help");
    protected JMenu       fileMenu = new JMenu("File");
    protected Component[] comps = {this, tblData};
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
      JComboBox<String> searchOptions = new JComboBox<String>(options);
      searchOptions.setSelectedIndex(options.length-1);
      add(searchOptions);
      
      JTextField tInput = new JTextField(10);
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
	   bh.SetJTable(tblData);
	   add(jspData);
   }




} //SSNGUI