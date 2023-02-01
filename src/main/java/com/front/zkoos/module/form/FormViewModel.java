package com.front.zkoos.module.form;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.InputEvent;

public class FormViewModel extends UserForm {
	
	
	
		
	@Command
	public void submit() {
		int data = this.getRatings().getIdAlumno();
		System.out.println("######## data ="+data);
	      	
		
	}

}
