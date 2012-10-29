package com.fmf.twebg.service;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.fmf.twebg.model.UserPicture;
import com.fmf.twebg.module.TwebgAppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class UserPicturesServiceTest {

	private Injector injector;

	@Before
	public void before() {
		injector = Guice.createInjector(new TwebgAppModule());
	}

	@Test
	public void should_get_marcelfalliere_pictures() {
		IUserPicturesService service = injector.getInstance(IUserPicturesService.class);

		List<UserPicture> pictures = service.getUserPictures("marcelfalliere");

		Assertions.assertThat(pictures.size()).isEqualTo(19);
	}

}
