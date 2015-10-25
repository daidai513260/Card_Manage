import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.List;

import javax.swing.JScrollPane;


/**                         
* Class:	<CMS_Dialog>                                          
* Comments:
* 1) Having a button "open" for load xml records into system
* 2) Having a table for showing the records
* 3) Having a list for showing all errors while analyzing data                                                                       
* Author:	<Wang Lu>                                      
*/ 
public class CMS_Dialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private final JPanel defaultPane = new JPanel();
	private JMenuItem menuItem_Open;
	private JMenuBar menuBar;

	private CMS_View parentView;
	private CMS_ErrorListControl cmsErrorList;
	
	private CMS_Listener actionListener;

	CMS_DataTableControl cmsTableControl;
	/**
	 * Create the dialog.
	 */
	public CMS_Dialog(CMS_View view) {
		setResizable(false);
		actionListener = new CMS_Listener();
		parentView = view;
		InitDialog();
	}
	
	private void InitDialog(){
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//set the dialog style to current system's style;
		
			setBounds(100, 100, 842, 659);
			getContentPane().setLayout(new BorderLayout());
			defaultPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			getContentPane().add(defaultPane, BorderLayout.CENTER);
			defaultPane.setLayout(null);
			
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 826, 21);	
			defaultPane.add(menuBar);
			{
				JMenu mnFilef = new JMenu(CMS_StringTable.STR_FILE);
				mnFilef.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				mnFilef.setMnemonic(KeyEvent.VK_F); 
				menuBar.add(mnFilef);
				{
					menuItem_Open = new JMenuItem(CMS_StringTable.STR_OPEN);
					menuItem_Open.setMnemonic(KeyEvent.VK_O); 
					menuItem_Open.addActionListener(actionListener);
					mnFilef.add(menuItem_Open);
				}
			}
			
			 
			InitTable();
			
			InitErrorList();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//create a table for showing the records
	private void InitTable(){ 
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 806, 361);
		
		defaultPane.add(scrollPane);
		
		cmsTableControl = new CMS_DataTableControl(10, 31, 806, 361);
		
		scrollPane.setViewportView(cmsTableControl.getInstance());
		
	}
	
	//create a list for showing the errors
	private void InitErrorList(){
		cmsErrorList = new CMS_ErrorListControl(10, 402, 800, 218);	
		
		defaultPane.add(cmsErrorList.getInstance());
		
	}
	
	
	public void resetData(List<CMS_Data> dataList, List<String> errorList){
		cmsTableControl.resetCMSData(dataList);
		cmsErrorList.resetErrorInfo(errorList);
	}
	
	private class CMS_Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object source = e.getSource();
			if(source == menuItem_Open){
				String path;
				JFileChooser jDir = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						 "*.xml","xml");  
				jDir.setFileFilter(filter);
				if(jDir.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					path = jDir.getSelectedFile().getAbsolutePath();
					
					parentView.OnNotify(CMS_Message.MSG_OPEN_FILE, path);		
				}
		
			}
		}
	}
}
