import java.applet.*;
import java.awt.*;
import java.awt.event.*;


/*
<applet code="ThreadTest3.class" codebase="class" width="150" height="150">
</applet>
*/

public class SampleApplet extends Applet implements Runnable,MouseListener,MouseMotionListener,KeyListener{
	
	boolean GameClear = false;
	boolean GameOver = false;
	
	int ondo = (int)(Math.random() *3);
	
	Image img[];//画像を確保するための変数imgを配列として宣言
	Image img2;

    Point pos;//マウスの座標
    Thread thread;
    int x, y;//円用の座標軸
	int ax, ay;//四角用の座標軸
	
	int flifeBox=300;//フユにゃんライフボックス用のサイズ（長さ）
	int olifeBox=250;//オラフライフボックス用のサイズ（長さ
	
	int ix[],iy[];//画像用の座標//メンバー(オラフ・弾))//配列で共有
	
	//フユニャン移動用のフラグ
	boolean fuyunFlag = false;
	//フユにゃん
	
	//オラフの画像サイズ 82 x 166
	int osizex = 82;
	int osizey = 166;
	
	//boolean Change = false;//画像操作切り替えようフラグ
	
	//タイトル表示用フラグ
	boolean TitleFlag = false;
	
    Color col;
	
	Color title = Color.yellow;
    int size = 50;
	int width = 640, height = 500;//ウィンドウの幅、高さ設定
    int threadCount = 0;
	
//	int tama_x = 500;//弾の座標x
//	int tama_y = 200;//弾の座標y
	
	boolean tama_shot_flag = false; //弾を発射したかどうかを判定するフラグ
	
	int imgNum = 0;//画像用
	
	
	//背景色
	int bred;
	int bgreen;
	int bblue;
	
	//背景色の入れ替わり用
	boolean bcol = false;
	
	//オラフのHP
	int HP; 

    //マウスがアプレットの中にあるかを示す
    boolean apenter = false;
	
	private Image BackBuffer;//バックバッファ用のImage型変数

   //

    public void init(){

     	 //マウスリスナーの登録
        addMouseListener(this);
        addMouseMotionListener(this);
		
		BackBuffer = createImage(width,height);
		
		ix = new int[3];
		iy = new int[3];
		
		
		ix[0]=200;iy[0]=300; //オラフの初期位置
		ix[1]=230;iy[1]=285;//弾の初期位置
		ix[2]=100;iy[2]=50;//フユにゃんの初期位置
		
		
		img = new Image[10];
		
		
		img[0] = getImage(getDocumentBase(),"Sample.png");//画像1
		img[1] = getImage(getDocumentBase(),"jiba.png");//画像2
		
		img2 = getImage(getDocumentBase(),"tama.png");//弾
		

		
		//キーリスナーの登録	
		addKeyListener(this); 

        //背景色の初期化
         bred = 0;
         bgreen =0; 
         bblue =0;
         col = new Color(bred, bgreen, bblue);
		 setBackground(col);

        x = 10;
        y = 10;

        col = new Color(255, 0 ,0);

        thread = new Thread(this);
        thread.start();
    }
	
	public void update(Graphics g){
	paint(g);
	}
	
    public void paint(Graphics g){
		
		Font f1 = new Font("SansSerif",Font.BOLD,15);//フォント用の変数宣言
		Font f2 = new Font("SansSerif",Font.BOLD,20);//フォント用の変数宣言
		
		Font f3 = new Font("SansSerif",Font.BOLD,25);//フォント用の変数宣言
		Font f4 = new Font("SansSerif",Font.BOLD,30);//フォント用の変数宣言
		
		
		
		Graphics gback = BackBuffer.getGraphics();
		gback.clearRect(0,0,width,height);//作りたてのバッファをクリアしておく
		
        gback.setColor(col);
        gback.drawOval(x, y, size + threadCount,size + threadCount);
		
		gback.drawRect(110,3,flifeBox,20);//オラフのライフ
		gback.drawRect(110,25,olifeBox,20);//フユにゃんのライフ
	
		gback.setFont(f1);
		
	if(olifeBox>0)gback.drawImage(img[imgNum],ix[0],iy[0],this);
	if(olifeBox>0)gback.drawImage(img2,ix[1], iy[1],this);               
	if(flifeBox>0)gback.drawImage(img[1],ix[2],iy[2],this);
		
//	gback.drawString("タマのX座標: ("+ix[1]+")",10,150);
//	gback.drawString("タマのY座標: ("+iy[1]+")",10,170);
		
	//	gback.drawString("オラフのX座標: ("+ix[0]+")",10,190);
	//	gback.drawString("オラフのY座標: ("+iy[0]+")",10,210);
		
		
	//	gback.drawString("画面の横幅は("+width+")やで！",10,130);
	//	gback.drawString("画面の高さは("+height+")やで！",10,100);
		gback.setColor(Color.white);
		
		gback.drawString("フユニャン:　"+flifeBox,15,20);//敵キャラ名
		gback.drawString("オラフ:　"+olifeBox,47,40);//プレイヤー名
		
		gback.drawString("この世界の気温:"+ondo,500,20);
	
		if(TitleFlag == true){
		gback.setFont(f1);
		gback.setColor(Color.gray);
		gback.drawString("オラゴンクエストIX",width/2-30,130);
		gback.setColor(title);
		gback.drawString("オラゴンクエストIX",width/2-30+2,132);
		gback.setColor(Color.red);
		gback.setFont(f2);
		gback.drawString("～フユニャンの逆襲～",width/2-30+40,150);
		gback.setColor(Color.blue);
		gback.drawString("～フユニャンの逆襲～",width/2-30+41,151);	
		}
		
		if(GameClear == true){
		if(GameOver == true){
		GameOver = false;
		}
		gback.setFont(f2);
		gback.setColor(Color.gray);
		gback.drawString("GAME CLEAR",width/2-30,130);
		gback.setColor(title);
		gback.drawString("GAME CLEAR",width/2-30+2,132);
		gback.setColor(Color.red);
		gback.setFont(f2);
		gback.drawString("世界は平和になった",width/2-30+40,160);
		gback.setColor(Color.blue);
		gback.drawString("世界は平和になった",width/2-30+41,161);	
		}
		
		if(GameOver == true){
		if(GameClear == true){
			GameClear = false;
		}
		gback.setFont(f2);
		gback.setColor(Color.gray);
		gback.drawString("GAME OVER",width/2-30,130);
		gback.setColor(title);
		gback.drawString("GAME OVER",width/2-30+2,132);
		gback.setColor(Color.red);
		gback.setFont(f2);
		gback.drawString("オラフは大地にかえった",width/2-30+40,160);
		gback.setColor(Color.blue);
		gback.drawString("オラフは大地にかえった",width/2-30+41,161);	
		}
		
		
		
		g.drawImage(BackBuffer,0,0,width,height,this);//BackBuffer をgの(0,0)座標に描画
		
		
		
		
		
		
		
		requestFocusInWindow();//Key入力用
	
    }



    public void run(){
		
		TitleFlag = true;
		
        while(true){
			
			//当たり判定
			if(tama_shot_flag == true){
			if(ix[1] + 51 > ix[2] && ix[1] < ix[2] + 94 &&iy[1] + 47 > ix[2] && ix[1] < ix[2] + 100){
				flifeBox -= 3;
				
				if(flifeBox <= 0){
					flifeBox = 0;
					GameClear = true;
				}
				
			}
		//	tama_shot_flag = false;
			}
			
			
			//温度によるオラフの体力減少
			if(ondo >=100&&GameClear!= true){
				if((int)(Math.random() *3) == 1){
					
				olifeBox -= (int)(Math.random() *8);
				}
			}
			
			if(olifeBox <=0){
				olifeBox = 0;
				GameOver = true;
			}
			

			
			
			if(tama_shot_flag == true){
				iy[1] -=40;
			}
			if(iy[1] <  -50){
				tama_shot_flag = false;
				ix[1] = ix[0] + 30;
				iy[1] = iy[0] - 10;
			}
			
			
		//敵の挙動
		if(ix[2] >= 500) fuyunFlag = false;
		if(fuyunFlag == false)ix[2]-= 5;
		if(ix[2] <= 0) fuyunFlag = true;
		if(fuyunFlag == true)ix[2]+= 5;
			
         //右と上を同時に押したとき
		 if(right && up && !left && !down){
		 	
		 //	ix[0] += 10;
		//	iy[0] -= 10;
				 if(tama_shot_flag == false){
		//		 ix[1] += 10;
		//		 iy[1] -= 10;
				 }
		 }
		 //右と下
		 if(right && down && !left && !up){
		 
		 //	ix[0] += 10;
		//	iy[0] += 10;
				if(tama_shot_flag == false){
		//		ix[1] += 10;
		//		iy[1] += 10;
				}
		 
		 }
		 //左と上
		 if(left && up && !right && !down){
	
		 //	ix[0] -= 10;
		//	iy[0] -= 10;	
				if(tama_shot_flag == false){
		//		ix[1] -= 10;
		//		iy[1] -= 10;
				}
			
		 }
		 //左と下
		 if(left && down && !right && !up){

		 //	ix[0] -= 10;
		//	iy[0] += 10;
					if(tama_shot_flag == false){
		//			ix[1] -= 10;
		//			iy[1] += 10;
					}
			
	
		 }
		 //右のみを押した場合
		  if(!left && !down && right && !up){

		 	ix[0] += 10;
					if(tama_shot_flag == false){
					ix[1] += 10;
					}
					
					if((int)(Math.random() *2) == 0&&GameOver != true){
					ondo += (int)(Math.random() *4);
					}

		 }
		 //左のみ
		  if(left && !down && !right && !up){

		 	ix[0] -= 10;
				if(tama_shot_flag == false){
				ix[1] -= 10;
				}
				
				if((int)(Math.random() *2) == 0&& GameOver != true){
				ondo += (int)(Math.random() *4);
				}

		 }
		 //上のみ
		  if(!left && !down && !right && up){

		//	iy[0] -= 10;
				if(tama_shot_flag == false){
			//	iy[1] -= 10;
				}
		
		 }
		 //下のみ
		  if(!left && down && !right && !up){
	
		//	iy[0] += 10;
				if(tama_shot_flag == false){
			//	iy[1] += 10;
				}

		 }
		 
		 //キャラが画面からはみ出た場合の処理
		 if(ix[0] <= 0){
		 	ix[0] = 0;
			ix[1] = 30;
		 }
		 
		  if(iy[0] <= 0){
		 	iy[0] = 0;
			iy[1] = -15;
		 }
		 
		  if(ix[0] >= (width -  osizex)){
		 	ix[0] = width - osizex;
			ix[1] = width - 55;
		 }
		  if(iy[0] + osizey >= height){
		 	iy[0] -= 10;
			iy[1] -= 10;
		 }
		 
		 

		 

			
			
          threadCount++;
          if(threadCount == 30){
            threadCount = threadCount - 15;
          }
		  
		  //背景色ランダムで徐々に変更
		  if(bcol == false){
		  bred = bred + (int)(Math.random() *2);
          bgreen =bgreen + (int)(Math.random() *2) ; 
          bblue =bblue + (int)(Math.random() *2);
          col = new Color(bred, bgreen, bblue);
		  
		  //RGBが253を超えたらbcol反転
		  if(bred == 200 || bgreen== 200 || bblue == 200){
		  	bcol = true;
		  }else if(bred == 30|| bgreen== 30|| bblue == 30){
		  	bcol = false;
		  }
		  
		  setBackground(col);
		  
		  }else if(bcol == true){
		  	bred = bred - (int)(Math.random() *2);
          bgreen =bgreen - (int)(Math.random() *2); 
          bblue =bblue - (int)(Math.random() *2);
          col = new Color(bred, bgreen, bblue);
		  
		  //RGBが255を超えたらbcol反転
		  if(bred == 200|| bgreen== 200|| bblue == 200){
		  	bcol = true;
		  }else if(bred == 30|| bgreen== 30| bblue == 30){
		  	bcol = false;
		  }
		  
		  setBackground(col);
		  }
		  
		  	

            //マウスが外の時の処理
          if(apenter == false){

            x = (int)(Math.random() * 500);
            y = (int)(Math.random() * 500);
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            col = new Color(red, green, blue);
            repaint();
              try{
            Thread.sleep(30);
            }catch(InterruptedException e){
            }

            //マウスが中の時の処理
          }else if(apenter == true){

            x = pos.x - size;
            //x = (int)(Math.random() * pos.x);
            y = pos.y - size;
            //y = (int)(Math.random() * pos.y);
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            col = new Color(red, green, blue);
            repaint();
              try{
            Thread.sleep(10);
            }catch(InterruptedException e){
            }

          }

        }

    }

    /*public void update(Graphics g){
    paint(g);
  } */

    //マウスの処理
    //アプレットの上に乗った
    public void mouseEntered(MouseEvent e){
      //  pos = e.getPoint();
        apenter = true;

      repaint();
    }
    //アプレットの外に出た
    public void mouseExited(MouseEvent e){

        apenter = false;

      repaint();
    }
    //マウスが押された
    public void mousePressed(MouseEvent e){

      repaint();
    }
    //マウスが押された状態から離された
    public void mouseReleased(MouseEvent e){
      repaint() ;
    }
    //マウスがクリックされた
    public void mouseClicked(MouseEvent e){
	  title = col;
	  flifeBox += 150;
      repaint() ;
    }

    //マウスの動きを監視
    public void mouseMoved(MouseEvent e){
      pos = e.getPoint();
      repaint();
    }

    public void mouseDragged(MouseEvent e){
        pos = e.getPoint();

      repaint();
    }
	
	boolean right,left,up,down;//キー入力用のフラグ	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
			
			if(TitleFlag == true){
				TitleFlag = false;
			}
			
				right = true;
				ax += 10;
				repaint();
				break;
			case KeyEvent.VK_LEFT:
			
			if(TitleFlag == true){
				TitleFlag = false;
			}
			
				left = true;
				ax -= 10;
				repaint();
				break;
			case KeyEvent.VK_UP:
				up = true;
				ay -= 10;
				repaint();
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				ay += 10;
				repaint();
				break;
			case KeyEvent.VK_SPACE:
				tama_shot_flag = true;
				repaint();
				break;

			default:
				break;
		}
	}
	
	public void keyReleased(KeyEvent e){
	
		switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
			
			
			
				right = false;
				ax += 10;
				repaint();
				break;
			case KeyEvent.VK_LEFT:
				left = false;
				ax -= 10;
				repaint();
				break;
			case KeyEvent.VK_UP:
				up = false;
				ay -= 10;
				repaint();
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				ay += 10;
				repaint();
				break;
			case KeyEvent.VK_SPACE:
				
			
				tama_shot_flag = true;
				repaint();
				break;
			default:
				break;
		}
	
	}
	public void keyTyped(KeyEvent e){}
	



}
