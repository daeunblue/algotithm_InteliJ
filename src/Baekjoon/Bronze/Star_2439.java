package Baekjoon.Bronze;
import java.io.*;

public class Star_2439 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			for(int k=1; k<N-i; k++) {
				System.out.print(" ");
			}
			for(int j=0; j<=i; j++) {	
				System.out.print("*");
			}
			
			System.out.println("");
		}
 	}

}
