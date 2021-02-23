package ca.bcit;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Homework - Sorting
 * Sort the list of doubles in the fastest possible way.
 * The only method you can change is the sort() method.
 * You can add additional methods if needed, without changing the load() and test() methods.
 */
public class Sorting {

    protected List<Integer> list = new ArrayList<Integer>();

    /**
     * Loading the text files with double numbers
     */
    protected void load() {
        try (Stream<String> stream = Files.lines(Paths.get("numbers.txt"))) {
            stream.forEach(x -> list.add(Integer.parseInt(x)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing of your solution, using 100 shuffled examples
     *
     * @return execution time
     */
    protected double test() {
        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < 100; i++) {
            Collections.shuffle(list, new Random(100));
            sort(list);
        }
        return watch.elapsedTime();
    }

    /**
     * Sorting method - add your code in here
     *
     * @param list - list to be sorted
     */
    private void sort(List list) {

        HoarsQuickSort(list,0, list.size()-1); // best result

        //optimizedQuickSort(list,0, list.size()-1);// best result

       // tailRecursiveQuicksort(list,0, list.size()-1);

        // QuickSort(list,0, list.size()-1);

       // RandomQuickSort(list,0, list.size()-1); //with random

        // Collections.sort(list);

       // dualPivotQuickSort(list,0, list.size()-1);

       // mergsort(list, 0, list.size()-1);
      //  heapsort(list);



       //take very very long time
       // bubbleSort(list);
      //  insertionSort(list,0, list.size()-1);
      //SelectionSort(list);


    }















    public static void insertionSort(List<Integer> arr, int low, int n)
    {
        // Start from second element (element at index 0
        // is already sorted)
        for (int i = low + 1; i <= n; i++)
        {
            int value = arr.get(i);
            int j = i;

            // Find the index j within the sorted subset arr[0..i-1]
            // where element arr[i] belongs
            while (j > low && arr.get(j - 1) > value)
            {
                arr.set(j, arr.get(j - 1));
                j--;
            }
            // Note that subarray arr[j..i-1] is shifted to
            // the right by one position i.e. arr[j+1..i]

            arr.set(j,  value);
        }
    }


////////////Quick Sort//////////////
    public static void QuickSort(List<Integer>a, int low, int high)
    {
        // base condition
        if (low >= high)
            return;

        // rearrange the elements across pivot
        int pivot = Partition_2 (a, low, high);

        // recur on sub-array containing elements less than pivot
        QuickSort(a, low, pivot - 1);

        // recur on sub-array containing elements more than pivot
        QuickSort(a, pivot + 1, high);
    }

    ////////////optimized Quick Sort//////////////
  public static void optimizedQuickSort(List<Integer> A, int low, int high)
    {
        while (low < high)
        {
            // do insertion sort if 10 or smaller
            if (high - low < 10)
            {
                insertionSort(A, low, high);
                break;
            }
            else
            {
                int pivot = Partition_2 (A, low, high);

                // tail call optimizations - recur on smaller sub-array
                if (pivot - low < high - pivot) {
                    optimizedQuickSort(A, low, pivot - 1);
                    low = pivot + 1;
                } else {
                    optimizedQuickSort(A, pivot + 1, high);
                    high = pivot - 1;
                }
            }
        }
    }
    public static int Partition_2 (List<Integer>a, int low, int high){
        // Pick rightmost element as pivot from the array
        int pivot = a.get(high);
        // elements less than pivot will be pushed to the left of pIndex
        // elements more than pivot will be pushed to the right of pIndex
        // equal elements can go either way
        int pIndex = low;
        // each time we finds an element less than or equal to pivot,
        // pIndex is incremented and that element would be placed
        // before the pivot.
        for (int i = low; i < high; i++)
        {
            if (a.get(i) <= pivot)
            {
                int temp = a.get(i);
                a.set(i,a.get(pIndex));
                a.set(pIndex,temp);

                pIndex++;
            }
        }
        // swap pIndex with Pivot
        int temp = a.get(high);
        a.set(high,a.get(pIndex)) ;
        a.set(pIndex,temp) ;
        // return pIndex (index of pivot element)
        return pIndex;
    }





    /////////////////RandomQuickSort/////////
    // Quicksort routine
    void RandomQuickSort(List<Integer>a ,int start, int end)
    {
        // base condition
        if (start >= end)
            return;

        // rearrange the elements across pivot
        int pivot = RandomizedPartition(a, start, end);

        // recur on sub-array containing elements that are less than pivot
        RandomQuickSort(a, start, pivot - 1);

        // recur on sub-array containing elements that are more than pivot
        RandomQuickSort(a, pivot + 1, end);
    }

    // using randomized partition
    int RandomizedPartition(List<Integer>a, int start, int end)
    {    // choose a random index between [start, end]
        Random rand= new Random();
        int pivotIndex = rand.nextInt(end-start)+start;



        // swap the end element with element present at random index

        int temp = a.get(pivotIndex);
        a.set(pivotIndex,a.get(end)) ;
        a.set(end,temp);

        // call partition procedure
        return Partition(a, start, end);
    }




    //////////tailRecursiveQuicksort//

    void tailRecursiveQuicksort(List<Integer>A, int start, int end)
    {
        while (start < end)
        {
            int pivot = Partition(A, start, end);

            // recur on smaller sub-array
            if (pivot - start < end - pivot)
            {
                tailRecursiveQuicksort(A, start, pivot - 1);
                start = pivot + 1;
            }
            else
            {
                tailRecursiveQuicksort(A, pivot + 1, end);
                end = pivot - 1;
            }
        }
    }

   int Partition(List<Integer>a, int start, int end)
    {        // Pick rightmost element as pivot from the array
        int pivot = a.get(end);
        // elements less than pivot will be pushed to the left of pIndex
        // elements more than pivot will be pushed to the right of pIndex
        // equal elements can go either way
        int pIndex = start;
        // each time we finds an element less than or equal to pivot, pIndex
        // is incremented and that element would be placed before the pivot.
        for (int i = start; i < end; i++)
        {
            if (a.get(i)  <= pivot)
            {
                int temp = a.get(i);
                a.set(i,a.get(pIndex));
                a.set(pIndex,temp);
                pIndex++;
            }
        }
        // swap pIndex with Pivot
        int temp = a.get(end);
        a.set(end,a.get(pIndex)) ;
        a.set(pIndex,temp);
        // return pIndex (index of pivot element)
        return pIndex;
    }

    ////////////////////////////////////////////////

    // Quicksort routine
    void HoarsQuickSort(List<Integer>a, int low, int high)
    {
        // base condition
        if (low >= high)
            return;

        // rearrange the elements across pivot
        int pivot = Partitionhoares(a, low, high);

        // recur on sub-array containing elements that are less than pivot
        HoarsQuickSort(a, low, pivot);

        // recur on sub-array containing elements that are more than pivot
        HoarsQuickSort(a, pivot + 1, high);
    }

    int Partitionhoares(List<Integer> a, int low, int high)
    {
        int pivot = a.get(low);
        int i = low - 1;
        int j = high + 1;
        while (true)
        {
            do {
                i++;
            } while (a.get(i) < pivot);

            do {
                j--;
            } while (a.get(j) > pivot);

            if (i >= j)
                return j;

            //swap
            int temp = a.get(i);
            a.set(i,a.get(j));
            a.set(j,temp);
        }
    }


//////////////////dualPivotQuickSort///////////////////////////////////////////

    static void swap(List<Integer>arr, int i, int j)
    {
        int temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }

    static void dualPivotQuickSort(List<Integer> arr,  int low, int high)
    {
        if (low < high)
        {

            // piv[] stores left pivot and right pivot.
            // piv[0] means left pivot and
            // piv[1] means right pivot
            int[] piv;
            piv = partition_3(arr, low, high);

            dualPivotQuickSort(arr, low, piv[0] - 1);
            dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1);
            dualPivotQuickSort(arr, piv[1] + 1, high);
        }
    }

    static int[] partition_3(List<Integer>arr, int low, int high)
    {
        if (arr.get(low) > arr.get(high))
            swap(arr, low, high);
        // p is the left pivot, and q
        // is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1,
                p = arr.get(low), q = arr.get(high);

        while (k <= g)
        {
            // If elements are less than the left pivot
            if (arr.get(k) < p)
            {
                swap(arr, k, j);
                j++;
            }
            // If elements are greater than or equal
            // to the right pivot
            else if (arr.get(k) >= q)
            {
                while (arr.get(g) > q && k < g)
                    g--;

                swap(arr, k, g);
                g--;

                if (arr.get(k) < p)
                {
                    swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;
        // Bring pivots to their appropriate positions.
        swap(arr, low, j);
        swap(arr, high, g);
        // Returning the indices of the pivots
        // because we cannot return two elements
        // from a function, we do that using an array.
        return new int[] { j, g };
    }
////////////////////////////////////////////////////



    public static void SelectionSort(List<Integer> arr)
    {
        int n = arr.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr.get(j) < arr.get(min_idx))
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr.get(min_idx);
            arr.set(min_idx ,arr.get(i));
            arr.set(i ,temp);
        }
    }
////////////////////////////////
        public static void bubbleSort(List<Integer> arr)
        {
            // (arr.length - 1) pass
            for (int k = 0; k < arr.size() - 1; k++)
            {
                // last k items are already sorted, so inner loop can
                // avoid looking at the last k items
                for (int i = 0; i < arr.size() - 1 - k; i++) {
                    if (arr.get(i) > arr.get(i + 1)) {
                        int temp = arr.get(i);
                        arr.set(i,arr.get(i+1));
                        arr.set(i+1 ,temp);
                    }
                }

                // the algorithm can be stopped if the inner loop
                // didn’t do any swap
            }
        }

    ////////////////////////////////
    public void heapsort(List<Integer> arr)
    {
        int n = arr.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>0; i--)
        {
            // Move current root to end
            int temp = arr.get(0);
            arr.set(0 ,arr.get(i));
            arr.set(i,temp);

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(List<Integer> arr, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr.get(l) > arr.get(largest))
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr.get(r) > arr.get(largest))
            largest = r;

        // If largest is not root
        if (largest != i)
        {
            int swap = arr.get(i);
            arr.set(i ,arr.get(largest));
            arr.set(largest ,swap);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

//////////////////////////////
void merge(List<Integer> arr, int l, int m, int r){
   // Find sizes of two subarrays to be merged
    int n1 = m - l + 1;
    int n2 = r - m;
    /* Create temp arrays */
    int L[] = new int[n1];
    int R[] = new int[n2];
    /*Copy data to temp arrays*/
    for (int i = 0; i < n1; ++i)
        L[i] = arr.get(l + i);
    for (int j = 0; j < n2; ++j)
        R[j] = arr.get(m + 1 + j);
    /* Merge the temp arrays */
    // Initial indexes of first and second subarrays
    int i = 0, j = 0;
    // Initial index of merged subarry array
    int k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr.set(k ,L[i]);
            i++;        }
        else {
            arr.set(k ,R[j]);
            j++;        }
        k++;    }
    /* Copy remaining elements of L[] if any */
    while (i < n1) {
        arr.set(k ,L[i]);
        i++;
        k++;    }
    /* Copy remaining elements of R[] if any */
    while (j < n2) {
        arr.set(k , R[j]);
        j++;
        k++;    }}

    // Main function that sorts arr[l..r] using
    // merge()
    void mergsort(List<Integer> arr, int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergsort(arr, l, m);
            mergsort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

}


