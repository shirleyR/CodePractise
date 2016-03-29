package sortAL;
import java.util.*;
public class SortMethod {
	/***
	 * 稳定：冒泡排序、插入排序、归并排序和基数排序

		不稳定：选择排序、快速排序、希尔排序、堆排序
	 * @param arr
	 * @param n
	 */
	
	
	public static void DirectInsert(int arr[]){
		int n=arr.length;
		for(int i=1;i<n;i++){
			int temp=arr[i];
			int j;
			for( j=i-1;j>=0;j--){
				if(arr[j]>temp){
					arr[j+1]=arr[j];
				}else {
					break;
				}
			}
			arr[j+1]=temp;

		}
	}
	public static void BiInsert(int arr[]){
		int n=arr.length;
		for(int i=1;i<n;i++){
			int temp=arr[i];
			int be=0;int en=i-1;
			int index=0;
			while(be!=en){
				int mid=(be+en)>>1;
		if(arr[mid]>temp){
			en=mid-1;
		}else {
			be=mid+1;
		}
			}
			for(int j=i-1;j>=be;j--){
				arr[j+1]=arr[j];
			}
			if(be!=i){
				arr[be]=temp;
			}

		}
	}


	public static void ChooseSort(int arr[]){
		for(int i=0;i<arr.length;i++){
			int min=i;
			for(int j=i+1;j<arr.length;j++){
				if(arr[min]>arr[j]) min=j;
			}
			if(min==i) continue;
			int temp=arr[min];
			arr[min]=arr[i];
			arr[i]=temp;
		}

	}
	//  BuildMaxHeap  HeapSort
	// need to search every inner node and arrive the leaf
	public static void heapSort(int arr[]){
		// 2*i+1 2*i+2 ---father i
		// how to find last inner node
		int n=arr.length;
		for(int s=n-1;s>=0;s--){
			int son=s;
			int fa=(son-1)/2;
			while(fa>=0){
				// Search every inner until 
				
				int max=0;
				// need to arrive leaf and 
				int k=fa;
				while(2*k+1<=s){
					if(k*2+2<=s){
						max=arr[k*2+1]>arr[k*2+2]?(k*2+1):(k*2+2);
					}else {
						max=2*k+1;
					}
					if(arr[k]<arr[max]){
						// exchange
						int temp=arr[k];
						arr[k]=arr[max];
						arr[max]=temp;
						k=max;
					}else break;
				}
				fa--;
			}
			
			int t=arr[0];
			arr[0]=arr[s];
			arr[s]=t;
		}

	}

	public static void bubbleSort(int arr[]){
		// big in last ,thin in front
		for(int i=0;i<arr.length;i++){
			boolean flag=true;
			for(int j=0;j<arr.length-i+1;j++){
				if(flag&&arr[j]>arr[j+1]){
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j]=temp;
				}else flag=false;
			}
		}
	}

	public static int getMiddle(int [] arr, int low ,int high){
		int temp=arr[low];
		while(low<high){
			while(low<high&&arr[high]>=temp){
				high--;
			}
			arr[low]=arr[high];
			while(low<high&&arr[low]<=temp){
				low++;
			}
			arr[high]=arr[low];
		}
		arr[low]=temp;
		return low;

	}
	public static void quickSort(int arr[] ,int low,int high ){
		// choose base 
		if(low<high){
			int middle=getMiddle(arr,low,high);
			quickSort(arr,0,middle-1);
			quickSort(arr,middle+1,high);
		}

	}
	public static void merge(int arr[],int low,int mid,int high){
		int temp[]=new int[arr.length];
		int mids=mid+1;
		int left=low;
		int right=low;
		while(left<=mid&&mids<high){
			if(arr[left]<=arr[mids]) temp[right++]=arr[left++];
			else temp[right++]=arr[mids++];
		}
		while(left<=mid){
			temp[right++]=arr[left++];
		}
		while(mids<=high){
			temp[right++]=arr[mids++];
		}
		for(int i=low;i<=high;i++){
			arr[i]=temp[i];
			
		}

	}

	public static void mergeSort(int arr[],int low,int high){
		if(low<high){
			int mid=(low+high)>>1;
			mergeSort(arr,low,mid);
			mergeSort(arr,mid+1,high);
			merge(arr,low,mid,high);

		}


	}
	public static void cardinalSort(int array[]){
		 int max = 0;
	        for (int i = 0; i < array.length; i++) {
	            if(max<array[i]){
	                max = array[i];
	            }
	        }
	        //判断位数
	        int times = 0;
	        while(max>0){
	            max = max/10;
	            times++;
	        }
	        //建立十个队列
	        Vector< Queue<Integer>> list = new Vector<Queue<Integer>>();
	        for (int i = 0; i < 10; i++) {
	            Queue<Integer> queue = new LinkedList<Integer>();
	            list.add(queue);
	        }
	        
	        
	        //进行times次分配和收集
	        for (int i = 0; i < times; i++) {
	            //分配
	            for (int j = 0; j < array.length; j++) {
	                int x = array[j]%(int)Math.pow(10, i+1)/(int)Math.pow(10, i);
	                Queue<Integer> queue2 = list.get(x);
	                queue2.add(array[j]);
	            }
	            //收集
	            int count = 0;
	            for (int j = 0; j < 10; j++) {
	                while(list.get(j).size()>0){
	                    Queue<Integer> queue3 = list.get(j);
	                    array[count] = queue3.poll();
	                    count++;
	                }
	            }
	        }
		
		
		
	}
	public static void Sprint(int arr[]){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
	public static void main(String args[]){
		int arr[]={100,200,230,123,450};
		ChooseSort(arr,arr.length);
		Sprint(arr);
	}


}
