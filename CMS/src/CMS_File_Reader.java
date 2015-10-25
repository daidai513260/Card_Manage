
import java.util.List;

/**                         
* Class:	<CMS_File_Reader>                                          
* Comments: The base class for all kinds of file reader                                                                   
* Author:	<Wang Lu>                                      
*/ 
public abstract class CMS_File_Reader {
	public CMS_File_Reader(){

	}
	
	public boolean Read_CMS_File(String file_path, CMS_ErrorCollector errorCollector, List<CMS_Data> data){
		return true;
	}
	
	
}
