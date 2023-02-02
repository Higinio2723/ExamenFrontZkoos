package com.front.zkoos.module.form;

import com.front.zkoos.module.ratings.Ratings;

public class UserForm {

	private Ratings ratings = new Ratings();


	public Ratings getRatings() {
		return ratings;
	}

	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}

}
