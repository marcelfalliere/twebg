package com.fmf.twebg.model;

public class UserPicture {

	private final String url;
	private final Integer tweetId;

	public UserPicture(String url, Integer tweetId) {
		super();
		this.url = url;
		this.tweetId = tweetId;
	}

	public Integer getTweetId() {
		return tweetId;
	}

	public String getUrl() {
		return url;
	}

}
