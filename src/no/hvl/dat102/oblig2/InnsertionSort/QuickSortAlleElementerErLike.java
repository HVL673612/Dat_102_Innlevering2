package no.hvl.dat102.oblig2.InnsertionSort;
public class QuickSortAlleElementerErLike {
	//Stack Overflow error: 
	public static void quickSort(Integer[] tabell, int low, int high) {
        if (low < high) {
            int pi = partition(tabell, low, high);
            quickSort(tabell, low, pi - 1);
            quickSort(tabell, pi + 1, high);
        }
    }
    private static int partition(Integer[] tabell, int low, int high) {
        Integer pivot = tabell[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (tabell[j].compareTo(pivot) < 0) {
                i++;
                Integer temp = tabell[i];
                tabell[i] = tabell[j];
                tabell[j] = temp;
            }
        }
        Integer temp = tabell[i + 1];
        tabell[i + 1] = tabell[high];
        tabell[high] = temp;
        return i + 1;
    }
    public static void main(String[] args) {
        int n = 32000; // størelse
        int antall = 10; // Antall Målinger
        Integer[] a = new Integer[n];

        // Tabell Generator
        for (int i = 0; i < n; i++) {
            a[i] = 1;
        }
        long startTidQuickSort = System.nanoTime();
        for (int i = 0; i < antall; i++) {
        	Integer[] kopi = a.clone();
        	quickSort(kopi,0,kopi.length-1);
        }
        long sluttTidQuickSort = System.nanoTime();
        long gjennomsnittTidQuickSort = (sluttTidQuickSort - startTidQuickSort) / antall;
        System.out.println("Gjennomsnittlig tid for Quick Sort: " + gjennomsnittTidQuickSort + " NanoSeconds");
    }
}