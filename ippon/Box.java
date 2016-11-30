
package ippon;

import java.awt.*;

public class Box {
    
    public static String point;
    
    public static int SIZE = 76;//1ボックスのサイズ(高さ)
    public static int BoxNum = 0;//(ボックスの占領域)パネル高さ - ボックスの高さ
    
    public Color color = Color.YELLOW;
    public Color bcolor = Color.BLACK;
    
    public void draw(Graphics g){//描画処理
        int i;
        for (i=0; i<=BoxNum; i++) {
         g.setColor(color);
         g.fillRect(0,MainPanel.HEIGHT-i*SIZE,MainPanel.WIDTH,SIZE);
         g.setColor(bcolor);
         g.drawRect(0,MainPanel.HEIGHT-i*SIZE,MainPanel.WIDTH,SIZE);
         point = String.valueOf(BoxNum);
         g.drawString(point, 1,750);
        }
    }
}
