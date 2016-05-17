package amazon;

public class AI {

	//public static final int BLACKISAI   = 1;    //黑方是AI
	//public static final int WHITEISAI   = 2;    //白方是AI
	public static final int NOCHESS     = 0;    //无棋
	public static final int BLACHCHESS  = 1;    //黑棋
	public static final int WHITECHESS  = 2;    //白棋
	public static final int OBSTACLE    = 3;    //障碍
	public int aI;
	private int whoIsAI ;
	private int[]  chess       = new int[8];     //用来存放皇后的种类及位置信息
	
	private int[][] boardArray = new int[10][10];//棋盘信息数组
	public  int[][] queenMoveAI = new int[10][10];//存放AI棋子能够到达的地方
    public  int[][] queenMoveHI = new int[10][10];//存放HI棋子能够到达的地方
    public  int[][] mobility    = new int[10][10];//存放每个空格的能够达到的位置
    public  int[][] kingMoveAI  = new int[10][10];//存放AI棋子king走法能到的位置
    public  int[][] kingMoveHI  = new int[10][10];//存放HI棋子king走法能到的位置
    
    /*private void getQueenPosition(){
    	int one=0;
    	for(int i=0;i<10;i++){
    		for(int j=0;j<10;j++){
    			if(boardArray[i][j]==BLACHCHESS){
    				chess[one] = 1*100 + 10 *i +j;
    				one++;
    			}
    			else if(boardArray[i][j]==WHITECHESS){
    				chess[one] =2*100 + 10*i +j;
    				one++;
    			}
    			if(one == 8){
    				break;
    			}
    		}
    	}
    }
    
    public void queenSearchofMine(){
    	int i,j,k,l,n;
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				queenMoveAI[i][j]=0;
				queenMoveHI[i][j]=0;
			}
		}
    }
	*/
 	public void queenSearch(){
		int i,j,k,l,n;
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				queenMoveAI[i][j]=0;
				queenMoveHI[i][j]=0;
			}
		}
		/*计算棋子一步能够到达的地方*/
		for(k=0;k<10;k++){
			for(l=0;l<10;l++){
				if(boardArray[k][l]==whoIsAI ){//AI一方皇后
					//统计从上到下的连子数
					 for(i=k-1;(i>=0)&& ((boardArray[i][l]==NOCHESS)||(queenMoveAI[i][l]==1));queenMoveAI[i][l]=1,i-- );
					 for(i=k+1;(i<=9)&& ((boardArray[i][l]==NOCHESS)||(queenMoveAI[i][l]==1));queenMoveAI[i][l]=1,i++ );
					 
					//统计从左到右的连子数
					 for(j=l-1;(j>=0) &&((boardArray[k][j]==NOCHESS)||(queenMoveAI[k][j]==1));queenMoveAI[k][j]=1,j-- );
					 for(j=l+1;(j<=9) &&((boardArray[k][j]==NOCHESS)||(queenMoveAI[k][j]==1));queenMoveAI[k][j]=1,j++ );
					 
					//统计从左下到右上的连子数
					 for(i=k-1,j=l-1;(i>=0)&&(j>=0)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveAI[i][j]=1,i--,j--);
					 for(i=k+1,j=l+1;(i<=9)&&(j<=9)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveAI[i][j]=1,i++,j++);
					 
					//统计从左上到右下的连子数
					 for(i=k-1,j=l+1;(i>=0)&&(j<=9)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveAI[i][j]=1,i--,j++);
					 for(i=k+1,j=l-1;(i<=9)&&(j>=0)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveAI[i][j]=1,i++,j--);
				}
				else if(boardArray[k][l] == (3-whoIsAI)){ //HI一方皇后
					//统计从上到下的连子数
					 for(i=k-1;(i>=0)&& ((boardArray[i][l]==NOCHESS)||(queenMoveAI[i][l]==1));queenMoveHI[i][l]=1,i-- );
					 for(i=k+1;(i<=9)&& ((boardArray[i][l]==NOCHESS)||(queenMoveAI[i][l]==1));queenMoveHI[i][l]=1,i++ );
					 
					//统计从左到右的连子数
					 for(j=l-1;(j>=0) &&((boardArray[k][j]==NOCHESS)||(queenMoveAI[k][j]==1));queenMoveHI[k][j]=1,j-- );
					 for(j=l+1;(j<=9) &&((boardArray[k][j]==NOCHESS)||(queenMoveAI[k][j]==1));queenMoveHI[k][j]=1,j++ );
					 
					//统计从左下到右上的连子数
					 for(i=k-1,j=l-1;(i>=0)&&(j>=0)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveHI[i][j]=1,i--,j--);
					 for(i=k+1,j=l+1;(i<=9)&&(j<=9)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveHI[i][j]=1,i++,j++);
					 
					//统计从左上到右下的连子数
					 for(i=k-1,j=l+1;(i>=0)&&(j<=9)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveHI[i][j]=1,i--,j++);
					 for(i=k+1,j=l-1;(i<=9)&&(j>=0)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveHI[i][j]=1,i++,j--);
				}
			}
		}
		/*计算棋子两步到五步能够到达的地方*/
		for(n=1;n<6;n++){
			for(k=0;k<10;k++){
				for(l=0;l<10;l++){
					if(queenMoveAI[k][l]==n ){//AI一方皇后
						//统计从上到下的连子数
						 for(i=k-1;(i>=0)&& ((boardArray[i][l]==NOCHESS)&&((queenMoveAI[i][l]==NOCHESS)||(queenMoveAI[i][l]==1+n)));queenMoveAI[i][l]=1+n,i-- );
						 for(i=k+1;(i<=9)&& ((boardArray[i][l]==NOCHESS)&&((queenMoveAI[i][l]==NOCHESS)||(queenMoveAI[i][l]==1+n)));queenMoveAI[i][l]=1+n,i++ );
						 
						//统计从左到右的连子数
						 for(j=l-1;(j>=0) &&((boardArray[k][j]==NOCHESS)&&((queenMoveAI[k][j]==NOCHESS)||(queenMoveAI[k][j]==1+n)));queenMoveAI[k][j]=1+n,j-- );
						 for(j=l+1;(j<=9) &&((boardArray[k][j]==NOCHESS)&&((queenMoveAI[k][j]==NOCHESS)||(queenMoveAI[k][j]==1+n)));queenMoveAI[k][j]=1+n,j++ );
						 
						//统计从左下到右上的连子数
						 for(i=k-1,j=l-1;(i>=0)&&(j>=0)&&((boardArray[i][j]==NOCHESS)&&((queenMoveAI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveAI[i][j]=1+n,i--,j--);
						 for(i=k+1,j=l+1;(i<=9)&&(j<=9)&&((boardArray[i][j]==NOCHESS)&&((queenMoveAI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveAI[i][j]=1+n,i++,j++);
						 
						//统计从左上到右下的连子数
						 for(i=k-1,j=l+1;(i>=0)&&(j<=9)&&((boardArray[i][j]==NOCHESS)&&((queenMoveAI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveAI[i][j]=1+n,i--,j++);
						 for(i=k+1,j=l-1;(i<=9)&&(j>=0)&&((boardArray[i][j]==NOCHESS)&&((queenMoveAI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveAI[i][j]=1+n,i++,j--);
					}
					else if(queenMoveHI[k][l] == n){ //HI一方皇后
						//统计从上到下的连子数
						 for(i=k-1;(i>=0)&& ((boardArray[i][l]==NOCHESS)&&((queenMoveHI[i][l]==NOCHESS)||(queenMoveAI[i][l]==1+n)));queenMoveHI[i][l]=1+n,i-- );
						 for(i=k+1;(i<=9)&& ((boardArray[i][l]==NOCHESS)&&((queenMoveHI[i][l]==NOCHESS)||(queenMoveAI[i][l]==1+n)));queenMoveHI[i][l]=1+n,i++ );
						 
						//统计从左到右的连子数
						 for(j=l-1;(j>=0) &&((boardArray[k][j]==NOCHESS)&&((queenMoveHI[k][j]==NOCHESS)||(queenMoveAI[k][j]==1+n)));queenMoveHI[k][j]=1+n,j-- );
						 for(j=l+1;(j<=9) &&((boardArray[k][j]==NOCHESS)&&((queenMoveHI[k][j]==NOCHESS)||(queenMoveAI[k][j]==1+n)));queenMoveHI[k][j]=1+n,j++ );
						 
						//统计从左下到右上的连子数
						 for(i=k-1,j=l-1;(i>=0)&&(j>=0)&&((boardArray[i][j]==NOCHESS)&&((queenMoveHI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveHI[i][j]=1+n,i--,j--);
						 for(i=k+1,j=l+1;(i<=9)&&(j<=9)&&((boardArray[i][j]==NOCHESS)&&((queenMoveHI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveHI[i][j]=1+n,i++,j++);
						 
						//统计从左上到右下的连子数
						 for(i=k-1,j=l+1;(i>=0)&&(j<=9)&&((boardArray[i][j]==NOCHESS)&&((queenMoveHI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveHI[i][j]=1+n,i--,j++);
						 for(i=k+1,j=l-1;(i<=9)&&(j>=0)&&((boardArray[i][j]==NOCHESS)&&((queenMoveHI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveHI[i][j]=1+n,i++,j--);
					}
				}
			}
		}
	}

 	/*此算法只记录空格八邻域的可达性*/
	public void mobilitysearch(){
		int i,j;
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				if(boardArray[i][j]==NOCHESS){
					if(boardArray[i-1][j-1]==NOCHESS)
					{mobility[i][j]=mobility[i][j]+1;}
					if(boardArray[i-1][j]==NOCHESS)
					{mobility[i][j]=mobility[i][j]+1;}
	                if(boardArray[i-1][j+1]==NOCHESS)
					{mobility[i][j]=mobility[i][j]+1;}
	                if(boardArray[i][j-1]==NOCHESS)
					{mobility[i][j]=mobility[i][j]+1;}
					if(boardArray[i][j+1]==NOCHESS)
					{mobility[i][j]=mobility[i][j]+1;}
					if(boardArray[i+1][j-1]==NOCHESS)
					{mobility[i][j]=mobility[i][j]+1;}
					if(boardArray[i+1][j]==NOCHESS)
					{mobility[i][j]=mobility[i][j]+1;}
					if(boardArray[i+1][j+1]==NOCHESS)
					{mobility[i][j]=mobility[i][j]+1;}

				}
			}
		}
	}

	/*搜索king走法*/
	public void kingSearch(){
		int i,j,n;
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				kingMoveAI[i][j]= 0;
				kingMoveHI[i][j]= 0;
			}
		}
		for(i=0;i<10;i++)
		{
			for(j=0;j<10;j++)
			{
				if(boardArray[i][j]==whoIsAI)//white king 1正方棋子
				{
					if(boardArray[i-1][j-1]==0)//左上角为空设为可移动
					{kingMoveAI[i-1][j-1]=1;}
					
	                if(boardArray[i-1][j]==0)//正上方为空设为可移动
					{kingMoveAI[i-1][j]=1;}

	                if(boardArray[i-1][j+1]==0)//右上方为空设为可移动
					{kingMoveAI[i-1][j+1]=1;}

	                if(boardArray[i][j-1]==0)//左边为空设为可移动
					{kingMoveAI[i][j-1]=1;}

	                if(boardArray[i][j+1]==0)//右边为空设为可移动
					{kingMoveAI[i][j+1]=1;}

	                if(boardArray[i+1][j-1]==0)//左下方为空设为可移动
					{kingMoveAI[i+1][j-1]=1;}

	                if(boardArray[i+1][j]==0)//正下方为空设为可移动
					{kingMoveAI[i+1][j]=1;}

	                if(boardArray[i+1][j+1]==0)//右下方为空设为可移动
					{kingMoveAI[i+1][j+1]=1;}
				}

	            if(boardArray[i][j]==(3-whoIsAI))//反方棋子
				{
					if(boardArray[i-1][j-1]==0)
					{kingMoveHI[i-1][j-1]=1;}
					
	                if(boardArray[i-1][j]==0)
					{kingMoveHI[i-1][j]=1;}

	                if(boardArray[i-1][j+1]==0)
					{kingMoveHI[i-1][j+1]=1;}

	                if(boardArray[i][j-1]==0)
					{kingMoveHI[i][j-1]=1;}

	                if(boardArray[i][j+1]==0)
					{kingMoveHI[i][j+1]=1;}

	                if(boardArray[i+1][j-1]==0)
					{kingMoveHI[i+1][j-1]=1;}

	                if(boardArray[i+1][j]==0)
					{kingMoveHI[i+1][j]=1;}

	                if(boardArray[i+1][j+1]==0)
					{kingMoveHI[i+1][j+1]=1;}
				}
			}
		}
        
		//计算10步king走法可以到达的位置
		for( n=1;n<11;n++)
		{
			for(i=0;i<10;i++)
			{
				for(j=0;j<10;j++)
				{
					if(kingMoveAI[i][j]==n)//white king 2-10
					{
						if(boardArray[i-1][j-1]==0&&kingMoveAI[i-1][j-1]==0)
						{kingMoveAI[i-1][j-1]=n+1;}

	                    if(boardArray[i-1][j]==0&&kingMoveAI[i-1][j]==0)
						{kingMoveAI[i-1][j]=n+1;}

	                    if(boardArray[i-1][j+1]==0&&kingMoveAI[i-1][j+1]==0)
						{kingMoveAI[i-1][j+1]=n+1;}

	                    if(boardArray[i][j-1]==0&&kingMoveAI[i][j-1]==0)
						{kingMoveAI[i][j-1]=n+1;}

	                    if(boardArray[i][j+1]==0&&kingMoveAI[i][j+1]==0)
						{kingMoveAI[i][j+1]=n+1;}

	                    if(boardArray[i+1][j-1]==0&&kingMoveAI[i+1][j-1]==0)
						{kingMoveAI[i+1][j-1]=n+1;}

	                    if(boardArray[i+1][j]==0&&kingMoveAI[i+1][j]==0)
						{kingMoveAI[i+1][j]=n+1;}

	                    if(boardArray[i+1][j+1]==0&&kingMoveAI[i+1][j+1]==0)
						{kingMoveAI[i+1][j+1]=n+1;}
					}

	                if(kingMoveHI[i][j]==n)//black king 2-10
					{
						if(boardArray[i-1][j-1]==0&&kingMoveHI[i-1][j-1]==0)
						{kingMoveHI[i-1][j-1]=n+1;}

	                    if(boardArray[i-1][j]==0&&kingMoveHI[i-1][j]==0)
						{kingMoveHI[i-1][j]=n+1;}

	                    if(boardArray[i-1][j+1]==0&&kingMoveHI[i-1][j+1]==0)
						{kingMoveHI[i-1][j+1]=n+1;}

	                    if(boardArray[i][j-1]==0&&kingMoveHI[i][j-1]==0)
						{kingMoveHI[i][j-1]=n+1;}

	                    if(boardArray[i][j+1]==0&&kingMoveHI[i][j+1]==0)
						{kingMoveHI[i][j+1]=n+1;}

	                    if(boardArray[i+1][j-1]==0&&kingMoveHI[i+1][j-1]==0)
						{kingMoveHI[i+1][j-1]=n+1;}

	                    if(boardArray[i+1][j]==0&&kingMoveHI[i+1][j]==0)
						{kingMoveHI[i+1][j]=n+1;}

	                    if(boardArray[i+1][j+1]==0&&kingMoveHI[i+1][j+1]==0)
						{kingMoveHI[i+1][j+1]=n+1;}
					}
				}
			}
		}
	}
	
	public void setWhoIsAI(int whoIsAI) {
		this.whoIsAI = whoIsAI;
	}
	public int getWhoIsAI() {
		return whoIsAI;
	}
	public int[][] getBoardArray() {
		return boardArray;
	}
	public void setBoardArray(int[][] boardArray) {
		this.boardArray = boardArray;
	}
	
}
