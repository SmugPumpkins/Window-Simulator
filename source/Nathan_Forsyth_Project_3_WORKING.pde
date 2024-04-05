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
import toxi.physics2d.*;
import toxi.physics2d.behaviors.*;
import toxi.geom.*;

//minim
import ddf.minim.*;

//stuff to see if I am yelling
import processing.sound.*;
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
color pStroke, pFill;


////////////////////////////////////////////////////SET UP///////////////////////////////////////////////
void setup() {
  fullScreen();
  // Initialize the physics world
  physics=new VerletPhysics2D();
  physics.addBehavior(new GravityBehavior2D(new Vec2D(0, -20)));
  physics.setWorldBounds(new Rect(0, 0, width, height));

  //makes images manageable as well as rectangles
  rectMode(CENTER);
  imageMode(CENTER);

  //initialize stock object
  stock1 = new Stock(height/25, 0.3, height/115, width/12, height-height/6);
  stock2 = new Stock(height/25, 0.3, height/115, width - width/12, height-height/6);
  stock3 = new Stock(height/25, 0.3, height/115, (width/7)*2, (height-height/6));
  stock4 = new Stock(height/25, 0.3, height/115, (width/7)*3, (height-height/6));
  stock5 = new Stock(height/25, 0.3, height/115, (width/7)*4, (height-height/6));
  stock6 = new Stock(height/25, 0.3, height/115, (width/7)*5, (height-height/6));

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
  encouragement.setGain(7.5);

  //places the helpful nathan image
  iX = width/6;
  iY = height/6-40;
} 
///////////////////////////////////////////////////////////DRAW//////////////////////////////////////////
void draw() {
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
    if (amp.analyze() > 0.4) {
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
      frostOpacity -= 0.15; //opacity gradually fades, just like a real foggy window!
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
void mousePressed() {
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
void keyPressed() {
  //on space it fogs up the window
  if (key == ' ') {
    frostyWindow = true; //draws window
    frostOpacity = 50; //sets opacity of frost to 50
    foggy.rewind(); //makes a breathing sound
    foggy.play();
  }
}

/////////////////////////////////////MOUSE RELEASED////////////////////////////////////////////////
void mouseReleased() {
  //lets go of the plants when you stop dragging them
  stock1.release();
  stock2.release();
  stock3.release();
  stock4.release();
  stock5.release();
  stock6.release();
}

////////////////////////////////////KEY RELEASED//////////////////////////////////////////////////
void keyReleased() {

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
void mountainScene() {
  //sets background color and picture frame
  background(#206F95);
  stroke(#7C460C);
  strokeWeight(50);
  fill(#7C460C);
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
  image(swapPic[current], width/2, height/2-150, width * 0.632099, height * 0.631944);

  //makes a little shelf
  noStroke();
  fill(#124055);
  rect(width/2, height, width, height*0.3);

  //makes two little pots
  fill(#E8AC6C);
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
  pStroke = #EE884E;
  pFill = #CE5A20;
}

///////////////////////////////////////////CITY SCENE////////////////////////////////////////////////
void cityScene() {
  //sets background color and picture frame
  background(#685968);
  stroke(#958794);
  strokeWeight(50);
  fill(#958794);
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
  image(swapPic[current], width/2, height/2-150, width * 0.632099, height * 0.631944);

  //makes a little shelf
  noStroke();
  fill(#4E3B4F);
  rect(width/2, height, width, height*0.3);

  //makes two little pots
  fill(#7996D3);
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
  pStroke = #1884EF;
  pFill = #314153;
}

/////////////////////////////////////////////FOREST SCENE///////////////////////////////////////////
void forestScene() {
  //sets background color and picture frame
  background(#E0D2AE);
  stroke(#483A2D);
  strokeWeight(50);
  fill(#483A2D);
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
  image(swapPic[current], width/2, height/2-150, width * 0.632099, height * 0.631944);

  //makes a little shelf
  noStroke();
  fill(#332920);
  rect(width/2, height, width, height*0.3);

  //makes two little pots
  fill(#9FCAFB);
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
  pStroke = #1A5613;
  pFill = #43743F;
}

////////////////////////////////////////////WINTER ECENE/////////////////////////////////////////////
void winterScene() {
  //sets background color and picture frame
  background(#EDDDDC);
  stroke(#BA9291);
  strokeWeight(50);
  fill(#BA9291);
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
  image(swapPic[current], width/2, height/2-150, width * 0.632099, height * 0.631944);

  //makes a little shelf
  noStroke();
  fill(#D3C2C1);
  rect(width/2, height, width, height*0.3);

  //makes two little pots
  fill(#838D94);
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
  pStroke = #3F0303;
  pFill = #F2C1C0;
}

/////////////////////////////////////////////RAIN SCENE////////////////////////////////////////////
void rainScene() {
  //sets background color and picture frame
  background(#918469);
  stroke(#473818);
  strokeWeight(50);
  fill(#473818);
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
  image(swapPic[current], width/2, height/2-150, width * 0.632099, height * 0.631944);

  //makes a little shelf
  noStroke();
  fill(#A56807);
  rect(width/2, height, width, height*0.3);

  //makes two little pots
  fill(#7B959C);
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
  pStroke = #9BDCF1;
  pFill = #FE0000;
}

///////////////////////////////////////STARS SCENE///////////////////////////////////////////////////
void starsScene() {
  //sets background color and picture frame
  background(#2D1F5E);
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
  image(swapPic[current], width/2, height/2-150, width * 0.632099, height * 0.631944);

  //makes a little shelf
  noStroke();
  fill(#21192F);
  rect(width/2, height, width, height*0.3);

  //makes two little pots
  fill(#182D47);
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
  pStroke = #13101B;
  pFill = 255;
}

//////////////////////////////////////////////////PLANTS SCENE////////////////////////////////////
void plantsOnly() {
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
void msk() {
  //masks use a black and white image to create alpha channels 
  //I use a PGraphic so I can hide it underneath the project
  myMask.beginDraw();
  myMask.fill(255);
  myMask.noStroke();
  myMask.rectMode(CENTER);
  if (frostyWindow) {//this makes it so that only one rectangle is drawn with each space press
  //if it updates every frame you can't draw
    myMask.rect(width/2, height/2-150, width * 0.632099, height * 0.631944);
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
void frost() {
  //just a glorified white rectangle
  frost.beginDraw();
  frost.rectMode(CENTER);
  frost.noStroke();
  frost.fill(255);
  frost.rect(width/2, height/2-150, width * 0.632099, height * 0.631944);
  frost.endDraw();
}
