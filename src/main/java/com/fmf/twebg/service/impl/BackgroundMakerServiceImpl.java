package com.fmf.twebg.service.impl;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.fmf.twebg.model.UserPicture;
import com.fmf.twebg.service.IBackgroundMakerService;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class BackgroundMakerServiceImpl implements IBackgroundMakerService {

	private static final int THUMB_SIZE = 150;
	private final String tweetsPicturesPath;

	@Inject
	public BackgroundMakerServiceImpl(
			@Named("TWEETS_PICTURES_PATH") String tweetsPicturesPath) {
		this.tweetsPicturesPath = tweetsPicturesPath;
	}

	public String generateBackground(List<UserPicture> userPictures) {
		int targetWidth = (int) (THUMB_SIZE * floor(userPictures.size() / 2));
		int targetHeight = (int) (THUMB_SIZE * ceil(userPictures.size() / 2));
		BufferedImage generatedBackground = new BufferedImage(targetWidth,
				targetHeight, BufferedImage.TYPE_INT_ARGB);
		int imageIndex = 0;
		int lineIndex = 0;

		try {
			for (UserPicture userPicture : userPictures) {

				BufferedImage currentImage = ImageIO.read(new URL(userPicture
						.getUrl()));

				int currentX = (imageIndex * THUMB_SIZE) % targetWidth;
				int currentY = lineIndex * THUMB_SIZE;
				System.out.println(currentX + " vs " + currentY);

				generatedBackground.getGraphics().drawImage(currentImage,
						currentX, currentY, THUMB_SIZE, THUMB_SIZE, null);

				imageIndex++;
				if (imageIndex % floor(userPictures.size() / 2) == 0) {
					lineIndex++;
				}
			}

			ImageIO.write(generatedBackground, "png", new File(
					tweetsPicturesPath + "gen.png"));

		} catch (MalformedURLException e) {
			Logger.getAnonymousLogger().log(
					Level.WARNING,
					"An user picture has a malformed url (" + e.getMessage()
							+ ")");
		} catch (IOException e) {
			Logger.getAnonymousLogger().log(
					Level.WARNING,
					"An user picture can not be read OR cannot write final image ("
							+ e.getMessage() + ")");
		}

		return tweetsPicturesPath + "gen.png";
	}
}
