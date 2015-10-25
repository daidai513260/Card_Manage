import java.util.LinkedList;
import java.util.List;

/**                         
* Enum:	<CMS_ErrorCollector>                                          
* Comments: 1s. Create formated error string for the error occurred in system running    
* 			2. Collect those errors into a linked list                                     
* Author:	<Wang Lu>                                      
*/ 
public class CMS_ErrorCollector {
	
	public CMS_ErrorCollector(){
		error_string_list = new LinkedList<String>();
	}
	
	public void AddError(String item, String errorMessage){
		String errorString = BuildErrorString(item, errorMessage);
		error_string_list.add(errorString);
	}
	
	public List<String> getErrors(){
		return error_string_list;
	}
	
	public void Clear(){
		error_string_list.clear();
	}
	
	//build a formated error string
	protected String BuildErrorString(String item, String errorMessage){
		String error_string = "";
		if(item.length() != 0){
			error_string = CMS_StringTable.STR_ERROR + item + " " + errorMessage;
		}else{
			error_string = CMS_StringTable.STR_ERROR + errorMessage;
		}
		
		return error_string;
	}
	
	
	protected List<String> error_string_list;
}
