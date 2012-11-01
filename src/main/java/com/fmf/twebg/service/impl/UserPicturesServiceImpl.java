package com.fmf.twebg.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fmf.twebg.dao.IUserPicturesDao;
import com.fmf.twebg.model.UserPicture;
import com.fmf.twebg.service.IUserPicturesService;
import com.google.inject.Inject;

public class UserPicturesServiceImpl implements IUserPicturesService {

	private final IUserPicturesDao userPictureDao;

	@Inject
	public UserPicturesServiceImpl(IUserPicturesDao userPictureDao) {
		this.userPictureDao = userPictureDao;
	}

	public List<UserPicture> getUserPictures(String screenName) {
		List<UserPicture> allPictures = new ArrayList<UserPicture>();

		Long lastTweetId = null;
		do {
			String jsonFromApi = userPictureDao.getJsonFromApi(screenName,
					lastTweetId);

			Object[] parsedArray = userPictureDao
					.getUserPicturesFromJsonApi(jsonFromApi);

			List<UserPicture> userPicturesFromJsonApi = (List<UserPicture>) parsedArray[0];
			lastTweetId = (Long) parsedArray[1];
			allPictures.addAll(userPicturesFromJsonApi);

			System.out.println("passage pour l'id " + lastTweetId);
		} while (lastTweetId != null);

		return allPictures;
	}
}
