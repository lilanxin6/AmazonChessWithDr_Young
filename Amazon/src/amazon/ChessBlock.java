package amazon;

import java.awt.Color;
import java.awt.Graphics;

public class ChessBlock {

	private int chessType ; //1黑棋，2白棋 ，3障碍，4无棋  
	private int X  ; //棋子的x坐标
    private int Y;//棋子的y坐标
    public ChessBlock(int x ,int y ,int type){
    	this.setX(x);
    	this.setY(y);
    	this.chessType = type;
    }
	
	public void draw(Graphics g){
		//根据棋子的类型，画新的棋子
		if(chessType == 1){
			g.setColor(Color.BLACK);
			g.fillOval(50*X+70, 50*Y+80, 50, 50);
		}
		else if(chessType == 2){
			g.setColor(Color.WHITE);
			g.fillOval(50*X+70, 50*Y+80, 50, 50);
		}
		else if(chessType == 3){
			g.setColor(Color.BLACK);
			g.drawLine(50*X+70, 50*Y+80, 50*X+120, 50*Y+130);
			g.drawLine(50*X+120, 50*Y+80, 50*X+70, 50*Y+130);
		}
	}

	public void setX(int x) {
		X = x;
	}

	public int getX() {
		return X;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getY() {
		return Y;
	}
	public int getChessType() {
		return chessType;
	}
	public void setChessType(int chessType) {
		this.chessType = chessType;
	}
}
