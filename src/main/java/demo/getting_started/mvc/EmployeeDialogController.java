package demo.getting_started.mvc;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class EmployeeDialogController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	@Wire
	Window modalDialog;
	
	@Listen("onClick = #createBtn")
	public void showModal(Event e) {
		modalDialog.detach();
	}
}
