package madi;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

class AudioManager {
	private Minim minim;

	private ArrayList<AudioPlayer>[][] players = new ArrayList[Global.NUMBER_OF_SOURCE][Global.NUMBER_OF_NODETYPE];

	private int[][] volumes = new int[Global.NUMBER_OF_SOURCE][Global.NUMBER_OF_NODETYPE];

	public void playSound(int sourceNum, int nodeNum) {

		/*boolean bAllUsing = true;
		for (AudioPlayer player : players[sourceNum][nodeNum]) {
			if (!player.isPlaying()) {
				bAllUsing = false;

				player.cue(0);
				player.setGain(volumes[sourceNum][nodeNum]);
				player.play();
				break;
			}
		}

		if (bAllUsing) {
			AudioPlayer player = minim.loadFile("../data/" + sourceNum + nodeNum
					+ ".wav");
			players[sourceNum][nodeNum].add(player);
			System.out.println("ADD NEW PLYAER("
					+ players[sourceNum][nodeNum].size() + ") - source:"
					+ sourceNum + ", node:" + nodeNum);

			player.setGain(volumes[sourceNum][nodeNum]);
			player.play();
		}*/
	}

	public void init(Minim minim) {
		this.minim = minim;

		/*for (int src = 0; src < Global.NUMBER_OF_SOURCE; src++) {
			for (int node = 0; node < Global.NUMBER_OF_NODETYPE; node++) {
				players[src][node] = new ArrayList<AudioPlayer>(
						Global.NUMBER_OF_EACH_NODE * 2 + 1);

				for (int i = 0; i < Global.NUMBER_OF_EACH_NODE; i++)
					players[src][node].add(minim.loadFile("../data/" + src + node
							+ ".wav"));
			}
		}*/

	}

	public AudioManager() {
		for (int src = 0; src < Global.NUMBER_OF_SOURCE; src++) {
			for (int node = 0; node < Global.NUMBER_OF_NODETYPE; node++) {
				volumes[src][node] = 0;
			}
		}

	}

	public void setVolume(int src, int node, int volume) {
		volumes[src][node] = volume;
	}

	public int getVolume(int src, int node) {
		return volumes[src][node];
	}

	
	private static AudioManager mAudioManager = null;
	public static AudioManager getAudioManager() {
		if (mAudioManager == null) {
			mAudioManager = new AudioManager();
		}
		return mAudioManager;
	}
}