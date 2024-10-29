package student_score_calculation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SuccesPage {
	public void successDownload(int menu, List<Integer> scoreList) throws FileNotFoundException {
		
		processData(menu, scoreList);
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("-----------------------Aplikasi Pengolah Nilai Siswa-----------------------");
		System.out.println("---------------------------------------------------------------------------\n");
		System.out.println("File telah di generate di : \n C://temp/direktori \n");
		System.out.println("Silakan Cek \n ");
		System.out.println("0. Exit");
		System.out.println("1. Kembali ke menu utama \n");
		
		Scanner input = new Scanner(System.in);
		boolean isCorrectMenu = false;
		while (!isCorrectMenu) {
			try {
				
				int newMenu = input.nextInt();
				if (newMenu > 1) {
					throw new InputMismatchException();
				}
				
				isCorrectMenu = true;
				if (newMenu == 1) {
					new HomePage().start();
				} else {
					input.close();
					break;
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Menu tidak tersedia. Silakan pilih kembali.");
				isCorrectMenu = false;
			}
		}
		
		return;
		
	}
	
	public void processData(int menu, List<Integer> scoreList) throws FileNotFoundException {
		
		Collections.sort(scoreList);
		Integer total = 0;
		Map<Integer, Integer> modusMap = new HashMap<>();
		Integer modus = scoreList.get(0);
		Integer tempLenght = 0;
		for (Integer score : scoreList) {
			total += score;
			if (modusMap.containsKey(score)) {
				if (tempLenght < (modusMap.get(score)+1)) {
					tempLenght = (modusMap.get(score)+1);
					modus = score;
				}
				modusMap.put(score, modusMap.get(score)+1);
			} else {
				modusMap.put(score, 1);
			}
		}
		
		
		if (menu == 1) {
			generateFileModus(modusMap);
		} else {
			double scale = Math.pow(10, 2);
			Double mean = (double) total/scoreList.size();
			mean = Math.ceil(mean * scale) / scale;
			
			Double median = 0d;
			if (scoreList.size()%2 == 0) {
				median = (double) ((scoreList.get(scoreList.size()/2) + scoreList.get((scoreList.size()/2) + 1))/2);
			} else {
				median = (double) (scoreList.get(scoreList.size()/2 + 1));
			}
			
			if (menu == 2) {
				generateFileAll(mean, median, (double)modus);
			} else {
				generateFileModus(modusMap);
				generateFileAll(mean, median, (double)modus);
			}
		}

	}
	
	public void generateFileModus(Map<Integer, Integer> modusMap) throws FileNotFoundException {
		PrintWriter outputStream = null;
		
		
		outputStream = new PrintWriter(new FileOutputStream("C://temp/direktori/data_sekolah_modus.txt"));

		outputStream.println("Berikut Hasil Pengolahan Nilai:\n");
		outputStream.println("Nilai \t\t| Frekuensi");
		Integer valueLess6 = 0;
		for (Map.Entry<Integer, Integer> modus : modusMap.entrySet()) {
			if (modus.getKey() < 6) {
				valueLess6 += modus.getValue();
				if (modus.getKey().equals(5)) {
					outputStream.println("kurang dari 6 \t| " + valueLess6);
				}
			} else {
				outputStream.println(modus.getKey() + " \t\t| " + modus.getValue());
			}
			
		}
		
		outputStream.close();
	}
	
	public void generateFileAll(Double mean, Double median, Double modus) throws FileNotFoundException {
		PrintWriter outputStream = null;
		outputStream = new PrintWriter(new FileOutputStream("C://temp/direktori/data_sekolah_mean_median_modus.txt"));
		
		outputStream.println("Berikut Hasil Pengolahan Nilai:\n");
		outputStream.println("Berikut hasil sebaran data nilai");
		outputStream.println("Mean \t: " + mean);
		outputStream.println("Median \t: " + median);
		outputStream.println("Modus \t: " + modus);
		outputStream.close();
	}
}
