package fop.w3box;


public class CoverTheTiles extends MiniJava {

  // Output of open tiles. Don't modify this
  public static void outputTiles(boolean[] tiles) {
    StringBuilder sb = new StringBuilder("Open tiles: 1:");
    sb.append(tiles[0]);
    for (int i = 1; i < tiles.length; i++) {
      sb.append(" ").append(i + 1).append(":").append(tiles[i]);
    }
    write(sb.toString());
  }

  public static void main(String[] args) throws IllegalAccessException {
    boolean[] tiles = new boolean[10];

    for (int i = 0; i < 10; i++) {
      tiles[i] = true;                   //filling with true values
    }

    int a1 /*player1 tile1*/ = 0;
    int a2 /*player1 tile2 */ = 0;
    int b1 /*player2 tile1*/ = 0;
    int b2 /*player2 tile1*/ = 0;
    int ab1=0; //account balance 1
    int ab2=0; //account balance 2
    int n=0;  // used for checking if array is filled
    int Rand11=0; //first dice num
    int Rand12=0; //second dice num

    for (int j = 0; j < 10; j++) {

      for (int y = 0; y < 1; y++) {
        y=0;                          //infinite loop
        {
          System.out.println("Player 1 numbers:");
          Rand11=dice();
          Rand12=dice();
          System.out.println(Rand11);
          System.out.println(Rand12);
          outputTiles(tiles);
          System.out.println("Which tiles to cover by player 1? (0 for no valid combination)");
          System.out.print("Tile 1:");
          a1 = readInt();
          System.out.print("Tile 2:");
          a2 = readInt();

          {
            if (a1 == 0 || a2 == 0) {
              for(int i = 0; i <10 ; i++) {                         //exit loop if input is 0 (0)
                if (tiles[i])
                  ab1 = ab1 + i + 1;
              }
              break;

            }
            for (int i = 0; i < 10000 ; i++)  //10000 mistakes allowed. Could be more but its arbitrary
            {
            if ( a1<0 || a2<0 || a1 == a2 || a1 + a2 > 12 || a1 + a2 != Rand11 + Rand12 || !tiles[a1 - 1] || !tiles[a2 - 1])/* all exceptions, checking faulty inputs*/ {
              System.out.println("Please Insert Correct Numbers:");
              System.out.print("Tile 1:");
              a1 = readInt();
              System.out.print("Tile 2:");         //choosing numbers
              a2 = readInt();
            }
            if (a1==0 || a2==0)
              break;                    //if user wants to skip after wrong inputs
          }
            if (a1==0 || a2==0)
              break;


             {
              tiles[a1 - 1] = false;
              tiles[a2 - 1] = false;   //making tiles false
              outputTiles(tiles);
              break;
            }
          }
        }
      }

      n=0;
      for (int h = 0; h <10 ; h++) {
        if (!tiles[h]) {
          n = n + 1;
        }
      }                                                        //if player fills tiles then we have a winner
      if (n==10){
        System.out.println("Player 1 Wins!");
        break;

      }

        for ( int y = 0; y < 1; y++) {
          y=0;                         //infinite loop
          {
            System.out.println("Player 2 numbers:");
            Rand11=dice();
            Rand12=dice();
            System.out.println(Rand11);
            System.out.println(Rand12);
            outputTiles(tiles);
            System.out.println("Which tiles to cover by player 2? (0 for no valid combination)");
            System.out.print("Tile 1:");
            b1 = readInt();
            System.out.print("Tile 2:");
            b2 = readInt();

            {
              if (b1 == 0 || b2 == 0) {
                for(int t = 0; t <10 ; t++) {
                  if (tiles[t])                    //exit loop if input is 0 (0)
                    ab2 = ab2 + t + 1;
                }
                break;
              }
              for (int i = 0; i < 10000 ; i++)   //10000 mistakes allowed. Could be more but its arbitrary
              {
                if (b1<0 || b2<0 || b1 + b2 > 12 || b1 + b2 != Rand11 + Rand12 || b1 == b2 || !tiles[b1 - 1] || !tiles[b2 - 1]) /*checking faulty inputs*/ {
                  System.out.println("Please Insert Correct Numbers:");
                  System.out.print("Tile 1:");
                  b1 = readInt();
                  System.out.print("Tile 2:");
                  b2 = readInt();
                }
                if (b1==0 || b2==0)  //if user wants to skip after wrong inputs
                  break;
              }
              if (b1==0 || b2==0)
                break;

               {
                tiles[b1 - 1] = false;
                tiles[b2 - 1] = false;  //making tiles false
                outputTiles(tiles);
                break;
              }
            }
          }
        }

        n=0;
      for (int h = 0; h <10 ; h++) {
        if (!tiles[h]) {
          n = n + 1;
        }
      }                                                        //if player fills tiles then we have a winner
      if (n==10){
        System.out.println("Player 2 Wins!");
        break;
      }
      
    }
    if (n != 10) {
      if (ab1 > ab2)
        System.out.println("Player 2 Wins!");
      else if (ab2 > ab1)
        System.out.println("Player 1 Wins!");              //Comparing account balances
      else if (ab1==ab2)
        System.out.println("Draw!");
    }


    }
  }

