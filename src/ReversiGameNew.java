

import java.util.Scanner;

public class ReversiGameNew {
    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        //check command line arguments
        if(args.length!=0){
            System.out.print("Reversi takes no command line arguments to play.");
            System.exit(1);
        }


        //set initial board filled states
        // 1 = white ,  -1 = black ,  5 = empty space

        StdDraw.setScale(0,8.0);
        int [][] pieces = new int[8][8];
        for (int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if(i==3&&j==4||i==4&&j==3){
                    pieces[i][j] = 1;
                }
                else if(i==3&&j==3||i==4&&j==4){
                    pieces[i][j] = -1;
                }
                else{pieces[i][j] = 5;}
            }
        }

//      Initial color for next move (white goes first every time
        // next_color is the color of the piece that will be placed next
        int next_color = 1;
        int next_x;
        int next_y;

        boolean game_ended = false;


        //game loop
        while (!game_ended){

            drawBoard();
            drawPieces(pieces);

            //maybe put these in the update method to get it to call again if not a valid move?
            if(next_color ==1){
            System.out.println("Current color is: White");}
            else{System.out.println("Current color is: Black");}

// ask user for they next move values
            System.out.print("X value for next move:");
            next_x = keyboard.nextInt();

            System.out.print("Y value for next move:");
            next_y = keyboard.nextInt();
//             valid move check for chosen spot:
//             there is at least one direction where all the following hold true
//             The adjacent piece in the row must be of opposite color
//              row must end in empty space (Box = empty at some point and is still on the board)
//              None of the pieces before the empty space should be of the same color as the current player's piece
//
//
//              The board board_fill_states updates and all the pieces in that row become the current players color and
//              a piece is added in the empty space at the end of teh row

//          check if its a valid move, if so draw the next board_fill_states, otherwise ask the questions again?
            if (isValidMove(pieces, next_x,next_y, next_color)){

                next_color *= -1;
            }

//              new board_fill_states is drawn

//
//             winning conditions:
//             if a player has zero pieces
//            boolean is_board_full = true;
//            for (int i = 0; i<8;i++) {
//                for (int j = 0; j < 8; j++) {
//                    if (!board[i][j].getFilled()){is_board_full=false;
//                        System.out.println("Testing when the board is not full"+" "+i+" "+j);
//                    break;}
//
//                }
//            }
//             if the board is full, tally the score
//
//
//             reiterate while loop or change game_ended to = true
//            game_ended = is_board_full;
         }


    }

    public static boolean isValidMove(int[][]pieces,int x_input , int y_input, int curr_color){

        //checks if it is a valid move and if it is it updates all_colors to the new board colors
        //is the move on the board?
        if(!onBoard(x_input,y_input)){
            System.out.println("Index must be between 0 and 7 , both included.");
            return false;
        }

        // is the space the player chose already filled?
        if(pieces[x_input][y_input]!=5){
            System.out.println("This space is filled, please pick again.");
            return false;}

//        //are all the main adjacent spaces empty?
//        boolean a = board[(x_input)][(y_input) +1].getFilled();
//        boolean b = board[(x_input) +1][(y_input)].getFilled();
//        boolean c = board[(x_input)][(y_input) -1].getFilled();
//        boolean d = board[(x_input) -1][(y_input)].getFilled();
//        if(!a &&!b&&!c&&!d){
//            System.out.println("There are no nearby pieces to capture, please pick again");
//            return false;}

        //grid of directions
        double [][] check_dir_grid = new double[2][8];
        check_dir_grid[0][0] = 0;
        check_dir_grid[0][1] = 1;
        check_dir_grid[0][2] = 1;
        check_dir_grid[0][3] = 1;
        check_dir_grid[0][4] = 0;
        check_dir_grid[0][5] = -1;
        check_dir_grid[0][6] = -1;
        check_dir_grid[0][7] = -1;
        check_dir_grid[1][0] = 1;
        check_dir_grid[1][1] = 1;
        check_dir_grid[1][2] = 0;
        check_dir_grid[1][3] = -1;
        check_dir_grid[1][4] = -1;
        check_dir_grid[1][5] = -1;
        check_dir_grid[1][6] = 0;
        check_dir_grid[1][7] = 1;

        //flip_num may be used to keep score in the future, may need to be defined in the game loop though
//        int flip_num = 0;

        //will be used to make new color grid by multiplying
        int[][] tiles_to_flip = new int[8][8];

        for (int k = 0; k<8;k++){
            for(int h = 0; h<8; h++){
                tiles_to_flip[k][h] = 1;
            }
        }
// check the directions for the conditions
            for (int j = 0; j<8; j++) {

                //initialize step location
                int x_step = (x_input);
                int y_step = (y_input);
                x_step += (int) check_dir_grid[0][j];
                y_step += (int) check_dir_grid[1][j];
                // two lines below are print err codes
//                System.out.println(board[x_step][y_step].getColor());
//                System.out.println(curr_color!=board[x_step][y_step].getColor());
                //is the step check on the board?

                //check first step space conditions
                if (!onBoard(x_step,y_step)) {
                    //                        line below is print code for debugging
                    //System.out.println("Out of bounds");
                    continue;
                }
                //is step check space filled?
                if (pieces[x_input][y_input]==5) {
                    //                        line below is print code for debugging
                    //System.out.println("Adjacent space is empty");

                    continue;
                }
                // is step check space a diff color?
                if(pieces[x_input][y_input]==curr_color){
                    //                        line below is print code for debugging
                    //System.out.println("The adjacent color is the same");

                    continue;}


                //index the step check until it's either:
                // unsuccessful move : the end of the board or the space is not filled
                // successful move: the second indexed space is the same color

                    while (pieces[x_input][y_input]==(curr_color*(-1))){
//                        line below is print code for debugging
//                        System.out.println("walking");

                        //index the step
                        x_step += (int) check_dir_grid[0][j];
                        y_step += (int) check_dir_grid[1][j];
                        if (!onBoard(x_step,y_step)){break;}
                    }
                    //if the last piece is not on the board, go through the for loop
                    if (!onBoard(x_step,y_step)){continue;}

                    //if the last space is not a filled space, go through the for loop
                    if (pieces[x_input][y_input]==5){continue;}

                    //if the last piece in the string is the current move color, unindex the step
                    if(pieces[x_input][y_input] == curr_color){
                    x_step -= (int) check_dir_grid[0][j];
                    y_step -= (int) check_dir_grid[1][j];

// create the tiles that you need to flip
                        while (x_step != (x_input) || y_step != (y_input)) {

//                            flip_num += 1;
                            tiles_to_flip[x_step][y_step] = -1;
                            x_step -= (int) check_dir_grid[0][j];
                            y_step -= (int) check_dir_grid[1][j];
                        }
                    }


            }

//        if (flip_num==0){
//            System.out.println("This is not a valid move");
//            return false;}

            pieces[(x_input)][(y_input)] = curr_color;
            for (int k = 0; k<8;k++){
                for(int h = 0; h<8; h++){
//                    System.out.println(tiles_to_flip[k][h]+" "+colors[k][h]+" "+k + " " + h);
                    pieces[k][h] = pieces[k][h]*tiles_to_flip[k][h];
                    System.out.println(tiles_to_flip[k][h]+" "+k + " " + h);

                }
            }
        //System.out.println(flip_num);

        return true;
    }
    public static boolean onBoard(int xval, int yval){
boolean check = (xval<8&&xval>=0&&yval>=0&&yval<8);
    if (!check){System.out.println("("+xval + ", "+ yval+") is not on the board.");}
        return(check);


    }

    public static void drawBoard(){
        for (int x =0; x<8; x++){
            for (int y=0; y<8; y++){
                StdDraw.setPenColor(StdDraw.DARK_GRAY);
                StdDraw.filledSquare(x+0.5, y+0.5,0.5);

                StdDraw.setPenColor(17,165,35);
                StdDraw.filledSquare(x+0.5, y+0.5,0.48);
            }
        }

    }

    public static void drawPieces(int[][] pieces){
        for (int x =0; x<8; x++){
            for (int y=0; y<8; y++){
                if (pieces[x][y] == 1){
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledCircle(x+0.5, y+0.5,0.45);
                }
                else if (pieces[x][y]== -1){
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledCircle(x+0.5, y+0.5,0.45);
                }
            }
        }
    }
}

