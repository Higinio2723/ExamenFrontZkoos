package com.front.zkoos.module.form;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.zkoos.module.ratings.dto.GeneralDto;
import com.front.zkoos.module.ratings.dto.RatingGeneralDto;
import com.front.zkoos.module.report.ReportRatingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Selectable;

import com.front.zkoos.module.ratings.dto.RatingFormatDto;
import com.front.zkoos.module.ratings.RatingsService;

public class SearchController extends SelectorComposer<Component> {

	private final Logger logger = LoggerFactory.getLogger(SearchController.class);

	private final static String DIRECTORIO_UPLOAD = "/report";

	private static final long serialVersionUID = 1L;

	private ListModel<RatingFormatDto> ratingsModel;

	@Wire
	private Textbox keywordBox;
	@Wire
	private Listbox ratingListbox;
	@Wire
	private Label modelLabel;
	@Wire
	private Label makeLabel;
	@Wire
	private Label priceLabel;
	@Wire
	private Label descriptionLabel;
	@Wire
	private Image previewImage;
	@Wire
	private Component detailBox;

	private Integer idCalificacion;

	private RatingFormatDto selected;

	private RatingsService ratingsService = new RatingsService();

	private ReportRatingsService reportService = new ReportRatingsService();



	@Listen("onClick = #searchButton")
	public void search(){
		String keyword = keywordBox.getValue();

		RatingGeneralDto data = ratingsService.search(keyword);
		List<RatingFormatDto> result = data.getListData();

		ratingListbox.setModel(new ListModelList<RatingFormatDto>(result));
		ratingsModel = new ListModelList<RatingFormatDto>(result);
		((ListModelList<RatingFormatDto>)ratingsModel).setMultiple(true);

	}
	

	@Listen("onClick = #deleteBtn")
	public void deleteRatings(Event e) throws JsonProcessingException {

       logger.info("################## idCalificacion {}", idCalificacion);

		GeneralDto data = ratingsService.deleteRatings(idCalificacion.toString());

		this.search();

	}


	@Listen("onClick = #reportBtn")
	public void reportRatings(Event e) throws IOException {

		String keyword = keywordBox.getValue();

		byte[] data =reportService.downloadReportFiles(keyword);
		logger.info("################## keyword {} ",keyword);
		if(keyword == "" || keyword == null || keyword == " ") {
			keyword = "all";
		}
		Filedownload.save(data,"application/pdf","report_"+keyword+".pdf");

	}

	@Listen("onClick = #updateBtn")
	public void updateRatings(Event e) throws JsonProcessingException {
		//create a window programmatically and use it as a modal dialog.
		HashMap map = new HashMap<String, Object>();
		logger.info("################## idCalificacion {}", idCalificacion);
		map.put("idCalificacion", ""+idCalificacion);
		map.put("selected", selected);

		Window window = (Window)Executions.createComponents(
				"~./widgets/window/modal_dialog/ratings_update.zul", null, map);
		window.doModal();
	}

	
	@Listen("onClick = #orderBtn")
	public void showModal(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"~./widgets/window/modal_dialog/ratings_create.zul", null, null);
		window.doModal();
	}

	@Command
	@NotifyChange("ratingListbox")
	public void removeMail(@BindingParam("mail") RatingFormatDto myMail) {
//		mailData.deleteMail(myMail);
	}

	@Listen("onSelect = #ratingListbox")
	public void showDetail(){
//		detailBox.setVisible(true);
		Set<RatingFormatDto> selection = ((Selectable<RatingFormatDto>)ratingListbox.getModel()).getSelection();
		if (selection!=null && !selection.isEmpty()){
			selected = selection.iterator().next();
			logger.info("############ {}",selected.getId_calificacion());
			idCalificacion = selected.getId_calificacion();
		}
	}

	public Path getPath(String nombre) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombre).toAbsolutePath();
	}

}
