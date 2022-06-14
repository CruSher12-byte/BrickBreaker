
import java.awt.Graphics2D;
import java.awt.Color;


public class BrickMap
{
    public int map[][];
    public int brickwidth;
    public int brickheight;
    public BrickMap(int row,int column)
    {
        map=new int[row][column];
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                map[i][j]=1;
            }
        }    
        brickwidth=90;
        brickheight=40;
    }
    public void draw(Graphics2D d)
    {
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                if(map[i][j]>0)
                {
                    d.setColor(Color.blue);
                    d.fillRect(j*brickwidth + 80,i*brickheight + 50,brickwidth,brickheight);
                    d.setColor(Color.black);
                    d.drawRect(j*brickwidth + 80,i*brickheight + 50,brickwidth,brickheight);

                }
            }
        }
    }
    public void setbrickvalue(int value,int row,int column)
    {
        map[row][column]=value;
    }
}