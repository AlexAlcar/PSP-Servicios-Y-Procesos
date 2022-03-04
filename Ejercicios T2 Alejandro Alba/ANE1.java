package es.florida.ane2;

public class ANE1 {

	public static void main(String[] args) {
		//Scanner entrada = new Scanner(System.in);
		int a= Integer.parseInt(args[0]);
		int b=Integer.parseInt(args[1]);;
		int total=0;
		
		for (int i=a+1;i<b;i++) total+=i;
		System.out.println(total);

	}

}