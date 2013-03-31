package com.fmf.twebg.service;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

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
		service = Guice.createInjector(new TwebgAppModule()).getInstance(
				IBackgroundMakerService.class);
	}

	@Test
	public void can_generate_even_if_a_picture_got_a_404()
			throws MalformedURLException, IOException {
		ArrayList<UserPicture> dummyList = Lists.newArrayList(
		//
				new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"http://pbs.twimg.com/media/AxoKK97CMAI7sZr.jpg", 3l), // the
																				// bad
																				// duck
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				);

		String generateBackground = service.generateBackground(dummyList);

		assertThat(generateBackground).isNotNull();
		assertThat(generateBackground).isNotEmpty();
		BufferedImage read = ImageIO.read(new File(generateBackground));
		assertThat(read.getWidth()).isEqualTo(300);
		assertThat(read.getHeight()).isEqualTo(300);

	}

	@Test
	public void should_generate_a_url_pointing_to_an_image()
			throws MalformedURLException, IOException {
		ArrayList<UserPicture> dummyList = Lists.newArrayList(
		//
				new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg", 3l), //
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				);

		String generateBackground = service.generateBackground(dummyList);

		assertThat(generateBackground).isNotNull();
		assertThat(generateBackground).isNotEmpty();
		BufferedImage read = ImageIO.read(new File(generateBackground));
		assertThat(read.getWidth()).isEqualTo(300);
		assertThat(read.getHeight()).isEqualTo(300);

	}

	@Test
	public void should_generate_a_url_pointing_to_an_image_big_siez()
			throws MalformedURLException, IOException {
		ArrayList<UserPicture> dummyList = Lists.newArrayList(
		//
				new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg", 3l), //
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				, new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg", 3l), //
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				, new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg", 3l), //
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				, new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg", 3l), //
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				, new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg", 3l), //
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				, new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg", 3l), //
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				, new UserPicture(
						"https://pbs.twimg.com/media/A59ZlAICEAAZSb_.jpg", 1l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A5U2og6CAAAZ29C.jpg", 2l), //
				new UserPicture(
						"https://pbs.twimg.com/media/A4TCuH2CYAAWPuu.jpg", 3l), //
				new UserPicture(
						"https://pbs.twimg.com/media/AwFjpVJCQAI552P.jpg", 4l) //
				);

		String generateBackground = service.generateBackground(dummyList);

		assertThat(generateBackground).isNotNull();
		assertThat(generateBackground).isNotEmpty();
		BufferedImage read = ImageIO.read(new File(generateBackground));
		assertThat(read.getWidth()).isEqualTo(600);
		assertThat(read.getHeight()).isEqualTo(750);

	}
}
