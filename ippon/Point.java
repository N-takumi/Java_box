
package ippon;

import java.awt.*;

public class Point {
    
   public String pi;
    
   public static int SIZE = 180;//ポイント円のサイズ
   
   public boolean Pflag = (false);
    
   public void draw(Graphics g){//ポイントの描画処理
       
    Font font = new Font("Banco", Font.ITALIC, 150);
    
    //外枠
    g.setColor(Color.black);
    g.drawOval(1000, 500, SIZE, SIZE);
    
    //円本体
    g.setColor(Color.orange);
    g.fillOval(1000, 500, SIZE, SIZE);
    
    //フォント
    g.setColor(Color.black);
    g.setFont(font);
    g.drawString(pi,1045,650);//ポイント自体
    
    }
    
}
