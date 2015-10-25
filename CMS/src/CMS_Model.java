import java.util.LinkedList;
import java.util.List;

/**                         
* Class:	<CMS_Model>                                          
* Comments: Read, load and store the records                                                                 
* Author:	<Wang Lu>                                      
*/ 
public class CMS_Model {
	public CMS_Model(CMS_File_Reader reader, CMS_File_Writer writer){
		Init(reader, writer);
	}
	

	private void Init(CMS_File_Reader reader, CMS_File_Writer writer){
		file_reader = reader;
		file_writer = writer;
		
		errorCollector = new CMS_ErrorCollector();
		cardInfoList = new LinkedList<CMS_Data>();
	}
	
	public void readFile(String filePath){
		errorCollector.Clear();
		cardInfoList.clear();
		
		file_reader.Read_CMS_File(filePath, errorCollector, cardInfoList);
		
		String fileName = CMS_Tools.GetFileName(filePath);
		
		if(errorCollector.getErrors().isEmpty()){
			file_writer.write_CMS_data(fileName, cardInfoList, errorCollector, true);
		}
		
		//every time read a file, call the view to refresh window
		cmsControl.OnNotify(CMS_Message.VM_DATA_RELOADED, "");
	}
	
	public List<String> GetErrors(){
		return errorCollector.getErrors();
	}
	
	public LinkedList<CMS_Data> GetCardsInfo(){
		return cardInfoList;
	}
	
	public void setControl(CMS_Control control){
		cmsControl = control;
	}
	
	private CMS_ErrorCollector errorCollector; 
	private CMS_File_Reader file_reader;
	private CMS_File_Writer file_writer;
	private LinkedList<CMS_Data> cardInfoList;
	private CMS_Control cmsControl;
;
}

