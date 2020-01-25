package fileProcessor;
import java.io.*;

import javax.swing.JOptionPane;

public class ProcessFile {
	File loadedFile, tempFile, savedFile;
	String tempFilePath = System.getProperty("user.home")+"\\Desktop\\postProcessTempFile.txt";
	
	String processLineOfFile(String originalLine) {
		// TODO implement here algorithm of process line and return them
		String newLine = originalLine + "XxX\r\n";
		return newLine;
	}
	
	public ProcessFile(File file) {
		this.loadedFile = file;
	}
	
	boolean run() {
		tempFile = new File(tempFilePath);
		BufferedReader reader=null;
		Writer writer = null;
		try {
			reader = new BufferedReader(new FileReader(loadedFile));
			writer = new BufferedWriter(new FileWriter(tempFile));
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				String newLine = processLineOfFile(currentLine);
				writer.write(newLine);
			}
		} catch (FileNotFoundException e) {
			displayErrorFrame(e.getMessage());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			displayErrorFrame(e.getMessage());
			e.printStackTrace();
			return false;
		}  finally {
					try {
						reader.close();
						writer.close();
					} catch (IOException e) {
						displayErrorFrame(e.getMessage());
						e.printStackTrace();
						return false;
					}
		}
		return true;
	}
	
	void displayErrorFrame(String errorMessage) {
		JOptionPane.showMessageDialog(null,
				"Wyst¹pi³ b³¹d:"+errorMessage,
		        "Wyst¹pi³ b³¹d",
		        JOptionPane.ERROR_MESSAGE);
	}
	
}
