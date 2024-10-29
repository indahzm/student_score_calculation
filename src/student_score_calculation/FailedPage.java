package student_score_calculation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FailedPage {
	public void failedDownload() {
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("-----------------------Aplikasi Pengolah Nilai Siswa-----------------------");
		System.out.println("---------------------------------------------------------------------------\n");
		System.out.println("File tidak ditemukan \n");
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

}
