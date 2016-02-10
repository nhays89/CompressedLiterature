/* Authors: Nicholas Hays and Ethan Rowell
 * Date: 2/9/2016
 * Assignment 3: Compressed Literature
 * Presented For: Dr. Chris Marriott
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder str = new StringBuilder();
		try {
			
			InputStream inputStream = new FileInputStream("WarAndPeace.txt");
			Reader diffReader = new InputStreamReader(inputStream, Charset.defaultCharset());
			Reader reader2 = new BufferedReader(diffReader);
			int r;
			while((r = reader2.read()) != -1) {
				char ch = (char) r;
				str.append(ch);
			}
			
			// reader = new BufferedReader(new FileReader("WarAndPeace.txt"));
			/*while (reader.ready()) {
				str.append(reader.readLine() + System.lineSeparator());
			}*/
			reader2.close();
			// reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// decodeTree();
		
		CodingTree<Character> tree = new CodingTree<Character>(str.toString());
		
		System.out.println("Total char count: " + tree.charFreq.size());
		
		writeCodes(tree, str);

	}
	
	private static void writeCodes(CodingTree<Character> tree, StringBuilder sBuilder) {
		StringBuilder codes = new StringBuilder();
		codes.append('{');
		for(Character code: tree.codes.keySet()) {
			codes.append(code + "=" + tree.codes.get(code)+ ", " + "\n");
		}
		codes.append('}');
		System.out.println(codes.toString());
		
		BufferedWriter bw, bw2;
		try {
			bw = new BufferedWriter(new FileWriter("our_codes.txt"));
			bw.write(codes.toString());
			
			bw2 = new BufferedWriter(new FileWriter("compressed.txt"));
			
			for (Character character : sBuilder.toString().toCharArray()) {
				// bw2.write(tree.codes.get(character));
				Byte byte1 = Byte.parseByte(tree.codes.get(character));
				bw2.write(byte1);
				//tree.bits.add(byte1);
				
			}
			
			bw.close();
			bw2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void decodeTree() {
		File file = new File("");
		
	}
}
