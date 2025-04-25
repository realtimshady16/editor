import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class VersionControl{
	
		
	public static String sha1(String input) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hashBytes = md.digest(input.getBytes());

		StringBuilder hexString = new StringBuilder();
		for (byte b: hashBytes){
			hexString.append(String.format("%02x", b));
		}	
		return hexString.toString();
	}

	public static void main(String [] args) throws FileNotFoundException {
		final Scanner textScanner = new Scanner(System.in);
		System.out.print("Please commit something: ");
		DAG dag = new DAG();
		Commit commit = new Commit();
		//System.out.print("Enter a file: ");
		//String fileName = textScanner.nextLine();
		//Scanner fileScanner = new Scanner(new File(fileName));
		//StringBuilder sb = new StringBuilder();
		//while(fileScanner.hasNextLine()){
			sb.append(fileScanner.nextLine());
		//}
		//String result = sb.toString();
		//System.out.println(result);
		//


	}

}


