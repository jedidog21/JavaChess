import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece{
    private JLabel image;
    private String color;
    ArrayList<Square> squares = new ArrayList<>();

    public Queen(String color, Point location) {
        super(color, location);
        this.color = super.getColor();
        setImage();
    }

    private void setImage(){
        if (color.equals("white")){
            image = new JLabel(new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/whiteQueen.png"));
        }
        else if (color.equals("black")){
            image = new JLabel(new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/blackQueen.png"));
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

        for (int i = 1; i < 7; i++){
            try{
                if (s[super.getY()][super.getX() + i].getPiece() != null) {
                    if (!s[super.getY()][super.getX() + i].getPiece().getColor().equals(color))
                        squares.add(s[super.getY()][super.getX()+i]);
                    break;
                }
                squares.add(s[super.getY()][super.getX()+i]);
            }
            catch (IndexOutOfBoundsException _){break;}
        }

        for (int i = 1; i < 7; i++){
            try{
                if (s[super.getY()][super.getX() - i].getPiece() != null) {
                    if (!s[super.getY()][super.getX() - i].getPiece().getColor().equals(color))
                        squares.add(s[super.getY()][super.getX()-i]);
                    break;
                }
                squares.add(s[super.getY()][super.getX()-i]);
            }
            catch (IndexOutOfBoundsException _){break;}
        }

        for (int i = 1; i < 7; i++){
            try{
                if (s[super.getY() + i][super.getX()].getPiece() != null) {
                    if (!s[super.getY()+i][super.getX()].getPiece().getColor().equals(color))
                        squares.add(s[super.getY()+i][super.getX()]);
                    break;
                }
                squares.add(s[super.getY()+i][super.getX()]);
            }
            catch (IndexOutOfBoundsException _){break;}
        }

        for (int i = 1; i < 7; i++){
            try{
                if (s[super.getY() - i][super.getX()].getPiece() != null) {
                    if (!s[super.getY()-i][super.getX()].getPiece().getColor().equals(color))
                        squares.add(s[super.getY()-i][super.getX()]);
                    break;
                }
                squares.add(s[super.getY()-i][super.getX()]);
            }
            catch (IndexOutOfBoundsException _){break;}
        }



        for (int i = 1; i < 7; i++){
            try{
                if (s[super.getY()+i][super.getX() + i].getPiece() != null) {
                    if (!s[super.getY()+i][super.getX() + i].getPiece().getColor().equals(color))
                        squares.add(s[super.getY()+i][super.getX()+i]);
                    break;
                }
                squares.add(s[super.getY()+i][super.getX()+i]);
            }
            catch (IndexOutOfBoundsException _){break;}
        }

        for (int i = 1; i < 7; i++){
            try{
                if (s[super.getY()-i][super.getX() - i].getPiece() != null) {
                    if (!s[super.getY()-i][super.getX() - i].getPiece().getColor().equals(color))
                        squares.add(s[super.getY()-i][super.getX()-i]);
                    break;
                }
                squares.add(s[super.getY()-i][super.getX()-i]);
            }
            catch (IndexOutOfBoundsException _){break;}
        }

        for (int i = 1; i < 7; i++){
            try{
                if (s[super.getY() + i][super.getX()-i].getPiece() != null) {
                    if (!s[super.getY()+i][super.getX()-i].getPiece().getColor().equals(color))
                        squares.add(s[super.getY()+i][super.getX()-i]);
                    break;
                }
                squares.add(s[super.getY()+i][super.getX()-i]);
            }
            catch (IndexOutOfBoundsException _){break;}
        }

        for (int i = 1; i < 7; i++){
            try{
                if (s[super.getY() - i][super.getX()+i].getPiece() != null) {
                    if (!s[super.getY()-i][super.getX()+i].getPiece().getColor().equals(color))
                        squares.add(s[super.getY()-i][super.getX()+i]);
                    break;
                }
                squares.add(s[super.getY()-i][super.getX()+i]);
            }
            catch (IndexOutOfBoundsException _){break;}
        }


        super.setSquares(squares);
        return squares;
    }
}
