package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.nav.MyObject;
import com.front.zkoos.util.contants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

//@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class FormViewModel extends UserForm {
//	private NavigationModel<MyObject> navModel = new NavigationModel<MyObject>();

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
	public String ratingsList() throws JsonProcessingException {
		logger.info("################## getRatings() {}",this.getRatings().toString());

		Map args = new HashMap();
		args.put("pCustomer", "this.selectedCustomer");
		args.put("recordMode", "this.recordMode");
		BindUtils.postGlobalCommand(null, null, "updateCustomerList", args);
		return "";
	}

	/**
	 * Load the course list content
	 */
	@Command
	public void goToCourseList(){

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", "");
		map.put("lastName", "");
		Executions.createComponents("/listData", null, map);
	}

}
