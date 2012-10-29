package com.fmf.twebg.service.impl;

import java.util.List;

import com.fmf.twebg.dao.IUserPicturesDao;
import com.fmf.twebg.model.UserPicture;
import com.fmf.twebg.service.IUserPicturesService;
import com.google.inject.Inject;

public class UserPicturesServiceImpl implements IUserPicturesService {

	private IUserPicturesDao userPictureDao;

	@Inject
	public UserPicturesServiceImpl(IUserPicturesDao userPictureDao) {
		this.userPictureDao = userPictureDao;
	}

	public List<UserPicture> getUserPictures(String screenName) {
		String jsonFromApi = userPictureDao.getJsonFromApi(screenName);

		return userPictureDao.getUserPicturesFromJsonApi(jsonFromApi);
	}

}
