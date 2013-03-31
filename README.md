What is this thing ?
---

This little java program takes all pictures tweeted by a specific account and generates a background with them.


How does it works ?
---

Twitter does not provide a service to fetch all tweeted pictures of a specific user. Therefore, what this program does is a bit hack'n'dirty, that is, it reads all the tweets someone tweeted, looks for images. So it works, but in regards to the twitter API limitations, the program may not generate an image containing all the tweeted prictures of an account.

How to use it ?
---

  - `$> git clone https://github.com/marcelfalliere/twebg.git`
  - Import in your favorite IDE
  - Open `com.fmf.twebg.appService.TweBgService`
  - Put the twitter handle on line 25 for whom you want a generated image of all tweeted pictures
  - Open `app.properties` and change the path to the generated output image
  - Run it
  - Dude, image generated !

