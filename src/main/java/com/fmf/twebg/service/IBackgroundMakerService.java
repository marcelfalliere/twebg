package com.fmf.twebg.service;

import java.util.List;

import com.fmf.twebg.model.UserPicture;

public interface IBackgroundMakerService {

	/**
	 * generates a background
	 * 
	 * @param userPictures
	 *            list of user pictures
	 * @return url of the background
	 */
	public String generateBackground(List<UserPicture> userPictures);

}
