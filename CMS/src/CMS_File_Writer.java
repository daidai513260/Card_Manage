
import java.util.List;

/**                         
* Class:	<CMS_File_Writer>                                          
* Comments: The base class for all kinds of file writer                                                                   
* Author:	<Wang Lu>                                      
*/ 
public abstract class CMS_File_Writer {
	
	public CMS_File_Writer(){

	}
	
	public boolean Init(){
		return true;
	}

	public void write_CMS_data(String fileName, List<CMS_Data> data, CMS_ErrorCollector errorCollector, boolean rewriteAll){

	}
	
	protected String file_path;

}
