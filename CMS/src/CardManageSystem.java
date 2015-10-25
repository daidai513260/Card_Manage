/**                         
* Project:	<CardManageSystem>                                          
* Comments:
* 1) Read xml file contains card records
* 2) Parse each record into first name, middle name, last name, credit card number, expiration month, expiration year, and service code
* 3) a) Show data in a Java GUI with a sortable table
     b) Saving data into a SQLite DB                              
* JDK version used:	<JDK1.8>                                                     
* Author:	<Wang Lu>                 
* Create Date	<2015-10-11>   
* Version:	<1.0>                       
*/ 

/**                                                               
* Comments:
* 1) Change behavior: while read a XML file with un-correct parameter name, stop reading and report "XML format error".
* 2) Decoupling view and dialog                                                                             
* Author:	<Wang Lu>                 
* Create Date	<2015-10-15>   
* Version:	<1.1>                       
*/ 

/**                         
* Class:	<CardManageSystem>                                          
* Comments: Initial the system      
* 			1. Create system instance
* 			2. Set database URL, username, password and other information if needed
* 			3. #important# don't forget include jdbc connector into build path!                                          
* Author:	<Wang Lu>                                      
*/ 
public class CardManageSystem {
	public CardManageSystem(){
		Init();
	}
	
	//init the system
	private void Init(){
		fileReader = new CMS_XML_Reader();
		
		String URL = "jdbc:sqlite:CardInfo.db";
		fileWriter = new CMS_SQLite_Writer(URL);
		
		control = new CMS_Control();
		view = new CMS_View();
		model = new CMS_Model(fileReader, fileWriter);
		
		view.setControl(control);
		model.setControl(control);
		control.setMode(model);
		control.setView(view);
	}
	
	public void Run(){
		view.ShowWindow();
	}
	
	CMS_File_Reader fileReader;
	CMS_File_Writer fileWriter;
	CMS_Control control;
	CMS_View view;
	CMS_Model model;
}
