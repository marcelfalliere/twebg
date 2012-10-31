package com.fmf.twebg.module;

import static com.google.inject.name.Names.bindProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import com.fmf.twebg.dao.IUserPicturesDao;
import com.fmf.twebg.dao.impl.UserPicturesDaoImpl;
import com.fmf.twebg.service.IBackgroundMakerService;
import com.fmf.twebg.service.IUserPicturesService;
import com.fmf.twebg.service.impl.BackgroundMakerServiceImpl;
import com.fmf.twebg.service.impl.UserPicturesServiceImpl;
import com.google.inject.AbstractModule;

public class TwebgAppModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			Properties properties = new Properties();
			properties.load(new FileReader(new File(TwebgAppModule.class
					.getResource("/app.properties").toURI())));

			bindProperties(binder(), properties);
			bind(IUserPicturesService.class).to(UserPicturesServiceImpl.class);
			bind(IUserPicturesDao.class).to(UserPicturesDaoImpl.class);
			bind(IBackgroundMakerService.class).to(
					BackgroundMakerServiceImpl.class);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Properties file not found.");
		} catch (IOException e) {
			throw new RuntimeException("IOE when opening properties file.");
		} catch (URISyntaxException e) {
			throw new RuntimeException(
					"URISyntaxException when opening properties file.");
		}
	}

}
