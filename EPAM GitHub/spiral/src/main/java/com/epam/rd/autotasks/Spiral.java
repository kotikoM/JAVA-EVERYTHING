package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int[][] spiral = new int[rows][columns];

        int value = 1;

        int topRow = 0;
        int bottomRow = rows - 1;

        int leftColumn = 0;
        int rightColumn = columns - 1;

        while (value <= rows * columns) {
            // right
            for (int col = leftColumn; col <= rightColumn; col++) {
                //avoid overwriting
                if (value > rows * columns) {
                    break;
                }
                spiral[topRow][col] = value++;
            }

            topRow++;

            // down
            for (int row = topRow; row <= bottomRow; row++) {
                //avoid overwriting
                if (value > rows * columns) {
                    break;
                }
                spiral[row][rightColumn] = value++;
            }
            rightColumn--;

            // left
            for (int col = rightColumn; col >= leftColumn; col--) {
                //avoid overwriting
                if (value > rows * columns) {
                    break;
                }
                spiral[bottomRow][col] = value++;
            }

            bottomRow--;

            // up
            for (int row = bottomRow; row >= topRow; row--) {
                //avoid overwriting
                if (value > rows * columns) {
                    break;
                }
                spiral[row][leftColumn] = value++;
            }
            leftColumn++;

        }

        return spiral;
    }

    public static void main(String[] args) {
        int[][] spiral = Spiral.spiral(3, 4);

        for (int i = 0; i < spiral.length; i++) {
            for (int j = 0; j < spiral[i].length; j++) {
                System.out.print(String.format("%4s", spiral[i][j]));
            }
            System.out.println();
        }
    }
}
