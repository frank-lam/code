package com.lk.example;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []a={4,8,7,2,9,4,21,41,11,19};
		
		quick_sort(a,0,a.length-1);
		
		for(int i =0;i<a.length;i++){
			System.out.print(a[i]+"  ");
		}
	}

	 //快速排序
	static void quick_sort(int s[], int left, int right)
	{
	    if (left < right)
	    {
			int i = left, j = right, x = s[left];
	        while (i < j)
	        {
	            while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
					j--;  
	            if(i < j) 
					s[i++] = s[j];
				
	            while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
					i++;  
	            if(i < j) 
					s[j--] = s[i];
	        }
	        s[i] = x;
	        quick_sort(s, left, i - 1); // 递归调用 
	        quick_sort(s, i + 1, right);
	    }
	}
}
