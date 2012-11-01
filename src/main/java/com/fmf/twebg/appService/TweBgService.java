package com.fmf.twebg.appService;

import java.util.List;

import com.fmf.twebg.model.UserPicture;
import com.fmf.twebg.module.TwebgAppModule;
import com.fmf.twebg.service.IBackgroundMakerService;
import com.fmf.twebg.service.IUserPicturesService;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class TweBgService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TwebgAppModule());
		IUserPicturesService userPicturesService = injector
				.getInstance(IUserPicturesService.class);
		IBackgroundMakerService backgroundMakerService = injector
				.getInstance(IBackgroundMakerService.class);

		List<UserPicture> userPictures = userPicturesService
				.getUserPictures("marcelfalliere");
		backgroundMakerService.generateBackground(userPictures);

	}

}
