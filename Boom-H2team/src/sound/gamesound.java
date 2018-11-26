package sound;



import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class gamesound {
	public static gamesound instance;

	public static final String MENU = "menu.wav";

	private HashMap<String, AudioClip> audioMap;

	public gamesound() {
		audioMap = new HashMap<>();
		loadAllAudio();
	}

	public static gamesound getIstance() {
		if (instance == null) {
			instance = new gamesound();
		}

		return instance;
	}

	public void loadAllAudio() {
		putAudio(MENU);
	
	}

	public void stop() {
		getAudio(MENU).stop();
		
	}

	public void putAudio(String name) {
		AudioClip auClip = Applet.newAudioClip(gamesound.class.getResource(name));
		audioMap.put(name, auClip);
	}

	public AudioClip getAudio(String name) {
		return audioMap.get(name);
	}
}
