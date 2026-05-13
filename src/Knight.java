import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece{
    private JLabel image;
    private String color;
    ArrayList<Square> squares = new ArrayList<>();

    public Knight(String color, Point location) {
        super(color, location);
        this.color = super.getColor();
        setImage();
    }

    private void setImage(){
        if (color.equals("white")){
            image = new JLabel (new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/whiteKnight.png"));
        }
        else if (color.equals("black")){
            image = new JLabel (new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/blackKnight.png"));
        }
        super.setImage(image);
    }

    public ArrayList<Square> getSquare(){
        return squares;
    }

    public String getColor(){
        return color;
    }

    public ArrayList<Square> findSquares(Square[][] s){
        squares = new ArrayList<>();


        try{
            if (s[super.getY()+1][super.getX()+2].getPiece() != null) {
                if (!s[super.getY()+1][super.getX()+2].getPiece().getColor().equals(color))
                    squares.add(s[super.getY()+1][super.getX()+2]);
            }
            else
                squares.add(s[super.getY()+1][super.getX()+2]);
        }
        catch (IndexOutOfBoundsException _){}
        try{
            if (s[super.getY()+2][super.getX()+1].getPiece() != null) {
                if (!s[super.getY()+2][super.getX()+1].getPiece().getColor().equals(color))
                    squares.add(s[super.getY()+2][super.getX()+1]);
            }
            else
                squares.add(s[super.getY()+2][super.getX()+1]);
        }
        catch (IndexOutOfBoundsException _){}

        try{
            if (s[super.getY()+1][super.getX()-2].getPiece() != null) {
                if (!s[super.getY()+1][super.getX()-2].getPiece().getColor().equals(color))
                    squares.add(s[super.getY()+1][super.getX()-2]);
            }
            else
                squares.add(s[super.getY()+1][super.getX()-2]);
        }
        catch (IndexOutOfBoundsException _){}
        try{
            if (s[super.getY()+2][super.getX()-1].getPiece() != null) {
                if (!s[super.getY()+2][super.getX()-1].getPiece().getColor().equals(color))
                    squares.add(s[super.getY()+2][super.getX()-1]);
            }
            else
                squares.add(s[super.getY()+2][super.getX()-1]);
        }
        catch (IndexOutOfBoundsException _){}

        try{
            if (s[super.getY()-1][super.getX()-2].getPiece() != null) {
                if (!s[super.getY()-1][super.getX()-2].getPiece().getColor().equals(color))
                    squares.add(s[super.getY()-1][super.getX()-2]);
            }
            else
                squares.add(s[super.getY()-1][super.getX()-2]);
        }
        catch (IndexOutOfBoundsException _){}
        try{
            if (s[super.getY()-2][super.getX()-1].getPiece() != null) {
                if (!s[super.getY()-2][super.getX()-1].getPiece().getColor().equals(color))
                    squares.add(s[super.getY()-2][super.getX()-1]);
            }
            else
                squares.add(s[super.getY()-2][super.getX()-1]);
        }
        catch (IndexOutOfBoundsException _){}

        try{
            if (s[super.getY()-1][super.getX()+2].getPiece() != null) {
                if (!s[super.getY()-1][super.getX()+2].getPiece().getColor().equals(color))
                    squares.add(s[super.getY()-1][super.getX()+2]);
            }
            else
                squares.add(s[super.getY()-1][super.getX()+2]);
        }
        catch (IndexOutOfBoundsException _){}
        try {
            if (s[super.getY() - 2][super.getX() + 1].getPiece() != null) {
                if (!s[super.getY() - 2][super.getX() + 1].getPiece().getColor().equals(color))
                    squares.add(s[super.getY() - 2][super.getX() + 1]);
            }
            else
                squares.add(s[super.getY() - 2][super.getX() + 1]);
        }
        catch (IndexOutOfBoundsException _){}

        super.setSquares(squares);
        return squares;
    }
}
