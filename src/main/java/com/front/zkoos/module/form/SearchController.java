package com.front.zkoos.module.form;


import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.ext.Selectable;

import com.front.zkoos.module.form.dto.RatingFormatDto;
import com.front.zkoos.module.ratings.RatingsService;

public class SearchController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
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
	
	private RatingsService ratingsService = new RatingsService();
	
	
	@Listen("onClick = #searchButton")
	public void search(){
		String keyword = keywordBox.getValue();
		
		List<RatingFormatDto> result = ratingsService.search(keyword);
		
		ratingListbox.setModel(new ListModelList<RatingFormatDto>(result));
	}
	
	@Listen("onSelect = #carListbox")
	public void showDetail(){
		detailBox.setVisible(true);
		
		Set<RatingFormatDto> selection = ((Selectable<RatingFormatDto>)ratingListbox.getModel()).getSelection();
		if (selection!=null && !selection.isEmpty()){
			RatingFormatDto selected = selection.iterator().next();
//			previewImage.setSrc(selected.getPreview());
//			modelLabel.setValue(selected.getModel());
//			makeLabel.setValue(selected.getMake());
//			priceLabel.setValue(selected.getPrice().toString());
//			descriptionLabel.setValue(selected.getDescription());
		}
	}
	
	
	@Listen("onClick = #orderBtn")
	public void showModal(Event e) {
		//create a window programmatically and use it as a modal dialog.
		Window window = (Window)Executions.createComponents(
				"~./widgets/window/modal_dialog/ratings_create.zul", null, null);
		window.doModal();
	}
	
}
