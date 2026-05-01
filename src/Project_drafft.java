

import java.util.Scanner;

public class Project_drafft {
    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        //check command line arguments
        if(args.length!=0){
            System.out.print("Reversi takes no command line arguments to play.");
            System.exit(1);
        }

        boolean game_ended = false;

        //get filled states for initial board setup
        StdDraw.setScale(0,8.0);
        boolean [][] state = new boolean[8][8];
        for (int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if(i==3&&j==4||i==3&&j==3||i==4&&j==3||i==4&&j==4){
                    state[i][j] = true;
                }
                else{state[i][j] = false;}
            }
        }


        //holds values for the initial colors of the board
        int [][] colors = new int[8][8];
        for (int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if(i==3&&j==4||i==4&&j==3){
                    colors[i][j] = 1;
                }
                else if(i==3&&j==3||i==4&&j==4){
                    colors[i][j] = -1;
                }
                else{colors[i][j] = 5;}

            }
        }



        //Make and draw initial board using state and color
        Box [][] board = new Box[8][8];

        for (int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Box(i+0.5,j+0.5,state[i][j],colors[i][j]);

            }
        }


//      Initial color for next move (white goes first every time)
        int next_color = 1;


        while (!game_ended){

            //maybe put these in the update method to get it to call again if not a valid move?
            if(next_color ==1){
            System.out.println("Current color is: White");}
            else{System.out.println("Current color is: Black");}


            System.out.print("X value for next move:");
            int next_x = keyboard.nextInt();

            System.out.print("Y value for next move:");
            int next_y = keyboard.nextInt();
//             valid move check for chosen spot:
//             there is at least one direction where all the following hold true
//             The adjacent piece in the row must be of opposite color
//              row must end in empty space (Box = empty at some point and is still on the board)
//              None of the pieces before the empty space should be of the same color as the current player's piece
//
//
//              The board state updates and all the pieces in that row become the current players color and
//              a piece is added in the empty space at the end of teh row

//          check if its a valid move, if so draw the next state, otherwise ask the questions again?
            if (isValidMove(board, next_x,next_y, next_color)){

                Box[][] next_board = update(board, colors, next_x, next_y, next_color);
//              The current player (ie the color of the piece for the next move) is changed
//               next line might not be necessary if included in update
//                colors[next_x][next_y] = next_color;
                next_color *= -1;
            }

//              new state is drawn
//
//
//             winning conditions:
//             if a player has zero pieces
            boolean is_board_full = true;
            for (int i = 0; i<8;i++) {
                for (int j = 0; j < 8; j++) {
                    if (!board[i][j].getFilled()){is_board_full=false;
                    break;}

                }
            }
//             if the board is full, tally the score
//
//
//             reiterate while loop or change game_ended to = true
            game_ended = is_board_full;
         }


    }

    // draw method that will be called on in the main method
    // takes state[][] as an input to draw the current state of the board
    public static void draw(int[][] state){
        //draw a box in each location of a spot
        for (int i=0; i<state.length; i++){
            for (int j =0; j<state.length;j++){

                if (state[i][j]==1){
                    //draw cell picture if filled
                    Box temp = new Box(i,j,true,1);


                }
                else {
                    //draw empty spot



                }

            }
        }
    }
    //state update method (takes in state and returns state)
    // every time the state is updated, the number of boxes with filled states
    // should increase by one if done correctly
    public static Box[][] update(Box[][] state,int[][] colors,int x, int y, int curr_color){
        //check if indexes are valid

        Box[][] next_state= new Box[state.length][state.length];
        colors[x][y] = curr_color;

        //write the boxes for the new state
        for (int i=0;i< state.length;i++){
            for (int j=0;j< state.length;j++){
                //I check this condition in isValidMove
//               if(i==x&&j==y&&state[i][j].getFilled()){
//                   System.out.print("This spot is already filled");
//
//               }
               if(i==x&&j==y){
                   state[i][j].updateBox(colors[i][j]);
                   next_state[i][j]=state[i][j];
               }
                next_state[i][j]=state[i][j];
            }
        }
        state = next_state;
        return state;
    }

    public static boolean isValidMove(Box[][] board,int x , int y, int curr_color ){

        //is the move on the board?
        int xstart = x;
        int ystart = y;
        if(!onBoard(board[xstart][ystart])){System.out.println("Index must be between 0 and 7 , both included.");
            return false;
        }

        // is the space the player chose already filled?
        if(board[x][y].getFilled()){
            System.out.println("This space is filled, please pick again.");
            return false;}

        //are all the nearby spaces empty?
        boolean a = board[xstart][ystart+1].getFilled();
        boolean b = board[xstart+1][ystart].getFilled();
        boolean c = board[xstart][ystart-1].getFilled();
        boolean d = board[xstart-1][ystart].getFilled();
        if(!a &&!b&&!c&&!d){
            System.out.println("There are no nearby pieces to capture, please pick again");
            return false;}



        Box temp = new Box(x+0.5,y+0.5,true,curr_color);
        //this wont work for checking because of the index reaching outside the board bounds
        boolean ac = temp.compareColor(board[x][y+1]);
        boolean bc = temp.compareColor(board[x+1][y]);
        boolean cc = temp.compareColor(board[x][y-1]);
        boolean dc = temp.compareColor(board[x-1][y]);
        if(ac&&bc&&cc&&dc){
            System.out.println(ac);
            System.out.println(bc);
            System.out.println(cc);
            System.out.println(dc);
            System.out.println("You cannot capture your own pieces, please pick again");
            return false;}


        return true;
    }
    public static boolean onBoard(Box box){
        double xval = box.getX();
        double yval = box.getY();
        return(xval<8&&xval>0&&yval>0&&yval<8);


    }

}
