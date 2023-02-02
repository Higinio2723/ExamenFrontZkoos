package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.ratings.RatingsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

public class FormViewModel extends UserForm {
	
	private final Logger logger = LoggerFactory.getLogger(FormViewModel.class);
	
	@Command
	public void submit() throws JsonProcessingException {
		int data = this.getRatings().getIdAlumno();
		System.out.println("######## data ="+data);

		RatingsService ratingsService = new RatingsService();
				ratingsService.saveRatings(this.getRatings());
	}

	
}
