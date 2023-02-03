package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.form.data.Ratings;
import com.front.zkoos.module.ratings.RatingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

public class UpdateViewModel extends SelectorComposer<Component> {

	@Wire
	Window modalDialog;

	private final Logger logger = LoggerFactory.getLogger(UpdateViewModel.class);

	private Ratings ratings = new Ratings();


	public Ratings getRatings() {
		return ratings;
	}

	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}


	@Init
	public void init(@BindingParam("arg1") String arg1) {
		final Execution execution = Executions.getCurrent();
		String idCalificacion = (String) execution.getArg().get("idCalificacion");
		logger.info("################### idCalificacion update {}", idCalificacion);
        int id = Integer.valueOf(idCalificacion).intValue();
		ratings.setIdCalificacion(id);
	}

	@Command
	public void updateSubmit() throws JsonProcessingException {
		int data = this.getRatings().getIdAlumno();
		System.out.println("######## data ="+data);
		System.out.println("######## calificacion ="+this.getRatings().getCalificacion());
		System.out.println("######## data ="+data);

		RatingsService ratingsService = new RatingsService();
				ratingsService.updateRatings(this.getRatings());

	}

	
}
