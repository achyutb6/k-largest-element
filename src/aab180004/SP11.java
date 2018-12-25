/**
 * Problem statement : Implement the expected O(n) algorithm for the k largest elements (select)
 *    of an array, and compare its performance with the algorithm using
 *    priority queues that we designed for the same problem on streams.
 *    Use k=n/2 (median), and try large values of n: 16M, 32M, 64M, 128M, 256M.
 *
 * @author Achyut Bhandiwad(aab180004)
 * @author Saurav Sharma (sxs179830)
 */

package aab180004;

import java.util.PriorityQueue;
import java.util.Random;

public class SP11 {


    /**
     * Insertion sort
     * @param arr
     */
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

    /**
     * Implementation of select algorithm O(n)
     * @param arr
     * @param k
     * @return value of kth largest element
     */
    public static int select(int[] arr, int k){
        return select(arr, 0, arr.length, k);
    }

    /**
     * Implementation of select algorithm O(n)
     * @param arr
     * @param p
     * @param n
     * @param k
     * @return value of kth largest element
     */
    private static int select (int[] arr, int p, int n, int k){
        //threshold of 17
        if(n < 17 ){
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

    /**
     * Exchange the values at index i with j in the arr A
     * @param A array
     * @param i
     * @param j
     */
    private static void exchange(int[] A, int i, int j) {
        if(A[i] == A[j]) return;

        A[i] = A[i] ^ A[j];
        A[j] = A[i] ^ A[j];
        A[i] = A[i] ^ A[j];
    }

    /**
     * Select using the priority queue
     * @param arr
     * @param k
     * @return value of kth largest element
     */
    public static int selectUsingPQ(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i;

        //add first k elements in the priority queue
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

        // the top of the heap will be the kth largest element
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
        int size = 16000000;

        //create array of random elements
        int[] arr = new int[size];
        Random rand = new Random();
        for(int i=0 ; i< size; i++){
            arr[i] = rand.nextInt();
        }

        Timer timer1 = new Timer();
        Timer timer2 = new Timer();

        //select using the O(n) algorithm
        timer1.start();
        System.out.println(select(arr,size/2));
        timer1.end();
        System.out.println(timer1);

        //select using the priority queue
        timer2.start();
        System.out.println(selectUsingPQ(arr,size/2));
        timer2.end();
        System.out.println(timer2);

    }
}
