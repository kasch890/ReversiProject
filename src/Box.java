public class Box {
    private double x;
    private double y;
    private boolean filled;
    private int clr;
    //this will create an item Box
    //That takes as an input (bool filled, optional? int color)
    // if !filled, the box is empty, if filled it is filled and the box is drawn with a piece on it instead
    public Box(double x_val, double y_val, boolean fill, int color) {
        //sets private variables to input values
        this.x = x_val;
        this.y = y_val;
        this.filled = fill;
        this.clr = color;

        //draw white piece
        if(filled&&color==1){
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledSquare(x_val, y_val,0.5);

            StdDraw.setPenColor(17,165,35);
            StdDraw.filledSquare(x_val, y_val,0.48);

            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(x_val, y_val,0.45);
        }
        //draw black piece
        else if(filled&&color==-1){
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledSquare(x_val, y_val,0.5);

            StdDraw.setPenColor(17,165,35);
            StdDraw.filledSquare(x_val, y_val,0.48);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(x_val, y_val,0.45);
        }
        //draw empty square
        else {
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledSquare(x_val, y_val,0.5);

            StdDraw.setPenColor(17,165,35);
            StdDraw.filledSquare(x_val, y_val,0.48);

        }
    }

   //these 4 methods return info about the boxes
    public boolean getFilled() {
        return filled;
    }
    public int getColor() {
        return clr;
    }
    public double getX(){return x;}
    public double getY(){return y;}


    public boolean compareColor(Box other){
        //return false if they are different colors
        if (!other.getFilled()){return true;}
        else if(this.getColor()==other.getColor()){return true;}
        else return false;
    }


    public Box updateBox(int next_color){
        Box temp = new Box(this.x,this.y,true,next_color);
//        this.filled = filled;
        return this;
    }

    public void updateFilled(int curr_color){
        this.filled = true;
        this.clr = curr_color;
    }
    //method update(int state)
    // if box_state = 0, the box is empty, if =1 it is filled (should only go from 0 ->1)
    //returns Box with new state

}

