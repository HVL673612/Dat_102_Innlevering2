package no.hvl.dat102.oblig2.InnsertionSort;

import java.util.Random;

public class SorteringsMetoder {

    public static void selectionSort(Integer[] tabell) {
        for (int i = 0; i < tabell.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < tabell.length; j++) {
                if (tabell[j].compareTo(tabell[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            Integer temp = tabell[minIndex];
            tabell[minIndex] = tabell[i];
            tabell[i] = temp;
        }
    }

    // Kvikksortering
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

    // Flettesortering
    public static void mergeSort(Integer[] tabell, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(tabell, l, m);
            mergeSort(tabell, m + 1, r);
            merge(tabell, l, m, r);
        }
    }

    private static void merge(Integer[] tabell, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];

        System.arraycopy(tabell, l, L, 0, n1);
        System.arraycopy(tabell, m + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                tabell[k] = L[i];
                i++;
            } else {
                tabell[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            tabell[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            tabell[k] = R[j];
            j++;
            k++;
        }
    }

    // Hovedmetoden for å kjøre sorteringsmetoder og måle tiden
    public static void main(String[] args) {
    	Random tilfeldig = new Random();
        int n = 32000; // størelse
        int antall = 10; // Antall Målinger
        Integer[][] a = new Integer[antall][n];

        // number Generator
        for (int i = 0; i < antall; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = tilfeldig.nextInt();
            }
        }
    	
    	
    	
        //Selection Sort//////////////////////////
        long startTidSelectionSort = System.nanoTime();
        for (int i = 0; i < antall; i++) {
        	Integer[] kopi = a[i].clone();
        	selectionSort(kopi);
        }
        long sluttTidSelectionSort = System.nanoTime();
        long gjennomsnittTidSelectionSort = (sluttTidSelectionSort - startTidSelectionSort) / antall;

        //Quick Sort  ///////////////////////////////////    
        long startTidQuickSort = System.nanoTime();
        for (int i = 0; i < antall; i++) {
        	Integer[] kopi = a[i].clone();
        	quickSort(kopi,0,kopi.length-1);
        }
        long sluttTidQuickSort = System.nanoTime();
        long gjennomsnittTidQuickSort = (sluttTidQuickSort - startTidQuickSort) / antall;

        //mergeSort/////////////////
        long startTidMergeSort = System.nanoTime();
        for (int i = 0; i < antall; i++) {
        	Integer[] kopi = a[i].clone();
        	mergeSort(kopi,0,kopi.length-1);
        }
        long sluttTidMergekSort = System.nanoTime();
        long gjennomsnittTidMergeSort = (sluttTidMergekSort - startTidMergeSort) / antall;


        // Skriv ut resultatene
        System.out.println("Gjennomsnittlig tid for selection sort: " + gjennomsnittTidSelectionSort + " NanoSeconds");
        System.out.println("Gjennomsnittlig tid for Quick Sort: " + gjennomsnittTidQuickSort + " NanoSeconds");
        System.out.println("Gjennomsnittlig tid for Merge Sort: " + gjennomsnittTidMergeSort + " NanoSeconds");
    }
}
