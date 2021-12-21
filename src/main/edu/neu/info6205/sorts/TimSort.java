package edu.neu.info6205.sorts;
import edu.neu.info6205.utils.SortUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Credits: geeksforgeeks for reference to build custom implementation
 *  https://www.geeksforgeeks.org/timsort/
 */
public class TimSort
{
    private static int cutoff=128;
    public static void sort(String[]a, String[] chinese)
    {
        sort(a, chinese, a.length);
    }
    public static void doSort(String[] chinese)
    {
        try {
            String[] chineseConv = SortUtils.convertToPinyin(chinese);
            sort(chineseConv, chinese);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
    }

    private static void sort(String[] a, String[] chinese,int n)
    {
        int m=minLength(n);
        for(int i=0;i<n;i+=m)
        {
            insertionSort(a,chinese,i,Math.min((i+m-1),n-1));
        }
        for(int s=m;s<n;s=2*s)
        {
            for(int l=0;l<n;l+=2*s)
            {
                int o=l+s-1;
                int p=Math.min((l+2*s-1),(n-1));
                if(o<p)
                {
                    mergeSort(a,chinese,l,o,p);
                }
            }
        }
    }
    private static int minLength(int n)
    {
        int temp=0;
        while(n>=cutoff)
        {
            temp|=(n &1);
            n>>=1;
        }
        return n+temp;
    }
    private static void insertionSort(String[] a,String[] chinese, int l, int r)
    {
        for(int i=l+1;i<=r;i++)
        {
            String temp=a[i];
            String temp1 = chinese[i];
            int j=i-1;
            while(j>=l && SortUtils.greaterThan(a[j], temp))
            {
                SortUtils.swap(a, j, j+1);
                SortUtils.swap(chinese, j, j+1);
                j--;
            }
            a[j+1]=temp;
            chinese[j+1]=temp1;
        }
    }
    private static void mergeSort(String[] a, String[] chinese, int l, int m,int r)
    {
        int l1=m-l+1, l2=r-m;
        String[] left= new String[l1];
        String[] right= new String[l2];
        String[] left1= new String[l1];
        String[] right1= new String[l2];
        for(int t=0;t<l1;t++)
        {
            left[t]=a[l+t];
            left1[t] = chinese[l+t];
        }
        for(int t=0;t<l2;t++)
        {
            right[t]=a[m+1+t];
            right1[t] = chinese[m+1+t];
        }
        int i=0,j=0,k=l;
        while(i<l1 && j<l2)
        {
            if(SortUtils.greaterThan(right[j], left[i]))
            {
                a[k]=left[i];
                chinese[k] = left1[i];
                i++;
            }
            else
            {
                chinese[k] = right1[j];
                a[k]=right[j];
                j++;
            }
            k++;
        }
        while(i<l1)
        {
            chinese[k] = left1[i];
            a[k]=left[i];
            k++;
            i++;
        }
        while(j<l2)
        {
            chinese[k] = right1[j];
            a[k]=right[j];
            k++;
            j++;
        }
    }
}
