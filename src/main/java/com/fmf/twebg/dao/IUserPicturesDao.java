package com.fmf.twebg.dao;

import java.util.List;

import com.fmf.twebg.model.UserPicture;

public interface IUserPicturesDao {

	/**
	 * parse le json de tweeter et récupére les images
	 * 
	 * @param json
	 * @return
	 */
	public List<UserPicture> getUserPicturesFromJsonApi(String json);

	/**
	 * récupére le json sur GET api v1
	 * 
	 * @param screenName
	 * @return
	 */
	public String getJsonFromApi(String screenName);

}
