import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**                         
* Class:	<CMS_SQLite_Writer>                                          
* Comments: Used for writing data into SQLite database;                                                                
* Author:	<Wang Lu>                                      
*/ 
public class CMS_SQLite_Writer extends CMS_File_Writer{
	private Connection connection;
	
	public CMS_SQLite_Writer(String URL) {
		super();
		
		file_path = URL;
		InitConnection();
		// TODO Auto-generated constructor stub
	}
	
	//Initial the connection to database
	public CMS_ERROR InitConnection(){
		try{

			//load mysql driver
			String driver = "org.sqlite.JDBC";
			Class.forName(driver);
			

			//connect to database
			connection = DriverManager.getConnection(file_path);
			

			//check database opened successfully
			if(!connection.isClosed()){
				return CMS_ERROR.ERROR_NO_ERROR;
			}else{
				return CMS_ERROR.ERROR_SQLCONNECTION_FAIL;
			}
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return CMS_ERROR.ERROR_SQLCONNECTION_FAIL;
		}
	}
	
	public void CloseConnection(){
		if(connection == null){
			return;
		}else{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	


	//check if the current table exist
	private boolean CheckTableExist(String tableName){
		if(connection == null){
			return false;
		}

		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, tableName, null);
			return tables.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	//writing data into database, table name = current record file name
	public void write_CMS_data(String tableName, List<CMS_Data> dataList, CMS_ErrorCollector errorCollector, boolean rewriteAll){
		String sql = "";
		try{
			//if current table not exist, create a new table
			if(!CheckTableExist(tableName)){
				sql = String.format("Create Table %s ("
						+ "%s varchar(255)"
						+ ", %s varchar(255)"
						+ ", %s varchar(255)"
						+ ", %s varchar(255)"
						+ ", %s varchar(255)"
						+ ", %s varchar(255)"
						+ ", %s varchar(255))"
						, tableName
						, CMS_StringTable.STR_FIRST_NAME
						, CMS_StringTable.STR_MID_NAME
						, CMS_StringTable.STR_LAST_NAME
						, CMS_StringTable.STR_CARD_NUMBER
						, CMS_StringTable.STR_EXP_MONTH
						, CMS_StringTable.STR_EXP_YEAR
						, CMS_StringTable.STR_SVC);
				
				Statement stmt = connection.createStatement();
				stmt.execute(sql);
			}
			
			Statement stmt = connection.createStatement();
				
			if(rewriteAll){
				sql = String.format("delete from %s", tableName);
				stmt.execute(sql);	
			}
			
			//writing data into database
			//for high writing efficiency, using  preparedStatement and patch
			sql =  String.format("insert into %s (%s,%s,%s,%s,%s,%s,%s) values (?, ?, ?, ?, ?, ?, ?)"
					, tableName 
					, CMS_StringTable.STR_FIRST_NAME
					, CMS_StringTable.STR_MID_NAME
					, CMS_StringTable.STR_LAST_NAME
					, CMS_StringTable.STR_CARD_NUMBER
					, CMS_StringTable.STR_EXP_MONTH
					, CMS_StringTable.STR_EXP_YEAR
					, CMS_StringTable.STR_SVC
					);
			
			PreparedStatement insertDatas = connection.prepareStatement(sql);
			
			connection.setAutoCommit(false);
		
			for(CMS_Data data : dataList){
				insertDatas.setString(1, data.first_name);
				insertDatas.setString(2, data.mid_name);
				insertDatas.setString(3, data.last_name);
				insertDatas.setString(4, data.card_number);
				insertDatas.setString(5, data.exp_month);
				insertDatas.setString(6, data.exp_year);
				insertDatas.setString(7, data.service_code);
				
				insertDatas.addBatch();
			}
			
			insertDatas.executeBatch();
			connection.commit();
			insertDatas.clearBatch();
			insertDatas.close();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorCollector.AddError(sql, CMS_ERROR.GetErrorString(CMS_ERROR.ERROR_SQLEXECUTE_FAIL));
			return;
		}
		
		
		return;
	}
}
