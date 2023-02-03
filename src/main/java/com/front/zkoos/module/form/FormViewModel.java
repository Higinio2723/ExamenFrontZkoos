package com.front.zkoos.module.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.ratings.RatingsService;

import com.front.zkoos.module.ratings.dto.SubjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class FormViewModel extends UserForm {
	private final Logger logger = LoggerFactory.getLogger(FormViewModel.class);

	private String materia;


	private List<String> materias = new ArrayList<String>();


	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public List<String> getMaterias() {
		return materias;
	}

	public void setMaterias(List<String> materias) {
		this.materias = materias;
	}

	@Init
	public void init(@BindingParam("arg1") String arg1) throws JsonProcessingException {
		RatingsService ratingsService = new RatingsService();
		List<SubjectDto> list = ratingsService.getAllSubject();
		logger.info("############# list{}",list.toString());

		list.forEach(data ->
		{
			materias.add(data.getIdMateria()+ "-"+data.getNombre());
		});

	}

	@Command
	public void submit() throws JsonProcessingException {

		logger.info("################ materia {}",materia);
		String[] dataMateria = materia.split("-");
		String idMateria = dataMateria[0].trim();

		RatingsService ratingsService = new RatingsService();
				ratingsService.saveRatings(this.getRatings(),idMateria);
	}

	
}
