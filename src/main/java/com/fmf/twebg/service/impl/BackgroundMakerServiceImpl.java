package com.fmf.twebg.service.impl;

import java.util.List;

import com.fmf.twebg.model.UserPicture;
import com.fmf.twebg.service.IBackgroundMakerService;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BackgroundMakerServiceImpl implements IBackgroundMakerService {

	private String tweetsPicturesPath;

	@Inject
	public BackgroundMakerServiceImpl(@Named("TWEETS_PICTURES_PATH") String tweetsPicturesPath) {
		this.tweetsPicturesPath = tweetsPicturesPath;

	}

	public String generateBackground(List<UserPicture> userPictures) {

		return "url";
	}

}
