import java.io.File;

/**                         
* Class:	<CMS_Tools>                                          
* Comments: Tools can be directly used in any part of the program without creating class instance.
* 			1. All the method should be static
* 			2. No member parameters should exist
* 			3. "Tools" belongs to the entire program
* 			4. "Tools" can be reused in other programs                                                   
* Author:	<Wang Lu>                                      
*/ 
public class CMS_Tools {
	//split full name into first name, mid name, last name
	public static void SplitFullName(String strFullName, CMS_Name splitedName){
		strFullName = strFullName.trim();
		
		int start = strFullName.indexOf(' ');
		int end = strFullName.lastIndexOf(' ');
		
		if(start >= 0){
			splitedName.firstName = strFullName.substring(0, start);
			splitedName.firstName = splitedName.firstName.trim();
			if(end > start){
				splitedName.midName = strFullName.substring(start + 1, end);
				splitedName.midName = splitedName.midName.trim();
			}
			splitedName.lastName = strFullName.substring(end + 1, strFullName.length());
			splitedName.lastName = splitedName.lastName.trim();
		}else{
			splitedName.firstName = strFullName;
		}
	}
	
	//check if a file is exist, and whether the file type is correct
	public static CMS_ERROR CheckFilePathVaild(String path, String targetExtension){
		String curExetesion = "";
		int i = path.lastIndexOf('.');
		if (i > 0) {
			curExetesion = path.substring(i+1);
		}
		if(!curExetesion.equalsIgnoreCase(targetExtension)){
			return CMS_ERROR.ERROR_WRONG_EXETENSION;
		}
		
		File file = new File(path);
		if(!file.exists() || file.isDirectory()){
			return CMS_ERROR.ERROR_FILE_NOTEXIST;
		}
		return CMS_ERROR.ERROR_NO_ERROR;
	}
	
	//check if a track2 record is correct 
	public static boolean CheckTrack2DataValid(String strTrack2){
		int start = strTrack2.indexOf(';');
		int end = strTrack2.indexOf('?');
		int separator = strTrack2.indexOf('=');
		
		return (start == 0)
				&&(end >= strTrack2.length() - 2)
				&&(end > start)
				&&(separator > start)
				&&(separator < end)
				&&(separator - start - 1 <= 19)
				&&(end - separator - 1 >= 7)
				&&(strTrack2.indexOf(';') == strTrack2.lastIndexOf(';'))
				&&(strTrack2.indexOf('?') == strTrack2.lastIndexOf('?'))
				&&(strTrack2.indexOf('=') == strTrack2.lastIndexOf('='));
	}
	
	//translate track2 data into card information
	public static void Track2DataToCardInfo(String strTrack2, CMS_CardInfo info){
		strTrack2 = strTrack2.trim();
		
		int start = 1;
//		int end = strTrack2.indexOf('?');
		int separator = strTrack2.indexOf('=');
		int expYearStart = separator + 1;
		int expMonStart = separator + 3;
		int svcStart = separator + 5;
		int ddStart = separator + 8;
		
		info.cardID = strTrack2.substring(start, separator);
		info.exp_year = strTrack2.substring(expYearStart, expMonStart);
		info.exp_month = strTrack2.substring(expMonStart, svcStart);
		info.svc = strTrack2.substring(svcStart, ddStart);
	}
	
	public static String GetFileName(String filePath){
		String fileName;
		
		int start = filePath.lastIndexOf('\\');
		start = start > -1 ? start : -1;
		int end = filePath.lastIndexOf('.');
		end = end > -1 ? end : filePath.length();
		
		fileName = filePath.substring(start+1, end);
		
		return fileName;
	}
}
