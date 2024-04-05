import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import toxi.physics2d.*; 
import toxi.physics2d.behaviors.*; 
import toxi.geom.*; 
import ddf.minim.*; 
import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Nathan_Forsyth_Project_3_WORKING extends PApplet {

/*
NATHAN FORSYTH
RELAXING WINDOW SIMULATOR
PROJECT 3
*/


/**
 * Dan Shiffman
 * "http://www.shiffman.net/teaching/nature/toxiclibs/"
 * Spring 2010</p>
*/

//toxiclibs 




//minim


//stuff to see if I am yelling

Amplitude amp;
AudioIn in;

//Minim & tracks
Minim minim;
AudioPlayer winterLofi; //invention_ - Snowstalgia https://chll.to/c8b3bf82
AudioPlayer cityLofi; //Ruck P, Shuko - 5 am https://chll.to/2656fbfe
AudioPlayer forestLofi; //G Mills, Kyle McEvoy, Luke Otwell - Ocean View https://chll.to/22760e63
AudioPlayer mountainLofi; //niquo - Our Star https://chll.to/e98d6ee1
AudioPlayer rainLofi; //Knowmadic - Faces https://chll.to/dc25042b
AudioPlayer starsLofi; //Ian Ewing - Healing https://chll.to/59009739
AudioPlayer plantsLofi; //Philanthrope, Ian Ewing, Sleepy Fish - Plants https://chll.to/63ecd6fe
AudioPlayer encouragement;
AudioPlayer foggy;

// Reference to physics "world" (2D)
VerletPhysics2D physics;

// Our "Sock" object
Stock stock1;
Stock stock2;
Stock stock3;
Stock stock4;
Stock stock5;
Stock stock6;

//images that are through the window
PImage rain;//https://www.flickr.com/photos/stillugly/25663155978/in/photolist-F6Lobw-UfyEo9-RA1zKG-ZpB6y5-2df5t15-LeE6Fv-29VptMJ-28BELqn-26ZBkeg-SHZsfk-VoTTQN-EQX8mE-27bo7ZW-zw9VHq-SHZtje-24tyi2q-SyaS8Y-YT7JUf-RZgJ1h-2dthg3q-9tsfwG-KfRWkr-2eoYUwo-RPXs2s-MZ9d7z-SiVZhA-pZhFBf-28p3nAs-21M9qqC-NE39L5-JqCGPX-Kex4iZ-pXpb4P-PfqbvM-GUEkDD-iNQJbt-2dDmtL1-TH7pq5-5A3zZW-ofKJFw-91kWGN-x8pBGA-247ZWgn-mDnAJu-GoDEDk-7PPGyR-ddh5v6-sLSMy9-zPUvK1-Sk9r9
PImage stars; //https://www.flickr.com/photos/122323654@N05/15024418586/in/photolist-oTE5cL-qTnATA-u2Jjv-4csmxE-5KFvcL-5KFuZU-5KFviy-5LQDAL-261n61q-euTdwd-5uQPZf-97AUWh-4dnU2-8HLsg4-6Ukoex-6gvbWj-m1xdB-2nLp8-6xbPjy-a7EE38-5tbiPr-aeehDx-5KBgip-5KFDpb-41t3T-7sCnu-LGpBh-6212cx-fs5y3N-aeeh5T-8ufh5f-4oJF58-d2MN8U-nkf2S-HdKdko-vwPhb-uA7KS-vzfDy-8Yo1TS-8EGwVy-648vPX-bi8BRg-5c9eNk-6wxz2P-aeh5mh-4VHicA-Nz5Xdn-akP1vx-2kaCnR-7mP8om
PImage winter; //https://www.flickr.com/photos/torremountain/6831414535/in/photolist-bpEMxe-aXEcaT-aC2igE-9QzQgu-9GRKCW-9zbkRF-9exB5x-9d1wDR-98THYY-93Edq3-8PCvvA-7K4apc-7FNwVb-7FGmni-7DvBgg-7BX8BB-7B66RL-7zNfEk-7yVitd-7y5YS3-7xQJ78-7JDtVm-7ufK6r-7udjer-7peLyH-7nYehF-6GDMCp-6v3fPX-6do9Ps-5ZhjYp-5XPVoD-5ThVRM-5PDhAg-5PkCeq-5NDJ5A-5FKV3g-5tB2Jr-5gZqqs-4oFoFW-4onviq-4gD4zF-4f62bn-s5FvK-miWRA7-bAE38e-8rV943-4kiZse-2k6N7u7-2k1t4Pj-2j84sWn
PImage forest; //https://www.flickr.com/photos/tim_gorman/7529595228/in/photolist-ctn9ko-d1k14q-dnxa2t-wytgPq-8yjUig-pinTqH-21PG7Fp-Mfrc6Y-gJAvUU-gJBpMk-24sy3Z4-227eBV7-8R1c-nvUV3b-dyXjEt-6qsCQC-JhkaTk-9TR7Xg-d1jV6U-mnATLr-Pu9tJf-6X4XbL-2eCoWW-gJuTnA-6YLKpW-UpFyBh-mnBBdn-eKCw1s-poXBNW-5NiEZy-7Tqx6J-6SydKA-5YokF6-2amGWyL-gpwdbQ-rfdCq5-2amGXVJ-Jbd9Wp-21Q4kUM-8Ub2wu-8j3kxW-TTmr3m-hb7VJj-6m96th-tXW7NQ-GiHSCA-6H38Rf-21Q4mhv-9Fcgiz-diSZP1
PImage city; //https://www.flickr.com/photos/lennykphotography/24382932290/in/photolist-D9CUhj-8RDRJn-HWsuf9-qAPEwp-dyBDjm-DpeasQ-2bXpFCu-JXcce3-AENoR7-DCtiHK-b21Mc-Vf5wnj-4aBY2-Cro9xJ-VyvAcf-qnu4dk-263Np8R-8BL7Qm-wmr58Z-ZHWeNA-KNZvn6-KfAk8H-KKNPQr-HgD5Rj-Y3U6bv-29ayPtH-vy49qb-rFtAzv-22ke7P9-25v7nnS-GS5HPT-uVQvgW-2ezpiFd-2auwpmg-21eUDV5-27PTBrR-v4vHyH-58ebMi-p4JBcu-s1VY7n-SkEjPd-wpZkAF-uJQqad-xVf8iE-DV4zW9-xE4DcX-BrMWkC-uGdK2-tLEs4d-29hY5hg
PImage mountain; //https://www.flickr.com/photos/nasacommons/15656927981/in/photolist-pRxRda-TFbyAs-26w9QTo-26fWsA3-29oBmQj-22TzPTm-27WE6vS-297wrDJ-ZVWnfZ-2aCEtgu-257mYTF-2eZisGK-23zdWoi-21kcsUA-2433gRs-24EaNwB-2beHBCd-28zj3my-YExZQs-24yCcuW-21Py7mD-JbRqoK-23DdVnu-9fRmrW-CD7KTs-25fGqrN-23x3ZPk-S5e7vn-23ySuUB-ZYKZk9-RAoBvB-Z69iCU-23vXD5t-297Pf21-2cQHH4J-LLMLT6-252j9HM-29KvdWN-22NxnVs-23yaYCQ-pznyGP-212mnPH-22Kw2Ff-2bXj66A-2a2k6zn-EymzPA-ZNP9Fn-NNsod9-TiW6Au-P8HWam

//array slideshow for images
PImage [] swapPic;
int current = 0;

//things for "helpful Nathan"
int  m, startTime; //controls animation time
boolean callib, panicTest, runSim; //controls if animation should run and callibrates "m" when it does
float iX, iY; //image x and y position for nathan

//nathan images (not all got used)
PImage armscrossed, exhale, fingerguns, five, four, hug, inhale, knock1, knock2, one, self, shrug, three, thumbsup, two, wavebye, wavehello;

//PGraphics pieces for frosty window to draw on
PGraphics myMask;
PGraphics frost;
boolean frostyWindow = true;
float frostOpacity = 0;
boolean noWindow = false;

//one of the screens doesn't have a window and the physics run wayyyy smoother
boolean plantMode = false;

//controls plant colors
int pStroke, pFill;


////////////////////////////////////////////////////SET UP///////////////////////////////////////////////
public void setup() {
  
  // Initialize the physics world
  physics=new VerletPhysics2D();
  physics.addBehavior(new GravityBehavior2D(new Vec2D(0, -20)));
  physics.setWorldBounds(new Rect(0, 0, width, height));

  //makes images manageable as well as rectangles
  rectMode(CENTER);
  imageMode(CENTER);

  //initialize stock object
  stock1 = new Stock(height/25, 0.3f, height/115, width/12, height-height/6);
  stock2 = new Stock(height/25, 0.3f, height/115, width - width/12, height-height/6);
  stock3 = new Stock(height/25, 0.3f, height/115, (width/7)*2, (height-height/6));
  stock4 = new Stock(height/25, 0.3f, height/115, (width/7)*3, (height-height/6));
  stock5 = new Stock(height/25, 0.3f, height/115, (width/7)*4, (height-height/6));
  stock6 = new Stock(height/25, 0.3f, height/115, (width/7)*5, (height-height/6));

  //initialize images and image array
  mountain = loadImage("mountain_view.jpg");
  city = loadImage("city_view.jpg");
  forest = loadImage("forest_view.jpg");
  winter = loadImage("winter_view.jpg");
  rain = loadImage("rainy_view.jpg");
  stars = loadImage("star_view.jpg");
  swapPic = new PImage [] {mountain, city, forest, winter, rain, stars};

  //initialize helpful nathan images
  armscrossed = loadImage("armscrossed.png");
  exhale = loadImage("exhale.png");
  fingerguns = loadImage("fingerguns.png");
  five = loadImage("five.png");
  four = loadImage("four.png");
  hug = loadImage("hug.png");
  inhale = loadImage("inhale.png");
  knock1 = loadImage("knock1.png");
  knock2 = loadImage("knock2.png");
  one = loadImage("one.png");
  self = loadImage("self.png");
  shrug = loadImage("shrug.png");
  three = loadImage("three.png");
  thumbsup = loadImage("thumbsup.png");
  two = loadImage("two.png");
  wavebye = loadImage("wavebye.png");
  wavehello = loadImage("wavehello.png");

  //initialize minim & audio files
  minim = new Minim(this);
  mountainLofi = minim.loadFile("mountainLofi.mp3");
  cityLofi = minim.loadFile("cityScapeLofi.mp3");
  forestLofi = minim.loadFile("forestLofi.mp3");
  winterLofi = minim.loadFile("winterLofi.mp3");
  rainLofi = minim.loadFile("rainLofi.mp3");
  starsLofi = minim.loadFile("starsLofi.mp3");
  plantsLofi = minim.loadFile("Plants.mp3");
  encouragement = minim.loadFile("encouragement.mp3");
  foggy = minim.loadFile("foggy.mp3");

  //initialize yell detecter
  amp = new Amplitude(this);
  in = new AudioIn(this, 0);
  in.start();
  amp.input(in);
  panicTest = true;

  //initializes PGraphics for frosty window
  myMask = createGraphics(width, height);
  frost = createGraphics(width, height);

  //sets the volume so things can be heard properly
  mountainLofi.setGain(-25);
  cityLofi.setGain(-25);
  forestLofi.setGain(-25);
  starsLofi.setGain(-25);
  rainLofi.setGain(-25);
  winterLofi.setGain(-25);
  plantsLofi.setGain(-25);
  encouragement.setGain(7.5f);

  //places the helpful nathan image
  iX = width/6;
  iY = height/6-40;
} 
///////////////////////////////////////////////////////////DRAW//////////////////////////////////////////
public void draw() {
  //calls the custom functions that draw the frost and the mask that allows transparency on it
  msk();
  frost();
  frost.mask(myMask);

  //slideshow of scenes, controlled by pressing f (f increases current)
  if (current == 0) {
    plantMode = false;
    noWindow = false;
    mountainScene();
  } else if (current == 1) {
    cityScene();
  } else if (current == 2) {
    forestScene();
  } else if (current == 3) {
    winterScene();
  } else if (current ==4) {
    rainScene();
  } else if (current ==5) {
    starsScene();
  } else if (current ==6) {
    noWindow = true;
    plantMode = true;
    plantsOnly();
  } else {
    current = 0;
  }

  //listens for volume of audio input only if helpful nathan isn't currently doing his thing 
  //AND a window exists in frame (there is no window on plant only mode)
  if (panicTest && !noWindow) {
    if (amp.analyze() > 0.4f) {
      panicTest = false;
      runSim = true; //triggers runSim (run simulation)
      callib = false;
    }
  }
  //if the runSim switch has been flipped, then it plays the helpful nathan Audio and animation
  if (runSim) {
    if (!callib) { //callibration set up so that startTime only gets changed upon triggering the simulation
      encouragement.rewind();
      encouragement.play();
      callib = true;
      startTime = millis();
    }
    pushMatrix(); //needed to scale nathan so that he was an appropriate fit for the screen
    scale(3);
    m = millis() - startTime;
    if (m < 420) {
      image(knock1, iX, iY);
    } else if (m < 575) {
      image(knock2, iX, iY);
    } else if (m < 850) {
      image(knock1, iX, iY);
    } else if (m < 1050) {
      image(knock2, iX, iY);
    } else if (m < 1260) {
      image(knock1, iX, iY);
    } else if (m < 1500) {
      image(knock2, iX, iY);
    } else if (m < 3150) {
      image(wavehello, iX, iY);
    } else if (m < 5640) {
      image(armscrossed, iX, iY);
    } else if (m < 7150) {
      image(self, iX, iY);
    } else if (m < 9300) {
      image(shrug, iX, iY);
    } else if (m < 12250) {
      image(hug, iX, iY);
    } else if (m < 14700) {
      image(armscrossed, iX, iY);
    } else if (m < 15750) {
      image(self, iX, iY);
    } else if (m < 16500) {
      image(shrug, iX, iY);
    } else if (m < 17500) {
      image(self, iX, iY);
    } else if (m < 18725) {
      image(fingerguns, iX, iY);
    } else if (m < 19400) {
      image(self, iX, iY);
    } else if (m < 21150) {
      image(thumbsup, iX, iY);
    } else if (m < 22170) {
      image(five, iX, iY);
    } else if (m < 23750) {
      image(wavehello, iX, iY);
    } else if (m < 25000) {
      image(one, iX, iY);
    } else if (m < 25550) {
      image(fingerguns, iX, iY);
    } else if (m < 26800) {
      image(hug, iX, iY);
    } else if (m < 28800) {
      image(thumbsup, iX, iY);
    } else if (m < 30750) {
      image(self, iX, iY);
    } else if (m < 32350) {
      image(inhale, iX, iY);
    } else if (m < 34150) {
      image(exhale, iX, iY);
    } else if (m < 36000) {
      image(inhale, iX, iY);
    } else if (m < 37900) {
      image(exhale, iX, iY);
    } else if (m < 39750) {
      image(inhale, iX, iY);
    } else if (m < 42150) {
      image(exhale, iX, iY);
    } else if (m < 44000) {
      image(inhale, iX, iY);
    } else if (m < 46000) {
      image(exhale, iX, iY);
    } else if (m < 48000) {
      image(inhale, iX, iY);
    } else if (m < 50000) {
      image(exhale, iX, iY);
    } else if (m < 51500) {
      image(inhale, iX, iY);
    } else if (m < 53500) {
      image(exhale, iX, iY);
    } else if (m < 55150) {
      image(inhale, iX, iY);
    } else if (m < 57000) {
      image(exhale, iX, iY);
    } else if (m < 58800) {
      image(inhale, iX, iY);
    } else if (m < 60700) {
      image(exhale, iX, iY);
    } else if (m < 62550) {
      image(inhale, iX, iY);
    } else if (m < 64500) {
      image(exhale, iX, iY);
    } else if (m < 66250) {
      image(inhale, iX, iY);
    } else if (m < 68600) {
      image(exhale, iX, iY);
    } else if (m < 70100) {
      image(self, iX, iY);
    } else if (m < 71900) {
      image(thumbsup, iX, iY);
    } else if (m <74050) {
      image(five, iX, iY);
    } else if (m < 75360) {
      image(wavebye, iX, iY);
    } else if (m >75360) {
      runSim = false;
      panicTest = true;
    }
    popMatrix();
  }

  //draws the frosty window if a window exists
  if (!noWindow) {
    tint(255, frostOpacity); //tints the window with a lower opacity
    image(frost, width/2, height/2);
    if (frostOpacity > 0) {
      frostOpacity -= 0.15f; //opacity gradually fades, just like a real foggy window!
    }
  }
  tint(255, 255);//resets tint for all the other images

  //updates the physiscs for the plant
  physics.update();

  //sets colors for the plant
  stroke(pStroke);
  fill(pFill);

  //displays and updates the leaf positions of the plants
  stock1.updateLeaf(mouseX, mouseY);
  stock1.display();
  stock2.updateLeaf(mouseX, mouseY);
  stock2.display();
  if (plantMode) { //these 4 plants only are displayed in plant mode
    stock3.updateLeaf(mouseX, mouseY);
    stock3.display();
    stock4.updateLeaf(mouseX, mouseY);
    stock4.display();
    stock5.updateLeaf(mouseX, mouseY);
    stock5.display();
    stock6.updateLeaf(mouseX, mouseY);
    stock6.display();
  }
}

////////////////////////////////////////MOUSE PRESSED//////////////////////////////////////////
public void mousePressed() {
  //just checks to see if the mouse is within the circles on each plant
  stock1.contains(mouseX, mouseY);
  stock2.contains(mouseX, mouseY);
  if (plantMode) {//only exist in plant mode
    stock3.contains(mouseX, mouseY);
    stock4.contains(mouseX, mouseY);  
    stock5.contains(mouseX, mouseY);  
    stock6.contains(mouseX, mouseY);
  }
}
/////////////////////////////////////KEY PRESSED/////////////////////////////////////////////////
public void keyPressed() {
  //on space it fogs up the window
  if (key == ' ') {
    frostyWindow = true; //draws window
    frostOpacity = 50; //sets opacity of frost to 50
    foggy.rewind(); //makes a breathing sound
    foggy.play();
  }
}

/////////////////////////////////////MOUSE RELEASED////////////////////////////////////////////////
public void mouseReleased() {
  //lets go of the plants when you stop dragging them
  stock1.release();
  stock2.release();
  stock3.release();
  stock4.release();
  stock5.release();
  stock6.release();
}

////////////////////////////////////KEY RELEASED//////////////////////////////////////////////////
public void keyReleased() {

  //decided to use key released to change scenes so that it didn't ever accidentally skip a scene
  if (key == 'f' || key == 'F') {
    current++;//increases slide counter
    //rewinds all the music so it will always start at the beginning
    mountainLofi.rewind();
    cityLofi.rewind();
    forestLofi.rewind();
    winterLofi.rewind();
    rainLofi.rewind();
    starsLofi.rewind();
    plantsLofi.rewind();
  }
}

///////////////////////////////////////MOUNTAIN SCENE///////////////////////////////////////////////
public void mountainScene() {
  //sets background color and picture frame
  background(0xff206F95);
  stroke(0xff7C460C);
  strokeWeight(50);
  fill(0xff7C460C);
  rect(width/2, height/2-150, 2048, 1365);

  //plays the correct song while pausing all the others
  mountainLofi.play();
  cityLofi.pause();
  forestLofi.pause();
  winterLofi.pause();
  rainLofi.pause();
  starsLofi.pause();
  plantsLofi.pause();

  //displays the picture using the slideshow array
  image(swapPic[current], width/2, height/2-150, width * 0.632099f, height * 0.631944f);

  //makes a little shelf
  noStroke();
  fill(0xff124055);
  rect(width/2, height, width, height*0.3f);

  //makes two little pots
  fill(0xffE8AC6C);
  beginShape();
  vertex(width/12 + width/15, height-height/6);
  vertex(width/12 - width/15, height-height/6);
  vertex(width/12 - width/20, height-height/20);
  vertex(width/12 + width/20, height-height/20);
  endShape();
  beginShape();
  vertex(width - width/12 + width/15, height-height/6);
  vertex(width - width/12 - width/15, height-height/6);
  vertex(width - width/12 - width/20, height-height/20);
  vertex(width - width/12 + width/20, height-height/20);
  endShape();

  //sets the color for the plants in this room
  pStroke = 0xffEE884E;
  pFill = 0xffCE5A20;
}

///////////////////////////////////////////CITY SCENE////////////////////////////////////////////////
public void cityScene() {
  //sets background color and picture frame
  background(0xff685968);
  stroke(0xff958794);
  strokeWeight(50);
  fill(0xff958794);
  rect(width/2, height/2-150, 2048, 1365);

  //plays the correct song while pausing all the others
  mountainLofi.pause();
  cityLofi.play();
  forestLofi.pause();
  winterLofi.pause();
  rainLofi.pause();
  starsLofi.pause();
  plantsLofi.pause();

  //displays the picture using the slideshow array
  image(swapPic[current], width/2, height/2-150, width * 0.632099f, height * 0.631944f);

  //makes a little shelf
  noStroke();
  fill(0xff4E3B4F);
  rect(width/2, height, width, height*0.3f);

  //makes two little pots
  fill(0xff7996D3);
  beginShape();
  vertex(width/12 + width/15, height-height/6);
  vertex(width/12 - width/15, height-height/6);
  vertex(width/12 - width/20, height-height/20);
  vertex(width/12 + width/20, height-height/20);
  endShape();
  beginShape();
  vertex(width - width/12 + width/15, height-height/6);
  vertex(width - width/12 - width/15, height-height/6);
  vertex(width - width/12 - width/20, height-height/20);
  vertex(width - width/12 + width/20, height-height/20);
  endShape();

  //sets the color for the plants in this room
  pStroke = 0xff1884EF;
  pFill = 0xff314153;
}

/////////////////////////////////////////////FOREST SCENE///////////////////////////////////////////
public void forestScene() {
  //sets background color and picture frame
  background(0xffE0D2AE);
  stroke(0xff483A2D);
  strokeWeight(50);
  fill(0xff483A2D);
  rect(width/2, height/2-150, 2048, 1365);

  //plays the correct song while pausing all the others
  mountainLofi.pause();
  cityLofi.pause();
  forestLofi.play();
  winterLofi.pause();
  rainLofi.pause();
  starsLofi.pause();
  plantsLofi.pause();

  //displays the picture using the slideshow array
  image(swapPic[current], width/2, height/2-150, width * 0.632099f, height * 0.631944f);

  //makes a little shelf
  noStroke();
  fill(0xff332920);
  rect(width/2, height, width, height*0.3f);

  //makes two little pots
  fill(0xff9FCAFB);
  beginShape();
  vertex(width/12 + width/15, height-height/6);
  vertex(width/12 - width/15, height-height/6);
  vertex(width/12 - width/20, height-height/20);
  vertex(width/12 + width/20, height-height/20);
  endShape();
  beginShape();
  vertex(width - width/12 + width/15, height-height/6);
  vertex(width - width/12 - width/15, height-height/6);
  vertex(width - width/12 - width/20, height-height/20);
  vertex(width - width/12 + width/20, height-height/20);
  endShape();

  //sets the color for the plants in this room
  pStroke = 0xff1A5613;
  pFill = 0xff43743F;
}

////////////////////////////////////////////WINTER ECENE/////////////////////////////////////////////
public void winterScene() {
  //sets background color and picture frame
  background(0xffEDDDDC);
  stroke(0xffBA9291);
  strokeWeight(50);
  fill(0xffBA9291);
  rect(width/2, height/2-150, 2048, 1365);

  //plays the correct song while pausing all the others
  mountainLofi.pause();
  cityLofi.pause();
  forestLofi.pause();
  winterLofi.play();
  rainLofi.pause();
  starsLofi.pause();
  plantsLofi.pause();

  //displays the picture using the slideshow array
  image(swapPic[current], width/2, height/2-150, width * 0.632099f, height * 0.631944f);

  //makes a little shelf
  noStroke();
  fill(0xffD3C2C1);
  rect(width/2, height, width, height*0.3f);

  //makes two little pots
  fill(0xff838D94);
  beginShape();
  vertex(width/12 + width/15, height-height/6);
  vertex(width/12 - width/15, height-height/6);
  vertex(width/12 - width/20, height-height/20);
  vertex(width/12 + width/20, height-height/20);
  endShape();
  beginShape();
  vertex(width - width/12 + width/15, height-height/6);
  vertex(width - width/12 - width/15, height-height/6);
  vertex(width - width/12 - width/20, height-height/20);
  vertex(width - width/12 + width/20, height-height/20);
  endShape();

  //sets the color for the plants in this room
  pStroke = 0xff3F0303;
  pFill = 0xffF2C1C0;
}

/////////////////////////////////////////////RAIN SCENE////////////////////////////////////////////
public void rainScene() {
  //sets background color and picture frame
  background(0xff918469);
  stroke(0xff473818);
  strokeWeight(50);
  fill(0xff473818);
  rect(width/2, height/2-150, 2048, 1365);

  //plays the correct song while pausing all the others
  mountainLofi.pause();
  cityLofi.pause();
  forestLofi.pause();
  winterLofi.pause();
  rainLofi.play();
  starsLofi.pause();
  plantsLofi.pause();

  //displays the picture using the slideshow array
  image(swapPic[current], width/2, height/2-150, width * 0.632099f, height * 0.631944f);

  //makes a little shelf
  noStroke();
  fill(0xffA56807);
  rect(width/2, height, width, height*0.3f);

  //makes two little pots
  fill(0xff7B959C);
  beginShape();
  vertex(width/12 + width/15, height-height/6);
  vertex(width/12 - width/15, height-height/6);
  vertex(width/12 - width/20, height-height/20);
  vertex(width/12 + width/20, height-height/20);
  endShape();
  beginShape();
  vertex(width - width/12 + width/15, height-height/6);
  vertex(width - width/12 - width/15, height-height/6);
  vertex(width - width/12 - width/20, height-height/20);
  vertex(width - width/12 + width/20, height-height/20);
  endShape();

  //sets the color for the plants in this room
  pStroke = 0xff9BDCF1;
  pFill = 0xffFE0000;
}

///////////////////////////////////////STARS SCENE///////////////////////////////////////////////////
public void starsScene() {
  //sets background color and picture frame
  background(0xff2D1F5E);
  stroke(0);
  strokeWeight(50);
  fill(0);
  rect(width/2, height/2-150, 2048, 1365);

  //plays the correct song while pausing all the others
  mountainLofi.pause();
  cityLofi.pause();
  forestLofi.pause();
  winterLofi.pause();
  rainLofi.pause();
  starsLofi.play();
  plantsLofi.pause();

  //displays the picture using the slideshow array
  image(swapPic[current], width/2, height/2-150, width * 0.632099f, height * 0.631944f);

  //makes a little shelf
  noStroke();
  fill(0xff21192F);
  rect(width/2, height, width, height*0.3f);

  //makes two little pots
  fill(0xff182D47);
  beginShape();
  vertex(width/12 + width/15, height-height/6);
  vertex(width/12 - width/15, height-height/6);
  vertex(width/12 - width/20, height-height/20);
  vertex(width/12 + width/20, height-height/20);
  endShape();
  beginShape();
  vertex(width - width/12 + width/15, height-height/6);
  vertex(width - width/12 - width/15, height-height/6);
  vertex(width - width/12 - width/20, height-height/20);
  vertex(width - width/12 + width/20, height-height/20);
  endShape();

  //sets the color for the plants in this room
  pStroke = 0xff13101B;
  pFill = 255;
}

//////////////////////////////////////////////////PLANTS SCENE////////////////////////////////////
public void plantsOnly() {
  //sets background
  background(0);

  //plays the correct song while pausing all the others
  mountainLofi.pause();
  cityLofi.pause();
  forestLofi.pause();
  winterLofi.pause();
  rainLofi.pause();
  starsLofi.pause();
  plantsLofi.play();
  
  //sets plants to black and white
  pFill = 0;
  pStroke = 255;
  
  //makes one BIG pot
  fill(255);
  beginShape();
  vertex(width/12 - width/15, height-height/6);
  vertex(width - width/12 + width/15, height-height/6);
  vertex(width - width/12 + width/20, height-height/20);
  vertex(width/12 - width/20, height-height/20);
  endShape();
}

/////////////////////////////////////////////MASK//////////////////////////////////////////////////////
public void msk() {
  //masks use a black and white image to create alpha channels 
  //I use a PGraphic so I can hide it underneath the project
  myMask.beginDraw();
  myMask.fill(255);
  myMask.noStroke();
  myMask.rectMode(CENTER);
  if (frostyWindow) {//this makes it so that only one rectangle is drawn with each space press
  //if it updates every frame you can't draw
    myMask.rect(width/2, height/2-150, width * 0.632099f, height * 0.631944f);
    frostyWindow = false;
  }
  //if the mouse is pressed then it draws
  if (mousePressed) {
    myMask.stroke(0);
    myMask.strokeWeight((height/115)*4);
    myMask.line(mouseX, mouseY, pmouseX, pmouseY);
  }
  myMask.endDraw();
}

///////////////////////////////////////////FROST///////////////////////////////////////////////////////
public void frost() {
  //just a glorified white rectangle
  frost.beginDraw();
  frost.rectMode(CENTER);
  frost.noStroke();
  frost.fill(255);
  frost.rect(width/2, height/2-150, width * 0.632099f, height * 0.631944f);
  frost.endDraw();
}
/*
A LOT of the code and resources are my own, but not all of it.

All of the toxic libs physics is based off the work of Dan Shiffman. Because his code isn't directly
used anywhere, I just included the link at the top of the project

The music in this project is from chillhop.com, a service for creators by creators that allows lofi 
music to be shared and used in content. Each creator is cited more carefully when their music is
declared.

The images in this project is from the flickr creative commons. Again, each image is cited more
carefully when the image is declared
*/
/*
I would definitely say I am much happier with how this project turned out than project 3. I think
it really helped that all the things I wanted to do weren't to hard to figure out. And the one part
that I did struggle with (making multiple arrays and for loops for a plant) I was able to just
brute force my way through (which wasn't ideal, but I got the end product I wanted). Through this
project I wanted to comment on isolation. Many times throught this pandemic, I've looked out my
window and wanted to be somewhere else. The window I have created here I would say is even more
isolating than the window in my kitchen. The plants don't grow, the light is all digital, the
images are just that, the fog on the windows is just a white rectangle, and the "helpful Nathan" is
just a series of pictures and audio files. I think many over the course of the past few months 
have turned to technology as an escape instead of elsewhere. Technology is just so easy. Why go
experience nature when you can look through a fake window on your laptop? And it's a mindset
that's taken a toll. If anything, I think working on this project taught me to just go outside. 
And while I hope other's first interactions with it they find it interesting, ultimately I hope
it leaves them longing for the real thing enough to get up and go for a walk.
*/
/*
This program is interactive! There are some key inputs and some mouse inputs.

All the leaves on the vines can be dragged by clicking on them

To progress the slide show, press F

To fog up the window, press SPACE BAR

To draw on the foggy window, click and drag with the mouse

To go through a guided breathing exercise with yours truly, scream (loud).
*/
// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// Notice how we are using inheritance here!
// We could have just stored a reference to a VerletParticle object
// inside the Particle class, but inheritance is a nice alternative

class Particle extends VerletParticle2D {

  float radius = 4;  // Adding a radius for each particle


  Particle(float x, float y) {
    super(x, y);
  }

  // All we're doing really is adding a display() function to a VerletParticle
  public void display() {
    //fill(#5BCB00);
    //stroke(#4FA708);
    strokeWeight(2);
    ellipse(x, y, radius*2, radius*2);
  }

}


class Stem {
  //variables
  float eachLength; //how long each stem piece is
  float springStrength; //how tense the springs are
  float radius; //how big the "leaves" are
  float startX, startY; //x,y position of the base
  boolean drawTip; //if its the last one in the stem, then a tip will be drawn
  boolean bottom;//if its the first one in the stem, the base will be locked
/*
  Particle leafLeft; //different objects for each leaf so they can be grabbed seperately
  Particle leafRight;
  Particle leafTip;
  */

  PVector offset = new PVector(); //gonna be honest, I will figure this out later, right now I just know it works
  boolean draggedLeft = false; //different dragged boolean for each leaf so they get dragged seperately
  boolean draggedRight = false;
  boolean draggedTip = false;

  //attemptint to create global variables instead of local variables
  Particle base;
  Particle left;
  Particle right;
  Particle top;


  //constructor
  Stem(float l, float s, float r, float xPos, float yPos, boolean tip, boolean bot) {
    //length of each, strength of springs, radius of leaves, base x, base y, tip?, bottom?
    eachLength = l;
    springStrength = s;
    radius = r;
    startX= xPos;
    startY = yPos;
    drawTip = tip;
    bottom = bot;

    //create the particles
    base =new Particle(startX, startY);
    left =new Particle(startX-eachLength, startY);
    right =new Particle(startX+eachLength, startY);
    top =new Particle(startX, startY-eachLength);


    //lock the base in place if it is the bottom
    if (bottom) { //when this is an array list it will check i instead
      base.lock();
    }

    //create the springs
    //particle 1, particle 2, length, strength
    VerletSpring2D leftSpring=new VerletSpring2D(base, left, eachLength, springStrength);
    VerletSpring2D rightSpring=new VerletSpring2D(base, right, eachLength, springStrength);
    VerletSpring2D topSpring=new VerletSpring2D(base, top, eachLength, springStrength);
    VerletSpring2D leftTense=new VerletSpring2D(top, left, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense=new VerletSpring2D(top, right, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer=new VerletSpring2D(left, right, eachLength, springStrength*1.5f);
    

    //add the particles to the world
    physics.addParticle(base);
    physics.addParticle(left);
    physics.addParticle(right);
    physics.addParticle(top);

    //add the springs to the world
    physics.addSpring(leftSpring);
    physics.addSpring(rightSpring);
    physics.addSpring(topSpring);
    physics.addSpring(leftTense);
    physics.addSpring(rightTense);
    physics.addSpring(spacer);

    //assign radius to leaves
    left.radius = radius;
    right.radius = radius;
    top.radius = radius;
  }

  //methods 
  public void display() {
    stroke(0xff418609);
    strokeWeight(15);
    line(base.x, base.y, left.x, left.y);
    line(base.x, base.y, top.x, top.y);
    line(base.x, base.y, right.x, right.y);
    left.display();
    right.display();
    if (drawTip) {
      top.display();
    }
  }

  public void containsLeft(int x, int y) {
    float d = dist(x, y, left.x, left.y);
    if (d < radius) {
      offset.x = left.x - x;
      offset.y = left.y - y;
      left.lock();
      draggedLeft = true;
    }
  }
  public void containsRight(int x, int y) {
    float d = dist(x, y, right.x, right.y);
    if (d < radius) {
      offset.x = right.x - x;
      offset.y = right.y - y;
      right.lock();
      draggedRight = true;
    }
  }
  public void containsTip(int x, int y) {
    float d = dist(x, y, top.x, top.y);
    if (d < radius) {
      offset.x = top.x - x;
      offset.y = top.y - y;
      top.lock();
      draggedTip = true;
    }
  }

  // Release the leaf
  public void release() {
    left.unlock();
    right.unlock();
    top.unlock();
    draggedLeft = false;
    draggedRight = false;
    draggedTip = false;
  }

  // Update leaf location if being dragged
  public void updateRight(int x, int y) {
    if (draggedRight) {
      right.set(x+offset.x, y+offset.y);
    }
  }
  public void updateLeft(int x, int y) {
    if (draggedLeft) {
      left.set(x+offset.x, y+offset.y);
    }
  }
  public void updateTip(int x, int y) {
    if (draggedTip) {
      top.set(x+offset.x, y+offset.y);
    }
  }
}
//okay so if I'm being real, this code gonna be ugly and unneccesarily long. Sometimes it's a little faster to do things the long way you already know than it is to try and figure out the wheel ¯\_(ツ)_/¯
//the big challenge was getting variables for each leaf to be dragged instead of the whole left or right side of the plant, oh well

class Stock {
  //variables
  float eachLength; //how long each stem piece is
  float springStrength; //how tense the springs are
  float radius; //how big the "leaves" are
  float startX, startY; //x,y position of the base
  boolean drawTip; //if its the last one in the stem, then a tip will be drawn
  boolean bottom;//if its the first one in the stem, the base will be locked
  float spacerStregnth = 5;
  /*
  Particle leafLeft; //different objects for each leaf so they can be grabbed seperately
   Particle leafRight;
   Particle leafTip;
   */

  PVector offset = new PVector(); //gonna be honest, I will figure this out later, right now I just know it works
  boolean draggedLeft1 = false; //different dragged boolean for each leaf so they get dragged seperately
  boolean draggedRight1 = false;
  boolean draggedTip = false;
  boolean draggedLeft2 = false;
  boolean draggedRight2 = false;
  boolean draggedLeft3 = false;
  boolean draggedRight3 = false;
  boolean draggedLeft4 = false;
  boolean draggedRight4 = false;
  boolean draggedLeft5 = false;
  boolean draggedRight5 = false;
  boolean draggedLeft6 = false;
  boolean draggedRight6 = false;
  boolean draggedLeft7 = false;
  boolean draggedRight7 = false;
  boolean draggedLeft8 = false;
  boolean draggedRight8 = false;
  boolean draggedLeft9 = false;
  boolean draggedRight9 = false;
  boolean draggedLeft10 = false;
  boolean draggedRight10 = false;
  boolean draggedLeft11 = false;
  boolean draggedRight11 = false;
  boolean draggedLeft12 = false;
  boolean draggedRight12 = false;
  boolean draggedLeft13 = false;
  boolean draggedRight13 = false;
  boolean draggedLeft14 = false;
  boolean draggedRight14 = false;
  boolean draggedLeft15 = false;
  boolean draggedRight15 = false;

  //attemptint to create global variables instead of local variables
  Particle base;
  Particle left1;
  Particle right1;
  Particle part1;
  Particle left2;
  Particle right2;
  Particle part2;
  Particle left3;
  Particle right3;
  Particle part3;
  Particle left4;
  Particle right4;
  Particle part4;
  Particle left5;
  Particle right5;
  Particle part5;
  Particle left6;
  Particle right6;
  Particle part6;
  Particle left7;
  Particle right7;
  Particle part7;
  Particle left8;
  Particle right8;
  Particle part8;
  Particle left9;
  Particle right9;
  Particle part9;
  Particle left10;
  Particle right10;
  Particle part10;
  Particle left11;
  Particle right11;
  Particle part11;
  Particle left12;
  Particle right12;
  Particle part12;
  Particle left13;
  Particle right13;
  Particle part13;
  Particle left14;
  Particle right14;
  Particle part14;
  Particle left15;
  Particle right15;
  Particle tip;


  //constructor
  Stock(float l, float s, float r, float xPos, float yPos/*, boolean tip, boolean bot*/) {
    //length of each, strength of springs, radius of leaves, base x, base y
    eachLength = l;
    springStrength = s;
    radius = r;
    startX= xPos;
    startY = yPos;
    //drawTip = tip;
    //bottom = bot;

    //create the particles
    base =new Particle(startX, startY);
    left1 =new Particle(startX-eachLength, startY);
    right1 =new Particle(startX+eachLength, startY);
    part1 =new Particle(startX, startY-eachLength);

    left2 =new Particle(startX-eachLength, startY-eachLength);
    right2 =new Particle(startX+eachLength, startY-eachLength);
    part2 =new Particle(startX, startY-eachLength*2);

    left3 =new Particle(startX-eachLength, startY-eachLength*2);
    right3 =new Particle(startX+eachLength, startY-eachLength*2);
    part3 =new Particle(startX, startY-eachLength*3);

    left4 =new Particle(startX-eachLength, startY-eachLength*3);
    right4 =new Particle(startX+eachLength, startY-eachLength*3);
    part4 =new Particle(startX, startY-eachLength*4);

    left5 =new Particle(startX-eachLength, startY-eachLength*4);
    right5 =new Particle(startX+eachLength, startY-eachLength*4);
    part5 =new Particle(startX, startY-eachLength*5);

    left6 =new Particle(startX-eachLength, startY-eachLength*5);
    right6 =new Particle(startX+eachLength, startY-eachLength*5);
    part6 =new Particle(startX, startY-eachLength*6);

    left7 =new Particle(startX-eachLength, startY-eachLength*6);
    right7 =new Particle(startX+eachLength, startY-eachLength*6);
    part7 =new Particle(startX, startY-eachLength*7);

    left8 =new Particle(startX-eachLength, startY-eachLength*7);
    right8 =new Particle(startX+eachLength, startY-eachLength*7);
    part8 =new Particle(startX, startY-eachLength*8);

    left9 =new Particle(startX-eachLength, startY-eachLength*8);
    right9 =new Particle(startX+eachLength, startY-eachLength*8);
    part9 =new Particle(startX, startY-eachLength*9);

    left10 =new Particle(startX-eachLength, startY-eachLength*9);
    right10 =new Particle(startX+eachLength, startY-eachLength*9);
    part10 =new Particle(startX, startY-eachLength*10);

    left11 =new Particle(startX-eachLength, startY-eachLength*10);
    right11 =new Particle(startX+eachLength, startY-eachLength*10);
    part11 =new Particle(startX, startY-eachLength*11);

    left12 =new Particle(startX-eachLength, startY-eachLength*11);
    right12 =new Particle(startX+eachLength, startY-eachLength*11);
    part12 =new Particle(startX, startY-eachLength*12);

    left13 =new Particle(startX-eachLength, startY-eachLength*12);
    right13 =new Particle(startX+eachLength, startY-eachLength*12);
    part13 =new Particle(startX, startY-eachLength*13);

    left14 =new Particle(startX-eachLength, startY-eachLength*13);
    right14 =new Particle(startX+eachLength, startY-eachLength*13);
    part14 =new Particle(startX, startY-eachLength*14);

    left15 =new Particle(startX-eachLength, startY-eachLength*14);
    right15 =new Particle(startX+eachLength, startY-eachLength*14);
    tip =new Particle(startX, startY-eachLength*15.5f);

    //lock the base in place if it is the bottom
    base.lock();

    //create the springs
    //particle 1, particle 2, length, strength
    VerletSpring2D leftSpring1=new VerletSpring2D(base, left1, eachLength, springStrength);
    VerletSpring2D rightSpring1=new VerletSpring2D(base, right1, eachLength, springStrength);
    VerletSpring2D partSpring1=new VerletSpring2D(base, part1, eachLength, springStrength);
    VerletSpring2D leftTense1=new VerletSpring2D(part1, left1, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense1=new VerletSpring2D(part1, right1, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer1=new VerletSpring2D(left1, right1, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft2=new VerletSpring2D(left1, right2, eachLength*2, springStrength*3);
    VerletSpring2D diagRight2=new VerletSpring2D(left2, right1, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft2=new VerletSpring2D(left1, left2, eachLength, springStrength*3);
    VerletSpring2D spacerRight2=new VerletSpring2D(right2, right1, eachLength, springStrength*3);
    VerletSpring2D leftSpring2=new VerletSpring2D(part1, left2, eachLength, springStrength);
    VerletSpring2D rightSpring2=new VerletSpring2D(part1, right2, eachLength, springStrength);
    VerletSpring2D partSpring2=new VerletSpring2D(part1, part2, eachLength, springStrength);
    VerletSpring2D leftTense2=new VerletSpring2D(part2, left2, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense2=new VerletSpring2D(part2, right2, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer2=new VerletSpring2D(left2, right2, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft3=new VerletSpring2D(left2, right3, eachLength*2, springStrength*3);
    VerletSpring2D diagRight3=new VerletSpring2D(left3, right2, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft3=new VerletSpring2D(left2, left3, eachLength, springStrength*3);
    VerletSpring2D spacerRight3=new VerletSpring2D(right3, right2, eachLength, springStrength*3);
    VerletSpring2D leftSpring3=new VerletSpring2D(part2, left3, eachLength, springStrength);
    VerletSpring2D rightSpring3=new VerletSpring2D(part2, right3, eachLength, springStrength);
    VerletSpring2D partSpring3=new VerletSpring2D(part2, part3, eachLength, springStrength);
    VerletSpring2D leftTense3=new VerletSpring2D(part3, left3, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense3=new VerletSpring2D(part3, right3, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer3=new VerletSpring2D(left3, right3, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft4=new VerletSpring2D(left3, right4, eachLength*2, springStrength*3);
    VerletSpring2D diagRight4=new VerletSpring2D(left4, right3, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft4=new VerletSpring2D(left3, left4, eachLength, springStrength*3);
    VerletSpring2D spacerRight4=new VerletSpring2D(right4, right3, eachLength, springStrength*3);
    VerletSpring2D leftSpring4=new VerletSpring2D(part3, left4, eachLength, springStrength);
    VerletSpring2D rightSpring4=new VerletSpring2D(part3, right4, eachLength, springStrength);
    VerletSpring2D partSpring4=new VerletSpring2D(part3, part4, eachLength, springStrength);
    VerletSpring2D leftTense4=new VerletSpring2D(part4, left4, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense4=new VerletSpring2D(part4, right4, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer4=new VerletSpring2D(left4, right4, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft5=new VerletSpring2D(left4, right5, eachLength*2, springStrength*3);
    VerletSpring2D diagRight5=new VerletSpring2D(left5, right4, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft5=new VerletSpring2D(left4, left5, eachLength, springStrength*3);
    VerletSpring2D spacerRight5=new VerletSpring2D(right5, right4, eachLength, springStrength*3);
    VerletSpring2D leftSpring5=new VerletSpring2D(part4, left5, eachLength, springStrength);
    VerletSpring2D rightSpring5=new VerletSpring2D(part4, right5, eachLength, springStrength);
    VerletSpring2D partSpring5=new VerletSpring2D(part4, part5, eachLength, springStrength);
    VerletSpring2D leftTense5=new VerletSpring2D(part5, left5, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense5=new VerletSpring2D(part5, right5, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer5=new VerletSpring2D(left5, right5, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft6=new VerletSpring2D(left5, right6, eachLength*2, springStrength*3);
    VerletSpring2D diagRight6=new VerletSpring2D(left6, right5, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft6=new VerletSpring2D(left5, left6, eachLength, springStrength*3);
    VerletSpring2D spacerRight6=new VerletSpring2D(right5, right6, eachLength, springStrength*3);
    VerletSpring2D leftSpring6=new VerletSpring2D(part5, left6, eachLength, springStrength);
    VerletSpring2D rightSpring6=new VerletSpring2D(part5, right6, eachLength, springStrength);
    VerletSpring2D partSpring6=new VerletSpring2D(part5, part6, eachLength, springStrength);
    VerletSpring2D leftTense6=new VerletSpring2D(part6, left6, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense6=new VerletSpring2D(part6, right6, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer6=new VerletSpring2D(left6, right6, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft7=new VerletSpring2D(left6, right7, eachLength*2, springStrength*3);
    VerletSpring2D diagRight7=new VerletSpring2D(left7, right6, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft7=new VerletSpring2D(left6, left7, eachLength, springStrength*3);
    VerletSpring2D spacerRight7=new VerletSpring2D(right6, right7, eachLength, springStrength*3);
    VerletSpring2D leftSpring7=new VerletSpring2D(part6, left7, eachLength, springStrength);
    VerletSpring2D rightSpring7=new VerletSpring2D(part6, right7, eachLength, springStrength);
    VerletSpring2D partSpring7=new VerletSpring2D(part6, part7, eachLength, springStrength);
    VerletSpring2D leftTense7=new VerletSpring2D(part7, left7, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense7=new VerletSpring2D(part7, right7, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer7=new VerletSpring2D(left7, right7, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft8=new VerletSpring2D(left7, right8, eachLength*2, springStrength*3);
    VerletSpring2D diagRight8=new VerletSpring2D(left8, right7, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft8=new VerletSpring2D(left7, left8, eachLength, springStrength*3);
    VerletSpring2D spacerRight8=new VerletSpring2D(right7, right8, eachLength, springStrength*3);
    VerletSpring2D leftSpring8=new VerletSpring2D(part7, left8, eachLength, springStrength);
    VerletSpring2D rightSpring8=new VerletSpring2D(part7, right8, eachLength, springStrength);
    VerletSpring2D partSpring8=new VerletSpring2D(part7, part8, eachLength, springStrength);
    VerletSpring2D leftTense8=new VerletSpring2D(part8, left8, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense8=new VerletSpring2D(part8, right8, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer8=new VerletSpring2D(left8, right8, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft9=new VerletSpring2D(left8, right9, eachLength*2, springStrength*3);
    VerletSpring2D diagRight9=new VerletSpring2D(left9, right8, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft9=new VerletSpring2D(left8, left9, eachLength, springStrength*3);
    VerletSpring2D spacerRight9=new VerletSpring2D(right8, right9, eachLength, springStrength*3);
    VerletSpring2D leftSpring9=new VerletSpring2D(part8, left9, eachLength, springStrength);
    VerletSpring2D rightSpring9=new VerletSpring2D(part8, right9, eachLength, springStrength);
    VerletSpring2D partSpring9=new VerletSpring2D(part8, part9, eachLength, springStrength);
    VerletSpring2D leftTense9=new VerletSpring2D(part9, left9, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense9=new VerletSpring2D(part9, right9, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer9=new VerletSpring2D(left9, right9, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft10=new VerletSpring2D(left9, right10, eachLength*2, springStrength*3);
    VerletSpring2D diagRight10=new VerletSpring2D(left10, right9, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft10=new VerletSpring2D(left9, left10, eachLength, springStrength*3);
    VerletSpring2D spacerRight10=new VerletSpring2D(right9, right10, eachLength, springStrength*3);
    VerletSpring2D leftSpring10=new VerletSpring2D(part9, left10, eachLength, springStrength);
    VerletSpring2D rightSpring10=new VerletSpring2D(part9, right10, eachLength, springStrength);
    VerletSpring2D partSpring10=new VerletSpring2D(part9, part10, eachLength, springStrength);
    VerletSpring2D leftTense10=new VerletSpring2D(part10, left10, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense10=new VerletSpring2D(part10, right10, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer10=new VerletSpring2D(left10, right10, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft11=new VerletSpring2D(left10, right11, eachLength*2, springStrength*3);
    VerletSpring2D diagRight11=new VerletSpring2D(left11, right10, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft11=new VerletSpring2D(left10, left11, eachLength, springStrength*3);
    VerletSpring2D spacerRight11=new VerletSpring2D(right10, right11, eachLength, springStrength*3);
    VerletSpring2D leftSpring11=new VerletSpring2D(part10, left11, eachLength, springStrength);
    VerletSpring2D rightSpring11=new VerletSpring2D(part10, right11, eachLength, springStrength);
    VerletSpring2D partSpring11=new VerletSpring2D(part10, part11, eachLength, springStrength);
    VerletSpring2D leftTense11=new VerletSpring2D(part11, left11, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense11=new VerletSpring2D(part11, right11, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer11=new VerletSpring2D(left11, right11, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft12=new VerletSpring2D(left11, right12, eachLength*2, springStrength*3);
    VerletSpring2D diagRight12=new VerletSpring2D(left12, right11, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft12=new VerletSpring2D(left11, left12, eachLength, springStrength*3);
    VerletSpring2D spacerRight12=new VerletSpring2D(right11, right12, eachLength, springStrength*3);
    VerletSpring2D leftSpring12=new VerletSpring2D(part11, left12, eachLength, springStrength);
    VerletSpring2D rightSpring12=new VerletSpring2D(part11, right12, eachLength, springStrength);
    VerletSpring2D partSpring12=new VerletSpring2D(part11, part12, eachLength, springStrength);
    VerletSpring2D leftTense12=new VerletSpring2D(part12, left12, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense12=new VerletSpring2D(part12, right12, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer12=new VerletSpring2D(left12, right12, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft13=new VerletSpring2D(left12, right13, eachLength*2, springStrength*3);
    VerletSpring2D diagRight13=new VerletSpring2D(left13, right12, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft13=new VerletSpring2D(left12, left13, eachLength, springStrength*3);
    VerletSpring2D spacerRight13=new VerletSpring2D(right12, right13, eachLength, springStrength*3);
    VerletSpring2D leftSpring13=new VerletSpring2D(part12, left13, eachLength, springStrength);
    VerletSpring2D rightSpring13=new VerletSpring2D(part12, right13, eachLength, springStrength);
    VerletSpring2D partSpring13=new VerletSpring2D(part12, part13, eachLength, springStrength);
    VerletSpring2D leftTense13=new VerletSpring2D(part13, left13, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense13=new VerletSpring2D(part13, right13, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer13=new VerletSpring2D(left13, right13, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft14=new VerletSpring2D(left13, right14, eachLength*2, springStrength*3);
    VerletSpring2D diagRight14=new VerletSpring2D(left14, right13, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft14=new VerletSpring2D(left13, left14, eachLength, springStrength*3);
    VerletSpring2D spacerRight14=new VerletSpring2D(right13, right14, eachLength, springStrength*3);
    VerletSpring2D leftSpring14=new VerletSpring2D(part13, left14, eachLength, springStrength);
    VerletSpring2D rightSpring14=new VerletSpring2D(part13, right14, eachLength, springStrength);
    VerletSpring2D partSpring14=new VerletSpring2D(part13, part14, eachLength, springStrength);
    VerletSpring2D leftTense14=new VerletSpring2D(part14, left14, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense14=new VerletSpring2D(part14, right14, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer14=new VerletSpring2D(left14, right14, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft15=new VerletSpring2D(left14, right15, eachLength*2, springStrength*3);
    VerletSpring2D diagRight15=new VerletSpring2D(left15, right14, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft15=new VerletSpring2D(left14, left15, eachLength, springStrength*3);
    VerletSpring2D spacerRight15=new VerletSpring2D(right14, right15, eachLength, springStrength*3);
    VerletSpring2D leftSpring15=new VerletSpring2D(part14, left15, eachLength, springStrength);
    VerletSpring2D rightSpring15=new VerletSpring2D(part14, right15, eachLength, springStrength);
    VerletSpring2D partSpring15=new VerletSpring2D(part14, tip, eachLength*2, springStrength);
    VerletSpring2D leftTense15=new VerletSpring2D(tip, left15, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D rightTense15=new VerletSpring2D(tip, right15, eachLength*0.75f, springStrength*1.5f);
    VerletSpring2D spacer15=new VerletSpring2D(left15, right15, eachLength, springStrength*spacerStregnth);


    //add the particles to the world
    physics.addParticle(base);
    physics.addParticle(left1);
    physics.addParticle(right1);
    physics.addParticle(part1);

    physics.addParticle(left2);
    physics.addParticle(right2);
    physics.addParticle(part2);

    physics.addParticle(left3);
    physics.addParticle(right3);
    physics.addParticle(part3);

    physics.addParticle(left4);
    physics.addParticle(right4);
    physics.addParticle(part4);

    physics.addParticle(left5);
    physics.addParticle(right5);
    physics.addParticle(part5);

    physics.addParticle(left6);
    physics.addParticle(right6);
    physics.addParticle(part6);

    physics.addParticle(left7);
    physics.addParticle(right7);
    physics.addParticle(part7);

    physics.addParticle(left8);
    physics.addParticle(right8);
    physics.addParticle(part8);

    physics.addParticle(left9);
    physics.addParticle(right9);
    physics.addParticle(part9);

    physics.addParticle(left10);
    physics.addParticle(right10);
    physics.addParticle(part10);

    physics.addParticle(left11);
    physics.addParticle(right11);
    physics.addParticle(part11);

    physics.addParticle(left12);
    physics.addParticle(right12);
    physics.addParticle(part12);

    physics.addParticle(left13);
    physics.addParticle(right13);
    physics.addParticle(part13);

    physics.addParticle(left14);
    physics.addParticle(right14);
    physics.addParticle(part14);

    physics.addParticle(left15);
    physics.addParticle(right15);
    physics.addParticle(tip);


    //add the springs to the world
    physics.addSpring(leftSpring1);
    physics.addSpring(rightSpring1);
    physics.addSpring(partSpring1);
    physics.addSpring(leftTense1);
    physics.addSpring(rightTense1);
    physics.addSpring(spacer1);

    physics.addSpring(diagLeft2);
    physics.addSpring(diagRight2);
    physics.addSpring(spacerLeft2);
    physics.addSpring(spacerRight2);
    physics.addSpring(leftSpring2);
    physics.addSpring(rightSpring2);
    physics.addSpring(partSpring2);
    physics.addSpring(leftTense2);
    physics.addSpring(rightTense2);
    physics.addSpring(spacer2);

    physics.addSpring(diagLeft3);
    physics.addSpring(diagRight3);
    physics.addSpring(spacerLeft3);
    physics.addSpring(spacerRight3);
    physics.addSpring(leftSpring3);
    physics.addSpring(rightSpring3);
    physics.addSpring(partSpring3);
    physics.addSpring(leftTense3);
    physics.addSpring(rightTense3);
    physics.addSpring(spacer3);

    physics.addSpring(diagLeft4);
    physics.addSpring(diagRight4);
    physics.addSpring(spacerLeft4);
    physics.addSpring(spacerRight4);
    physics.addSpring(leftSpring4);
    physics.addSpring(rightSpring4);
    physics.addSpring(partSpring4);
    physics.addSpring(leftTense4);
    physics.addSpring(rightTense4);
    physics.addSpring(spacer4);

    physics.addSpring(diagLeft5);
    physics.addSpring(diagRight5);
    physics.addSpring(spacerLeft5);
    physics.addSpring(spacerRight5);
    physics.addSpring(leftSpring5);
    physics.addSpring(rightSpring5);
    physics.addSpring(partSpring5);
    physics.addSpring(leftTense5);
    physics.addSpring(rightTense5);
    physics.addSpring(spacer5);

    physics.addSpring(diagLeft6);
    physics.addSpring(diagRight6);
    physics.addSpring(spacerLeft6);
    physics.addSpring(spacerRight6);
    physics.addSpring(leftSpring6);
    physics.addSpring(rightSpring6);
    physics.addSpring(partSpring6);
    physics.addSpring(leftTense6);
    physics.addSpring(rightTense6);
    physics.addSpring(spacer6);

    physics.addSpring(diagLeft7);
    physics.addSpring(diagRight7);
    physics.addSpring(spacerLeft7);
    physics.addSpring(spacerRight7);
    physics.addSpring(leftSpring7);
    physics.addSpring(rightSpring7);
    physics.addSpring(partSpring7);
    physics.addSpring(leftTense7);
    physics.addSpring(rightTense7);
    physics.addSpring(spacer7);

    physics.addSpring(diagLeft8);
    physics.addSpring(diagRight8);
    physics.addSpring(spacerLeft8);
    physics.addSpring(spacerRight8);
    physics.addSpring(leftSpring8);
    physics.addSpring(rightSpring8);
    physics.addSpring(partSpring8);
    physics.addSpring(leftTense8);
    physics.addSpring(rightTense8);
    physics.addSpring(spacer8);

    physics.addSpring(diagLeft9);
    physics.addSpring(diagRight9);
    physics.addSpring(spacerLeft9);
    physics.addSpring(spacerRight9);
    physics.addSpring(leftSpring9);
    physics.addSpring(rightSpring9);
    physics.addSpring(partSpring9);
    physics.addSpring(leftTense9);
    physics.addSpring(rightTense9);
    physics.addSpring(spacer9);

    physics.addSpring(diagLeft10);
    physics.addSpring(diagRight10);
    physics.addSpring(spacerLeft10);
    physics.addSpring(spacerRight10);
    physics.addSpring(leftSpring10);
    physics.addSpring(rightSpring10);
    physics.addSpring(partSpring10);
    physics.addSpring(leftTense10);
    physics.addSpring(rightTense10);
    physics.addSpring(spacer10);

    physics.addSpring(diagLeft11);
    physics.addSpring(diagRight11);
    physics.addSpring(spacerLeft11);
    physics.addSpring(spacerRight11);
    physics.addSpring(leftSpring11);
    physics.addSpring(rightSpring11);
    physics.addSpring(partSpring11);
    physics.addSpring(leftTense11);
    physics.addSpring(rightTense11);
    physics.addSpring(spacer11);

    physics.addSpring(diagLeft12);
    physics.addSpring(diagRight12);
    physics.addSpring(spacerLeft12);
    physics.addSpring(spacerRight12);
    physics.addSpring(leftSpring12);
    physics.addSpring(rightSpring12);
    physics.addSpring(partSpring12);
    physics.addSpring(leftTense12);
    physics.addSpring(rightTense12);
    physics.addSpring(spacer12);

    physics.addSpring(diagLeft13);
    physics.addSpring(diagRight13);
    physics.addSpring(spacerLeft13);
    physics.addSpring(spacerRight13);
    physics.addSpring(leftSpring13);
    physics.addSpring(rightSpring13);
    physics.addSpring(partSpring13);
    physics.addSpring(leftTense13);
    physics.addSpring(rightTense13);
    physics.addSpring(spacer13);

    physics.addSpring(diagLeft14);
    physics.addSpring(diagRight14);
    physics.addSpring(spacerLeft14);
    physics.addSpring(spacerRight14);
    physics.addSpring(leftSpring14);
    physics.addSpring(rightSpring14);
    physics.addSpring(partSpring14);
    physics.addSpring(leftTense14);
    physics.addSpring(rightTense14);
    physics.addSpring(spacer14);

    physics.addSpring(diagLeft15);
    physics.addSpring(diagRight15);
    physics.addSpring(spacerLeft15);
    physics.addSpring(spacerRight15);
    physics.addSpring(leftSpring15);
    physics.addSpring(rightSpring15);
    physics.addSpring(partSpring15);
    physics.addSpring(leftTense15);
    physics.addSpring(rightTense15);
    physics.addSpring(spacer15);


    //assign radius to leaves
    tip.radius = radius*2;
    left1.radius = radius;
    right1.radius = radius;
    left2.radius = radius;
    right2.radius = radius;
    left3.radius = radius;
    right3.radius = radius;
    left4.radius = radius;
    right4.radius = radius;
    left5.radius = radius;
    right5.radius = radius;
    left6.radius = radius;
    right6.radius = radius;
    left7.radius = radius;
    right7.radius = radius;
    left8.radius = radius;
    right8.radius = radius;
    left9.radius = radius;
    right9.radius = radius;
    left10.radius = radius;
    right10.radius = radius;
    left11.radius = radius;
    right11.radius = radius;
    left12.radius = radius;
    right12.radius = radius;
    left13.radius = radius;
    right13.radius = radius;
    left14.radius = radius;
    right14.radius = radius;
    left15.radius = radius;
    right15.radius = radius;
  }

  //methods 
  public void display() {
    //stroke(#418609);
    strokeWeight(15);
    line(base.x, base.y, left1.x, left1.y);
    line(base.x, base.y, part1.x, part1.y);
    line(base.x, base.y, right1.x, right1.y);
    line(part1.x, part1.y, left2.x, left2.y);
    line(part1.x, part1.y, part2.x, part2.y);
    line(part1.x, part1.y, right2.x, right2.y);
    line(part2.x, part2.y, part3.x, part3.y);
    line(part2.x, part2.y, left3.x, left3.y);
    line(part2.x, part2.y, right3.x, right3.y);
    line(part4.x, part4.y, part3.x, part3.y);
    line(part3.x, part3.y, left4.x, left4.y);
    line(part3.x, part3.y, right4.x, right4.y);
    line(part5.x, part5.y, part4.x, part4.y);
    line(part4.x, part4.y, left5.x, left5.y);
    line(part4.x, part4.y, right5.x, right5.y);
    line(part6.x, part6.y, part5.x, part5.y);
    line(part5.x, part5.y, left6.x, left6.y);
    line(part5.x, part5.y, right6.x, right6.y);
    line(part7.x, part7.y, part6.x, part6.y);
    line(part6.x, part6.y, left7.x, left7.y);
    line(part6.x, part6.y, right7.x, right7.y);
    line(part8.x, part8.y, part7.x, part7.y);
    line(part7.x, part7.y, left8.x, left8.y);
    line(part7.x, part7.y, right8.x, right8.y);
    line(part9.x, part9.y, part8.x, part8.y);
    line(part8.x, part8.y, left9.x, left9.y);
    line(part8.x, part8.y, right9.x, right9.y);
    line(part10.x, part10.y, part9.x, part9.y);
    line(part9.x, part9.y, left10.x, left10.y);
    line(part9.x, part9.y, right10.x, right10.y);
    line(part11.x, part11.y, part10.x, part10.y);
    line(part10.x, part10.y, left11.x, left11.y);
    line(part10.x, part10.y, right11.x, right11.y);
    line(part12.x, part12.y, part11.x, part11.y);
    line(part11.x, part11.y, left12.x, left12.y);
    line(part11.x, part11.y, right12.x, right12.y);
    line(part13.x, part13.y, part12.x, part12.y);
    line(part12.x, part12.y, left13.x, left13.y);
    line(part12.x, part12.y, right13.x, right13.y);
    line(part14.x, part14.y, part13.x, part13.y);
    line(part13.x, part13.y, left14.x, left14.y);
    line(part13.x, part13.y, right14.x, right14.y);
    line(tip.x, tip.y, part14.x, part14.y);
    line(part14.x, part14.y, left15.x, left15.y);
    line(part14.x, part14.y, right15.x, right15.y);
    tip.display();
    left1.display();
    right1.display();
    left2.display();
    right2.display();
    left3.display();
    right3.display();
    left4.display();
    right4.display();
    left5.display();
    right5.display();
    left6.display();
    right6.display();
    left7.display();
    right7.display();
    left8.display();
    right8.display();
    left9.display();
    right9.display();
    left10.display();
    right10.display();
    left11.display();
    right11.display();
    left12.display();
    right12.display();
    left13.display();
    right13.display();
    left14.display();
    right14.display();
    left15.display();
    right15.display();
  }

  public void contains(int x, int y) {    
    float dTip = dist(x, y, tip.x, tip.y);
    if (dTip < radius*2) {
      offset.x = tip.x - x;
      offset.y = tip.y - y;
      tip.lock();
      draggedTip = true;
    }
    float dLeft1 = dist(x, y, left1.x, left1.y);
    if (dLeft1 < radius) {
      offset.x = left1.x - x;
      offset.y = left1.y - y;
      left1.lock();
      draggedLeft1 = true;
    }
    float dRight1 = dist(x, y, right1.x, right1.y);
    if (dRight1 < radius) {
      offset.x = right1.x - x;
      offset.y = right1.y - y;
      right1.lock();
      draggedRight1 = true;
    }
    float dLeft2 = dist(x, y, left2.x, left2.y);
    if (dLeft2 < radius) {
      offset.x = left2.x - x;
      offset.y = left2.y - y;
      left2.lock();
      draggedLeft2 = true;
    }
    float dRight2 = dist(x, y, right2.x, right2.y);
    if (dRight2 < radius) {
      offset.x = right2.x - x;
      offset.y = right2.y - y;
      right2.lock();
      draggedRight2 = true;
    }
    float dLeft3 = dist(x, y, left3.x, left3.y);
    if (dLeft3 < radius) {
      offset.x = left3.x - x;
      offset.y = left3.y - y;
      left3.lock();
      draggedLeft3 = true;
    }
    float dRight3 = dist(x, y, right3.x, right3.y);
    if (dRight3 < radius) {
      offset.x = right3.x - x;
      offset.y = right3.y - y;
      right3.lock();
      draggedRight3 = true;
    }
    float dLeft4 = dist(x, y, left4.x, left4.y);
    if (dLeft4 < radius) {
      offset.x = left4.x - x;
      offset.y = left4.y - y;
      left4.lock();
      draggedLeft4 = true;
    }
    float dRight4 = dist(x, y, right4.x, right4.y);
    if (dRight4 < radius) {
      offset.x = right4.x - x;
      offset.y = right4.y - y;
      right4.lock();
      draggedRight4 = true;
    }
    float dLeft5 = dist(x, y, left5.x, left5.y);
    if (dLeft5 < radius) {
      offset.x = left5.x - x;
      offset.y = left5.y - y;
      left5.lock();
      draggedLeft5 = true;
    }
    float dRight5 = dist(x, y, right5.x, right5.y);
    if (dRight5 < radius) {
      offset.x = right5.x - x;
      offset.y = right5.y - y;
      right5.lock();
      draggedRight5 = true;
    }
    float dLeft6 = dist(x, y, left6.x, left6.y);
    if (dLeft6 < radius) {
      offset.x = left6.x - x;
      offset.y = left6.y - y;
      left6.lock();
      draggedLeft6 = true;
    }
    float dRight6 = dist(x, y, right6.x, right6.y);
    if (dRight6 < radius) {
      offset.x = right6.x - x;
      offset.y = right6.y - y;
      right6.lock();
      draggedRight6 = true;
    }
    float dLeft7 = dist(x, y, left7.x, left7.y);
    if (dLeft7< radius) {
      offset.x = left7.x - x;
      offset.y = left7.y - y;
      left7.lock();
      draggedLeft7 = true;
    }
    float dRight7 = dist(x, y, right7.x, right7.y);
    if (dRight7 < radius) {
      offset.x = right7.x - x;
      offset.y = right7.y - y;
      right7.lock();
      draggedRight7 = true;
    }
    float dLeft8 = dist(x, y, left8.x, left8.y);
    if (dLeft8< radius) {
      offset.x = left8.x - x;
      offset.y = left8.y - y;
      left8.lock();
      draggedLeft8 = true;
    }
    float dRight8 = dist(x, y, right8.x, right8.y);
    if (dRight8 < radius) {
      offset.x = right8.x - x;
      offset.y = right8.y - y;
      right8.lock();
      draggedRight8 = true;
    }
    float dLeft9 = dist(x, y, left9.x, left9.y);
    if (dLeft9< radius) {
      offset.x = left9.x - x;
      offset.y = left9.y - y;
      left9.lock();
      draggedLeft9 = true;
    }
    float dRight9 = dist(x, y, right9.x, right9.y);
    if (dRight9 < radius) {
      offset.x = right9.x - x;
      offset.y = right9.y - y;
      right9.lock();
      draggedRight9 = true;
    }
    float dLeft10 = dist(x, y, left10.x, left10.y);
    if (dLeft10< radius) {
      offset.x = left10.x - x;
      offset.y = left10.y - y;
      left10.lock();
      draggedLeft10 = true;
    }
    float dRight10 = dist(x, y, right10.x, right10.y);
    if (dRight10 < radius) {
      offset.x = right10.x - x;
      offset.y = right10.y - y;
      right10.lock();
      draggedRight10 = true;
    }
    float dLeft11 = dist(x, y, left11.x, left11.y);
    if (dLeft11< radius) {
      offset.x = left11.x - x;
      offset.y = left11.y - y;
      left11.lock();
      draggedLeft11 = true;
    }
    float dRight11 = dist(x, y, right11.x, right11.y);
    if (dRight11 < radius) {
      offset.x = right11.x - x;
      offset.y = right11.y - y;
      right11.lock();
      draggedRight11 = true;
    }
    float dLeft12 = dist(x, y, left12.x, left12.y);
    if (dLeft12< radius) {
      offset.x = left12.x - x;
      offset.y = left12.y - y;
      left12.lock();
      draggedLeft12 = true;
    }
    float dRight12 = dist(x, y, right12.x, right12.y);
    if (dRight12 < radius) {
      offset.x = right12.x - x;
      offset.y = right12.y - y;
      right12.lock();
      draggedRight12 = true;
    }
    float dLeft13 = dist(x, y, left13.x, left13.y);
    if (dLeft13 < radius) {
      offset.x = left13.x - x;
      offset.y = left13.y - y;
      left13.lock();
      draggedLeft13 = true;
    }
    float dRight13 = dist(x, y, right13.x, right13.y);
    if (dRight13 < radius) {
      offset.x = right13.x - x;
      offset.y = right13.y - y;
      right13.lock();
      draggedRight13 = true;
    }
    float dLeft14 = dist(x, y, left14.x, left14.y);
    if (dLeft14 < radius) {
      offset.x = left14.x - x;
      offset.y = left14.y - y;
      left14.lock();
      draggedLeft14 = true;
    }
    float dRight14 = dist(x, y, right14.x, right14.y);
    if (dRight14 < radius) {
      offset.x = right14.x - x;
      offset.y = right14.y - y;
      right14.lock();
      draggedRight14 = true;
    }
    float dLeft15 = dist(x, y, left15.x, left15.y);
    if (dLeft15 < radius) {
      offset.x = left15.x - x;
      offset.y = left15.y - y;
      left15.lock();
      draggedLeft15 = true;
    }
    float dRight15 = dist(x, y, right15.x, right15.y);
    if (dRight15 < radius) {
      offset.x = right15.x - x;
      offset.y = right15.y - y;
      right15.lock();
      draggedRight15 = true;
    }
  }

  public void updateLeaf(int x, int y) {
    if (draggedRight1) {
      right1.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft1) {
      left1.set(x+offset.x, y+offset.y);
    }
    if (draggedTip) {
      tip.set(x+offset.x, y+offset.y);
    }
    if (draggedRight2) {
      right2.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft2) {
      left2.set(x+offset.x, y+offset.y);
    }
    if (draggedRight3) {
      right3.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft3) {
      left3.set(x+offset.x, y+offset.y);
    }
    if (draggedRight4) {
      right4.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft4) {
      left4.set(x+offset.x, y+offset.y);
    }
    if (draggedRight5) {
      right5.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft5) {
      left5.set(x+offset.x, y+offset.y);
    }
    if (draggedRight6) {
      right6.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft6) {
      left6.set(x+offset.x, y+offset.y);
    }
    if (draggedRight7) {
      right7.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft7) {
      left7.set(x+offset.x, y+offset.y);
    }
    if (draggedRight8) {
      right8.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft8) {
      left8.set(x+offset.x, y+offset.y);
    }
    if (draggedRight9) {
      right9.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft9) {
      left9.set(x+offset.x, y+offset.y);
    }
    if (draggedRight10) {
      right10.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft10) {
      left10.set(x+offset.x, y+offset.y);
    }
    if (draggedRight11) {
      right11.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft11) {
      left11.set(x+offset.x, y+offset.y);
    }
    if (draggedRight12) {
      right12.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft12) {
      left12.set(x+offset.x, y+offset.y);
    }
    if (draggedRight13) {
      right13.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft13) {
      left13.set(x+offset.x, y+offset.y);
    }
    if (draggedRight14) {
      right14.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft14) {
      left14.set(x+offset.x, y+offset.y);
    }
    if (draggedRight15) {
      right15.set(x+offset.x, y+offset.y);
    }
    if (draggedLeft15) {
      left15.set(x+offset.x, y+offset.y);
    }
  }

  // Release the leaf
  public void release() {
    tip.unlock();
    left1.unlock();
    right1.unlock();
    left2.unlock();
    right2.unlock();
    left3.unlock();
    right3.unlock();
    left4.unlock();
    right4.unlock();
    left5.unlock();
    right5.unlock();
    left6.unlock();
    right6.unlock();
    left7.unlock();
    right7.unlock();
    left8.unlock();
    right8.unlock();
    left9.unlock();
    right9.unlock();
    left10.unlock();
    right10.unlock();
    left11.unlock();
    right11.unlock();
    left12.unlock();
    right12.unlock();
    left13.unlock();
    right13.unlock();
    left14.unlock();
    right14.unlock();
    left15.unlock();
    right15.unlock();
    draggedTip = false;
    draggedLeft1 = false;
    draggedRight1 = false;
    draggedLeft2 = false;
    draggedRight2 = false;
    draggedLeft3 = false;
    draggedRight3 = false;
    draggedLeft4 = false;
    draggedRight4 = false;
    draggedLeft5 = false;
    draggedRight5 = false;
    draggedLeft6 = false;
    draggedRight6 = false;
    draggedLeft7 = false;
    draggedRight7 = false;
    draggedLeft8 = false;
    draggedRight8 = false;
    draggedLeft9 = false;
    draggedRight9 = false;
    draggedLeft10 = false;
    draggedRight10 = false;
    draggedLeft11 = false;
    draggedRight11 = false;
    draggedLeft12 = false;
    draggedRight12 = false;
    draggedLeft13 = false;
    draggedRight13 = false;
    draggedLeft14 = false;
    draggedRight14 = false;
    draggedLeft15 = false;
    draggedRight15 = false;
  }
}
/*
  void containsLeft1(int x, int y) {
 float dLeft1 = dist(x, y, left1.x, left1.y);
 if (dLeft1 < radius) {
 offset.x = left1.x - x;
 offset.y = left1.y - y;
 left1.lock();
 draggedLeft1 = true;
 }
 }
 
 void containsRight1(int x, int y) {
 float dRight1 = dist(x, y, right1.x, right1.y);
 if (dRight1 < radius) {
 offset.x = right1.x - x;
 offset.y = right1.y - y;
 right1.lock();
 draggedRight1 = true;
 }
 }
 
 void containsTip(int x, int y) {
 float dTip = dist(x, y, part1.x, part1.y);
 if (dTip < radius) {
 offset.x = part1.x - x;
 offset.y = part1.y - y;
 part1.lock();
 draggedTip = true;
 }
 }
 
 
 
 // Update leaf location if being dragged
 void updateRight(int x, int y) {
 if (draggedRight1) {
 right1.set(x+offset.x, y+offset.y);
 }
 }
 void updateLeft(int x, int y) {
 if (draggedLeft1) {
 left.set(x+offset.x, y+offset.y);
 }
 }
 void updateTip(int x, int y) {
 if (draggedTip) {
 top.set(x+offset.x, y+offset.y);
 }
 }
 }
 */
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Nathan_Forsyth_Project_3_WORKING" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
