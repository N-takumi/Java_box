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
	
	Image img[];//�摜���m�ۂ��邽�߂̕ϐ�img��z��Ƃ��Đ錾
	Image img2;

    Point pos;//�}�E�X�̍��W
    Thread thread;
    int x, y;//�~�p�̍��W��
	int ax, ay;//�l�p�p�̍��W��
	
	int flifeBox=300;//�t���ɂ�񃉃C�t�{�b�N�X�p�̃T�C�Y�i�����j
	int olifeBox=250;//�I���t���C�t�{�b�N�X�p�̃T�C�Y�i����
	
	int ix[],iy[];//�摜�p�̍��W//�����o�[(�I���t�E�e))//�z��ŋ��L
	
	//�t���j�����ړ��p�̃t���O
	boolean fuyunFlag = false;
	//�t���ɂ��
	
	//�I���t�̉摜�T�C�Y 82 x 166
	int osizex = 82;
	int osizey = 166;
	
	//boolean Change = false;//�摜����؂�ւ��悤�t���O
	
	//�^�C�g���\���p�t���O
	boolean TitleFlag = false;
	
    Color col;
	
	Color title = Color.yellow;
    int size = 50;
	int width = 640, height = 500;//�E�B���h�E�̕��A�����ݒ�
    int threadCount = 0;
	
//	int tama_x = 500;//�e�̍��Wx
//	int tama_y = 200;//�e�̍��Wy
	
	boolean tama_shot_flag = false; //�e�𔭎˂������ǂ����𔻒肷��t���O
	
	int imgNum = 0;//�摜�p
	
	
	//�w�i�F
	int bred;
	int bgreen;
	int bblue;
	
	//�w�i�F�̓���ւ��p
	boolean bcol = false;
	
	//�I���t��HP
	int HP; 

    //�}�E�X���A�v���b�g�̒��ɂ��邩������
    boolean apenter = false;
	
	private Image BackBuffer;//�o�b�N�o�b�t�@�p��Image�^�ϐ�

   //

    public void init(){

     	 //�}�E�X���X�i�[�̓o�^
        addMouseListener(this);
        addMouseMotionListener(this);
		
		BackBuffer = createImage(width,height);
		
		ix = new int[3];
		iy = new int[3];
		
		
		ix[0]=200;iy[0]=300; //�I���t�̏����ʒu
		ix[1]=230;iy[1]=285;//�e�̏����ʒu
		ix[2]=100;iy[2]=50;//�t���ɂ��̏����ʒu
		
		
		img = new Image[10];
		
		
		img[0] = getImage(getDocumentBase(),"Sample.png");//�摜1
		img[1] = getImage(getDocumentBase(),"jiba.png");//�摜2
		
		img2 = getImage(getDocumentBase(),"tama.png");//�e
		

		
		//�L�[���X�i�[�̓o�^	
		addKeyListener(this); 

        //�w�i�F�̏�����
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
		
		Font f1 = new Font("SansSerif",Font.BOLD,15);//�t�H���g�p�̕ϐ��錾
		Font f2 = new Font("SansSerif",Font.BOLD,20);//�t�H���g�p�̕ϐ��錾
		
		Font f3 = new Font("SansSerif",Font.BOLD,25);//�t�H���g�p�̕ϐ��錾
		Font f4 = new Font("SansSerif",Font.BOLD,30);//�t�H���g�p�̕ϐ��錾
		
		
		
		Graphics gback = BackBuffer.getGraphics();
		gback.clearRect(0,0,width,height);//��肽�Ẵo�b�t�@���N���A���Ă���
		
        gback.setColor(col);
        gback.drawOval(x, y, size + threadCount,size + threadCount);
		
		gback.drawRect(110,3,flifeBox,20);//�I���t�̃��C�t
		gback.drawRect(110,25,olifeBox,20);//�t���ɂ��̃��C�t
	
		gback.setFont(f1);
		
	if(olifeBox>0)gback.drawImage(img[imgNum],ix[0],iy[0],this);
	if(olifeBox>0)gback.drawImage(img2,ix[1], iy[1],this);               
	if(flifeBox>0)gback.drawImage(img[1],ix[2],iy[2],this);
		
//	gback.drawString("�^�}��X���W: ("+ix[1]+")",10,150);
//	gback.drawString("�^�}��Y���W: ("+iy[1]+")",10,170);
		
	//	gback.drawString("�I���t��X���W: ("+ix[0]+")",10,190);
	//	gback.drawString("�I���t��Y���W: ("+iy[0]+")",10,210);
		
		
	//	gback.drawString("��ʂ̉�����("+width+")��ŁI",10,130);
	//	gback.drawString("��ʂ̍�����("+height+")��ŁI",10,100);
		gback.setColor(Color.white);
		
		gback.drawString("�t���j����:�@"+flifeBox,15,20);//�G�L������
		gback.drawString("�I���t:�@"+olifeBox,47,40);//�v���C���[��
		
		gback.drawString("���̐��E�̋C��:"+ondo,500,20);
	
		if(TitleFlag == true){
		gback.setFont(f1);
		gback.setColor(Color.gray);
		gback.drawString("�I���S���N�G�X�gIX",width/2-30,130);
		gback.setColor(title);
		gback.drawString("�I���S���N�G�X�gIX",width/2-30+2,132);
		gback.setColor(Color.red);
		gback.setFont(f2);
		gback.drawString("�`�t���j�����̋t�P�`",width/2-30+40,150);
		gback.setColor(Color.blue);
		gback.drawString("�`�t���j�����̋t�P�`",width/2-30+41,151);	
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
		gback.drawString("���E�͕��a�ɂȂ���",width/2-30+40,160);
		gback.setColor(Color.blue);
		gback.drawString("���E�͕��a�ɂȂ���",width/2-30+41,161);	
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
		gback.drawString("�I���t�͑�n�ɂ�������",width/2-30+40,160);
		gback.setColor(Color.blue);
		gback.drawString("�I���t�͑�n�ɂ�������",width/2-30+41,161);	
		}
		
		
		
		g.drawImage(BackBuffer,0,0,width,height,this);//BackBuffer ��g��(0,0)���W�ɕ`��
		
		
		
		
		
		
		
		requestFocusInWindow();//Key���͗p
	
    }



    public void run(){
		
		TitleFlag = true;
		
        while(true){
			
			//�����蔻��
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
			
			
			//���x�ɂ��I���t�̗̑͌���
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
			
			
		//�G�̋���
		if(ix[2] >= 500) fuyunFlag = false;
		if(fuyunFlag == false)ix[2]-= 5;
		if(ix[2] <= 0) fuyunFlag = true;
		if(fuyunFlag == true)ix[2]+= 5;
			
         //�E�Ə�𓯎��ɉ������Ƃ�
		 if(right && up && !left && !down){
		 	
		 //	ix[0] += 10;
		//	iy[0] -= 10;
				 if(tama_shot_flag == false){
		//		 ix[1] += 10;
		//		 iy[1] -= 10;
				 }
		 }
		 //�E�Ɖ�
		 if(right && down && !left && !up){
		 
		 //	ix[0] += 10;
		//	iy[0] += 10;
				if(tama_shot_flag == false){
		//		ix[1] += 10;
		//		iy[1] += 10;
				}
		 
		 }
		 //���Ə�
		 if(left && up && !right && !down){
	
		 //	ix[0] -= 10;
		//	iy[0] -= 10;	
				if(tama_shot_flag == false){
		//		ix[1] -= 10;
		//		iy[1] -= 10;
				}
			
		 }
		 //���Ɖ�
		 if(left && down && !right && !up){

		 //	ix[0] -= 10;
		//	iy[0] += 10;
					if(tama_shot_flag == false){
		//			ix[1] -= 10;
		//			iy[1] += 10;
					}
			
	
		 }
		 //�E�݂̂��������ꍇ
		  if(!left && !down && right && !up){

		 	ix[0] += 10;
					if(tama_shot_flag == false){
					ix[1] += 10;
					}
					
					if((int)(Math.random() *2) == 0&&GameOver != true){
					ondo += (int)(Math.random() *4);
					}

		 }
		 //���̂�
		  if(left && !down && !right && !up){

		 	ix[0] -= 10;
				if(tama_shot_flag == false){
				ix[1] -= 10;
				}
				
				if((int)(Math.random() *2) == 0&& GameOver != true){
				ondo += (int)(Math.random() *4);
				}

		 }
		 //��̂�
		  if(!left && !down && !right && up){

		//	iy[0] -= 10;
				if(tama_shot_flag == false){
			//	iy[1] -= 10;
				}
		
		 }
		 //���̂�
		  if(!left && down && !right && !up){
	
		//	iy[0] += 10;
				if(tama_shot_flag == false){
			//	iy[1] += 10;
				}

		 }
		 
		 //�L��������ʂ���͂ݏo���ꍇ�̏���
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
		  
		  //�w�i�F�����_���ŏ��X�ɕύX
		  if(bcol == false){
		  bred = bred + (int)(Math.random() *2);
          bgreen =bgreen + (int)(Math.random() *2) ; 
          bblue =bblue + (int)(Math.random() *2);
          col = new Color(bred, bgreen, bblue);
		  
		  //RGB��253�𒴂�����bcol���]
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
		  
		  //RGB��255�𒴂�����bcol���]
		  if(bred == 200|| bgreen== 200|| bblue == 200){
		  	bcol = true;
		  }else if(bred == 30|| bgreen== 30| bblue == 30){
		  	bcol = false;
		  }
		  
		  setBackground(col);
		  }
		  
		  	

            //�}�E�X���O�̎��̏���
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

            //�}�E�X�����̎��̏���
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

    //�}�E�X�̏���
    //�A�v���b�g�̏�ɏ����
    public void mouseEntered(MouseEvent e){
      //  pos = e.getPoint();
        apenter = true;

      repaint();
    }
    //�A�v���b�g�̊O�ɏo��
    public void mouseExited(MouseEvent e){

        apenter = false;

      repaint();
    }
    //�}�E�X�������ꂽ
    public void mousePressed(MouseEvent e){

      repaint();
    }
    //�}�E�X�������ꂽ��Ԃ��痣���ꂽ
    public void mouseReleased(MouseEvent e){
      repaint() ;
    }
    //�}�E�X���N���b�N���ꂽ
    public void mouseClicked(MouseEvent e){
	  title = col;
	  flifeBox += 150;
      repaint() ;
    }

    //�}�E�X�̓������Ď�
    public void mouseMoved(MouseEvent e){
      pos = e.getPoint();
      repaint();
    }

    public void mouseDragged(MouseEvent e){
        pos = e.getPoint();

      repaint();
    }
	
	boolean right,left,up,down;//�L�[���͗p�̃t���O	
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
