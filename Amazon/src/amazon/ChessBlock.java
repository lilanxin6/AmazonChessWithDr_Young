package amazon;

import java.awt.Color;
import java.awt.Graphics;

public class ChessBlock {

	private int chessType ; //1���壬2���� ��3�ϰ���4����  
	private int X  ; //���ӵ�x����
    private int Y;//���ӵ�y����
    public ChessBlock(int x ,int y ,int type){
    	this.setX(x);
    	this.setY(y);
    	this.chessType = type;
    }
	
	public void draw(Graphics g){
		//�������ӵ����ͣ����µ�����
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
