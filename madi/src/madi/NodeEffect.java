package madi;

import processing.core.PApplet;
import processing.core.PGraphics;

class NodeEffect {
	static private PApplet parentApplet = null;
	static public void setParent(PApplet parent) {
		NodeEffect.parentApplet = parent;
	}
	static {
		parentApplet = Madi.nowApplet();
	}


	private NodeBase parentNode;
	private int effectColor;
	private float size;
	private float step;
	private float effectSpeed;
	private final float MAX_SIZE;
	private boolean bRunning = false;

	public NodeEffect(NodeBase nodeBase, float effectSpeed) {
		parentNode = nodeBase;
		this.effectSpeed = effectSpeed;
		effectColor = nodeBase.getColor()|0xFF000000;
		MAX_SIZE = parentNode.getSize()*2;
	}

	public void startEffect() {
		size = 0f;
		bRunning = true;
	}

	public void run() {
		if(bRunning) {
			size = size + effectSpeed*2;
			nextColor();

			if(size >= MAX_SIZE) {
				bRunning = false;
			}
		}
	}

	public void draw(PGraphics canvas) {
		if(bRunning) {
			canvas.noStroke();
			canvas.fill(effectColor);
			canvas.ellipse(parentNode.getX(), parentNode.getY(), size, size);
		}
	}

	private void nextColor() {
		int alpha = (int)PApplet.map(size, 0, MAX_SIZE, 255, 0);
		alpha = (alpha<<24)&0xFF000000;
		effectColor = (effectColor&0x00FFFFFF)|alpha;
	} 
}
