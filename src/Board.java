import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Board {
    private static Square[][] squares;
    JFrame frame = new JFrame("Chess");
    JLayeredPane layeredPane = new JLayeredPane();
    private ArrayList<Square> s = new ArrayList<>();
    private Piece piece;
    private Square squ;
    private String turn = "white";


    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int y = e.getX() / squares[0][0].getSize();
            int x = e.getY() / squares[0][0].getSize();

            move(x, y);

            try {
                piece = squares[x][y].getPiece();
                squ = squares[x][y];
            }
            catch (NullPointerException _){}

            getMoves(x,y);
        }
    };

    public Board(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(100,100,100));
        frame.setLayout(null);

        layeredPane.setPreferredSize(frame.getPreferredSize());
        layeredPane.setLayout(null);

        squares = new Square[8][8];
        for (int i = 0; i < squares.length; i++){
            for (int j = 0; j < squares[i].length; j++){
                if ((i%2==0 && !(j%2==0)) || (!(i%2==0) && j%2==0)){
                    squares[i][j] = new Square("black", frame.getHeight()/8, new Point(j,i));
                }
                else squares[i][j] = new Square("white", frame.getHeight()/8, new Point(j,i));

                JLabel label = new JLabel(squares[i][j].getImage());
                label.setSize(label.getPreferredSize());
                label.setLocation(i*label.getWidth(), j*label.getHeight());
                frame.add(label);
            }
        }
        setStartingPosition();
        frame.setVisible(true);
        frame.getContentPane().addMouseListener(mouseAdapter);
    }

    public Square getSquare(int x, int y){
        return squares[x][y];
    }

    public void setStartingPosition(){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                Square square = setStartingSquare(i, j);

                try {
                    JLabel label = square.getPiece().getImage();
                    label.setLocation(j*square.getSize()+(square.getSize()-label.getWidth())/2, i*square.getSize()+(square.getSize()-label.getHeight())/2);
                    label.setSize(label.getPreferredSize());
                    frame.getLayeredPane().add(label, Integer.valueOf(5));
                }
                catch (NullPointerException e){
                    continue;
                }
            }
        }
    }

    private static Square setStartingSquare(int i, int j) {
        Square square = squares[i][j];
        if (i == 0) {
            if (j == 0 || j == 7)
                square.setPiece(new Rook("black", new Point(j,i)));
            else if (j == 1 || j == 6)
                square.setPiece(new Knight("black", new Point(j,i)));
            else if (j == 2 || j == 5)
                square.setPiece(new Bishop("black", new Point(j,i)));
            else if (j == 3)
                square.setPiece(new Queen("black", new Point(j,i)));
            else if (j == 4)
                square.setPiece(new King("black", new Point(j,i)));
        }
        else if (i == 1)
            square.setPiece(new Pawn("black", new Point(j,i)));

        else if (i == 7){
            if (j == 0 || j == 7)
                square.setPiece(new Rook("white", new Point(j,i)));
            else if (j == 1 || j == 6)
                square.setPiece(new Knight("white", new Point(j,i)));
            else if (j == 2 || j == 5)
                square.setPiece(new Bishop("white", new Point(j,i)));
            else if (j == 3)
                square.setPiece(new Queen("white", new Point(j,i)));
            else if (j == 4)
                square.setPiece(new King("white", new Point(j,i)));
        }
        else if (i == 6)
            square.setPiece(new Pawn("white", new Point(j,i)));
        return square;
    }

    ArrayList<JLabel> jLabels = new ArrayList<>();

    public void addToSquare(ArrayList<Square> squares1) {
        for (Square square : squares1) {
            JLabel label;
            if (square.getPiece() != null){
                label = new JLabel(new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/capturePiece.png"));
            }
            else{
                label = new JLabel(new ImageIcon("/home/benf/IdeaProjects/TestGame/Images/moveTo.png"));
            }
            jLabels.add(label);
            label.setSize(label.getPreferredSize());
            label.setLocation(square.getX() * square.getSize() + (square.getSize() - label.getWidth()) / 2, square.getY() * square.getSize() + (square.getSize() - label.getHeight()) / 2);
            frame.getLayeredPane().add(label, Integer.valueOf(0));
            square.setCanMoveTo(true);
        }
        frame.revalidate();
    }

    public void getMoves(int x, int y){
        while(!s.isEmpty()){
            jLabels.getFirst().setIcon(null);
            jLabels.removeFirst();
            s.getFirst().setCanMoveTo(false);
            s.removeFirst();
        }
        frame.revalidate();

        if (squares[x][y].getPiece()!= null){
            if (squares[x][y].getPiece().getColor().equals(turn)) {
                s = getSquare(x, y).getPiece().findSquares(squares);
            }
        }
        addToSquare(s);
    }

    public void move(int x, int y){
        Square square = squares[x][y];
        if (square.getCanMoveTo()){
            System.out.println(square.getLocation());
            if (piece != null){
                if (square.getPiece() != null){
                    square.getPiece().getImage().setIcon(null);
                    frame.revalidate();
                }
                JLabel label = piece.getImage();
                label.setLocation(y * square.getSize() + (square.getSize() - label.getWidth()) / 2, x * square.getSize() + (square.getSize() - label.getHeight()) / 2);

                if (piece.getClass().equals(King.class)){
                    if (piece.getX() + 2 == y){
                        squ.setPiece(null);
                        square.setPiece(piece);
                        piece.setX(square.getLocation().x);

                        piece = squares[square.getY()][square.getX()+1].getPiece();
                        squ = squares[square.getY()][square.getX()+1];
                        move(x, y-1);

                        return;
                    }
                    else if (piece.getX() - 2 == y){
                        squ.setPiece(null);
                        square.setPiece(piece);
                        piece.setX(square.getLocation().x);

                        piece = squares[square.getY()][square.getX()-2].getPiece();
                        squ = squares[square.getY()][square.getX()-2];
                        move(x, y+1);

                        return;
                    }
                }

                squ.setPiece(null);
                square.setPiece(piece);
                piece.setX(square.getLocation().x);
                piece.setY(square.getLocation().y);

                if(piece.getClass().equals(Pawn.class)){
                    if (piece.getColor().equals("black") && piece.getY() == 5){
                        if (squares[4][piece.getX()].getPiece() != null && squares[4][piece.getX()].getPiece().isCanEnPassent()){
                            squares[4][piece.getX()].getPiece().getImage().setIcon(null);
                            squares[4][piece.getX()].setPiece(null);

                        }
                    }
                    else if (piece.getColor().equals("white") && piece.getY() == 2){
                        System.out.println(squares[3][piece.getX()].getLocation());
                        if (squares[3][piece.getX()].getPiece() != null && squares[3][piece.getX()].getPiece().isCanEnPassent()){
                            squares[3][piece.getX()].getPiece().getImage().setIcon(null);
                            squares[3][piece.getX()].setPiece(null);

                        }
                    }
                }
                for (int i = 0; i < 8; i++){
                    for (int j = 0; j < 8; j++){
                        if (squares[i][j].getPiece() != null && squares[i][j].getPiece().getClass().equals(Pawn.class)){
                            if (squares[i][j].getPiece().isCanEnPassent() && squares[i][j].getPiece().getHasNotMove()){
                                squares[i][j].getPiece().setCanEnPassent(false);
                                squares[i][j].getPiece().setHasNotMove(false);
                            }
                        }
                    }
                }

                Class<? extends Piece> c = piece.getClass();

                if (c.equals(King.class) || c.equals(Rook.class)){
                    piece.setHasNotMove(false);
                }
                if (c.equals(Pawn.class)){
                    if (piece.getY() == 3 || piece.getY() == 4){
                        piece.setCanEnPassent(piece.getHasNotMove());
                    }
                    else piece.setHasNotMove(false);
                }
            }
            if (turn.equals("white")){
                turn = "black";
            }
            else if (turn.equals("black")){
                turn = "white";
            }
        }
        frame.revalidate();
    }
}
