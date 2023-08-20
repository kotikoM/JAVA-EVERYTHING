

public class FindPingu extends Maze {
    static int[][] maze;

    public static void main(String[] args) {
        int width = readInt("Width of the labyrinth?");
        int height = readInt("Height of the labyrinth?");
        int maxDistance = readInt("How far do you dare to go?");
        if (width < 3 || height < 3 || maxDistance < 1) {
            write("Erroneous input!");
            return;
        }
        // always generates the same maze.
        maze = generateStandardPenguinMaze(width, height);
        // generate random maze.
        // maze = generatePenguinMaze(width, height);
        int penguins = walk(1, 0, maxDistance);
        System.out.println("Rescued penguins: " + penguins);
    }

    public static int walk(int x, int y, int maxDistance) {
        if (maxDistance < 0)
            return 0;

        // Are we dropping out of the maze?
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length)
            return 0;

        // Are we running into a wall or a position which already has been visited?
        if (maze[x][y] == WALL || maze[x][y] == OLD_PATH_ACTIVE)
            return 0;

        int penguins = 0;
        if (maze[x][y] == PENGUIN) {
            penguins = 1;
        }

        // We enter the position.
        maze[x][y] = PLAYER;
        draw(maze);
        maze[x][y] = OLD_PATH_ACTIVE;

        penguins += walk(x + 1, y, maxDistance - 1);

        // At that point, we return from the recursion. We therefore enter it again.
        maze[x][y] = PLAYER;
        draw(maze);
        maze[x][y] = OLD_PATH_ACTIVE;

        penguins += walk(x, y + 1, maxDistance - 1);

        // At that point, we return from the recursion. We therefore enter it again.
        maze[x][y] = PLAYER;
        draw(maze);
        maze[x][y] = OLD_PATH_ACTIVE;

        penguins += walk(x - 1, y, maxDistance - 1);

        // At that point, we return from the recursion. We therefore enter it again.
        maze[x][y] = PLAYER;
        draw(maze);
        maze[x][y] = OLD_PATH_ACTIVE;

        penguins += walk(x, y - 1, maxDistance - 1);

        // At that point, we return from the recursion. We therefore enter it again.
        maze[x][y] = PLAYER;
        draw(maze);

//        // Shorter version of the recursion
//        int[][] directions = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
//        for (int i = 0; i < directions.length; i++) {
//            penguins += walk(x + directions[i][0], y + directions[i][1], maxDistance - 1);
//            maze[x][y] = PLAYER;
//            draw(maze);
//            maze[x][y] = OLD_PATH_ACTIVE;
//        }

        // All directions have been covered; this position is finished!
        maze[x][y] = OLD_PATH_DONE;
        return penguins;

    }

}
