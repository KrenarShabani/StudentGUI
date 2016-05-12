import javax.swing.*;
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
public class StudentGui extends JFrame {

	private static final long serialVersionUID = 1L;
	static JTable tblData;
	static DefaultTableModel tblModel;
	static JMenuItem   item;
	static JMenuBar    menuBar  = new JMenuBar();
	static JMenu		  helpMenu = new JMenu("Help");
	static JMenu       fileMenu = new JMenu("File");
	static JTextField tInput = new JTextField(10);
	static JComboBox<String> searchOptions;
	static JFileChooser jfc = new JFileChooser();

	protected static addDialog add = new addDialog();
	protected static replaceDialog rep = new replaceDialog();
	protected static deleteDialog del = new deleteDialog();

	FileMenuHandler fmh  = new FileMenuHandler(this);
	// i hope making everything static is fine
	public StudentGui(String title, int height, int width) {
		setTitle(title);
		setSize(height,width);
		setLocation  (400,200);
		createFileMenu();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void createFileMenu( ) {
		this.setLocationRelativeTo(null);

		setLayout(new FlowLayout());

		JLabel search = new JLabel("Search by:");
		add(search);
		String[] options = {"Row ID", "First Name", "Last Name", "Cuny ID", "GPA", "Venus Login" };
		searchOptions = new JComboBox<String>(options);

		add(searchOptions);

		add (tInput);

		JButton JBtn = new JButton("Add");
		JBtn.setMnemonic(KeyEvent.VK_N);
		JBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				add.dia.setVisible(true);
			}
		});

		add(JBtn);

		JBtn = new JButton("Delete");
		JBtn.setMnemonic(KeyEvent.VK_D);
		JBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(StudentGui.tblData.getSelectedRow() != -1)
					del.setUp(StudentGui.tblData.getSelectedRow());
				else
					JOptionPane.showMessageDialog(tblData, "No Student Selected");
			}
		});
		add(JBtn);

		item = new JMenuItem("About");
		KeyStroke keyToAbout = KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
		item.setAccelerator(keyToAbout);
		item.addActionListener(fmh);
		helpMenu.add(item);

		item = new JMenuItem("Open");   
		KeyStroke keyToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
		item.setAccelerator(keyToOpen);
		item.addActionListener( fmh );
		fileMenu.add( item );

		item = new JMenuItem("Export");
		KeyStroke keyToExport = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
		item.setAccelerator(keyToExport);
		item.addActionListener( fmh );
		fileMenu.add( item );
		fileMenu.addSeparator();          

		item = new JMenuItem("Quit"); 
		KeyStroke keyToQuit = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
		item.setAccelerator(keyToQuit);
		item.addActionListener( fmh );
		fileMenu.add( item );
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		createDataMenu(options);
		JBtn = new JButton ("Export");
		JBtn.addActionListener( fmh );
		add(JBtn);
		

	} 

	protected void createDataMenu(String[] Data)
	{
		tblData = new JTable() 
		{
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}

		};
		
		
		tblData.setModel(new DefaultTableModel()
		{
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column)
			{
				switch(column)
				{
				case 0:
					return Integer.class;
					
			//	case 3:
				//	return Integer.class;
					
					
				default:
					return String.class;
				}
				
			}
			
		});
		
		JScrollPane jspData = new JScrollPane(tblData);
		/////
		tblModel = (DefaultTableModel)tblData.getModel();
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
			public void keyReleased(KeyEvent e) {if(e.getKeyCode() == KeyEvent.VK_ENTER)newFilter(sorter);}  
		});

		tblData.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				{
					if(e.getClickCount() == 2 & tblData.getSelectedRow() != -1)
					{
						rep.setUp(tblData.getSelectedRow());
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e){}
		});
		add(jspData);

	}
	/*void newSorter(TableRowSorter<TableModel> sor)
	{
		RowSorter<? super Integer> rf = null;
		try
		{
			
			
		}
		
	}*/
	void newFilter(TableRowSorter<TableModel> sor)
	{
		
		RowFilter<? super TableModel, ? super Integer> rf = null; 
		try
		{
			
			int index = searchOptions.getSelectedIndex();
			rf = RowFilter.regexFilter(tInput.getText(),index);
			sor.setRowFilter(rf);
			System.out.println("enter");
		}catch(java.util.regex.PatternSyntaxException e)//return if user enters a string unknown in tblData
		{
			return;
		}
	}  
} 