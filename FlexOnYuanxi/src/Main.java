import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader reader = new BufferedReader( new InputStreamReader(System.in) );
	public static String prompt(String prompt) throws IOException {
		System.out.println(prompt);
		return reader.readLine();
	}
	
	public static void main(String[] args) throws IOException {
		String name1 = prompt("Enter Person 1's Name ");
		String height1 = prompt("Enter Person 1's Height ");
		String name2 = prompt("Enter Person 2's Name ");
		String height2 = prompt("Enter Person 2's Height ");
		System.out.println( 
			String.format(
				"%s is %scm tall, %s is %scm tall.", 
				name1, 
				height1, 
				name2, 
				height2
			)
		);
	}

}
