package sorts;


import java.util.Random;

public class Root {
    public final int[] randomArray = new int[50000];
    public int[] array = new int[50000];

    Root() {
        var random = new Random();

        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = random.nextInt(100000) + 1;
        }
    }

    public void showData() {
        for (int item : array) {
            System.out.println(item);
        }
    }

    public int[] bubbleSort() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]) {
                    var tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }

    public int[] insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }

    public int[] mergeSort(int[] arr, int lo, int hi) {

        if (lo == hi) {
            int[] br = new int[1];
            br[0] = arr[lo];

            return br;
        }

        int mid = (lo + hi) / 2;

        int[] fh = mergeSort(arr, lo, mid);
        int[] sh = mergeSort(arr, mid + 1, hi);

        return mergeTwoSortedArrays(fh, sh);
    }

    public int[] mergeTwoSortedArrays(int[] one, int[] two) {

        int[] sorted = new int[one.length + two.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < one.length && j < two.length) {

            if (one[i] < two[j]) {
                sorted[k] = one[i];
                k++;
                i++;
            } else {
                sorted[k] = two[j];
                k++;
                j++;
            }
        }

        if (i == one.length) {

            while (j < two.length) {
                sorted[k] = two[j];
                k++;
                j++;
            }
        }

        if (j == two.length) {

            while (i < one.length) {
                sorted[k] = one[i];
                k++;
                i++;
            }
        }

        return sorted;

    }

    public int[] quickSort(int[] array, int startIndex, int endIndex) {
        if(startIndex < endIndex) {
            int pivotIndex = getPivotIndex(array, startIndex, endIndex);
            quickSort(array, startIndex, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, endIndex);
        }

        return array;
    }

    private void swap(int[] array, int startIndex, int pivotIndex) {
        int tmp = array[startIndex];
        array[startIndex] = array[pivotIndex];
        array[pivotIndex] = tmp;
    }

    private int getPivotIndex(int[] array, int startIndex, int endIndex) {
        int pivotIndex = startIndex;
        int i;
        for(i = startIndex + 1; i <= endIndex; i++) {
            if(array[i] < array[startIndex]) {
                swap(array, ++pivotIndex, i);
            }
        }

        swap(array, startIndex, pivotIndex);
        return pivotIndex;
    }

    public int[] oddEvenSort(int[] arr, int n)
    {
        boolean isSorted = false;

        while (!isSorted)
        {
            isSorted = true;
            int temp =0;

            for (int i=1; i<=n-2; i=i+2)
            {
                if (arr[i] > arr[i+1])
                {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    isSorted = false;
                }
            }

            for (int i=0; i<=n-2; i=i+2)
            {
                if (arr[i] > arr[i+1])
                {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    isSorted = false;
                }
            }
        }

        return arr;
    }

    //TimSort
    static int MIN_MERGE = 32;
 
    public static int minRunLength(int n)
    {
        assert n >= 0;
       
        int r = 0;
        while (n >= MIN_MERGE)
        {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }
 
    public static void timSortInsertionSort(int[] arr, int left,
                                     int right)
    {
        for (int i = left + 1; i <= right; i++)
        {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp)
            {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
 
    public static void timSortMerge(int[] arr, int l,
                                 int m, int r)
    {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];
        for (int x = 0; x < len1; x++)
        {
            left[x] = arr[l + x];
        }
        for (int x = 0; x < len2; x++)
        {
            right[x] = arr[m + 1 + x];
        }
 
        int i = 0;
        int j = 0;
        int k = l;
 
        while (i < len1 && j < len2)
        {
            if (left[i] <= right[j])
            {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
 
        while (i < len1)
        {
            arr[k] = left[i];
            k++;
            i++;
        }
 
        while (j < len2)
        {
            arr[k] = right[j];
            k++;
            j++;
        }
    }
 
    public static int[] timSort(int[] arr, int n)
    {
        int minRun = minRunLength(MIN_MERGE);
       
        for (int i = 0; i < n; i += minRun)
        {
            timSortInsertionSort(arr, i,
                          Math.min((i + MIN_MERGE - 1), (n - 1)));
        }
 
        for (int size = minRun; size < n; size = 2 * size)
        {
 
            for (int left = 0; left < n;
                                 left += 2 * size)
            {
 
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                                     (n - 1));
 
                  if(mid < right)
                    timSortMerge(arr, left, mid, right);
            }
        }

        return arr;
    }

    public static int[] shakerSort(int[] arr, int n)
    {
        int begin = 0;
        int end = n - 1;
        while (begin < end) {
            for (int j = begin; j < end; j++) {
                if (arr[j + 1] < arr[j]) {
                    int t;
                    t = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = t;
                }
            }

            end--;
            for (int j = end; j > begin; j--) {
                if (arr[j] < arr[j-1]) {
                    int t;
                    t = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = t;
                }
            }
            begin++;
        }
        return arr;
    }

}
