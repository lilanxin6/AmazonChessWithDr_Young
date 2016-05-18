package amazon;
//white ��Ӧ�����  AI
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
	
	
	public double t1 =0 ;
	public void t1account(){
		int i,j;
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				if(boardArray[i][j]==0){
					if((queenMoveAI[i][j]==queenMoveHI[i][j])&&(queenMoveAI[i][j]!=0)){//�ڰ�˫���ʺ��߷����貽����ͬ�ĵ�
						t1=t1+0.1;
					}
				    if(queenMoveAI[i][j]<queenMoveHI[i][j]){//AI����С��HI
						if(queenMoveAI[i][j]==0){
							t1=t1-1;
						}
					    if(queenMoveAI[i][j]!=0){
					    	t1=t1+1;
					    }
					}
					if(queenMoveAI[i][j]>queenMoveHI[i][j]){//AI��������HI
						if(queenMoveHI[i][j]==0){
							t1=t1+1;
						}
						if(queenMoveHI[i][j]!=0){
							t1=t1-1;
						}
					}
				}
			}
		}
	}
	
	public double t2 = 0;
	public void t2account(){
		int i,j;
	    for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				if(boardArray[i][j]==0){
					if((kingMoveAI[i][j]==kingMoveHI[i][j])&&(kingMoveAI[i][j]!=0)){
						t2=t2+0.1;
					}
				    if(kingMoveAI[i][j]<kingMoveHI[i][j]){
						if(kingMoveAI[i][j]==0){
							t2=t2-1;
						}
					    if(kingMoveAI[i][j]!=0){
					    	t2=t2+1;
					    }
					}
					if(kingMoveAI[i][j]>kingMoveHI[i][j]){
						if(kingMoveHI[i][j]==0){
							t2=t2+1;
						}
						if(kingMoveHI[i][j]!=0){
							t2=t2-1;
						}
					}
				}
			}
		}
	}

	public double c1 = 0;
	public void c1account(){
		int i,j;
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				if(boardArray[i][j]==0)
				{
					if((queenMoveAI[i][j]!=0)&&(queenMoveHI[i][j]!=0)){
						c1=c1+Math.pow(2.0,(double)(-(queenMoveAI[i][j])))-Math.pow(2.0,(double)(-(queenMoveHI[i][j])));
					}
					if((queenMoveAI[i][j]==0)&&(queenMoveHI[i][j]!=0)){
						c1=c1-Math.pow(2.0,(double)(-(queenMoveHI[i][j])));
					}
					if((queenMoveAI[i][j]!=0)&&(queenMoveHI[i][j]==0)){
						c1=c1+Math.pow(2.0,(double)(-(queenMoveAI[i][j])));
					}
				}
			}
		}
		c1=c1*2;
	}

	public double c2 = 0;
	public void c2account(){
		int i,j;
		double a;
	    for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				if(boardArray[i][j]==0){
					if(kingMoveAI[i][j]!=0&&kingMoveHI[i][j]!=0){
						a=(kingMoveHI[i][j]-kingMoveAI[i][j]);
						c2=c2+Math.min(1,(double)Math.max(-1,a/6));
					}
					if(kingMoveAI[i][j]==0&&kingMoveHI[i][j]!=0){
						c2=c2-1;
					}
					if(kingMoveAI[i][j]!=0&&kingMoveHI[i][j]==0){
						c2=c2+1;
					}
				}
			}
		}
	}

	public double w = 0;
	public void wacount(){
		int i,j;
		for(i=0;i<10;i++){
			for(j=0;j<10;j++){
				if(boardArray[i][j]==0)
				{
					if((queenMoveAI[i][j]!=0)&&(queenMoveHI[i][j]!=0))
					{
						w=w+Math.pow(2.0,(double)(-(Math.abs((double)(queenMoveAI[i][j]-queenMoveHI[i][j])))));
					}
				}
			}
		}
	}

	public int[] awhite = new int[4];
	public int[] ablack = new int[4];
	public void aacount(){
		int i,j,k,l,m;
		int n=0;
		int h=0;
		awhite[0]=awhite[1]=awhite[2]=awhite[3]=ablack[0]=ablack[1]=ablack[2]=ablack[3]=0;
		
		//����������������ǵ�HI��Ӧ����������
		for(i=0;i<12;i++){
			for(j=0;j<12;j++){
				if(boardArray[i][j]==(3-whoIsAI)){//�������˴�����HI
					k=i;l=j;m=1;
					while((boardArray[k-1][l-1]==0)&&(queenMoveHI[k-1][l-1]!=0))
					{awhite[n]=awhite[n]+mobility[k-1][l-1]/m;k--;l--;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k-1][l]==0)&&(queenMoveHI[k-1][l]!=0))
					{awhite[n]=awhite[n]+mobility[k-1][l]/m;k--;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k-1][l+1]==0)&&(queenMoveHI[k-1][l+1]!=0))
					{awhite[n]=awhite[n]+mobility[k-1][l+1]/m;k--;l++;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k][l-1]==0)&&(queenMoveHI[k][l-1]!=0))
					{awhite[n]=awhite[n]+mobility[k][l-1]/m;l--;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k][l+1]==0)&&(queenMoveHI[k][l+1]!=0))
					{awhite[n]=awhite[n]+mobility[k][l+1]/m;l++;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k+1][l-1]==0)&&(queenMoveHI[k+1][l-1]!=0))
					{awhite[n]=awhite[n]+mobility[k+1][l-1]/m;k++;l--;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k+1][l]==0)&&(queenMoveHI[k+1][l]!=0))
					{awhite[n]=awhite[n]+mobility[k+1][l]/m;k++;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k+1][l+1]==0)&&(queenMoveHI[k+1][l+1]!=0))
					{awhite[n]=awhite[n]+mobility[k+1][l+1]/m;k++;l++;m=m*2;}
					n++;
				}

				if(boardArray[i][j]==whoIsAI){
					k=i;l=j;m=1;
					while((boardArray[k-1][l-1]==0)&&(queenMoveAI[k-1][l-1]!=0))
					{ablack[h]=ablack[h]+mobility[k-1][l-1]/m;k--;l--;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k-1][l]==0)&&(queenMoveAI[k-1][l]!=0))
					{ablack[h]=ablack[h]+mobility[k-1][l]/m;k--;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k-1][l+1]==0)&&(queenMoveAI[k-1][l+1]!=0))
					{ablack[h]=ablack[h]+mobility[k-1][l+1]/m;k--;l++;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k][l-1]==0)&&(queenMoveAI[k][l-1]!=0))
					{ablack[h]=ablack[h]+mobility[k][l-1]/m;l--;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k][l+1]==0)&&(queenMoveAI[k][l+1]!=0))
					{ablack[h]=ablack[h]+mobility[k][l+1]/m;l++;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k+1][l-1]==0)&&(queenMoveAI[k+1][l-1]!=0))
					{ablack[h]=ablack[h]+mobility[k+1][l-1]/m;k++;l--;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k+1][l]==0)&&(queenMoveAI[k+1][l]!=0))
					{ablack[h]=ablack[h]+mobility[k+1][l]/m;k++;m=m*2;}

					k=i;l=j;m=1;
					while((boardArray[k+1][l+1]==0)&&(queenMoveAI[k+1][l+1]!=0))
					{ablack[h]=ablack[h]+mobility[k+1][l+1]/m;k++;l++;m=m*2;}
					h++;
				}
			}
		}
	}

	public int ec = 0;
	public double evaluation(){
		ec++;
		double value;
		nside=side;
		kingSearch();
		queenSearch();
		mobilitysearch();
		t1account();t2account();c1account();c2account();
		wacount();
		//aaccount();
		// t1=rand()%5; t2=rand()%15;  c1=rand()%15; c2=rand()%125;
		double a=(5/(w+5));
		double b=(w/(w+20));
		double c=(1-(a+b))/2;
//	    double m=(w/250)*(awhite[0]+awhite[1]+awhite[2]+awhite[3])-(w/200)*(ablack[0]+ablack[1]+ablack[2]+ablack[3]);
//	    cout<<"a,b,c,w,m"<<endl;
//		cout<<a<<" "<<b<<" "<<c<<" "<<w<<" "<<m<<endl;
//		cout<<"t1,t2,c1,c2"<<endl;
//		cout<<t1<<" "<<t2<<" "<<c1<<" "<<c2<<endl;

		value=a*t1+b*(t2/2)+c*((c1+c2)/2);	
		if(side==0)
			return value;
		else
			return -value;
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
