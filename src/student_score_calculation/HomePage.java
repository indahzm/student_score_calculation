package student_score_calculation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("resource")
public class HomePage {
	
	public void start() {
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("-----------------------Aplikasi Pengolah Nilai Siswa-----------------------");
		System.out.println("---------------------------------------------------------------------------\n");
		System.out.println("Letakan file csv dengan nama file data_sekolah di direktori berikut : \nC://temp/direktori \n");
		System.out.println("pilih menu : ");
		System.out.println("1. Generate txt untuk menampilkan modus");
		System.out.println("2. Generate txt untuk menampilkan nilai rata rata, median");
		System.out.println("3. Generate kedua file");
		System.out.println("0. Exit \n");
		
		boolean isCorrectMenu = false;
		while (!isCorrectMenu) {
			try {
				Scanner input = new Scanner(System.in);
				int menu = input.nextInt();
				if (menu > 3) {
					throw new InputMismatchException();
				}
				
				isCorrectMenu = true;
				
				if (menu == 0) {
					break;
				}
				readFile(menu);
			} catch (InputMismatchException e) {
				System.out.println("Menu tidak tersedia. Silakan pilih kembali.");
				isCorrectMenu = false;
			}
		}
		
		return;
		
	}
	
	private void readFile(int menu) {
		try {
			FileReader file = new FileReader("C://temp/direktori/data_sekolah.csv");
			BufferedReader br = new BufferedReader(file);
			String line = br.readLine();
			
			List<Integer> scoreList = new ArrayList<>();
			while(line != null) {
				String[] scores = line.split(";");
				for (int i = 0; i < scores.length; i++) {
					if (i != 0)
						scoreList.add(Integer.valueOf(scores[i]));
				}
				line = br.readLine();
			}
			
			new SuccesPage().successDownload(menu, scoreList);
			
		} catch (FileNotFoundException e) {
			new FailedPage().failedDownload();
		} catch (IOException e) {
			new FailedPage().failedDownload();
		}
	}
}
