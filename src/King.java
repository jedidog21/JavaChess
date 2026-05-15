import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class King extends Piece{
    private JLabel image;
    private String color;
    ArrayList<Square> squares = new ArrayList<>();

    public King(String color, Point location) {
        super(color, location);
        this.color = super.getColor();
        setImage();
    }

    private void setImage(){
        if (color.equals("white")){
            image = new JLabel(new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/whiteKing.png"));
        }
        else if (color.equals("black")){
            image = new JLabel(new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/blackKing.png"));
        }
        super.setImage(image);
    }

    public ArrayList<Square> getSquare(){
        return squares;
    }

    public String getColor(){
        return color;
    }

    public ArrayList<Square> findSquares(Square[][] s) {
        squares = new ArrayList<>();

        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                try{
                    if (i == 0 && j == 0){
                        continue;
                    }
                    if (s[super.getY()+i][super.getX()+j].getPiece() != null) {
                        if (!s[super.getY()+i][super.getX()+j].getPiece().getColor().equals(color))
                            squares.add(s[super.getY()+i][super.getX()+j]);
                        continue;
                    }
                    squares.add(s[super.getY()+i][super.getX()+j]);

                    //Castling
                    if (s[super.getY()][super.getX()+1].getPiece() == null && s[super.getY()][super.getX()+2].getPiece() == null) {
                        if (s[super.getY()][super.getX() + 3].getPiece().getClass().equals(Rook.class)) {
                            if (super.getHasNotMove() && s[super.getY()][super.getX() + 3].getPiece().getHasNotMove()) {
                                squares.add(s[super.getY()][super.getX() + 2]);
                            }
                        }
                    }

                    //Long castling
                    if (s[super.getY()][super.getX()-1].getPiece() == null && s[super.getY()][super.getX()-2].getPiece() == null && s[super.getY()][super.getX()-3].getPiece() == null) {
                        if (s[super.getY()][super.getX() - 4].getPiece().getClass().equals(Rook.class)) {
                            if (super.getHasNotMove() && s[super.getY()][super.getX() - 4].getPiece().getHasNotMove()) {
                                squares.add(s[super.getY()][super.getX() - 2]);
                            }
                        }
                    }
                }
                catch (IndexOutOfBoundsException _){}
            }
        }

        super.setSquares(squares);
        return squares;
    }
}
