package com.fmf.twebg.dao;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.fmf.twebg.model.UserPicture;
import com.fmf.twebg.module.TwebgAppModule;
import com.google.inject.Guice;

public class UserPictureDaoTest {

	private IUserPicturesDao dao;
	private String marcelfalliereStatusesSample;

	@Before
	public void before() throws IOException, URISyntaxException {
		dao = Guice.createInjector(new TwebgAppModule()).getInstance(
				IUserPicturesDao.class);
		marcelfalliereStatusesSample = FileUtils.readFileToString(new File(
				UserPictureDaoTest.class.getResource(
						"/marcelfalliere.statuses.sample.json").toURI()));
	}

	@Test
	public void can_do_a_get_request_to_twitter_api() throws Exception {
		String jsonFromApi = dao.getJsonFromApi("marcelfalliere", null);

		assertThat(jsonFromApi).isNotNull();
		assertThat(jsonFromApi).isNotEmpty();
		assertThat(jsonFromApi).contains("\"type\":\"photo\"");
	}

	@Test
	public void can_parse_json_to_get_user_pictures() throws Exception {
		Object[] parsedArray = dao
				.getUserPicturesFromJsonApi(marcelfalliereStatusesSample);
		List<UserPicture> userPicturesFromJsonApi = (List<UserPicture>) parsedArray[0];
		assertThat(userPicturesFromJsonApi).hasSize(1);
		assertThat(userPicturesFromJsonApi.get(0).getUrl()).isEqualTo(
				"http://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg");
	}

}
