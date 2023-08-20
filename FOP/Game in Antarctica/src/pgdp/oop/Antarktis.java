package pgdp.oop;

import java.awt.event.WindowEvent;


public class Antarktis extends Maze {
    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5]; //5
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20]; //20
    private static Fish[] fishes = new Fish[500];//500
    private static PlayerPenguin playerPenguin;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);
        Animal.antarktis=antarktis;
        setupMaze();
        gameLoop();
        closeFrame();
    }

    private static void gameLoop() {
        while(true){
            draw();
            if (currentEvent!=NOTHING)
                moveAll();
            currentEvent=NOTHING;

           if (!playerPenguin.alive) {
                System.out.println("Game Over!");
                if (playerPenguin.getX()==lostPenguin.getX() && lostPenguin.getY()== playerPenguin.getY())
                    System.out.println("Congratulations! You have found the Lost Penguin");
                else System.out.println("You Were Eaten or Did Illegal Move");
                break;}
            if (!lostPenguin.alive) {
                System.out.println("LostPenguin was Eaten!");
                break;
                }
            //for testing purposes
            //ignore this part
            //else
//                if ((playerPenguin.x==lostPenguin.x+1 && playerPenguin.y==lostPenguin.y) ||
//                    (playerPenguin.x==lostPenguin.x-1 && playerPenguin.y==lostPenguin.y) ||
//                    (playerPenguin.x==lostPenguin.x && playerPenguin.y==lostPenguin.y+1) ||
//                    (playerPenguin.x==lostPenguin.x && playerPenguin.y==lostPenguin.y-1)) {
//                    //if playerPenguin is on the adjacent tile of lost penguin. Penguin is found
//                    //I think this is more appropriate than actually going on the tile of the lostPenguin
//                System.out.println("Congratulations! You have found the Lost Penguin");
//                break;
//            }

            }
        }



    private static void moveAll() {
        for (int i = 0; i <whales.length ; i++) {

           whales[i].move();
        }

        for (int i = 0; i < leopardSeals.length; i++) {

            leopardSeals[i].move();
        }


        if (currentEvent==UP) playerPenguin.move(0, -1);
        else if (currentEvent==RIGHT) playerPenguin.move(1, 0);
        else if (currentEvent==LEFT) playerPenguin.move(-1, 0);
        else if (currentEvent==DOWN) playerPenguin.move(0, 1);

        currentEvent=NOTHING;
        if (!(playerPenguin.getX()==lostPenguin.getX() && lostPenguin.getY()== playerPenguin.getY()))
            lostPenguin.move();

        for (int i = 0; i < fishes.length ; i++) {
            fishes[i].move();
        }
    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }



        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }
}
