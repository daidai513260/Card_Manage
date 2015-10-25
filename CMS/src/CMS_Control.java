import java.util.List;


public class CMS_Control {
	public CMS_Control(){
	
	}
	
	//Notify the CMS messages with specific parameter;
	public void OnNotify(CMS_Message message, Object parameter){
		switch(message){
			case MSG_OPEN_FILE :
			{
				String path = (String)parameter;
				cmsModel.readFile(path);
				break;
			}
			
			case VM_DATA_RELOADED:{
				cmsView.OnNotify(message, parameter);
				break;
			}
		}
	}
	
	public List<CMS_Data> getCMSData(){
		return cmsModel.GetCardsInfo();
	}
	
	public List<String> GetErrors(){
		return cmsModel.GetErrors();
	}
	
	public void setMode(CMS_Model model){
		cmsModel = model;
	}
	
	public void setView(CMS_View view){
		cmsView = view;
	}
	private CMS_View cmsView;
	private CMS_Model cmsModel;
}
