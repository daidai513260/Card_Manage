
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.border.TitledBorder;


/**                         
* Class:	<CMS_ErrorCollector>                                          
* Comments: Shows the errors occurred in system running                                                                  
* Author:	<Wang Lu>                                      
*/ 
public class CMS_ErrorListControl {
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private int m_x;
	private int m_y;
	private int m_width;
	private int m_height;
	
	public CMS_ErrorListControl(int x, int y, int width, int hight){
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = hight;
		initList();
	}
	
	public JList<String> getInstance(){
		if(list  == null){
			initList();
		}
		
		return list;
	}
	
	private void initList(){
		list = new JList<String>();
		list.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		list.setBounds(m_x, m_y, m_width, m_height);
		
		listModel = new DefaultListModel<String>();
		list.setModel(listModel);
	}
	
	public void removeAllErrorInfo(){
		listModel.removeAllElements();
	}
	
	public void resetErrorInfo(List<String> errorList){
		removeAllErrorInfo();
		
		if(errorList.size() == 0){
			listModel.addElement(CMS_StringTable.STR_NO_ERROR);
			return;
		}
		
		for(String error : errorList){
			listModel.addElement(error);
		}
		
	}
}
