import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Piece {
    private String color = "";
    private boolean hasMove = true;
    private JLabel image;
    private ArrayList<Square> squares = new ArrayList<>();
    private int x;
    private int y;
    private int lastMoved = 0;

    public Piece(String color, Point location){
        this.color = color;
        x = location.x;
        y = location.y;
    }

    public String getColor() {
            return color;
    }
    public boolean getHasMove(){
        return hasMove;
    }
    public JLabel getImage(){
        return image;
    }
    public int getX(){return x;}
    public int getY(){return y;}

    public ArrayList<Square> getSquares() {
        return squares;
    }
    public int getLastMoved(){
        return lastMoved;
    }
    public void addLastMove(){
        lastMoved++;
    }

    public void setHasMove(boolean f){
        hasMove = f;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public Point getLocation(){
        return new Point(x,y);
    }

    public void setImage(JLabel image){
        this.image = image;
        if (image != null)
            image.setSize(image.getPreferredSize());
    }
    public ArrayList<Square> findSquares(Square[][] s){
        return squares;
    }

    public void setSquares(ArrayList<Square> s){
        squares = s;
    }
}
