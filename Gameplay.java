
import javax.swing.JPanel;
import javax.swing.Timer;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Rectangle;


public class Gameplay extends JPanel implements KeyListener,ActionListener
{
    private boolean play=false;
    private int score=0;
    private Timer timer;
    private int delay=4;
    private int playerX=320;
    private int ballposX=330;
    private int ballposY=580;
    private int balldirX=-2;
    private int balldirY=-4;
    private BrickMap bricks;
    public Gameplay()
    {
        bricks = new BrickMap(6,6);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer =new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics d)
    {
        //background
        d.setColor(Color.black);
        d.fillRect(0,0,726,726);
        //painting bricks
        bricks.draw((Graphics2D)d);
        //borders
        d.setColor(Color.yellow);
        d.fillRect(0,0,3,726);
        d.fillRect(0,0,726,3);
        d.fillRect(710,0,3,726);
        d.fillRect(0,686,726,3);
        //scores
        d.setColor(Color.pink);
        d.setFont(new Font("monospace",Font.BOLD,30));
        d.drawString(" Score==> "+score,500,600);
        //paddle
        d.setColor(Color.green);
        //330 is centre
        d.fillRect(playerX,640,70,10);
        //ball
        d.setColor(Color.red);
        d.fillOval(ballposX,ballposY,18,18);
        if(score==360)
        {
            
            play=false;
            balldirX=0;
            balldirY=0;
            d.setFont(new Font("monospace",Font.BOLD,50));
            d.drawString("Congratulations",170,300);
            d.drawString("You won the game ",150,360);
            d.drawString("Press Enter to Restart",100,420);

        }
        if(ballposY>700)
        {
            play=false;
            balldirX=0;
            balldirY=0;
            d.setFont(new Font("monospace",Font.BOLD,50));
            d.drawString("Oops Game Over!",160,300);
            d.drawString("Score is "+score,230,360);
            d.drawString("Press Enter to Restart",100,420);
        }
        d.dispose();
    }
    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if(play)
        {
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,640,60,10)))
            {
                balldirY=-balldirY;
            }
            A:for(int i=0;i<bricks.map.length;i++)
            {
                for(int j=0;j<bricks.map[0].length;j++)
                {
                    if(bricks.map[i][j]>0)
                    {
                        int brickX=j*bricks.brickwidth+80;
                        int brickY=i*bricks.brickheight+50;
                        int brickwidth=bricks.brickwidth;
                        int brickheight=bricks.brickheight;
                        Rectangle brickrect=new Rectangle(brickX,brickY,brickwidth,brickheight);
                        Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
                        if(ballrect.intersects(brickrect))
                        {
                            bricks.setbrickvalue(0,i,j);
                            score+=10;
                            if(ballposX + 19<=brickrect.x||ballposX + 1>=brickrect.x + brickrect.width)
                            {
                                balldirX=-balldirX;
                            }
                            else
                            {
                                balldirY=-balldirY;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposX=ballposX+balldirX;
            ballposY=ballposY+balldirY;
            if(ballposX<0)
            {
                balldirX=-balldirX;
            }
            if(ballposY<0)
            {
                balldirY=-balldirY;
            }
            if(ballposX>690)
            {
                balldirX=-balldirX;
            }
        }
        repaint();
    }
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerX>=650)
            {
                playerX=650;
            }
            else
            {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(playerX<=3)
            {
                playerX=3;
            }
            else
            {
                moveLeft();
            }        
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!play)
            {
            play=true;
            ballposX=330;
            ballposY=580;
            balldirX=-2;
            balldirY=-4;
            playerX=320;
            score=0;
            bricks = new BrickMap(6,6);
            repaint();            
            }
        }

    }
    public void moveRight()
    {
        play=true;
        playerX+=20;
    }
    public void moveLeft()
    {
        play=true;
        playerX-=20;
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}    
}
