package com.lk.example;

public class MergeSort {
//合并排序
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []a={4,8,7,2,9,4,21,41,11,19};
		
		Merge_Sort(a,a.length);
			
		for(int i =0;i<a.length;i++){
			System.out.print(a[i]+"  ");
		}
		
	}
	
	//将有序数组a[]和b[]合并到c[]中
	void MemeryArray(int a[], int n, int b[], int m, int c[])
	{
		int i, j, k;

		i = j = k = 0;
		while (i < n && j < m)
		{
			if (a[i] < b[j])
				c[k++] = a[i++];
			else
				c[k++] = b[j++]; 
		}

		while (i < n)
			c[k++] = a[i++];

		while (j < m)
			c[k++] = b[j++];
	}
	

	//将有二个有序数列a[first...mid]和a[mid...last]合并。
	static void mergearray(int a[], int first, int mid, int last, int temp[])
	{
		int i = first, j = mid + 1;
		int m = mid,   n = last;
		int k = 0;
		
		while (i <= m && j <= n)
		{
			if (a[i] <= a[j])
				temp[k++] = a[i++];
			else
				temp[k++] = a[j++];
		}
		
		while (i <= m)
			temp[k++] = a[i++];
		
		while (j <= n)
			temp[k++] = a[j++];
		
		for (i = 0; i < k; i++)
			a[first + i] = temp[i];
	}
	
	static void mergesort(int a[], int first, int last, int temp[])
	{
		if (first < last)
		{
			int mid = (first + last) / 2;
			mergesort(a, first, mid, temp);    //左边有序
			mergesort(a, mid + 1, last, temp); //右边有序
			mergearray(a, first, mid, last, temp); //再将二个有序数列合并
		}
	}

	private static void Merge_Sort(int a[], int n)
	{
		int []p = new int[n];
		mergesort(a, 0, n - 1, p);
	}

}
