import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

class Main{
    public static double acceleration = 0.0;
    public static void main(String[] args){
        JFrame frame = new JFrame("Flappy Bird");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight()-100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(173, 216, 230));

        frame.setLayout(null);

        JLabel bird = new JLabel(new ImageIcon("birdImage1.png"));
        bird.setSize(bird.getPreferredSize());
        frame.add(bird);

        bird.setLocation(10, (frame.getHeight()-100)/2);

        frame.setVisible(true);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                acceleration = -5.0;
            }
        };

        JLabel[] topPipes = new JLabel[4];
        JLabel[] bottomPipes = new JLabel[4];

        for (int i = 0; i < topPipes.length; i++){
            topPipes[i] = new JLabel(new ImageIcon("Pipe.png"));
            topPipes[i].setSize(topPipes[i].getPreferredSize());
            topPipes[i].setLocation(frame.getWidth()+(i*(frame.getWidth()/(topPipes.length-1))), (int)((Math.random()+.1)*frame.getHeight()/2 - (topPipes[0].getHeight() - 150)));
            frame.add(topPipes[i]);

            bottomPipes[i] = new JLabel(new ImageIcon("bottomPipe.png"));
            bottomPipes[i].setSize(bottomPipes[i].getPreferredSize());
            bottomPipes[i].setLocation(topPipes[i].getX(), topPipes[i].getY()+topPipes[i].getHeight()+(bird.getHeight()*4)-50);
            frame.add(bottomPipes[i]);
        }


        long target = 1000/60;

        while (bird.getY() < frame.getHeight()) {
            long start = System.currentTimeMillis();

            for (int i = 0; i < topPipes.length; i++) {
                JLabel pipe = topPipes[i];
                pipe.setLocation(pipe.getX() - 2, pipe.getY());
                if (pipe.getX() < -pipe.getWidth()) {
                    pipe.setLocation(frame.getWidth() + (frame.getWidth() / (topPipes.length - 1)) - pipe.getWidth(), (int) (Math.random() * frame.getHeight() / 2 - (topPipes[0].getHeight() - 250)));
                }
                bottomPipes[i].setLocation(pipe.getX(), topPipes[i].getY()+topPipes[i].getHeight()+(bird.getHeight()*4)-50);
            }

            frame.getContentPane().addMouseListener(mouseAdapter);

            bird.setLocation(10, (int) (bird.getY() + acceleration));
            if (bird.getY() < 0) {
                bird.setLocation(10, 0);
                acceleration = 0.0;
            }
            acceleration = acceleration + .1;

            if (acceleration > 3.0){
                acceleration = 3.0;
            }

            long end = System.currentTimeMillis();

                if (target - (end-start) > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(target - (end-start));
                    }
                    catch (InterruptedException e){
                        break;
                    }
                }
        }
    }
}