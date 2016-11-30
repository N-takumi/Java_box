
package ippon;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;   

public class MainPanel extends JPanel implements Runnable,KeyListener{
    
    //パネルサイズ
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 756;
    
    //IPPON一回を再生したかどうか
    public boolean ippsone = false;
   
    
    //ボックスオブジェクト
    private Box box;
    
    //ポイントオブジェクト
    private Point point;
    
    //エフェクトオブジェクト
    private Efect efect;
    
    public int BoxLevel = 0;
    
    //アニメーション用スレッド
    private Thread thread = null;
    
    //プログラム終了用変数
    private int exit = 0;
    
    private AudioClip ippon;
    private AudioClip kettei;
    private AudioClip s0;
    private AudioClip s1;
    private AudioClip s2;
    private AudioClip s3;
    private AudioClip s4;
    private AudioClip s5;
    private AudioClip s6;
    private AudioClip s7;
    private AudioClip s8;
    private AudioClip s9;
    
   
    public MainPanel(){
        
        //パネルの推奨サイズを設定。pack()でいる。
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setSize(WIDTH,HEIGHT);
        
        
        //パネルがキーボードを受け付けるようにする
        this.setFocusable(true);
        addKeyListener(this);
        
        
        //ボックスの作成
        box = new Box();
        
        //ポイントの作成
        point = new Point();
        
        //エフェクトの作成
        efect = new Efect();
        
        ippon = Applet.newAudioClip(getClass().getResource("sound/ippon2.wav"));
        kettei = Applet.newAudioClip(getClass().getResource("sound/kettei.wav"));
        s0 = Applet.newAudioClip(getClass().getResource("sound/s0.wav"));
        s1 = Applet.newAudioClip(getClass().getResource("sound/s1.wav"));
        s2 = Applet.newAudioClip(getClass().getResource("sound/s2.wav"));
        s3 = Applet.newAudioClip(getClass().getResource("sound/s3.wav"));
        s4 = Applet.newAudioClip(getClass().getResource("sound/s4.wav"));
        s5 = Applet.newAudioClip(getClass().getResource("sound/s5.wav"));
        s6 = Applet.newAudioClip(getClass().getResource("sound/s6.wav"));
        s7 = Applet.newAudioClip(getClass().getResource("sound/s7.wav"));
        s8 = Applet.newAudioClip(getClass().getResource("sound/s8.wav"));
        s9 = Applet.newAudioClip(getClass().getResource("sound/s9.wav"));  
    }
    
    //初期化
    public void init(){
        
        thread = new Thread(this);
        thread.start();
        
        
    }
    
    //メインループ
    public void run(){
        //プログラム終了までフレーム処理を繰り返す
        
        
        
        while(exit == 0){
            
                
            
            //100ミリ秒 休止させる
            try{
                Thread.sleep(40);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            repaint();
        }
       
      
        
        
    }

    //再描画時に画面をクリアしない
    public void update(Graphics g){
    paintComponent(g);
    }
    
    //描画処理
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //ボックス描画 //
        if(BoxLevel != 0 ){
                box.draw(g);
               
                
                if(point.Pflag == true){
                point.draw(g);
                }
                
                if(efect.Ippon == true){
                efect.draw(g);
                efect.x -= 8;
                if(efect.x == -100){
                    efect.Ippon = false;
                }
                }
        }
        repaint();
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_SPACE:
                 if(BoxLevel <= 9){
                 BoxLevel++;
                 Box.BoxNum += 1;
                 switch(box.BoxNum){
                     case 1:
                     s0.play();
                     break;
                     case 2:
                     s0.stop();
                     s1.play();
                     break;
                     case 3:
                     s1.stop();
                     s2.play();
                     break;
                     case 4:
                     s2.stop();
                     s3.play();
                     break;
                     case 5:
                     s3.stop();
                     s4.play();
                     break;
                     case 6:
                     s4.stop();
                     s5.play();
                     break;
                     case 7:
                     s5.stop();
                     s6.play();
                     break;
                     case 8:
                     s6.stop();
                     s7.play();
                     break;
                     case 9:
                     s7.stop();
                     s8.play();
                     break;
                     case 10:
                     s8.stop();
                     s9.play();
                     break;
                 }
                 }else if(BoxLevel == 10){
                 efect.Ippon = true;
                 box.color = Color.RED;
                 box.bcolor = Color.WHITE;
                 if(ippsone == false){
                 s9.stop();
                 ippon.play(); 
                 ippsone = true;
                 }
                 
                 }
                 break;
            case KeyEvent.VK_Z://リセット
                 BoxLevel = 0;
                 Box.BoxNum = 0;
                 point.Pflag = false;
                 efect.Ippon = false;
                 ippsone = false;
                 efect.x = 1050;
                 box.color = Color.YELLOW;
                 box.bcolor = Color.BLACK;
                 break;
            case KeyEvent.VK_ENTER://点数確定
                 if(BoxLevel != 0  && BoxLevel != 10){
                 point.Pflag= true;
                 point.pi = String.valueOf(BoxLevel);
                 kettei.play();
                 }
                 break;
            case KeyEvent.VK_ESCAPE:
                 exit += 1;
        }
        repaint();
    }
    
    public void keyReleased(KeyEvent e){
        
    }
    
    public void keyTyped(KeyEvent e){
    }
    
    static void sleep(Thread th,long t){
    try{//InterruptedException 例外処理が必要
      th.sleep(t);//時間待ち
    }catch(InterruptedException e){
   
    }
  }
    
    
}
