package no.hvl.dat102.oblig2.InnsertionSort;

import java.util.Random;

public class Innsetting {
	
	public static void insertSortOrginal(Integer[] tabell) {
		int n = tabell.length;
		
		for(int i = 1; i < n; i++) {
			Integer temp = tabell[i];
			int j = i-1;
			
			while(j >= 0 && tabell[j].compareTo(temp)>0) {
				tabell[j+1] = tabell[j];
				j--;
			}
			tabell[j+1]= temp;
		}
	}
	public static void sittMinVerdiPaIndexO(Integer[] tabell) {
		int n = tabell.length;
		int minIndex = 0;
		//finne indexen til minste i tabell
		for(int i = 1; i < n;i++) {
			if(tabell[i].compareTo(tabell[minIndex])<0) {
				minIndex = i;
			}
		}
		Integer minVerdi = tabell[minIndex];
		tabell[minIndex] = tabell[0];
		tabell[0] = minVerdi;
	}
	
	public static void InsertForenklet(Integer[] tabell) {
		sittMinVerdiPaIndexO(tabell);
		int n = tabell.length;
		for(int i = 1; i < n; i++) {
			Integer temp = tabell[i];
			int j = i-1;
			
			while(tabell[j].compareTo(temp)>0) {
				tabell[j+1] = tabell[j];
				j--;
			}
			tabell[j+1]= temp;
		}
	}
	
	
	public static void insertToElementerOmGangen(Integer[] tabell) {
        sittMinVerdiPaIndexO(tabell);
        int n = tabell.length;

        for (int i = 1; i < n - 1; i += 2) {
            int minIndex = tabell[i].compareTo(tabell[i + 1]) <= 0 ? i : i + 1;
            int maxIndex = i + 1 - minIndex + i;
            Integer min = tabell[minIndex];
            Integer max = tabell[maxIndex];

            int j = i - 1;
            while (tabell[j].compareTo(max) > 0) {
                tabell[j + 2] = tabell[j];
                j -= 2;
            }
            tabell[j + 2] = max;

            if (minIndex != i) {
                j = i;
            } else {
                j = i - 1;
            }
            while (tabell[j].compareTo(min) > 0) {
                tabell[j + 1] = tabell[j];
                j--;
            }
            tabell[j + 1] = min;
        }

        // Håndter siste element hvis n er et oddetall
        if (n % 2 != 0) {
            Integer last = tabell[n - 1];
            int j = n - 2;
            while (tabell[j].compareTo(last) > 0) {
                tabell[j + 1] = tabell[j];
                j--;
            }
            tabell[j + 1] = last;
        }
    }
	
//////////////////////////////////////////////////////////////
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

        // Mål tiden for standard insertion sort
        long startTidStandard = System.nanoTime();
        for (int i = 0; i < antall; i++) {
            Integer[] kopi = a[i].clone();
            insertSortOrginal(kopi);
        }
        long sluttTidStandard = System.nanoTime();

        // Mål tiden for oppgave a) insertion sort
        long startTidForenklet = System.nanoTime();
        for (int i = 0; i < antall; i++) {
            Integer[] kopi = a[i].clone();
            InsertForenklet(kopi);
        }
        long sluttTidForenklet = System.nanoTime();

        // Mål tiden for oppgave c) insertion sort
        long startTidOptimalisert = System.nanoTime();
        for (int i = 0; i < antall; i++) {
            Integer[] kopi = a[i].clone();
            insertToElementerOmGangen(kopi);
        }
        long sluttTidOptimalisert = System.nanoTime();

        // Gjenomsnitt
        long gjennomsnittTidStandard = (sluttTidStandard - startTidStandard) / antall;
        long gjennomsnittTidForenklet = (sluttTidForenklet - startTidForenklet) / antall;
        long gjennomsnittTidOptimalisert = (sluttTidOptimalisert - startTidOptimalisert) / antall;

        System.out.println("Gjennomsnittlig tid for standard insertion sort: " + gjennomsnittTidStandard/1000000 + " MiliSec");
        System.out.println("Gjennomsnittlig tid for forenklet insertion sort: " + gjennomsnittTidForenklet/1000000 + " MiliSec");
        System.out.println("Gjennomsnittlig tid for optimalisert insertion sort: " + gjennomsnittTidOptimalisert/1000000 + " MiliSec");
    }
}