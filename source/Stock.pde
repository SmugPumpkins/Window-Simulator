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
    tip =new Particle(startX, startY-eachLength*15.5);

    //lock the base in place if it is the bottom
    base.lock();

    //create the springs
    //particle 1, particle 2, length, strength
    VerletSpring2D leftSpring1=new VerletSpring2D(base, left1, eachLength, springStrength);
    VerletSpring2D rightSpring1=new VerletSpring2D(base, right1, eachLength, springStrength);
    VerletSpring2D partSpring1=new VerletSpring2D(base, part1, eachLength, springStrength);
    VerletSpring2D leftTense1=new VerletSpring2D(part1, left1, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense1=new VerletSpring2D(part1, right1, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer1=new VerletSpring2D(left1, right1, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft2=new VerletSpring2D(left1, right2, eachLength*2, springStrength*3);
    VerletSpring2D diagRight2=new VerletSpring2D(left2, right1, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft2=new VerletSpring2D(left1, left2, eachLength, springStrength*3);
    VerletSpring2D spacerRight2=new VerletSpring2D(right2, right1, eachLength, springStrength*3);
    VerletSpring2D leftSpring2=new VerletSpring2D(part1, left2, eachLength, springStrength);
    VerletSpring2D rightSpring2=new VerletSpring2D(part1, right2, eachLength, springStrength);
    VerletSpring2D partSpring2=new VerletSpring2D(part1, part2, eachLength, springStrength);
    VerletSpring2D leftTense2=new VerletSpring2D(part2, left2, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense2=new VerletSpring2D(part2, right2, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer2=new VerletSpring2D(left2, right2, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft3=new VerletSpring2D(left2, right3, eachLength*2, springStrength*3);
    VerletSpring2D diagRight3=new VerletSpring2D(left3, right2, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft3=new VerletSpring2D(left2, left3, eachLength, springStrength*3);
    VerletSpring2D spacerRight3=new VerletSpring2D(right3, right2, eachLength, springStrength*3);
    VerletSpring2D leftSpring3=new VerletSpring2D(part2, left3, eachLength, springStrength);
    VerletSpring2D rightSpring3=new VerletSpring2D(part2, right3, eachLength, springStrength);
    VerletSpring2D partSpring3=new VerletSpring2D(part2, part3, eachLength, springStrength);
    VerletSpring2D leftTense3=new VerletSpring2D(part3, left3, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense3=new VerletSpring2D(part3, right3, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer3=new VerletSpring2D(left3, right3, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft4=new VerletSpring2D(left3, right4, eachLength*2, springStrength*3);
    VerletSpring2D diagRight4=new VerletSpring2D(left4, right3, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft4=new VerletSpring2D(left3, left4, eachLength, springStrength*3);
    VerletSpring2D spacerRight4=new VerletSpring2D(right4, right3, eachLength, springStrength*3);
    VerletSpring2D leftSpring4=new VerletSpring2D(part3, left4, eachLength, springStrength);
    VerletSpring2D rightSpring4=new VerletSpring2D(part3, right4, eachLength, springStrength);
    VerletSpring2D partSpring4=new VerletSpring2D(part3, part4, eachLength, springStrength);
    VerletSpring2D leftTense4=new VerletSpring2D(part4, left4, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense4=new VerletSpring2D(part4, right4, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer4=new VerletSpring2D(left4, right4, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft5=new VerletSpring2D(left4, right5, eachLength*2, springStrength*3);
    VerletSpring2D diagRight5=new VerletSpring2D(left5, right4, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft5=new VerletSpring2D(left4, left5, eachLength, springStrength*3);
    VerletSpring2D spacerRight5=new VerletSpring2D(right5, right4, eachLength, springStrength*3);
    VerletSpring2D leftSpring5=new VerletSpring2D(part4, left5, eachLength, springStrength);
    VerletSpring2D rightSpring5=new VerletSpring2D(part4, right5, eachLength, springStrength);
    VerletSpring2D partSpring5=new VerletSpring2D(part4, part5, eachLength, springStrength);
    VerletSpring2D leftTense5=new VerletSpring2D(part5, left5, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense5=new VerletSpring2D(part5, right5, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer5=new VerletSpring2D(left5, right5, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft6=new VerletSpring2D(left5, right6, eachLength*2, springStrength*3);
    VerletSpring2D diagRight6=new VerletSpring2D(left6, right5, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft6=new VerletSpring2D(left5, left6, eachLength, springStrength*3);
    VerletSpring2D spacerRight6=new VerletSpring2D(right5, right6, eachLength, springStrength*3);
    VerletSpring2D leftSpring6=new VerletSpring2D(part5, left6, eachLength, springStrength);
    VerletSpring2D rightSpring6=new VerletSpring2D(part5, right6, eachLength, springStrength);
    VerletSpring2D partSpring6=new VerletSpring2D(part5, part6, eachLength, springStrength);
    VerletSpring2D leftTense6=new VerletSpring2D(part6, left6, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense6=new VerletSpring2D(part6, right6, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer6=new VerletSpring2D(left6, right6, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft7=new VerletSpring2D(left6, right7, eachLength*2, springStrength*3);
    VerletSpring2D diagRight7=new VerletSpring2D(left7, right6, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft7=new VerletSpring2D(left6, left7, eachLength, springStrength*3);
    VerletSpring2D spacerRight7=new VerletSpring2D(right6, right7, eachLength, springStrength*3);
    VerletSpring2D leftSpring7=new VerletSpring2D(part6, left7, eachLength, springStrength);
    VerletSpring2D rightSpring7=new VerletSpring2D(part6, right7, eachLength, springStrength);
    VerletSpring2D partSpring7=new VerletSpring2D(part6, part7, eachLength, springStrength);
    VerletSpring2D leftTense7=new VerletSpring2D(part7, left7, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense7=new VerletSpring2D(part7, right7, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer7=new VerletSpring2D(left7, right7, eachLength, springStrength*spacerStregnth);

    VerletSpring2D diagLeft8=new VerletSpring2D(left7, right8, eachLength*2, springStrength*3);
    VerletSpring2D diagRight8=new VerletSpring2D(left8, right7, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft8=new VerletSpring2D(left7, left8, eachLength, springStrength*3);
    VerletSpring2D spacerRight8=new VerletSpring2D(right7, right8, eachLength, springStrength*3);
    VerletSpring2D leftSpring8=new VerletSpring2D(part7, left8, eachLength, springStrength);
    VerletSpring2D rightSpring8=new VerletSpring2D(part7, right8, eachLength, springStrength);
    VerletSpring2D partSpring8=new VerletSpring2D(part7, part8, eachLength, springStrength);
    VerletSpring2D leftTense8=new VerletSpring2D(part8, left8, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense8=new VerletSpring2D(part8, right8, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer8=new VerletSpring2D(left8, right8, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft9=new VerletSpring2D(left8, right9, eachLength*2, springStrength*3);
    VerletSpring2D diagRight9=new VerletSpring2D(left9, right8, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft9=new VerletSpring2D(left8, left9, eachLength, springStrength*3);
    VerletSpring2D spacerRight9=new VerletSpring2D(right8, right9, eachLength, springStrength*3);
    VerletSpring2D leftSpring9=new VerletSpring2D(part8, left9, eachLength, springStrength);
    VerletSpring2D rightSpring9=new VerletSpring2D(part8, right9, eachLength, springStrength);
    VerletSpring2D partSpring9=new VerletSpring2D(part8, part9, eachLength, springStrength);
    VerletSpring2D leftTense9=new VerletSpring2D(part9, left9, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense9=new VerletSpring2D(part9, right9, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer9=new VerletSpring2D(left9, right9, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft10=new VerletSpring2D(left9, right10, eachLength*2, springStrength*3);
    VerletSpring2D diagRight10=new VerletSpring2D(left10, right9, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft10=new VerletSpring2D(left9, left10, eachLength, springStrength*3);
    VerletSpring2D spacerRight10=new VerletSpring2D(right9, right10, eachLength, springStrength*3);
    VerletSpring2D leftSpring10=new VerletSpring2D(part9, left10, eachLength, springStrength);
    VerletSpring2D rightSpring10=new VerletSpring2D(part9, right10, eachLength, springStrength);
    VerletSpring2D partSpring10=new VerletSpring2D(part9, part10, eachLength, springStrength);
    VerletSpring2D leftTense10=new VerletSpring2D(part10, left10, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense10=new VerletSpring2D(part10, right10, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer10=new VerletSpring2D(left10, right10, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft11=new VerletSpring2D(left10, right11, eachLength*2, springStrength*3);
    VerletSpring2D diagRight11=new VerletSpring2D(left11, right10, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft11=new VerletSpring2D(left10, left11, eachLength, springStrength*3);
    VerletSpring2D spacerRight11=new VerletSpring2D(right10, right11, eachLength, springStrength*3);
    VerletSpring2D leftSpring11=new VerletSpring2D(part10, left11, eachLength, springStrength);
    VerletSpring2D rightSpring11=new VerletSpring2D(part10, right11, eachLength, springStrength);
    VerletSpring2D partSpring11=new VerletSpring2D(part10, part11, eachLength, springStrength);
    VerletSpring2D leftTense11=new VerletSpring2D(part11, left11, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense11=new VerletSpring2D(part11, right11, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer11=new VerletSpring2D(left11, right11, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft12=new VerletSpring2D(left11, right12, eachLength*2, springStrength*3);
    VerletSpring2D diagRight12=new VerletSpring2D(left12, right11, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft12=new VerletSpring2D(left11, left12, eachLength, springStrength*3);
    VerletSpring2D spacerRight12=new VerletSpring2D(right11, right12, eachLength, springStrength*3);
    VerletSpring2D leftSpring12=new VerletSpring2D(part11, left12, eachLength, springStrength);
    VerletSpring2D rightSpring12=new VerletSpring2D(part11, right12, eachLength, springStrength);
    VerletSpring2D partSpring12=new VerletSpring2D(part11, part12, eachLength, springStrength);
    VerletSpring2D leftTense12=new VerletSpring2D(part12, left12, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense12=new VerletSpring2D(part12, right12, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer12=new VerletSpring2D(left12, right12, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft13=new VerletSpring2D(left12, right13, eachLength*2, springStrength*3);
    VerletSpring2D diagRight13=new VerletSpring2D(left13, right12, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft13=new VerletSpring2D(left12, left13, eachLength, springStrength*3);
    VerletSpring2D spacerRight13=new VerletSpring2D(right12, right13, eachLength, springStrength*3);
    VerletSpring2D leftSpring13=new VerletSpring2D(part12, left13, eachLength, springStrength);
    VerletSpring2D rightSpring13=new VerletSpring2D(part12, right13, eachLength, springStrength);
    VerletSpring2D partSpring13=new VerletSpring2D(part12, part13, eachLength, springStrength);
    VerletSpring2D leftTense13=new VerletSpring2D(part13, left13, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense13=new VerletSpring2D(part13, right13, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer13=new VerletSpring2D(left13, right13, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft14=new VerletSpring2D(left13, right14, eachLength*2, springStrength*3);
    VerletSpring2D diagRight14=new VerletSpring2D(left14, right13, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft14=new VerletSpring2D(left13, left14, eachLength, springStrength*3);
    VerletSpring2D spacerRight14=new VerletSpring2D(right13, right14, eachLength, springStrength*3);
    VerletSpring2D leftSpring14=new VerletSpring2D(part13, left14, eachLength, springStrength);
    VerletSpring2D rightSpring14=new VerletSpring2D(part13, right14, eachLength, springStrength);
    VerletSpring2D partSpring14=new VerletSpring2D(part13, part14, eachLength, springStrength);
    VerletSpring2D leftTense14=new VerletSpring2D(part14, left14, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense14=new VerletSpring2D(part14, right14, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer14=new VerletSpring2D(left14, right14, eachLength, springStrength*spacerStregnth);  

    VerletSpring2D diagLeft15=new VerletSpring2D(left14, right15, eachLength*2, springStrength*3);
    VerletSpring2D diagRight15=new VerletSpring2D(left15, right14, eachLength*2, springStrength*3);
    VerletSpring2D spacerLeft15=new VerletSpring2D(left14, left15, eachLength, springStrength*3);
    VerletSpring2D spacerRight15=new VerletSpring2D(right14, right15, eachLength, springStrength*3);
    VerletSpring2D leftSpring15=new VerletSpring2D(part14, left15, eachLength, springStrength);
    VerletSpring2D rightSpring15=new VerletSpring2D(part14, right15, eachLength, springStrength);
    VerletSpring2D partSpring15=new VerletSpring2D(part14, tip, eachLength*2, springStrength);
    VerletSpring2D leftTense15=new VerletSpring2D(tip, left15, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense15=new VerletSpring2D(tip, right15, eachLength*0.75, springStrength*1.5);
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
  void display() {
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

  void contains(int x, int y) {    
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

  void updateLeaf(int x, int y) {
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
  void release() {
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
