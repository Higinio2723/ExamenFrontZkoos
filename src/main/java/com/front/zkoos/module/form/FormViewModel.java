package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.util.contants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

//@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class FormViewModel extends UserForm {

	private final Logger logger = LoggerFactory.getLogger(FormViewModel.class);

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
	public void ratingsList() throws JsonProcessingException {
		logger.info("################## getRatings() {}",this.getRatings());

		setCenterPage(ApplicationConstants.HOME_PAGE);

//		RatingsService ratingsService = new RatingsService();
//		ratingsService.saveRatings(this.getRatings());
	}


}
