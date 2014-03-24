package madi;

import processing.data.*;
import processing.core.*;

class Connector {
	static private PApplet parent = null;
	static public void setParent(PApplet parent) {
		Connector.parent = parent;
	}
	static {
		parent = Madi.nowApplet();
	}

	private final Source source;
	private final Node node;

	private float x, y;
	private int step;
	private int goalStep;
	private int measureNum;
	private int tempSpeed;

	private float dist;

	private boolean bRunning;
	
	private boolean bPlaying;
	private boolean bConnecting;

	public Connector(Source src, Node nd) {
		source = src;
		node = nd;
		bRunning = false;
		calDist();
	}

	public void run() {
		calDist();
		
		if(bConnecting) {
			node.play(source.getSourceNumber());
			bPlaying = true;
		} else {
			node.stop(source.getSourceNumber());
			bPlaying = false;
		}

		/*if (dist >= Global.MAX_BETWEEN_DIST)
			bRunning = false;

		if (bRunning) {
			step++;

			// x = source.getX() + (((node.getX()-source.getX())/dist) *
			// Global.TEMPO_SPEED*step);
			// y = source.getY() + (((node.getY()-source.getY())/dist) *
			// Global.TEMPO_SPEED*step);

			x = source.getX() + ((node.getX() - source.getX()) * step / (float) goalStep);
			y = source.getY() + ((node.getY() - source.getY()) * step / (float) goalStep);

			// if(checkArrival()) {
			if (step == goalStep) {
				bRunning = false;
				node.play(source.getSourceNumber());
			}

		}*/
	}

	public void draw(PGraphics canvas) {
		// draw line
		if (isPlayingSound()) {
			canvas.strokeWeight(Global.CONNECTOR_STROKE_WEIGHT);
			canvas.stroke(parent.color(255, (int) PApplet.map(dist, 0f,
					Global.MAX_BETWEEN_DIST, 255f, 63f)));
			canvas.line(source.getX(), source.getY(), node.getX(), node.getY());
		}
	}

	public void startEmittingMeasure(int measureDivision, int tempoCount) {
		/*if( dist < Global.MAX_BETWEEN_DIST ) {
			bRunning = true;
			x = source.getX();
			y = source.getY();

			step = 0;
			int measureLength = (int)(Global.MAX_BETWEEN_DIST)/measureDivision;
			measureNum = ((int)dist/measureLength)+1;
			goalStep = measureNum * (tempoCount/measureDivision);

			//println(""+ measureLength + "/" + measureNum + "/" + goalStep);
		}*/
	}

	/*
	 * public void startEmitting() { if( dist < Global.MAX_BETWEEN_DIST ) {
	 * bRunning = true; x = source.getX(); y = source.getY(); step = 0; } }
	 */
	
	public void setConnect(boolean bConnect) {
		this.bConnecting = bConnect;
	}
	
	public boolean isConnecting() {
		return bConnecting;
	}
	
	public boolean isPlayingSound() {
		return AudioManager.getAudioManager()
				.isPlayingSound(source.getSourceNumber(), node.getNodeNumber());
		//return bPlaying;
	}

	public void calDist() {
		dist = PApplet.dist(source.getX(), source.getY(), node.getX(), node.getY());
	}

	private boolean checkArrival() {
		if (dist <= step * Global.TEMPO_SPEED)
			return true;
		else
			return false;
	}

	public float getDist() {
		//calDist();
		return dist;
	}
	
	public Node getNode() {
		return node;
	}
	
	public Source getSource() {
		return source;
	}
}
