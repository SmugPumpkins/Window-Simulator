

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
    VerletSpring2D leftTense=new VerletSpring2D(top, left, eachLength*0.75, springStrength*1.5);
    VerletSpring2D rightTense=new VerletSpring2D(top, right, eachLength*0.75, springStrength*1.5);
    VerletSpring2D spacer=new VerletSpring2D(left, right, eachLength, springStrength*1.5);
    

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
  void display() {
    stroke(#418609);
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

  void containsLeft(int x, int y) {
    float d = dist(x, y, left.x, left.y);
    if (d < radius) {
      offset.x = left.x - x;
      offset.y = left.y - y;
      left.lock();
      draggedLeft = true;
    }
  }
  void containsRight(int x, int y) {
    float d = dist(x, y, right.x, right.y);
    if (d < radius) {
      offset.x = right.x - x;
      offset.y = right.y - y;
      right.lock();
      draggedRight = true;
    }
  }
  void containsTip(int x, int y) {
    float d = dist(x, y, top.x, top.y);
    if (d < radius) {
      offset.x = top.x - x;
      offset.y = top.y - y;
      top.lock();
      draggedTip = true;
    }
  }

  // Release the leaf
  void release() {
    left.unlock();
    right.unlock();
    top.unlock();
    draggedLeft = false;
    draggedRight = false;
    draggedTip = false;
  }

  // Update leaf location if being dragged
  void updateRight(int x, int y) {
    if (draggedRight) {
      right.set(x+offset.x, y+offset.y);
    }
  }
  void updateLeft(int x, int y) {
    if (draggedLeft) {
      left.set(x+offset.x, y+offset.y);
    }
  }
  void updateTip(int x, int y) {
    if (draggedTip) {
      top.set(x+offset.x, y+offset.y);
    }
  }
}
