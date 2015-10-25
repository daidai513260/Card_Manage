import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;

/**                         
* Class:	<CMS_View>                                          
* Comments: read a formated xml file
* 			1. The xml file should contain root with key word "records"                                                  
* Author:	<Wang Lu>                                      
*/ 
public class CMS_XML_Reader extends CMS_File_Reader {

	public CMS_XML_Reader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean Read_CMS_File(String file_path, CMS_ErrorCollector errorCollector, List<CMS_Data> dataList){
		CMS_ERROR error = CMS_Tools.CheckFilePathVaild(file_path, CMS_StringTable.STR_XML);
		if(error != CMS_ERROR.ERROR_NO_ERROR){
			errorCollector.AddError(file_path, CMS_ERROR.GetErrorString(error));
			return false;
		}
		try{
			File fXMLFile = new File(file_path);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fXMLFile);
			
			doc.getDocumentElement().normalize();

			if(!doc.getDocumentElement().getNodeName().equalsIgnoreCase(CMS_StringTable.STR_RECORDS)){
				errorCollector.AddError(file_path, CMS_StringTable.STR_WRONG_FORMAT);
				return false;
			}
			
			NodeList nodeList = doc.getElementsByTagName(CMS_StringTable.STR_RECORD);

			for(int i = 0; i < nodeList.getLength(); i++){
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) node;
					
					String fullName = element.getElementsByTagName(CMS_StringTable.STR_NAME).item(0).getTextContent();
					CMS_Name splitedName = new CMS_Name();
					CMS_Tools.SplitFullName(fullName, splitedName);
					
					String track2Data = element.getElementsByTagName(CMS_StringTable.STR_TRACK2).item(0).getTextContent();
					CMS_CardInfo cardInfo = new CMS_CardInfo();
					if(!CMS_Tools.CheckTrack2DataValid(track2Data)){
						errorCollector.AddError(fullName + " " + track2Data, CMS_StringTable.STR_TRACK2_DATA_ERROR);
	
						return false;
					}
					CMS_Tools.Track2DataToCardInfo(track2Data, cardInfo);
					
					CMS_Data data = new CMS_Data(splitedName.firstName, splitedName.midName, splitedName.lastName,
							cardInfo.cardID, cardInfo.exp_month, cardInfo.exp_year, cardInfo.svc);
					
					dataList.add(data);
				}
			}
			
		} catch (Exception e){
			errorCollector.AddError(file_path, CMS_StringTable.STR_WRONG_FORMAT);
			return false;
		}
		return true;
	}
	
}
