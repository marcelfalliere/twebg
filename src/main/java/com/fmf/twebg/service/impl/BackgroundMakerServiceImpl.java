package com.fmf.twebg.service.impl;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
		int nbWidth = (int) floor(Math.sqrt(userPictures.size()));
		int nbHeight = (int) ceil(Math.sqrt(userPictures.size()));
		int targetWidth = THUMB_SIZE * nbWidth;
		int targetHeight = THUMB_SIZE * nbHeight;

		// test malade: ajouter des images pour combler
		int restants = userPictures.size() % nbWidth;
		while (restants >= 0) {
			userPictures
					.add(userPictures.get((int) (Math.random() * (userPictures
							.size()))));
			restants--;
		}

		BufferedImage generatedBackground = new BufferedImage(targetWidth,
				targetHeight, BufferedImage.TYPE_INT_ARGB);
		int imageIndex = 0;
		int lineIndex = 0;

		System.out.println("userPicturesSize=" + userPictures.size()
				+ " width:" + targetWidth + " height:" + targetHeight);

		try {
			for (UserPicture userPicture : userPictures) {

				BufferedImage currentImage = ImageIO.read(new URL(userPicture
						.getThumbnailUrl()));

				int currentX = (imageIndex * THUMB_SIZE) % targetWidth;
				int currentY = lineIndex * THUMB_SIZE;

				System.out.println("imageIndex=" + imageIndex + " foundX:"
						+ currentX + " foundY:" + currentY);

				generatedBackground.getGraphics().drawImage(currentImage,
						currentX, currentY, THUMB_SIZE, THUMB_SIZE, null);

				imageIndex++;
				if (imageIndex % floor(Math.sqrt(userPictures.size())) == 0) {
					lineIndex++;
				}
			}

			ImageIO.write(generatedBackground, "png", new File(
					tweetsPicturesPath + "gen.png"));

		} catch (MalformedURLException e) {
			throw new RuntimeException("An user picture has a malformed url ("
					+ e.getMessage() + ")");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"An user picture can not be read OR cannot write final image ("
							+ e.getMessage() + ")");

		}

		return tweetsPicturesPath + "gen.png";
	}
}
