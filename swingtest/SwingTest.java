
package swingtest;

import java.awt.*;
import javax.swing.*;
import swingtest.MainPanel;

public class SwingTest extends JFrame {
    public SwingTest(){
        //タイトル設定
        setTitle("ManyBalls");
        
        //サイズ変更禁止
        setResizable(false);
        
        //パネルの作成及びフレームに追加
        MainPanel panel = new MainPanel();
        add(panel);
        
        //パネル背景色設定
        panel.setBackground(Color.BLACK);
        
        //パネルサイズに合わせてフレームサイズを自動設定
        pack();
    }
    
    public static void main(String[] args) {
       SwingTest frame =  new SwingTest();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
    }
}

