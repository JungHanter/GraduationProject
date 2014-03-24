package madi;

import processing.core.PApplet;

public class Global {
  public static final int CANVAS_SIZE = 800;
  public static final int CANVAS_MARGIN_LEFT = 400;
  public static final int SOURCE_SIZE = CANVAS_SIZE/6; //10
  public static final int NODE_SIZE = SOURCE_SIZE/3;
  public static final int SOURCE_STROKE_WEIGHT = 20;
  public static final int CONNECTOR_STROKE_WEIGHT = 1;
  public static final int CONNECTOR_EMITTER_WEIGHT = 2;
  
  public static final boolean MOVE_GENERATOR = true;
  public static final float MOVEMENT_SPEED = 0.5f;
  public static final float MOVEMENT_SOURCE_SPEED = 0.1f;
  public static final float ROTATION_NODE_SPEED = PApplet.radians(20);
  public static final float ROTATION_SOURCE_SPEED = PApplet.radians(15);
  
  public static final float NODE_EFFECT_SPEED = 1.f;
  public static final float TEMPO_SPEED = 2.f;
  public static final int TEMPO_COUNT = 56;  //64
  
  public static final float MAX_BETWEEN_DIST = 240f;  //240
  public static final int MEASURE_DIVISION = 6;
  
  public static final int NUMBER_OF_SOURCE = 1;
  public static final int NUMBER_OF_NODETYPE = 4;
  public static final int NUMBER_OF_EACH_NODE = 1;
  public static final int NUMBER_OF_NODE = NUMBER_OF_NODETYPE * NUMBER_OF_EACH_NODE;
  
  private static final int CHANCE_SIDE_SMALL_ROTATION_VALUE = 85;
  private static final int CHANCE_SIDE_MID_ROTATION_VALUE = 10;
  private static final int CHANCE_OPPOSITE_DIRECTION_VALUE = 1;
  public static final int CHANCE_OPPOSITE_DIRECTION = CHANCE_OPPOSITE_DIRECTION_VALUE;
  public static final int CHANCE_SIDE_MID_ROTATION = CHANCE_OPPOSITE_DIRECTION+CHANCE_SIDE_MID_ROTATION_VALUE;
  public static final int CHANCE_SIDE_SMALL_ROTATION = CHANCE_SIDE_MID_ROTATION+CHANCE_SIDE_SMALL_ROTATION_VALUE;
  
  public static final float D30_PI = PApplet.HALF_PI/3f;
  public static final float D60_PI = D30_PI*2f;
  public static final float D15_PI = D30_PI/2f;
  
  public static final String DIR_IMAGE = "../data/image/";
  public static final String DIR_SOUND = "../data/sound/";

//  public static final color[] NODE_COLORS = {
//    0xFFB6C1, 0x9932CC, 0x6495ED, 0x20B2AA, 0xF0E68C, 0xE9967A
//  };  
  
  
}
