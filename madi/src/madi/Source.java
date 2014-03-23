package madi;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PGraphics;

class Source extends NodeBase {
	private final int sourceNumber;
	private final int MY_COUNT_NUM;
	private final int MY_TEMPO_COUNT;
	private int innerSize;
	private int innerColor;

	private int measureDivision;

	private ArrayList<Connector> connectorList = null;

	// private

	public Source(Random rnd, int num, int color) {
		super(rnd, color, Global.MOVEMENT_SPEED);

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
	}

	public void draw(PGraphics canvas) {
		//effect.draw(canvas);

		for (Connector conn : connectorList) {
			conn.draw(canvas);
		}

		canvas.fill(innerColor);
		canvas.noStroke();
		canvas.ellipse(x, y, innerSize, innerSize);
		canvas.noFill();
		canvas.stroke(color);
		canvas.strokeWeight(Global.SOURCE_STROKE_WEIGHT);
		canvas.ellipse(x, y, size, size);
	}

	// effect, emit
	public void run(long frames) {
		if (frames % MY_TEMPO_COUNT == MY_COUNT_NUM) {
			emit();
		}

		//effect.run();
		for (Connector conn : connectorList) {
			conn.run();
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
	}

	public int getSize() {
		return size + Global.SOURCE_STROKE_WEIGHT * 2;
	}

	public int getSourceNumber() {
		return sourceNumber;
	}
}
