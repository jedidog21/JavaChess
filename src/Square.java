import javax.swing.*;
import java.awt.*;

public class Square {
    private final String color;
    private ImageIcon image;
    private Piece piece = null;
    private int size = 0;
    private boolean canMoveTo = false;
    private Point location;

    public Square(String color, int size, Point location){
        this.color = color;
        this.size = size;
        this.location = location;
        setImage();
    }
    public Square(String color, int size, Piece piece){
        this.color = color;
        this.size = size;
        this.piece = piece;
        setImage();
    }

    public String getColor(){
        return color;
    }
    public Piece getPiece(){
        return piece;
    }
    public int getSize(){
        return size;
    }
    public ImageIcon getImage(){
        return image;
    }
    public boolean getCanMoveTo(){
        return canMoveTo;
    }
    public int getX(){
        return location.x;
    }
    public int getY(){
        return location.y;
    }
    public Point getLocation(){
        return location;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public void removePiece(){
        piece = null;
    }

    public void setImage(){
        if (color.equals("white")){
            image = new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/whiteSquare.png");
        }
        else if (color.equals("black")){
            image = new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/blackSquare.png");
        }
    }

    public void setImage(ImageIcon image){
        this.image = image;
    }

    public void setCanMoveTo(boolean bool){
        canMoveTo = bool;
    }
}
