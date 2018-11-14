package aab180004;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class SP11 {

    public static void insertionSort(int[] arr) {
        insertionSort(arr, 0, arr.length - 1);
    }

    private static void insertionSort(int[] arr, int p, int r) {
        int arrLength = r - p + 1;
        if (arrLength >= 2) {
            int i = p + 1;
            int temp, j;
            while (i <= r) {
                temp = arr[i];
                j = i - 1;
                while (j >= p && arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = temp;
                i++;
            }
        }
    }

    public static int select(int[] arr, int k){
        return select(arr, 0, arr.length, k);
    }

    private static int select (int[] arr, int p, int n, int k){
        if(n < 1 ){
            insertionSort(arr, p, p+n-1);
            return arr[p + n - k];
        }else{
            int q = randomizedPartition(arr,p,p+n-1);
            int left = q-p;
            int right = n - left - 1;
            if(right >= k)
                return select(arr,q+1,right,k);
            else if(right + 1 == k )
                return arr[q];
            else
                return select(arr,p,left,k-right-1);
        }
    }

    private static void exchange(int[] A, int i, int j) {
        if(A[i] == A[j]) return;

        A[i] = A[i] ^ A[j];
        A[j] = A[i] ^ A[j];
        A[i] = A[i] ^ A[j];
    }

    public static int selectUsingPQ(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i;
        for(i=0; i<k ; i++){
            pq.add(arr[i]);
        }
        while(i<arr.length){
            if(arr[i] > pq.peek()){
                pq.add(arr[i]);
                pq.remove();
            }
            i++;
        }
        return pq.peek();
    }

    private static int randomizedPartition(int[] A, int p, int r) {
        // Select pivot x uniformly from A[p...r]
        Random rand = new Random();
        int idx = p+rand.nextInt(r-p+1);
        int x = A[idx];

        int i = p;
        int j = r;

        // LI:A[p...i] ≤ x, A[j...r] ≥ x
        while (true) {

            while (A[i] < x) i++;
            while (A[j] > x) j--;

            if (i >= j) return j;

            exchange(A, i, j);
        }
    }

    public static void main(String[] args){
        int size = 8000000;
        //int size = 10;

        int[] arr = new int[size];
        Random rand = new Random();
        for(int i=0 ; i< size; i++){
            arr[i] = rand.nextInt();
        }
        System.out.println(select(arr,size/2));
        System.out.println(selectUsingPQ(arr,size/2));
        Arrays.sort(arr);
        System.out.println(arr[size/2]);
    }
}
