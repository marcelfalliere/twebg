package com.fmf.twebg.model;

public class UserPicture {

	private final String url;
	private final Long tweetId;

	public UserPicture(String url, Long tweetId) {
		super();
		this.url = url;
		this.tweetId = tweetId;
	}

	public Long getTweetId() {
		return tweetId;
	}

	public String getUrl() {
		return url;
	}

	public String getThumbnailUrl() {
		return url + ":thumb";
	}

}
