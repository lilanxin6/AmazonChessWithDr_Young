package amazon;

public class AI {

	//public static final int BLACKISAI   = 1;    //�ڷ���AI
	//public static final int WHITEISAI   = 2;    //�׷���AI
	public static final int NOCHESS     = 0;    //����
	public static final int BLACHCHESS  = 1;    //����
	public static final int WHITECHESS  = 2;    //����
	public static final int OBSTACLE    = 3;    //�ϰ�
	public int aI;
	private int whoIsAI ;
	private int[]  chess       = new int[8];     //������Żʺ�����༰λ����Ϣ
	
	private int[][] boardArray = new int[10][10];//������Ϣ����
	public  int[][] queenMoveAI = new int[10][10];//���AI�����ܹ�����ĵط�
    public  int[][] queenMoveHI = new int[10][10];//���HI�����ܹ�����ĵط�
    public  int[][] mobility    = new int[10][10];//���ÿ���ո���ܹ��ﵽ��λ��
    public  int[][] kingMoveAI  = new int[10][10];//���AI����king�߷��ܵ���λ��
    public  int[][] kingMoveHI  = new int[10][10];//���HI����king�߷��ܵ���λ��
    
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
		/*��������һ���ܹ�����ĵط�*/
		for(k=0;k<10;k++){
			for(l=0;l<10;l++){
				if(boardArray[k][l]==whoIsAI ){//AIһ���ʺ�
					//ͳ�ƴ��ϵ��µ�������
					 for(i=k-1;(i>=0)&& ((boardArray[i][l]==NOCHESS)||(queenMoveAI[i][l]==1));queenMoveAI[i][l]=1,i-- );
					 for(i=k+1;(i<=9)&& ((boardArray[i][l]==NOCHESS)||(queenMoveAI[i][l]==1));queenMoveAI[i][l]=1,i++ );
					 
					//ͳ�ƴ����ҵ�������
					 for(j=l-1;(j>=0) &&((boardArray[k][j]==NOCHESS)||(queenMoveAI[k][j]==1));queenMoveAI[k][j]=1,j-- );
					 for(j=l+1;(j<=9) &&((boardArray[k][j]==NOCHESS)||(queenMoveAI[k][j]==1));queenMoveAI[k][j]=1,j++ );
					 
					//ͳ�ƴ����µ����ϵ�������
					 for(i=k-1,j=l-1;(i>=0)&&(j>=0)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveAI[i][j]=1,i--,j--);
					 for(i=k+1,j=l+1;(i<=9)&&(j<=9)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveAI[i][j]=1,i++,j++);
					 
					//ͳ�ƴ����ϵ����µ�������
					 for(i=k-1,j=l+1;(i>=0)&&(j<=9)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveAI[i][j]=1,i--,j++);
					 for(i=k+1,j=l-1;(i<=9)&&(j>=0)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveAI[i][j]=1,i++,j--);
				}
				else if(boardArray[k][l] == (3-whoIsAI)){ //HIһ���ʺ�
					//ͳ�ƴ��ϵ��µ�������
					 for(i=k-1;(i>=0)&& ((boardArray[i][l]==NOCHESS)||(queenMoveAI[i][l]==1));queenMoveHI[i][l]=1,i-- );
					 for(i=k+1;(i<=9)&& ((boardArray[i][l]==NOCHESS)||(queenMoveAI[i][l]==1));queenMoveHI[i][l]=1,i++ );
					 
					//ͳ�ƴ����ҵ�������
					 for(j=l-1;(j>=0) &&((boardArray[k][j]==NOCHESS)||(queenMoveAI[k][j]==1));queenMoveHI[k][j]=1,j-- );
					 for(j=l+1;(j<=9) &&((boardArray[k][j]==NOCHESS)||(queenMoveAI[k][j]==1));queenMoveHI[k][j]=1,j++ );
					 
					//ͳ�ƴ����µ����ϵ�������
					 for(i=k-1,j=l-1;(i>=0)&&(j>=0)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveHI[i][j]=1,i--,j--);
					 for(i=k+1,j=l+1;(i<=9)&&(j<=9)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveHI[i][j]=1,i++,j++);
					 
					//ͳ�ƴ����ϵ����µ�������
					 for(i=k-1,j=l+1;(i>=0)&&(j<=9)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveHI[i][j]=1,i--,j++);
					 for(i=k+1,j=l-1;(i<=9)&&(j>=0)&&((boardArray[i][j]==NOCHESS)||(queenMoveAI[i][j]==1));queenMoveHI[i][j]=1,i++,j--);
				}
			}
		}
		/*���������������岽�ܹ�����ĵط�*/
		for(n=1;n<6;n++){
			for(k=0;k<10;k++){
				for(l=0;l<10;l++){
					if(queenMoveAI[k][l]==n ){//AIһ���ʺ�
						//ͳ�ƴ��ϵ��µ�������
						 for(i=k-1;(i>=0)&& ((boardArray[i][l]==NOCHESS)&&((queenMoveAI[i][l]==NOCHESS)||(queenMoveAI[i][l]==1+n)));queenMoveAI[i][l]=1+n,i-- );
						 for(i=k+1;(i<=9)&& ((boardArray[i][l]==NOCHESS)&&((queenMoveAI[i][l]==NOCHESS)||(queenMoveAI[i][l]==1+n)));queenMoveAI[i][l]=1+n,i++ );
						 
						//ͳ�ƴ����ҵ�������
						 for(j=l-1;(j>=0) &&((boardArray[k][j]==NOCHESS)&&((queenMoveAI[k][j]==NOCHESS)||(queenMoveAI[k][j]==1+n)));queenMoveAI[k][j]=1+n,j-- );
						 for(j=l+1;(j<=9) &&((boardArray[k][j]==NOCHESS)&&((queenMoveAI[k][j]==NOCHESS)||(queenMoveAI[k][j]==1+n)));queenMoveAI[k][j]=1+n,j++ );
						 
						//ͳ�ƴ����µ����ϵ�������
						 for(i=k-1,j=l-1;(i>=0)&&(j>=0)&&((boardArray[i][j]==NOCHESS)&&((queenMoveAI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveAI[i][j]=1+n,i--,j--);
						 for(i=k+1,j=l+1;(i<=9)&&(j<=9)&&((boardArray[i][j]==NOCHESS)&&((queenMoveAI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveAI[i][j]=1+n,i++,j++);
						 
						//ͳ�ƴ����ϵ����µ�������
						 for(i=k-1,j=l+1;(i>=0)&&(j<=9)&&((boardArray[i][j]==NOCHESS)&&((queenMoveAI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveAI[i][j]=1+n,i--,j++);
						 for(i=k+1,j=l-1;(i<=9)&&(j>=0)&&((boardArray[i][j]==NOCHESS)&&((queenMoveAI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveAI[i][j]=1+n,i++,j--);
					}
					else if(queenMoveHI[k][l] == n){ //HIһ���ʺ�
						//ͳ�ƴ��ϵ��µ�������
						 for(i=k-1;(i>=0)&& ((boardArray[i][l]==NOCHESS)&&((queenMoveHI[i][l]==NOCHESS)||(queenMoveAI[i][l]==1+n)));queenMoveHI[i][l]=1+n,i-- );
						 for(i=k+1;(i<=9)&& ((boardArray[i][l]==NOCHESS)&&((queenMoveHI[i][l]==NOCHESS)||(queenMoveAI[i][l]==1+n)));queenMoveHI[i][l]=1+n,i++ );
						 
						//ͳ�ƴ����ҵ�������
						 for(j=l-1;(j>=0) &&((boardArray[k][j]==NOCHESS)&&((queenMoveHI[k][j]==NOCHESS)||(queenMoveAI[k][j]==1+n)));queenMoveHI[k][j]=1+n,j-- );
						 for(j=l+1;(j<=9) &&((boardArray[k][j]==NOCHESS)&&((queenMoveHI[k][j]==NOCHESS)||(queenMoveAI[k][j]==1+n)));queenMoveHI[k][j]=1+n,j++ );
						 
						//ͳ�ƴ����µ����ϵ�������
						 for(i=k-1,j=l-1;(i>=0)&&(j>=0)&&((boardArray[i][j]==NOCHESS)&&((queenMoveHI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveHI[i][j]=1+n,i--,j--);
						 for(i=k+1,j=l+1;(i<=9)&&(j<=9)&&((boardArray[i][j]==NOCHESS)&&((queenMoveHI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveHI[i][j]=1+n,i++,j++);
						 
						//ͳ�ƴ����ϵ����µ�������
						 for(i=k-1,j=l+1;(i>=0)&&(j<=9)&&((boardArray[i][j]==NOCHESS)&&((queenMoveHI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveHI[i][j]=1+n,i--,j++);
						 for(i=k+1,j=l-1;(i<=9)&&(j>=0)&&((boardArray[i][j]==NOCHESS)&&((queenMoveHI[i][j]==NOCHESS)||(queenMoveAI[i][j]==1+n)));queenMoveHI[i][j]=1+n,i++,j--);
					}
				}
			}
		}
	}

 	/*���㷨ֻ��¼�ո������Ŀɴ���*/
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

	/*����king�߷�*/
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
				if(boardArray[i][j]==whoIsAI)//white king 1��������
				{
					if(boardArray[i-1][j-1]==0)//���Ͻ�Ϊ����Ϊ���ƶ�
					{kingMoveAI[i-1][j-1]=1;}
					
	                if(boardArray[i-1][j]==0)//���Ϸ�Ϊ����Ϊ���ƶ�
					{kingMoveAI[i-1][j]=1;}

	                if(boardArray[i-1][j+1]==0)//���Ϸ�Ϊ����Ϊ���ƶ�
					{kingMoveAI[i-1][j+1]=1;}

	                if(boardArray[i][j-1]==0)//���Ϊ����Ϊ���ƶ�
					{kingMoveAI[i][j-1]=1;}

	                if(boardArray[i][j+1]==0)//�ұ�Ϊ����Ϊ���ƶ�
					{kingMoveAI[i][j+1]=1;}

	                if(boardArray[i+1][j-1]==0)//���·�Ϊ����Ϊ���ƶ�
					{kingMoveAI[i+1][j-1]=1;}

	                if(boardArray[i+1][j]==0)//���·�Ϊ����Ϊ���ƶ�
					{kingMoveAI[i+1][j]=1;}

	                if(boardArray[i+1][j+1]==0)//���·�Ϊ����Ϊ���ƶ�
					{kingMoveAI[i+1][j+1]=1;}
				}

	            if(boardArray[i][j]==(3-whoIsAI))//��������
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
        
		//����10��king�߷����Ե����λ��
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
