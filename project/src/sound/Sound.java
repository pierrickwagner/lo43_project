package sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {//load sound
	public static final AudioClip MUSIC = Applet.newAudioClip(Sound.class.getResource("music.wav"));
}