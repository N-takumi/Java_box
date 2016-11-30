
package ippon;

import java.awt.*;

public class Efect {
    
    public String efect = "IPPON!";
    public boolean Ippon = false;
    
    public int x = 1050;
    
    
    public void draw(Graphics g){
        
        Font font = new Font("Banco", Font.ITALIC, 180);
        
        g.setColor(Color.orange);
        g.setFont(font);
        g.drawString(efect,x,670);
        g.setColor(Color.black);
        g.drawString(efect,x+5,675);
      
    }
    
}
