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

		Integer lastTweetId = null;
		do {
			String jsonFromApi = userPictureDao
					.getJsonFromApi(screenName, null);
			List<UserPicture> userPicturesFromJsonApi = userPictureDao
					.getUserPicturesFromJsonApi(jsonFromApi);
			lastTweetId = (userPicturesFromJsonApi.size() > 0) ? userPicturesFromJsonApi
					.get(userPicturesFromJsonApi.size() - 1).getTweetId()
					: null;
		} while (true);

	}
}
