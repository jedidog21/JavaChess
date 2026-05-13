import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece{
    private JLabel image;
    private String color;
    ArrayList<Square> squares = new ArrayList<>();

    public Pawn(String color, Point location) {
        super(color, location);
        this.color = super.getColor();
        setImage();
    }

    private void setImage(){
        if (color.equals("white")){
            image = new JLabel(new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/whitePawn.png"));
        }
        else if (color.equals("black")){
            image = new JLabel(new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/blackPawn.png"));
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
            int y = super.getY();
            int x = super.getX();
            if (color.equals("black")) {
                if (s[y + 1][x].getPiece() == null) {
                    squares.add(s[y + 1][x]);
                    if (y == 1 && s[y + 2][x].getPiece() == null)
                        squares.add(s[y + 2][x]);
                }
                if (s[y + 1][x+1].getPiece() != null) {
                    if (!s[y + 1][x + 1].getPiece().getColor().equals(color)) {
                        squares.add(s[y + 1][x + 1]);
                    }
                }
                if (s[y + 1][x-1].getPiece() != null) {
                    if (!s[y + 1][x - 1].getPiece().getColor().equals(color)) {
                        squares.add(s[y + 1][x - 1]);
                    }
                }

                //en passant
                if (s[y][x+1].getPiece() != null){
                    if (s[y][x+1].getPiece().getClass() == Pawn.class && s[y][x+1].getPiece().getLastMoved() == 1){
                        squares.add(s[y+1][x+1]);
                    }
                }

                if (s[y][x-1].getPiece() != null){
                    if (s[y][x-1].getPiece().getClass() == Pawn.class && s[y][x-1].getPiece().getLastMoved() == 1){
                        squares.add(s[y+1][x-1]);
                    }
                }
            }
            else if (color.equals("white")){
                if (s[y - 1][x].getPiece() == null) {
                squares.add(s[y - 1][x]);
                if (y == 6 && s[y - 2][x].getPiece() == null)
                    squares.add(s[y - 2][x]);
                }
                if (s[y - 1][x+1].getPiece() != null) {
                    if (!s[y - 1][x + 1].getPiece().getColor().equals(color)) {
                        squares.add(s[y - 1][x + 1]);
                    }
                }
                if (s[y - 1][x-1].getPiece() != null) {
                    if (!s[y - 1][x - 1].getPiece().getColor().equals(color)) {
                        squares.add(s[y - 1][x - 1]);
                    }
                }

                //en passant
                if (s[y][x+1].getPiece() != null){
                    if (s[y][x+1].getPiece().getClass() == Pawn.class && s[y][x+1].getPiece().isCanEnPassent()){
                        squares.add(s[y-1][x+1]);
                    }
                }

                if (s[y][x-1].getPiece() != null){
                    if (s[y][x-1].getPiece().getClass() == Pawn.class && s[y][x-1].getPiece().isCanEnPassent()){
                        squares.add(s[y-1][x-1]);
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException _){}

        super.setSquares(squares);
        return squares;
    }
}
