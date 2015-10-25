/**                         
* Enum:	<CMS_ERROR>                                          
* Comments: Represents the errors occur in system running                                                                  
* Author:	<Wang Lu>                                      
*/ 
public enum CMS_ERROR {
	ERROR_NO_ERROR,
	ERROR_FILE_NOTEXIST, 
	ERROR_WRONG_EXETENSION,  
	ERROR_TRACK2_DATA_ERROR,
	ERROR_WRONG_XML,
	ERROR_SQLCONNECTION_FAIL,
	ERROR_SQLEXECUTE_FAIL,
	ERROR_UNKNOW;
	
	
	//return error string by error flag
	public static String GetErrorString(CMS_ERROR errorType){
		String errorString = CMS_StringTable.STR_UNKNOWN_ERROR;
		switch(errorType){
			case ERROR_NO_ERROR:  errorString = CMS_StringTable.STR_NO_ERROR; break;
			case ERROR_FILE_NOTEXIST:  errorString = CMS_StringTable.STR_NO_ERROR; break;
			case ERROR_WRONG_EXETENSION:  errorString = CMS_StringTable.STR_NO_ERROR; break;
			case ERROR_TRACK2_DATA_ERROR:  errorString = CMS_StringTable.STR_NO_ERROR; break;
			case ERROR_WRONG_XML: errorString = CMS_StringTable.STR_WRONG_FORMAT; break;
			case ERROR_SQLCONNECTION_FAIL: errorString = CMS_StringTable.STR_SQLCONNECTION_FAIL; break;
			case ERROR_SQLEXECUTE_FAIL: errorString = CMS_StringTable.STR_SQLECECUTE_FAIL; break;
			default: errorString = CMS_StringTable.STR_UNKNOWN_ERROR; break;
		}
		return errorString;
	}
	
	
	
	//public final String ERROR_FILE_NOTEXIST = "file not exist!";
}
