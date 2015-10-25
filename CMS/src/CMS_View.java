import javax.swing.JDialog;

/**                         
* Class:	<CMS_View>                                          
* Comments: Show the window to user                                                   
* Author:	<Wang Lu>                                      
*/ 
public class CMS_View {
	public CMS_View(){
		Init();
	}
	
	public void Init(){
		dialog = new CMS_Dialog(this);	
	}
	
	public void setControl(CMS_Control cmsControl){
		control = cmsControl;
	}
	
	public void ShowWindow(){
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	public void OnNotify(CMS_Message message, Object parameter){
		
		switch(message){	
			case VM_DATA_RELOADED:{
				dialog.resetData(control.getCMSData(), control.GetErrors());
				break;
			}
			case MSG_OPEN_FILE: {
				control.OnNotify(CMS_Message.MSG_OPEN_FILE, parameter);
			}
			default:
				break;
		}

	}
	
	private CMS_Dialog dialog;
	private CMS_Control control;
}
