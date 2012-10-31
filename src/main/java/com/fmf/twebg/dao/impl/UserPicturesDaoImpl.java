package com.fmf.twebg.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.fmf.twebg.dao.IUserPicturesDao;
import com.fmf.twebg.model.UserPicture;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserPicturesDaoImpl implements IUserPicturesDao {

	public List<UserPicture> getUserPicturesFromJsonApi(String json) {
		List<UserPicture> ret = new ArrayList<UserPicture>();

		JsonParser jsonParser = new JsonParser();
		JsonElement parsedJson = jsonParser.parse(json);
		if (parsedJson.isJsonObject()) {
			if (parsedJson.getAsJsonObject().has("error")) {
				throw new RuntimeException(
						"An error occured when accessing twitter REST api. \nMessage from twitter:\""
								+ parsedJson.getAsJsonObject().get("error")
										.getAsString() + "\".");
			}
		} else {
			JsonArray tweets = parsedJson.getAsJsonArray();
			for (int i = 0; i < tweets.size(); i++) {
				JsonElement tweetJsonElement = tweets.get(i);
				JsonObject tweet = tweetJsonElement.getAsJsonObject();
				boolean hasEntities = tweet.has("entities");
				if (hasEntities) {
					JsonElement entitiesJsonElement = tweet.get("entities");
					JsonObject entitiesJsonObject = entitiesJsonElement
							.getAsJsonObject();
					boolean hasMedia = entitiesJsonObject.has("media");
					if (hasMedia) {
						JsonElement jsonElement = entitiesJsonObject
								.get("media");
						JsonArray mediaElementsArray = jsonElement
								.getAsJsonArray();
						for (int j = 0; j < mediaElementsArray.size(); j++) {
							JsonElement mediaElement = mediaElementsArray
									.get(j);
							JsonObject mediaObject = mediaElement
									.getAsJsonObject();
							boolean hasType = mediaObject.has("type");
							if (hasType) {
								JsonElement typeValue = mediaObject.get("type");
								if ("photo".equals(typeValue.getAsString())) {

									String url = mediaObject.get("media_url")
											.getAsString();
									Integer tweetId = mediaObject.get("id")
											.getAsInt();
									UserPicture userPicture = new UserPicture(
											url, tweetId);
									ret.add(userPicture);

								}
							}
						}
					}
				}
			}
		}

		return ret;
	}

	public String getJsonFromApi(String screenName, Integer lastTweetId) {

		String url = "http://api.twitter.com/1/statuses/user_timeline.json?include_entities=true&include_rts=false&count=200&screen_name="
				+ screenName;
		if (lastTweetId != null) {
			url += "&max_id=" + lastTweetId;
		}

		String json = null;
		try {
			GetMethod getMethod = new GetMethod(url);
			(new HttpClient()).executeMethod(getMethod);
			json = getMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new RuntimeException("Could not fetch url. Error was:" + e);
		}
		return json;
	}
}
