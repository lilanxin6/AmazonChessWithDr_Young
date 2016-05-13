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
	public boolean gameStart = false;         //判断游戏是否开始，初始为不开始
	public int whoIsAI = 1;                   //判断谁是AI，1代表黑方，2代表白方
	public int isWho;                         //判断当前是谁在行棋
	public int startIofHI,startJofHI;         //判断白棋的起始坐标(i,j)
	public int endIofHI,endJofHI;             //判断白棋的结束坐标(i,j)
	public int obstacleI,obstacleJ;           //判断障碍的放置的坐标(i,j)
	
	private static final long serialVersionUID = 1L;
	public static final int GAME_WIDTH = 720;   //棋盘宽度
	public static final int GAME_HEIGHT = 660;  //棋盘高度
	
	public static int steps=0;
	public static final int BLACKISAI   = 1;    //黑方是AI
	public static final int WHITEISAI   = 2;    //白方是AI
	public static final int NOCHESS     = 0;    //无棋
	public static final int BLACHCHESS  = 1;    //黑棋
	public static final int WHITECHESS  = 2;    //白棋
	public static final int OBSTACLE    = 3;    //障碍
	public static final String BLACKTIME ="黑方用时：";
	public static final String WHITETIME ="白方用时：";
	
	public int isChess = 1;                     //0表示点下当前该黑方或白方放置障碍，1表示第一次点击皇后，2表示皇后该换到新的位置
	public boolean isNowBlack= false;            //现在是黑方行棋
	public int   iArrayTemp = 0;                //记录点击的皇后的位置
	public int   jArrayTemp = 0;
	Image offScreenImage = null;
	Image image;                                //待显示的图片
	public int[][] chess = new int[10][10];     //存放棋盘信息
	
	public int xCoordinate;                     //存放鼠标点击的x坐标
	public int yCoordinate;                     //存放鼠标点击的y坐标
	public int iArray;                          //鼠标点击位置对应数组的i
	public int jArray;                          //鼠标点击位置对应数组的j
	
	Button startButton = new Button("开始");    //开始按钮
	Button regretButton= new Button("悔棋");    //悔棋按钮
	Button changeButton= new Button("角色转换");//角色转换按钮
	Button blackTimeButton=new Button();       //黑方用时
	Button whiteTimeButton    =new Button();   //白方用时
	Button blackChessButton   =new Button();   //黑放皇后的位置
	Button whiteChessButton   =new Button();   //白方皇后的位置
	Button blackObstacleButton=new Button();   //黑放障碍的位置
	Button whiteObstacleButton=new Button();   //白方障碍的位置
	Button legalButton        =new Button();   //显示当前HI的步骤是否合法
	
	boolean timeStart=false;
	int timeStartFlag=0;
	int secondCountWhite=0;		//最后得到的秒数
	int minuteCountWhite=0;
	int secondCountBlack=0;		//最后得到的秒数
	int minuteCountBlack=0;

	String whiteTime=new String("白棋时间：");
	String blackTime=new String("黑棋时间：");
	String secWhite =new String();
	String minWhite =new String();
	String secBlack =new String();
	String minBlack =new String();
	
	/*
	 * 更新界面
	 */
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		
		for(int i=0;i<11;i++){
			g.setFont(new Font("宋体", Font.BOLD, 20));                  //设置宋体加粗20号
			if(i<9){
				g.drawString(Integer.toString(i+1), 90+i*50, 608);       //添加数字行号
				g.drawString(String.valueOf((char)(74-i)), 45, 115+i*50); //添加字母行号
			}
			else if(i==9){
				g.drawString(Integer.toString(i+1), 85+i*50, 608);       //添加数字行号
				g.drawString(String.valueOf((char)(74-i)), 45, 115+i*50); //添加字母行号
			}
			g.drawLine(70,80+i*50,570,80+i*50);                          //画横线
			g.drawLine(70+i*50,80,70+i*50,580);                          //画竖线
		}
		
		for(int i=0;i<10;i++){                                           //i表示行，j表示列
			for(int j=0;j<10;j++){
				/*画方格背景*/
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
				
				/*放棋子相应的图片*/
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
		//g.drawString(whiteTime+minWhite+"分"+secWhite+"秒", 300, 50);	
		if(secondCountBlack<=9)
			secBlack ="0"+String.valueOf(secondCountBlack);
		else
			secBlack =String.valueOf(secondCountBlack);
		if(minuteCountBlack<=9)
			minBlack ="0"+String.valueOf(minuteCountBlack);
		else
			minBlack =String.valueOf(minuteCountBlack);
		//g.drawString(blackTime+minBlack+"分"+secBlack+"秒", 50, 50);
		blackTimeButton.setLabel(blackTime+minBlack+"分"+secBlack+"秒");
		whiteTimeButton.setLabel(whiteTime+minWhite+"分"+secWhite+"秒");
	}
	
	/*
	 * update()
	 * 防止闪屏
	 */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.WHITE);        //背景颜色
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}

	/*
	 * 程序的进口
	 */
	public void lauchFrame(){
		
		this.setLayout(null);       //设置成空，显示的按钮才能正常显示
		
		this.setLocation(400, 50);              //位置（400，50）
		this.setSize(GAME_WIDTH, GAME_HEIGHT);  //设置高600，宽600
		
		/*关闭窗口,点叉叉，退出程序*/
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.setTitle("文若一号");              //标题
		this.setResizable(false);              //设置是否可调尺寸
		this.setBackground(Color.WHITE);       //背景颜色
		setVisible(true);                      //设置为可见
		
		arrayInit();                           //棋盘数组初始化
		
		widgetInit();                          //控件位置初始化
		
		/*添加鼠标监听事件*/
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
				/*获得鼠标点击时的坐标*/
				xCoordinate=e.getX();
				yCoordinate=e.getY();

				/*在棋盘内点击才有效*/
				if((xCoordinate>=70)&& (xCoordinate<=570) && (yCoordinate>=80) && (yCoordinate<=580)){
					if(xyTransform()){   //将获得的坐标转化到数组当中去。
						repaint();
						if(whoIsAI == BLACKISAI){
							if(isWho == 4){
								//黑方为AI，返回黑方的皇后的起始和结束坐标，返回障碍的坐标
								//将坐标放入数组，并repaint()
								ai.setBoardArray(chess);
								ai.setWhoIsAI(whoIsAI);
								ai.queenSearch();
								isNowBlack = false;
								
							}
						}
						else if(whoIsAI == WHITEISAI){
							if(isWho == 2){
								//白方为AI，返回白方的皇后的起始和结束坐标，返回障碍的坐标
								//讲坐标放入数组，并repaint()
								isNowBlack = true;
							}
						}
					}
				}
			}
		});		
	}
	
	/*
	 * 行棋合法性检测
	 */
	private boolean avaliable(int starti,int endi,int startj,int endj){
		if(Math.abs(starti-endi)==Math.abs(startj-endj)){
			//斜向走时候如果有障碍就返回false
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
		//i方向行标相同，即x方向有障碍返回false
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
		}//j方向行标相同，即y方向有障碍返回false
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
	 * 坐标转换,确保坐标转换成功，并且保存到数组之中
	 */
	public boolean xyTransform(){
		iArray=(yCoordinate-80)/50;  //数组中的i
		jArray=(xCoordinate-70)/50;  //数组中的j
		boolean flag ;
		if(isChess == 0){            //当前行棋的不是皇后，进行放置障碍操作
			if(chess[iArray][jArray]==NOCHESS){ //如果点击的位置没有棋子，才进行行棋合法的判断
				flag=avaliable(endIofHI,iArray,endJofHI,jArray);//判断是否合法
				if(flag){                        //如果合法，数组放入障碍，ischess加1
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
				else{                         //如果不合法，则上显示非法操作信息
					if(whoIsAI == BLACKISAI){
						legalButton.setLabel("白方障碍非法");
						isNowBlack = true;
					}
					else if(whoIsAI == WHITEISAI){
						legalButton.setLabel("黑方障碍非法");
						isNowBlack = false;
					}
					return false;
				}
			}
			return false;
		}
		else if(isChess == 1){                  //当前行棋是第一次点击皇后
			if((whoIsAI==1)){
				if(chess[iArray][jArray] == WHITECHESS ){
					isChess = 2;
					isWho   = 5;
					iArrayTemp = iArray;  //把点击的皇后位置存到这里
					jArrayTemp = jArray;
					legalButton.setLabel(" ");
					return true;
				}
			}
			else if(whoIsAI == 2){
				if(chess[iArray][jArray] == BLACHCHESS){
					isChess = 2;
					isWho   = 5;
					iArrayTemp = iArray;  //把点击的皇后位置存到这里
					jArrayTemp = jArray;
					legalButton.setLabel(" ");
					return true;
				}
			}
			return false;
		}
		else if(isChess == 2){                  //当前行棋是第二次点击皇后
			if(whoIsAI == 1){
				if(chess[iArray][jArray] == NOCHESS){
					flag = avaliable(iArrayTemp, iArray, jArrayTemp, jArray);
					if(flag == false){
						legalButton.setLabel("白方皇后行棋违法");
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
					iArrayTemp = iArray;  //把点击的皇后位置存到这里
					jArrayTemp = jArray;
					legalButton.setLabel(" ");
					return true;
				}
			}
			else if(whoIsAI == 2){
				if(chess[iArray][jArray] == NOCHESS){
					flag = avaliable(iArrayTemp, iArray, jArrayTemp, jArray);
					if(flag == false){
						legalButton.setLabel("黑方皇后行棋违法");
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
					iArrayTemp = iArray;  //把点击的皇后位置存到这里
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
	 * 控件初始化函数
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
						if(isWho == 1){    //如果当前是黑方皇后
							iArrayTemp = buf[1];
							jArrayTemp = buf[3];
							chess[buf[2]][buf[4]] = NOCHESS;
							chess[iArrayTemp][jArrayTemp] = BLACHCHESS;
							isChess = 2;
							isWho = 4;
							steps--;
						}
						else if(isWho == 2){     //如果当期是黑方障碍
							chess[buf[1]][buf[3]] = NOCHESS;
							isChess = 0;
							isWho = 1;
							steps--;
						}
						else if(isWho == 3){    //如果当前是白方皇后
							iArrayTemp = buf[1];
							jArrayTemp = buf[3];
							chess[buf[2]][buf[4]] = NOCHESS;
							chess[iArrayTemp][jArrayTemp] = WHITECHESS;
							isChess = 2;
							isWho = 2;
							steps--;
						}
						else if(isWho == 4){    //如果当前是白方障碍
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
		changeButton.setLabel("AI：黑方");
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
					changeButton.setLabel("AI：白方");
				}
				else if(whoIsAI == 2){
					whoIsAI = 1;
					changeButton.setLabel("AI：黑方");
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
	 * 数组初始化
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
	 * 计时线程
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
	 *读取文件 
	 */
	private byte[] fileO() throws IOException{
		File file = new File("log","war.txt");
		RandomAccessFile war = new RandomAccessFile(file, "rw");
		war.seek(0);
		//一次性把文件中的内容读取到字节数组中
		byte[] buf = new byte[(byte)war.length()];
		war.read(buf);
		//把每个字节的内容依次输出
		byte[] retur = new byte[5];
		for(int i=0;i<5;i++){
			int temp=steps-1;
			if(temp>=0)
			retur[i]=buf[(int)(temp*5)+i];
		}
		return retur;
	}
	/*
	 * 写文件
	 */
	//flag值说明：1：黑皇后；2：黑障碍；3：白皇后；4：白障碍；5：悔棋。 
	private void fileI(int startI,int endI,int startJ,int endJ,int flag) throws IOException{
		File log = new File("log");
		if(!log.exists())
			log.mkdir();
		File file = new File(log,"war.txt");
		if(!file.exists())
			file.createNewFile();
			
		RandomAccessFile war = new RandomAccessFile(file, "rw");
		
		//移动写入指针
		war.seek(5*steps);
		steps++;
		//写入标志，标记后四字节是谁的坐标
		war.write(flag+10);
		//写入坐标
		war.write(startI);war.write(endI);war.write(startJ);war.write(endJ);
		war.close();
	}
}
