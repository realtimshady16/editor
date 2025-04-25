import java.util.stream.IntStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.io.FileNotFoundException;

public class Editor{
	private static int WIDTH;
	private static int HEIGHT;
	private static boolean isClosed = false;
	private static char[][] inputArr;
	private static final Scanner inputScanner = new Scanner(System.in);
	private static String fileLine = "";
	private static String writeLine;
	private static Queue<char[]> inputBuffer = new LinkedList<>();
	private static char[] inputText;
	private static int lastPos = 0;
	private static Queue<char[]> fileTextBuffer = new LinkedList<>();

	public static void main(String [] args){
	try{
		clearScreen();
		inputArr = new char[HEIGHT][WIDTH];
		printWelcome();
		handleInput();
		parseInputToInputArray();
		printInputArr();


	}
	catch(IOException e){
		e.printStackTrace();
	}
	}
	private static void handleInput(){
		while(!isClosed){
			String command = inputScanner.next();
			switch (command){
				case "q":
					isClosed = true;
					break;
				case "write":
					boolean writing = true;
					while(writing){
						System.out.print("Writing:\n");
						writeLine = inputScanner.nextLine();
						int escapeCode = 27;
						char escapeKey = (char) escapeCode;
						int escapeIndex = writeLine.indexOf(escapeKey);
						inputText = escapeIndex == -1 ? writeLine.toCharArray() : writeLine.substring(0, escapeIndex).toCharArray();
						inputBuffer.add(inputText);
						if(escapeIndex != -1){
							break;
						}
					}
					//System.out.println("Input Buffer: " + inputBuffer.toString());
					parseInputToInputArray();
					printInputArr();
					break;
				case "save":
					System.out.println("Enter filename: ");
					inputScanner.nextLine();
					fileLine = inputScanner.nextLine();
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(".temp.txt", false));
						String text = "";
						for(int i = 0; i < inputArr.length; i++){
							for(int j = 0; j < inputArr[i].length; j++){
								text += Character.toString(inputArr[i][j]);
							}
						}
						writer.write(text);
						writer.close();
						if(!fileLine.equals("")){
						executeSaveCommands(fileLine);}
					}catch(IOException | InterruptedException e){
						System.err.println("Error executing command: " + e.getMessage());}
					
					break;
				case "open":
					System.out.println("Enter filename: ");
					inputScanner.nextLine();
					fileLine = inputScanner.nextLine();
					try{
						BufferedReader reader = new BufferedReader(new FileReader(fileLine));
						String line;
						while ((line = reader.readLine()) != null){
							char[] charArr = line.toCharArray();
							fileTextBuffer.add(charArr);
						}
					}catch(IOException e){
						System.err.println("Error reading from file: " + e.getMessage());
					}
					parseFileBufferToInputArray();
					printInputArr();
					break;
				default:
					printInputArr();

			}
				
			}
		}
	private static void executeSaveCommands(String fileLine) throws IOException, InterruptedException{
		String touchCommand = String.format("touch %s", fileLine);
						String catCommand = String.format("cat .temp.txt >> %s", fileLine);
						String[] commands = {touchCommand, catCommand};
						for(String fcommand: commands){
						Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", fcommand});
						//System.out.printf("Command complete: %s\n", fcommand);
						int exitCode = process.waitFor();}

	}
	private static synchronized void parseInputToInputArray(){
		int rowsNeeded = inputBuffer.size();
		int rows = rowsNeeded == 0 ? 1 : rowsNeeded;
		int start = lastPos;
		while(inputBuffer.peek() != null){
			char[] inputText = inputBuffer.poll();
			for(int k = 0; k < inputText.length; k++){
				if(k % WIDTH == 0 && start != 0){
					start++;
					inputArr[start][k % WIDTH] = inputText[k];
				}
				else{
					inputArr[start][k % WIDTH] = inputText[k];
				}
			}
			}
		lastPos += start;
		}
	private static synchronized void parseFileBufferToInputArray(){
		int rowsNeeded = fileTextBuffer.size();
		assert rowsNeeded != 0;
		System.out.println(rowsNeeded);
		int rows = rowsNeeded == 0 ? 1 : rowsNeeded;
		int start = 0; //lastPos;
		assert fileTextBuffer.peek() != null;
		while(fileTextBuffer.peek() != null){
			char[] inputText = fileTextBuffer.poll();
			//for(int k = 0; k < inputText.length; k++){
			//if(k % WIDTH == 0 && start != 0){
			inputArr[start] = inputText;
			start++;
				}

		lastPos += start;
			}
	private static synchronized void printInputArr(){
		assert !Arrays.stream(inputArr).flatMapToInt(arr -> IntStream.range(0, arr.length).map(i-> arr[i])).allMatch(x -> x == '\0'); //checks that there is at least one non-null character in inputArr. If all characters are null, raise AssertionError
		for(int i = 0; i < HEIGHT -1; i++){
			System.out.print(Integer.toString(i)+ " ");
			for(int j = 0; j < WIDTH; j++)
			{
				System.out.print(inputArr[i][j]);
			}
			System.out.print("\n");	
		}
		System.out.println("\n");
		
	}
	private static void printWelcome(){
		System.out.println("Welcome to the Editor");
		System.out.println("1. Read file ");
		System.out.println("2. Write file ");
		System.out.println("3. Quit Editor ");
	}

	private static void clearScreen() throws IOException{
	String[] commands = {"tput lines", "tput cols", "clear"};
	String[] nonprintable = {"tput lines", "tput cols"};
	for(String command: commands){
	Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
	BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	String line;
	if(command.equals("tput lines")){
		String lines = reader.readLine();
		HEIGHT = Integer.parseInt(lines);
	}
	else if(command.equals("tput cols")){
		String columns = reader.readLine();
		WIDTH = Integer.parseInt(columns);
	}
	else{while((line = reader.readLine()) != null){System.out.println(line);}
	try {
		process.waitFor();	
	}catch (InterruptedException e){
		Thread.currentThread().interrupt();
	}
	}}


}
}
