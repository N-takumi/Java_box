
package ippon;

import java.awt.*;
import javax.swing.*;
import ippon.MainPanel;
import javax.sound.midi.*;


public class IPPON extends JFrame {
    
    public IPPON(){
        
        setTitle("IPPON GRAND PRIX in KINDAI");//タイトル
        setResizable(false);//画面サイズ変更禁止
        
        MainPanel panel = new MainPanel();
        
        add(panel);
        
        //パネル背景色設定
        panel.setBackground(Color.black);
        
        //パネルサイズに合わせてフレームサイズを自動設定
        pack() ;
    }

    public static void main(String[] args) {
        
     
        IPPON frame =  new IPPON();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        MidiChannel channel = null;
        
    
    
    }
    
}
