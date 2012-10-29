package com.fmf.twebg.service;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import com.fmf.twebg.model.UserPicture;
import com.fmf.twebg.module.TwebgAppModule;
import com.google.common.collect.Lists;
import com.google.inject.Guice;

public class BackgroundMakerServiceTest {
	private IBackgroundMakerService service;

	@Before
	public void before() {
		service = Guice.createInjector(new TwebgAppModule()).getInstance(IBackgroundMakerService.class);
	}

	@Test
	public void should_generate_a_url_pointing_to_an_image() throws MalformedURLException, IOException {
		String generateBackground = service.generateBackground(Lists.newArrayList( //
				new UserPicture("https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg:small"), //
				new UserPicture("https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg:small"), //
				new UserPicture("https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg:small"), //
				new UserPicture("https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg:small") //
				));

		assertThat(generateBackground).isNotNull();
		assertThat(generateBackground).isNotEmpty();
		BufferedImage read = ImageIO.read(new URL(generateBackground));
		assertThat(read.getHeight()).isGreaterThan(100);
		assertThat(read.getWidth()).isGreaterThan(100);

	}
}
