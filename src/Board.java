//public class Board {
//    private int nextplyr;
//    private Box[][] currstate;
//
//    public Board(Box[][] state){
//
//
//    }
//
//    public static Board update(Board current,int x, int y, int curr_color){
//        if(x>7||y>7){System.out.println("Index must be between 0 and 7 , both included.");
//            return current;
//        }
//
//        Board next_state= new Board(current);
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
//                if(i==x&&j==y&&state[i][j].getFilled()){
//                    System.out.print("This spot is already filled");
//
//                }
//                else if(i==x&&j==y){
//                    next_state[i][j] = new Box(i+0.5,j+0.5,true,curr_color);
//
//                }
//
//            }
//        }
//        state = next_state;
//        return state;
//    }
//
//}
