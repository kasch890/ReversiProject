

import java.util.Scanner;

public class Project_draft {
    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        if(args.length!=0){
            System.out.print("Reversi takes no command line arguments to play.");
            System.exit(1);
        }

        boolean game_ended = false;
        //draw initial board setup
        StdDraw.setScale(0,8.0);

        //set values for whether filled/not for the board
        boolean [][] state = new boolean[8][8];
        for (int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if(i==3&&j==4||i==3&&j==3||i==4&&j==3||i==4&&j==4){
                    state[i][j] = true;
                }
                else{state[i][j] = false;}
            }
        }


        //holds values for the colors of the board
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



        //Make and draw initial board
        Box [][] board = new Box[8][8];

        for (int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Box(i+0.5,j+0.5,state[i][j],colors[i][j]);

            }
        }


//      This is the color for the next piece to go on the board
        int next_color = 1;


        while (!game_ended){
//             this game will have a variable (Box[][] state) that will be updated through each iteration of the while loop
//             when the main method calls on the update method, this is when
//             the user will be prompted to select their next move.

            //maybe put these in the update method to get it to call again if not a valid move?
            System.out.println("Current color is:"+next_color);
            System.out.print("X value for next move:");
            int next_x = keyboard.nextInt();

            System.out.print("Y value for next move:");
            int next_y = keyboard.nextInt();
//             The player makes a move by inputting a spot on the board
//             The program then checks if it is a valid move, if so the state is then updated
//
//             valid move check for chosen spot:
//             there is at least one direction where all the following hold true
//             The adjacent piece in the row must be of opposite color
//              row must end in empty space (Box = empty at some point and is still on the board)
//              None of the pieces before the empty space should be of the same color as the current player's piece
//
//
//              The board state updates and all the pieces in that row become the current players color and
//              a piece is added in the empty space at the end of teh row
//
//            !!!!!!!!!uncomment
//            Box[][] next_board = update(board,next_x,next_y,next_color);
//
//
//              The current player (ie the color of the piece for the next move) is changed
            next_color*=-1;
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
//    public static Box[][] update(Box[][] state,int x, int y, int curr_color){
//        if(x>7||y>7){System.out.println("Index must be between 0 and 7 , both included.");
//            return state;
//        }
//
//        Box[][] next_state= new Box[state.length][state.length];
//
//        boolean is_valid_move=false;
//
////        while(!is_valid_move){
////
////        }
//
//
//
//        for (int i=0;i< state.length;i++){
//            for (int j=0;j< state.length;j++){
//               if(i==x&&j==y&&state[i][j].getFilled()){
//                   System.out.print("This spot is already filled");
//
//               }
//               else if(i==x&&j==y){
//                   next_state[i][j] = new Box(i+0.5,j+0.5,true,curr_color);
//
//               }
//
//            }
//        }
//        state = next_state;
//        return state;
//    }
}
