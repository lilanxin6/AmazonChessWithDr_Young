package amazon;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class ChessBoard extends Frame{ 
	
	public AI ai = new AI();
	public boolean gameStart = false;         //�ж���Ϸ�Ƿ�ʼ����ʼΪ����ʼ
	public int whoIsAI = 1;                   //�ж�˭��AI��1����ڷ���2����׷�
	public int isWho;                         //�жϵ�ǰ��˭������
	public int startIofHI,startJofHI;         //�жϰ������ʼ����(i,j)
	public int endIofHI,endJofHI;             //�жϰ���Ľ�������(i,j)
	public int obstacleI,obstacleJ;           //�ж��ϰ��ķ��õ�����(i,j)
	
	private static final long serialVersionUID = 1L;
	public static final int GAME_WIDTH = 720;   //���̿��
	public static final int GAME_HEIGHT = 660;  //���̸߶�
	
	public static int steps=0;
	public static final int BLACKISAI   = 1;    //�ڷ���AI
	public static final int WHITEISAI   = 2;    //�׷���AI
	public static final int NOCHESS     = 0;    //����
	public static final int BLACHCHESS  = 1;    //����
	public static final int WHITECHESS  = 2;    //����
	public static final int OBSTACLE    = 3;    //�ϰ�
	public static final String BLACKTIME ="�ڷ���ʱ��";
	public static final String WHITETIME ="�׷���ʱ��";
	
	public int isChess = 1;                     //0��ʾ���µ�ǰ�úڷ���׷������ϰ���1��ʾ��һ�ε���ʺ�2��ʾ�ʺ�û����µ�λ��
	public boolean isNowBlack= false;            //�����Ǻڷ�����
	public int   iArrayTemp = 0;                //��¼����Ļʺ��λ��
	public int   jArrayTemp = 0;
	Image offScreenImage = null;
	Image image;                                //����ʾ��ͼƬ
	public int[][] chess = new int[10][10];     //���������Ϣ
	
	public int xCoordinate;                     //����������x����
	public int yCoordinate;                     //����������y����
	public int iArray;                          //�����λ�ö�Ӧ�����i
	public int jArray;                          //�����λ�ö�Ӧ�����j
	
	Button startButton = new Button("��ʼ");    //��ʼ��ť
	Button regretButton= new Button("����");    //���尴ť
	Button changeButton= new Button("��ɫת��");//��ɫת����ť
	Button blackTimeButton=new Button();       //�ڷ���ʱ
	Button whiteTimeButton    =new Button();   //�׷���ʱ
	Button blackChessButton   =new Button();   //�ڷŻʺ��λ��
	Button whiteChessButton   =new Button();   //�׷��ʺ��λ��
	Button blackObstacleButton=new Button();   //�ڷ��ϰ���λ��
	Button whiteObstacleButton=new Button();   //�׷��ϰ���λ��
	Button legalButton        =new Button();   //��ʾ��ǰHI�Ĳ����Ƿ�Ϸ�
	
	boolean timeStart=false;
	int timeStartFlag=0;
	int secondCountWhite=0;		//���õ�������
	int minuteCountWhite=0;
	int secondCountBlack=0;		//���õ�������
	int minuteCountBlack=0;

	String whiteTime=new String("����ʱ�䣺");
	String blackTime=new String("����ʱ�䣺");
	String secWhite =new String();
	String minWhite =new String();
	String secBlack =new String();
	String minBlack =new String();
	
	/*
	 * ���½���
	 */
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		
		for(int i=0;i<11;i++){
			g.setFont(new Font("����", Font.BOLD, 20));                  //��������Ӵ�20��
			if(i<9){
				g.drawString(Integer.toString(i+1), 90+i*50, 608);       //��������к�
				g.drawString(String.valueOf((char)(74-i)), 45, 115+i*50); //�����ĸ�к�
			}
			else if(i==9){
				g.drawString(Integer.toString(i+1), 85+i*50, 608);       //��������к�
				g.drawString(String.valueOf((char)(74-i)), 45, 115+i*50); //�����ĸ�к�
			}
			g.drawLine(70,80+i*50,570,80+i*50);                          //������
			g.drawLine(70+i*50,80,70+i*50,580);                          //������
		}
		
		for(int i=0;i<10;i++){                                           //i��ʾ�У�j��ʾ��
			for(int j=0;j<10;j++){
				/*�����񱳾�*/
				if((i== iArrayTemp)&&(j==jArrayTemp)&&(isChess==2)){
					g.setColor(new Color(0x00, 0xFF, 0x00));
					g.fillRect(70+j*50, 80+i*50, 50, 50);
				}
				else{
					if(((i+j)%2)==0){
						g.setColor(new Color(0xFF, 0xDA, 0xB9));
						g.fillRect(70+j*50, 80+i*50, 50, 50);
					}
					else{
						g.setColor(new Color(0xD2, 0x69, 0x1E));
						g.fillRect(70+j*50, 80+i*50, 50, 50);
					}
				}
				
				/*��������Ӧ��ͼƬ*/
				if(chess[i][j]==BLACHCHESS){
					image=Toolkit.getDefaultToolkit().getImage("./photo/black.png");
					g.drawImage(image, 50*j+70, 50*i+80,this);
				}
				else if(chess[i][j]==WHITECHESS){
					image=Toolkit.getDefaultToolkit().getImage("./photo/yellow.png");
					g.drawImage(image, 50*j+70, 50*i+80,this);
				}
				else if(chess[i][j]==OBSTACLE){
					image=Toolkit.getDefaultToolkit().getImage("./photo/obstacle.png");
					g.drawImage(image, 50*j+70, 50*i+80,this);
				}
			}
		}
		g.setColor(Color.BLACK);
		if(secondCountWhite<=9)
			secWhite ="0"+String.valueOf(secondCountWhite);
		else
			secWhite =String.valueOf(secondCountWhite);
		if(minuteCountWhite<=9)
			minWhite ="0"+String.valueOf(minuteCountWhite);
		else
			minWhite =String.valueOf(minuteCountWhite);
		//g.drawString(whiteTime+minWhite+"��"+secWhite+"��", 300, 50);	
		if(secondCountBlack<=9)
			secBlack ="0"+String.valueOf(secondCountBlack);
		else
			secBlack =String.valueOf(secondCountBlack);
		if(minuteCountBlack<=9)
			minBlack ="0"+String.valueOf(minuteCountBlack);
		else
			minBlack =String.valueOf(minuteCountBlack);
		//g.drawString(blackTime+minBlack+"��"+secBlack+"��", 50, 50);
		blackTimeButton.setLabel(blackTime+minBlack+"��"+secBlack+"��");
		whiteTimeButton.setLabel(whiteTime+minWhite+"��"+secWhite+"��");
	}
	
	/*
	 * update()
	 * ��ֹ����
	 */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.WHITE);        //������ɫ
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}

	/*
	 * ����Ľ���
	 */
	public void lauchFrame(){
		
		this.setLayout(null);       //���óɿգ���ʾ�İ�ť����������ʾ
		
		this.setLocation(400, 50);              //λ�ã�400��50��
		this.setSize(GAME_WIDTH, GAME_HEIGHT);  //���ø�600����600
		
		/*�رմ���,���棬�˳�����*/
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.setTitle("����һ��");              //����
		this.setResizable(false);              //�����Ƿ�ɵ��ߴ�
		this.setBackground(Color.WHITE);       //������ɫ
		setVisible(true);                      //����Ϊ�ɼ�
		
		arrayInit();                           //���������ʼ��
		
		widgetInit();                          //�ؼ�λ�ó�ʼ��
		
		/*����������¼�*/
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				/*��������ʱ������*/
				xCoordinate=e.getX();
				yCoordinate=e.getY();

				/*�������ڵ������Ч*/
				if((xCoordinate>=70)&& (xCoordinate<=570) && (yCoordinate>=80) && (yCoordinate<=580)){
					if(xyTransform()){   //����õ�����ת�������鵱��ȥ��
						repaint();
						if(whoIsAI == BLACKISAI){
							if(isWho == 4){
								//�ڷ�ΪAI�����غڷ��Ļʺ����ʼ�ͽ������꣬�����ϰ�������
								//������������飬��repaint()
								ai.setBoardArray(chess);
								ai.setWhoIsAI(whoIsAI);
								ai.queenSearch();
								isNowBlack = false;
								
							}
						}
						else if(whoIsAI == WHITEISAI){
							if(isWho == 2){
								//�׷�ΪAI�����ذ׷��Ļʺ����ʼ�ͽ������꣬�����ϰ�������
								//������������飬��repaint()
								isNowBlack = true;
							}
						}
					}
				}
			}
		});		
	}
	
	/*
	 * ����Ϸ��Լ��
	 */
	private boolean avaliable(int starti,int endi,int startj,int endj){
		if(Math.abs(starti-endi)==Math.abs(startj-endj)){
			//б����ʱ��������ϰ��ͷ���false
			boolean flag=true;
			int xForward=0,yForward=0;
			xForward=(endi-starti)/Math.abs(endi-starti);
			yForward=(endj-startj)/Math.abs(endj-startj);
			for(int i=1;i<Math.abs(starti-endi);i++){
				int tempx=0,tempy=0;
				tempx=(int)(xForward*i);
				tempy=(int)(yForward*i);
				if(chess[starti+tempx][startj+tempy]!=NOCHESS){
					flag=false;
					break;
				}
			}
			return flag;
		}
		//i�����б���ͬ����x�������ϰ�����false
		else if((starti==endi)&&(startj!=endj)){
			boolean flag=true;
			int yForward=0;
			yForward=(endj-startj)/Math.abs(endj-startj);
			for(int i=1;i<Math.abs(startj-endj);i++){
				int temp=0;
				temp=(int)(yForward*i);
				if(chess[starti][startj+temp]!=NOCHESS){
					flag=false;
					break;
				}
			}
			return flag;
		}//j�����б���ͬ����y�������ϰ�����false
		else if((starti!=endi)&&(startj==endj)){
			boolean flag=true;
			int xForward=0;
			xForward=(endi-starti)/Math.abs(endi-starti);
			for(int i=1;i<Math.abs(starti-endi);i++){
				int temp=0;
				temp=(int)(xForward*i);
				if(chess[starti+temp][startj]!=NOCHESS){
					flag=false;
					break;
				}
			}
			return flag;
		}
		else return false;
	}
	
	/*
	 * ����ת��,ȷ������ת���ɹ������ұ��浽����֮��
	 */
	public boolean xyTransform(){
		iArray=(yCoordinate-80)/50;  //�����е�i
		jArray=(xCoordinate-70)/50;  //�����е�j
		boolean flag ;
		if(isChess == 0){            //��ǰ����Ĳ��ǻʺ󣬽��з����ϰ�����
			if(chess[iArray][jArray]==NOCHESS){ //��������λ��û�����ӣ��Ž�������Ϸ����ж�
				flag=avaliable(endIofHI,iArray,endJofHI,jArray);//�ж��Ƿ�Ϸ�
				if(flag){                        //����Ϸ�����������ϰ���ischess��1
					chess[iArray][jArray]=OBSTACLE;
					isChess    = 1;
					if(whoIsAI == BLACKISAI){
						isWho = 4;
					}
					else if(whoIsAI == WHITEISAI){
						isWho = 2;
					}
					legalButton.setLabel(" ");
					try {
						fileI(iArray,iArray,jArray,jArray,isWho);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return true;					
				}
				else{                         //������Ϸ���������ʾ�Ƿ�������Ϣ
					if(whoIsAI == BLACKISAI){
						legalButton.setLabel("�׷��ϰ��Ƿ�");
						isNowBlack = true;
					}
					else if(whoIsAI == WHITEISAI){
						legalButton.setLabel("�ڷ��ϰ��Ƿ�");
						isNowBlack = false;
					}
					return false;
				}
			}
			return false;
		}
		else if(isChess == 1){                  //��ǰ�����ǵ�һ�ε���ʺ�
			if((whoIsAI==1)){
				if(chess[iArray][jArray] == WHITECHESS ){
					isChess = 2;
					isWho   = 5;
					iArrayTemp = iArray;  //�ѵ���Ļʺ�λ�ô浽����
					jArrayTemp = jArray;
					legalButton.setLabel(" ");
					return true;
				}
			}
			else if(whoIsAI == 2){
				if(chess[iArray][jArray] == BLACHCHESS){
					isChess = 2;
					isWho   = 5;
					iArrayTemp = iArray;  //�ѵ���Ļʺ�λ�ô浽����
					jArrayTemp = jArray;
					legalButton.setLabel(" ");
					return true;
				}
			}
			return false;
		}
		else if(isChess == 2){                  //��ǰ�����ǵڶ��ε���ʺ�
			if(whoIsAI == 1){
				if(chess[iArray][jArray] == NOCHESS){
					flag = avaliable(iArrayTemp, iArray, jArrayTemp, jArray);
					if(flag == false){
						legalButton.setLabel("�׷��ʺ�����Υ��");
						isChess = 2;
						isWho = 5;
						return false;
					}
					else if(flag){
						endIofHI=iArray;
						endJofHI=jArray;
						chess[iArray][jArray]        =WHITECHESS;
						chess[iArrayTemp][jArrayTemp]=NOCHESS;
						isChess = 0;
						isWho = 3;
						legalButton.setLabel(" ");
						try {
							fileI(iArrayTemp,iArray,jArrayTemp,jArray,isWho);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return true;
					}
				}
				else if(chess[iArray][jArray]==WHITECHESS){
					isChess = 2;
					isWho   = 5;
					iArrayTemp = iArray;  //�ѵ���Ļʺ�λ�ô浽����
					jArrayTemp = jArray;
					legalButton.setLabel(" ");
					return true;
				}
			}
			else if(whoIsAI == 2){
				if(chess[iArray][jArray] == NOCHESS){
					flag = avaliable(iArrayTemp, iArray, jArrayTemp, jArray);
					if(flag == false){
						legalButton.setLabel("�ڷ��ʺ�����Υ��");
						isChess = 2;
						isWho = 5;
						legalButton.setLabel(" ");
						return false;
					}
					else if(flag){
						endIofHI=iArray;
						endJofHI=jArray;
						chess[iArray][jArray]=BLACHCHESS;
						chess[iArrayTemp][jArrayTemp]=NOCHESS;
						isChess = 0;
						isWho = 1;
						legalButton.setLabel(" ");
						try {
							fileI(iArrayTemp,iArray,jArrayTemp,jArray,isWho);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return true;
					}
				}
				else if(chess[iArray][jArray]==BLACHCHESS){
					isChess = 2;
					isWho   = 5;
					iArrayTemp = iArray;  //�ѵ���Ļʺ�λ�ô浽����
					jArrayTemp = jArray;
					legalButton.setLabel(" ");
					return true;
				}
			}
			return false;
		}
		
		return false;
	}

	/*
	 * �ؼ���ʼ������
	 */
	public void widgetInit(){
		startButton.setLocation(580,80);
		startButton.setSize(120, 50);
		startButton.setBackground(new Color(0xFF,0xE1,0xFF));
		startButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				gameStart = true;
				new Thread(new myThread()).start();
			}
		});
		this.add(startButton);
		regretButton.setLocation(580,150);
		regretButton.setSize(120, 50);
		regretButton.setBackground(new Color(0xFF,0xE1,0xFF));
		regretButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				byte[] buf = new byte[5];
				try {
					buf = fileO();
					if(steps>0){
						if(isWho == 1){    //�����ǰ�Ǻڷ��ʺ�
							iArrayTemp = buf[1];
							jArrayTemp = buf[3];
							chess[buf[2]][buf[4]] = NOCHESS;
							chess[iArrayTemp][jArrayTemp] = BLACHCHESS;
							isChess = 2;
							isWho = 4;
							steps--;
						}
						else if(isWho == 2){     //��������Ǻڷ��ϰ�
							chess[buf[1]][buf[3]] = NOCHESS;
							isChess = 0;
							isWho = 1;
							steps--;
						}
						else if(isWho == 3){    //�����ǰ�ǰ׷��ʺ�
							iArrayTemp = buf[1];
							jArrayTemp = buf[3];
							chess[buf[2]][buf[4]] = NOCHESS;
							chess[iArrayTemp][jArrayTemp] = WHITECHESS;
							isChess = 2;
							isWho = 2;
							steps--;
						}
						else if(isWho == 4){    //�����ǰ�ǰ׷��ϰ�
							chess[buf[1]][buf[3]] = NOCHESS;
							isChess = 0;
							isWho = 3;
							steps--;
						}
						repaint();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.add(regretButton);
		changeButton.setLocation(580, 220);
		changeButton.setSize(120, 50);
		changeButton.setBackground(new Color(0xFF,0xE1,0xFF));
		changeButton.setLabel("AI���ڷ�");
        changeButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(whoIsAI == 1){
					whoIsAI = 2;
					changeButton.setLabel("AI���׷�");
				}
				else if(whoIsAI == 2){
					whoIsAI = 1;
					changeButton.setLabel("AI���ڷ�");
				}
			}
		});
		this.add(changeButton);
		blackChessButton.setLocation(580, 290);
		blackChessButton.setSize(120, 50);
		blackChessButton.setBackground(new Color(0xFF,0xE1,0xFF));
		this.add(blackChessButton);
		blackObstacleButton.setLocation(580, 360);
		blackObstacleButton.setSize(120, 50);
		blackObstacleButton.setBackground(new Color(0xFF,0xE1,0xFF));
		this.add(blackObstacleButton);
		whiteChessButton.setLocation(580, 430);
		whiteChessButton.setSize(120, 50);
		whiteChessButton.setBackground(new Color(0xFF,0xE1,0xFF));
		this.add(whiteChessButton);
		whiteObstacleButton.setLocation(580, 500);
		whiteObstacleButton.setSize(120, 50);
		whiteObstacleButton.setBackground(new Color(0xFF,0xE1,0xFF));
		this.add(whiteObstacleButton);
		blackTimeButton.setLocation(70, 25);
		blackTimeButton.setSize(200, 50);
		blackTimeButton.setBackground(Color.WHITE);
		this.add(blackTimeButton);
		whiteTimeButton.setLocation(370, 25);
		whiteTimeButton.setSize(200, 50);
		whiteTimeButton.setBackground(Color.WHITE);
		this.add(whiteTimeButton);
		legalButton.setLocation(580, 570);
		legalButton.setSize(120, 50);
		legalButton.setBackground(new Color(0xFF,0xE1,0xFF));
		this.add(legalButton);
	}
	
	/*
	 * �����ʼ��
	 */
	public void arrayInit(){
		for(int i=0;i<10;i++){            
			for(int j=0;j<10;j++){
				chess[i][j]=NOCHESS;
			}
		}
		chess[0][3] = 1;
		chess[0][6] = 1;
		chess[3][0] = 1;
		chess[3][9] = 1;
		chess[6][0] = 2;
		chess[6][9] = 2;
		chess[9][3] = 2;
		chess[9][6] = 2;
	}
	
	
	/*
	 * ��ʱ�߳�
	 */
	private class myThread implements Runnable{
		public void run(){
			try {
					long tempTime = Calendar.getInstance().getTimeInMillis();
					
					while(true){
						Thread.currentThread();
						Thread.sleep(100);
										
						long lTime = Calendar.getInstance().getTimeInMillis();
						if((lTime-tempTime)>=1000){
							tempTime+=1000;
							if(!isNowBlack){
								secondCountWhite++;
							}
							else{
								secondCountBlack++;
							}
							
							if(secondCountWhite==60){
								secondCountWhite=0;minuteCountWhite++;
							}
							if(secondCountBlack==60){
								secondCountBlack=0;minuteCountBlack++;
							}
							repaint();
						}
					}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 *��ȡ�ļ� 
	 */
	private byte[] fileO() throws IOException{
		File file = new File("log","war.txt");
		RandomAccessFile war = new RandomAccessFile(file, "rw");
		war.seek(0);
		//һ���԰��ļ��е����ݶ�ȡ���ֽ�������
		byte[] buf = new byte[(byte)war.length()];
		war.read(buf);
		//��ÿ���ֽڵ������������
		byte[] retur = new byte[5];
		for(int i=0;i<5;i++){
			int temp=steps-1;
			if(temp>=0)
			retur[i]=buf[(int)(temp*5)+i];
		}
		return retur;
	}
	/*
	 * д�ļ�
	 */
	//flagֵ˵����1���ڻʺ�2�����ϰ���3���׻ʺ�4�����ϰ���5�����塣 
	private void fileI(int startI,int endI,int startJ,int endJ,int flag) throws IOException{
		File log = new File("log");
		if(!log.exists())
			log.mkdir();
		File file = new File(log,"war.txt");
		if(!file.exists())
			file.createNewFile();
			
		RandomAccessFile war = new RandomAccessFile(file, "rw");
		
		//�ƶ�д��ָ��
		war.seek(5*steps);
		steps++;
		//д���־����Ǻ����ֽ���˭������
		war.write(flag+10);
		//д������
		war.write(startI);war.write(endI);war.write(startJ);war.write(endJ);
		war.close();
	}
}
