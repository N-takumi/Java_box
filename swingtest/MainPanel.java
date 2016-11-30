
package swingtest;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;

public class MainPanel extends JPanel implements Runnable{
    
    //パネルサイズ
    public static final int WIDTH = 700;
    public static final int HEIGHT =500;
    
    //ボールの数
    private static final int NUM_BALL = 10;
    
    //ボールを格納する配列
    private Ball[] ball;
    
    //アニメーション用スレッド
    private Thread thread;
    
    //乱数生成器
    private static final Random rand = new Random();
    
    public MainPanel(){
        //パネルの推奨サイズを設定。pack()でいる。
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setSize(WIDTH,HEIGHT);
        
        //ボールを格納する配列を作成
        ball = new Ball[NUM_BALL];
        
        //ボールを作成
        for(int i = 0;i<NUM_BALL;i++){
            ball[i] = new Ball(rand.nextInt(100),rand.nextInt(100),rand.nextInt(10),rand.nextInt(10));
        }
        
        //スレッドを起動
        thread = new Thread(this);
        thread.start();
    }
    
    public void update(Graphics g){
    paintComponent(g);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //各ボールを描画
        for(int i = 0;i< NUM_BALL;i++){
            ball[i].draw(g);
        }
    }
    //メインループ
    public void run(){
        //プログラムが終了するまでフレーム処理を繰り返す
        while(true){
            
            //各ボールを速度分だけ移動する
            for(int i = 0;i < NUM_BALL;i++){
                ball[i].Move();
            }
            
            //ボールを再描画する
            repaint();
            
            //20ミリ秒 休止させる
            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    //ボールの数のgeter
    public static int getBallNum(){
        return NUM_BALL;
    }
    
}
