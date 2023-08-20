package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class Penguin extends Animal {
  static String filename = "tux.png";

  public Penguin(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  @Override
  public void move() {

    if (this.alive) {
      antarktis[this.x][this.y]=null;

      if (antarktis[cyclicalChecker(x-1)][y]!=null &&
              this.canEat(antarktis[cyclicalChecker(x-1)][y]) &&
              !isPredator(cyclicalChecker(x-1),y)){ //left if food no predators
        antarktis[cyclicalChecker(x-1)][y].alive=false;
        this.x=cyclicalChecker(x-1);
      }

      else if (antarktis[(x)][cyclicalChecker(y-1)]!=null &&
              this.canEat(antarktis[(x)][cyclicalChecker(y-1)]) &&
              !isPredator((x),cyclicalChecker(y-1))){ //above if food no predators
        antarktis[(x)][cyclicalChecker(y-1)].alive=false;
        this.y=cyclicalChecker(y-1);
      }

      else if(antarktis[cyclicalChecker(x+1)][y]!=null &&
              this.canEat(antarktis[cyclicalChecker(x+1)][y]) &&
              !isPredator(cyclicalChecker(x+1),y)){ //right if food no predators
        antarktis[cyclicalChecker(x+1)][y].alive=false;
        this.x=cyclicalChecker(x+1);
      }

      else if (antarktis[(x)][cyclicalChecker(y+1)]!=null &&
              this.canEat(antarktis[(x)][cyclicalChecker(y+1)]) &&
              !isPredator((x),cyclicalChecker(y+1))){ //below if food no predators
        antarktis[(x)][cyclicalChecker(y+1)].alive=false;
        this.y=cyclicalChecker(y+1);
      }




      else  if (antarktis[cyclicalChecker(x-1)][y]==null &&
              !isPredator(cyclicalChecker(x-1),y)){ ////left no food no predator
        this.x=cyclicalChecker(x-1);
      }

      else if (antarktis[(x)][cyclicalChecker(y-1)]==null  &&
              !isPredator((x),cyclicalChecker(y-1))){ //above no food no predators
        this.y=cyclicalChecker(y-1);
      }

      else if(antarktis[cyclicalChecker(x+1)][y]==null &&
              !isPredator(cyclicalChecker(x+1),y)){ //right no food no predators
        this.x=cyclicalChecker(x+1);
      }

      else if (antarktis[(x)][cyclicalChecker(y+1)]==null &&
              !isPredator((x),cyclicalChecker(y+1))) { //below no food no predators
        this.y = cyclicalChecker(y + 1);
      }

      antarktis[this.x][this.y]=this;
    }
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  @Override
  public int cyclicalChecker(int a) {
    //make sure that world is cyclical
    if (a>40) return 0;  //from right to left or from below to upper part
    else if (a<0) return 40; //from left to right or from upper part to below
    else return a;
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

    return first || second || third || fourth;
  }

  @Override
  public boolean nullChecker(int x, int y) {
    return antarktis[x][y] == null;
  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  @Override
  protected boolean eatenBy(Penguin penguin) {
    return false;
  }

  @Override
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return false;
  }

  @Override
  protected boolean eatenBy(Whale whale) {
    return true;
  }

  @Override
  protected boolean eatenBy(LeopardSeal leopardSeal) {
    return false;
  }

  @Override
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
