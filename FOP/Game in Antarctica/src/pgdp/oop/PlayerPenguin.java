package pgdp.oop;

import javax.swing.*;

public class PlayerPenguin extends Penguin {
    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }

    public boolean move(int x, int y){


        antarktis[this.x][this.y]=null;


        if (x==-1 && y==0 ) {
            if (!nullChecker(cyclical(this.x-1), this.y) && this.canEat(antarktis[cyclical(this.x-1)][this.y])) {
                antarktis[cyclical(this.x-1)][this.y].alive=false;

            }
            if (!nullChecker(cyclical(this.x-1), this.y) && !this.canEat(antarktis[cyclical(this.x-1)][this.y]))
                this.alive=false;
            this.x = cyclical(this.x - 1);
        }
        else if (x==1 && y==0) {
            if (!nullChecker(cyclical(this.x+1), this.y) && this.canEat(antarktis[cyclical(this.x+1)][this.y])) {
                antarktis[cyclical(this.x+1)][this.y].alive=false;

        }
            if (!nullChecker(cyclical(this.x+1), this.y) && !this.canEat(antarktis[cyclical(this.x+1)][this.y]))
                this.alive=false;
            this.x = cyclical(this.x + 1);
        }
        else if (y==-1) {
            if (!nullChecker(this.x, cyclical(this.y-1)) && this.canEat(antarktis[this.x][cyclical(this.y-1)])) {
                antarktis[this.x][cyclical(this.y-1)].alive=false;
            }
           if (!nullChecker(this.x, cyclical(this.y-1)) && !this.canEat(antarktis[this.x][cyclical(this.y-1)]))
               this.alive=false;
            this.y = cyclical(this.y - 1);

        }
        else if (y==1 && x==0) {
            if (!nullChecker(this.x, cyclical(this.y+1)) && this.canEat(antarktis[this.x][cyclical(this.y+1)])) {
                antarktis[this.x][cyclical(this.y+1)].alive=false;
            }
            if (!nullChecker(this.x, cyclical(this.y+1)) && !this.canEat(antarktis[this.x][cyclical(this.y+1)]))
                this.alive=false;
            this.y = cyclical(this.y + 1);
        }
        antarktis[this.x][this.y]=this;

        return !this.alive ;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }


    public int cyclical(int a) {
        //make sure that world is cyclical
        if (a>40) return 0;  //from right to left or from below to upper part
        else if (a<0) return 40; //from left to right or from upper part to below
        else return a;
    }
}
