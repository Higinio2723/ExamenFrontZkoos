package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.InputEvent;

public class FormViewModel extends UserForm {

		
	@Command
	public void submit() throws JsonProcessingException {
		int data = this.getRatings().getIdAlumno();
		System.out.println("######## data ="+data);

		RatingsService ratingsService = new RatingsService();
				ratingsService.saveRatings(this.getRatings());
	}

}
