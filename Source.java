
import javax.swing.JFrame;


public class Source
{
    public static void main(String args[])
    {
        JFrame obj=new JFrame();
        Gameplay engine=new Gameplay();
        obj.setBounds(400,64,726,726);
        obj.setTitle("BrickBreaker");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(engine);
    }

}