package es.florida.ane2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ANE3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a= Integer.parseInt(args[0]);
		int b=Integer.parseInt(args[1]);;
		int total=0;
		
		for (int i=a+1;i<b;i++) total+=i;
		System.out.println("Suma: "+total);
		
		try {
			//System.out.println("Lanzando ANE3...");
			FileWriter fw =new FileWriter("resultado.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(Integer.toString(total));
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
