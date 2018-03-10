package com.lk.example;
//矩阵连乘
public class MatrixChain {
	
	public static void main(String[] args)
	{
		int[] p={30,35,15,5,10,20,25};
		int n=6;
		int[][] m=new int[7][7];
		int[][] s=new int[7][7];
		//初始化m[i][i]
		for(int i=1;i<=n;i++){
			m[i][i]=0;
		}
		//求最优子结构
		for(int r=2;r<=n;r++){
			for(int i=1;i<=n-r+1;i++){
				int j=i+r-1;
				m[i][j]=m[i+1][j]+p[i-1]*p[i]*p[j];
				s[i][j]=i;
				
				for(int k=i+1;k<j;k++){
					int t=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
					if(t<m[i][j]){
						m[i][j]=t;
						s[i][j]=k;
					}
				}
			}
		}
//		输出m[i][j]
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				if(j>=i){
					System.out.print("m["+i+"]["+j+"]="+m[i][j]+"  ");
				}
				
			}
			System.out.println();
		}
//	  	 输出s[i][j]	
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				if(j>=i){
					System.out.print("s["+i+"]["+j+"]="+s[i][j]+"  ");
				}
				
			}
			System.out.println();
		}
		
		Traceback(1,6,s);
	}
//	输出序列
	public static void Traceback(int i,int j,int[][]s)
	{
		if(i==j) {
			return;
		}
		Traceback(i,s[i][j],s);
		Traceback(s[i][j]+1,j,s);
		System.out.println(" Multiply A"+i+","+s[i][j]+"and A"+(s[i][j]+1)+","+j);
	}
}
