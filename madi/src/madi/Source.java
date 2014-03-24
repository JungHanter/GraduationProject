package madi;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PGraphics;

class Source extends NodeBase {
	private final int sourceNumber;
	private final int MY_COUNT_NUM;
	private final int MY_TEMPO_COUNT;
	private int innerSize;
	private int innerColor;

	private int measureDivision;

	private ArrayList<Connector> connectorList = null;
	private Connector[] nearestConnector = null;
	private AudioManager audioManager = null;

	// private

	public Source(Random rnd, int num, int color) {
		super(rnd, color, Global.MOVEMENT_SPEED);
		
		audioManager = AudioManager.getAudioManager();

		sourceNumber = num;
		if (num == 0)
			MY_TEMPO_COUNT = Global.TEMPO_COUNT;
		else
			MY_TEMPO_COUNT = Global.TEMPO_COUNT;
		MY_COUNT_NUM = sourceNumber * MY_TEMPO_COUNT / Global.NUMBER_OF_SOURCE;
		// println(MY_COUNT_NUM);

		size = Global.SOURCE_SIZE - Global.SOURCE_STROKE_WEIGHT;
		innerSize = size - Global.SOURCE_STROKE_WEIGHT;
		x = random.nextInt(Global.CANVAS_SIZE - Global.SOURCE_SIZE * 2)
				+ Global.SOURCE_SIZE;
		y = random.nextInt(Global.CANVAS_SIZE - Global.SOURCE_SIZE * 2)
				+ Global.SOURCE_SIZE;

		this.color = (color & 0x00FFFFFF) | 0xCC000000;
		this.innerColor = (color & 0x00FFFFFF) | 0x66000000;
		//effect = new NodeEffect(this, Global.TEMPO_SPEED);
		
		this.speed = this.speed*1.5f;
		
		image = parent.loadImage(Global.DIR_IMAGE+"source"+sourceNumber+".png");
		rotationSpeed = sourceNumber%2==1? Global.ROTATION_SOURCE_SPEED : -Global.ROTATION_SOURCE_SPEED;
		size = innerSize = image.width;
	}

	@Override
	public void draw(PGraphics canvas) {
		for (Connector conn : connectorList) {
			conn.draw(canvas);
		}
		
		canvas.pushMatrix();
		canvas.translate(x, y);
		canvas.rotate(PApplet.radians(angle));
		canvas.image(image, 0, 0);
		if(Global.DEBUG_NODETEXT) {
			canvas.textSize(50);
			canvas.textAlign(PApplet.CENTER, PApplet.CENTER);
			canvas.text(""+sourceNumber+".", 0, 0);
		}
		canvas.popMatrix();
		
	}

	@Override
	public void move() {
		super.move();
		angle += rotationSpeed;
	}

	
	// effect, emit
	public void run(long frames) {
		/*if (frames % MY_TEMPO_COUNT == MY_COUNT_NUM) {
			emit();
		}*/
		
		findNearestNode();

		//effect.run();
		for (Connector conn : connectorList) {
			//if(conn != null)
				conn.run();
		}
	}
	
	private void findNearestNode() {
		for (Connector conn : nearestConnector) {
			conn = null;
		}
		
		boolean bAlreadyExist = false;
		float dist, minDist = 10000;
		int pos = 0;
		for (int k=0; k<Global.CONNECT_NODE_MAX; k++) {
//			for (Connector conn : connectorList) {
//				if(conn.isPlayingSound()) {
//					try {
//						nearestConnector[k] = conn;
//						k++;
//					} catch(ArrayIndexOutOfBoundsException e) {
//						/*for(int i=0; i<connectorList.size(); i++) {
//							Connector c = connectorList.get(i);
//							System.out.println("i="+i+", "+c.getSource().getSourceNumber()+
//									":"+c.getNode().getNodeNumber()+", dist="+(int)c.getDist());
//						}*/
//						System.out.println(conn.getSource().getSourceNumber() +
//								":"+conn.getNode().getNodeNumber()+", dist="+(int)conn.getDist());
//						break;
//					}
//				}
//			}
//			if(k==Global.CONNECT_NODE_MAX) break;
			
			minDist = 10000;
			pos = 0;
			
			if(k==0) {			
				for (Connector conn : connectorList) {
					conn.calDist();
					dist = conn.getDist();
					if(dist < minDist) {
						nearestConnector[k] = conn;
						minDist = dist;
					}
				}
			} else {
				for (Connector conn : connectorList) {
					dist = conn.getDist();
					if(dist < minDist) {
						bAlreadyExist = false;
						for(int n=0; n<k; n++) {
							if(nearestConnector[n] == conn) {
								bAlreadyExist = true;
								break;
							}
						}
						if(!bAlreadyExist) {
							nearestConnector[k] = conn;
							minDist = dist;
						}
					}
				}
			}
		}
		
		for (Connector conn : connectorList) {
			conn.setConnect(false);
		}
		
		for (Connector conn : nearestConnector) {
			if(conn != null) {
				if(conn.getDist() > Global.MAX_BETWEEN_DIST && !conn.isPlayingSound()) {
					conn.setConnect(false);
					conn = null;
				} else {
					conn.setConnect(true);
				}
			}
		}
	}

	private void emit() {
		// println("source num:"+sourceNumber+" emitting!!!");

		//effect.startEffect();
		for (Connector conn : connectorList) {
			conn.startEmittingMeasure(Global.MEASURE_DIVISION, MY_TEMPO_COUNT);
		}

	}

	public void connect(ArrayList<Node> nodeList) {
		connectorList = new ArrayList<Connector>(nodeList.size());
		for (Node node : nodeList) {
			connectorList.add(new Connector(this, node));
		}
		
		nearestConnector = new Connector[Global.CONNECT_NODE_MAX];
	}

	public int getSize() {
		return size;
	}

	public int getSourceNumber() {
		return sourceNumber;
	}
}
