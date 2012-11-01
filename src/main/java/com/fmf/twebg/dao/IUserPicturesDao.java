package com.fmf.twebg.dao;

public interface IUserPicturesDao {

	/**
	 * parse le json de tweeter et récupére les images
	 * 
	 * @param json
	 * @return 0:liste des userpictures 1:lasttweetid
	 */
	public Object[] getUserPicturesFromJsonApi(String json);

	/**
	 * récupére le json sur GET api v1
	 * 
	 * @param screenName
	 * @param lastTweetId
	 * @return
	 */
	public String getJsonFromApi(String screenName, Long lastTweetId);

}
