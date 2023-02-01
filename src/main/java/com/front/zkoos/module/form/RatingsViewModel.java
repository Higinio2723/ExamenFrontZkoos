package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;

//@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RatingsViewModel extends UserForm {

	private final Logger logger = LoggerFactory.getLogger(RatingsViewModel.class);

	private String centerPage;

	public void setCenterPage(String centerPage) {
		this.centerPage = centerPage;
	}



	@Command
	public void submit() throws JsonProcessingException {
		int data = this.getRatings().getIdAlumno();
		System.out.println("######## data ="+data);

		RatingsService ratingsService = new RatingsService();
				ratingsService.saveRatings(this.getRatings());
	}

	@Command
	public String ratingsList() throws JsonProcessingException {
		logger.info("################## getRatings() {}",this.getRatings());

		return "form_main";
	}


}
