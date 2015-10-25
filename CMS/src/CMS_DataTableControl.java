import java.awt.Color;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//Customized the JTable for card manage system
public class CMS_DataTableControl {
	private final int COL_FIRST_NAME = 0;
	private final int COL_MID_NAME = 1;
	private final int COL_LAST_NAME = 2;
	private final int COL_CARD_NUMBER = 3;
	private final int COL_EXP_MONTH = 4;
	private final int COL_EXP_YEAR = 5;
	private final int COL_SERVICE_CODE = 6;

	private JTable table;
	private DefaultTableModel tableModel;
	
	private int m_x;
	private int m_y;
	private int m_width;
	private int m_height;
	
	public CMS_DataTableControl(int x, int y, int width, int hight){
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = hight;
		initTable();
	}
	
	//return table instance, if there is no table instance, create one
	public JTable getInstance(){
		if(table  == null){
			initTable();
		}
		
		return table;
	}
	
	//create the table instance
	private void initTable(){
		//customize the table header's look and view.
		UIManager.put("TableHeader.background", Color.LIGHT_GRAY);
		
		//customize the table's look and view.
		Color alternateRowColor = new Color(242,242,242);
		UIManager.put("Table.alternateRowColor", alternateRowColor);
		
		Object[][] obj = new Object[0][0];
		String[] header = {CMS_StringTable.STR_FIRST_NAME, CMS_StringTable.STR_MID_NAME, CMS_StringTable.STR_LAST_NAME, 
				CMS_StringTable.STR_CARD_NUMBER, CMS_StringTable.STR_EXP_MONTH, CMS_StringTable.STR_EXP_YEAR, CMS_StringTable.STR_SVC};
		
		tableModel = new DefaultTableModel(obj ,header);
		table = new JTable(tableModel);
		table.setBounds(m_x, m_y, m_width, m_height);
		
		TableColumn firsetNameColumn = table.getColumnModel().getColumn(COL_FIRST_NAME);
		firsetNameColumn.setPreferredWidth(30);
		
		TableColumn midNameColumn = table.getColumnModel().getColumn(COL_MID_NAME);
		midNameColumn.setPreferredWidth(30);
		TableColumn lastNameColumn = table.getColumnModel().getColumn(COL_LAST_NAME);
		lastNameColumn.setPreferredWidth(30);
		TableColumn cardNumberColumn = table.getColumnModel().getColumn(COL_CARD_NUMBER);
		cardNumberColumn.setPreferredWidth(300);
		TableColumn cardMonthColumn = table.getColumnModel().getColumn(COL_EXP_MONTH);
		cardMonthColumn.setPreferredWidth(10);
		TableColumn cardYearColumn = table.getColumnModel().getColumn(COL_EXP_YEAR);
		cardYearColumn.setPreferredWidth(10);
		TableColumn cardSVCColumn = table.getColumnModel().getColumn(COL_SERVICE_CODE);
		cardSVCColumn.setPreferredWidth(10);
		
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);  
		table.setRowSorter(sorter); 
	}
	
	//interface for set cms data into table control
	//always reset the all data(remove all -> add all)
	public void resetCMSData(List<CMS_Data> dataList){
		if(tableModel == null){
			return;
		}
		
		removeAllData();
		
		for(CMS_Data data : dataList){
			String[] rowData = new String[7];
			
			rowData[COL_FIRST_NAME] = data.first_name;
			rowData[COL_MID_NAME] = data.mid_name;
			rowData[COL_LAST_NAME] = data.last_name;
			rowData[COL_CARD_NUMBER] = data.card_number;
			rowData[COL_EXP_MONTH] = data.exp_month;
			rowData[COL_EXP_YEAR] = data.exp_year;
			rowData[COL_SERVICE_CODE] = data.service_code;
			
			tableModel.addRow(rowData);
		}
	}
	
	public void removeAllData(){
		int rowCount = tableModel.getRowCount();
		
		for(int i = rowCount - 1; i >= 0; i--){
			tableModel.removeRow(i);
		}
	}
}
