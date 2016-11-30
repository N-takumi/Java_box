
package swingtest;
import java.awt.*;
import java.applet.*;
import java.util.*;

public class Ball {
    
    //サウンド (跳ね返り用）
    private AudioClip pong;
    private AudioClip pong2;
    //ボールの大きさ
    private static final  int SIZE = 100;
    //ボールの位置 (x,y) 円の中心の座標
    private int x,y;
    //ボールの速度(vx,vy)
    private int vx,vy;
    
    private Color color;
    
    //乱数生成器
    private static final Random rand = new Random();
    
    //コンストラクタ
    public Ball(int x,int y,int vx,int vy){
        //ボールの属性を設定
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        
        pong = Applet.newAudioClip(getClass().getResource("pong.wav"));
        pong2 = Applet.newAudioClip(getClass().getResource("pong2.wav"));
    }
    
    //ボールの動き
    public void Move(){
        //ボールを速度分だけ移動
        x += vx;
        y += vy;
        
        //ランダムで色を変える
        if(rand.nextInt(10) == 1){
            ChangeColor();
        }
        
        if(rand.nextInt(MainPanel.getBallNum()) == 1){
            
            
        }
        
        
        //左、または右に当たった時に、x軸方向の速度の符号を反転させる
        if(x < 0 || x > MainPanel.WIDTH - SIZE){
            
            
            if(rand.nextInt(30)== 1){
                pong2.play();
            }else{
                pong.play();
            }
            
            vx = -vx;
        }
        //上、または下に当たった時に、y軸方向の速度の符号を反転させる
        if(y < 0 || y > MainPanel.HEIGHT - SIZE){
            
            // 1/30の確率でキラン！音
            if(rand.nextInt(30) == 1){
                pong2.play();
            }else{
                pong.play();
            }
            
            vy = -vy;
        }
    }
    
    //ランダムで色を変える
    public void ChangeColor(){
        int red = rand.nextInt(256);
        int blue = rand.nextInt(256);
        int green = rand.nextInt(256);
        
        color = new Color(red,green,blue);
    }
    
    
    //描画処理
    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);
    }
}
