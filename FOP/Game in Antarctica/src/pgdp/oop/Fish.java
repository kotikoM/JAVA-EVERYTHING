package pgdp.oop;

import javax.swing.plaf.InsetsUIResource;
import java.awt.Toolkit;
import java.io.File;

public class Fish extends Animal {

  static String filename = "fish.png";

  public Fish(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  public boolean isVoid(int x, int y){
    return antarktis[x][y] == null;

  }



  public boolean nullChecker(int x, int y){
    return antarktis[x][y] == null;
  }

  @Override
  public boolean isPredator(int a, int b) {
    boolean first =false;
    boolean second=false;
    boolean third=false;
    boolean fourth=false;

    if (!nullChecker(cyclicalChecker(a-1), b)) { //left
      first=antarktis[cyclicalChecker(a-1)][b].canEat(this);
    }

    if (!nullChecker(a, cyclicalChecker(b-1))){ //up
      second=antarktis[a][cyclicalChecker(b-1)].canEat(this);
    }

    if (!nullChecker(cyclicalChecker(a+1), b)){ //right
      third=antarktis[cyclicalChecker(a+1)][b].canEat(this);
    }

    if (!nullChecker(a, cyclicalChecker(b+1))){//down
      fourth=antarktis[a][cyclicalChecker(b+1)].canEat(this);
    }


    return first || second|| third|| fourth;
  }

  @Override
  public void move() {
    if (this.alive) {
      antarktis[this.x][this.y]=null;
      //fish cant eat
      if (nullChecker(x, cyclicalChecker(this.y-1))  &&
              !isPredator((x),cyclicalChecker(y-1)) ){ //above  no predators
       this.y=cyclicalChecker(y-1);
      }
      else if(nullChecker(cyclicalChecker(x+1), y) &&
              !isPredator(cyclicalChecker(x+1),y)  ){ //right no predators
        this.x=cyclicalChecker(x+1);
      }
      else if (nullChecker(x, cyclicalChecker(y+1)) &&
              !isPredator((x),cyclicalChecker(y+1)) ) { //below no predators
        this.y = cyclicalChecker(y + 1);
      }
      else  if (nullChecker(cyclicalChecker(x-1), y) &&
              !isPredator(cyclicalChecker(x-1),y) ){ ////left no predator
      this.x=cyclicalChecker(x-1);
      }


      antarktis[this.x][this.y]=this;
    }



  }


  @Override
  public int cyclicalChecker(int a) {
    //make sure that world is cyclical
    if (a>40) return 0;  //from right to left or from below to upper part
    else if (a<0) return 40; //from left to right or from upper part to below
    else return a;
  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  @Override
  protected boolean eatenBy(Penguin penguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(Whale whale) {
    return false;
  }

  @Override
  protected boolean eatenBy(LeopardSeal leopardSeal) {
    return true;
  }

  @Override
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
