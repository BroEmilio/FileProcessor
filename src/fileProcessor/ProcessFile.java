package fileProcessor;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;
import java.nio.charset.Charset;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javax.swing.JOptionPane;

public class ProcessFile {
	Path loadedFile, tempFile, savingFile;
	SavingFileProfile saver;
	//Path tempFilePath = Paths.get(System.getProperty("user.home")+"\\Desktop");
	
	String processLineOfFile(String originalLine) {
        String[] splitLine = originalLine.split("\\s+");
        String pointNumber = splitLine[1];
        
        Pattern pointsPrefixPattern = Pattern.compile("\\d{3}\\.\\d{3}-"); //erase prefix (example 223.112-)
        Matcher matcher = pointsPrefixPattern.matcher(pointNumber);
        String noPrefixNumber = matcher.replaceAll("").toString();
        
        Pattern slashPattern = Pattern.compile("[/]");	//replace / by -
        matcher = slashPattern.matcher(noPrefixNumber);
        String finalNumber = matcher.replaceAll("-");
        
        String newLine = "";
        for(int splitIndex=1; splitIndex<splitLine.length; splitIndex++)
        	newLine = newLine+splitLine[splitIndex]+"\t";
        
        newLine += finalNumber+"\r\n";
        
		return newLine;
	}
	
	public ProcessFile(Path file) {
		this.loadedFile = file;
	}
	
	boolean run() {
		BufferedReader reader;
		BufferedWriter writer;
		try {
			reader = Files.newBufferedReader(loadedFile, Charset.defaultCharset());
			tempFile = Files.createTempFile("tempProcessFile", ".txt");
			writer = Files.newBufferedWriter(tempFile, Charset.defaultCharset());
			String currentLine=null;
			while((currentLine = reader.readLine()) != null) {
				String newLine = processLineOfFile(currentLine);
				writer.write(newLine);
				writer.flush();
			}
			saver = new SavingFileProfile();
			saver.setNameLoadedFile(loadedFile.getFileName().toString());
			saver.setSavingFileProfile();
			savingFile = saver.getPath();
			Files.copy(tempFile.toAbsolutePath(), savingFile, REPLACE_EXISTING);
			Files.delete(tempFile);
		} catch (FileNotFoundException e) {
			displayErrorFrame(e.toString());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			displayErrorFrame(e.toString());
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	void displayErrorFrame(String errorMessege) {
		JOptionPane.showMessageDialog(null,
				"Wyst¹pi³ b³ad: \r\n"+errorMessege,
		        "Wyst¹pi³ b³¹d",
		        JOptionPane.ERROR_MESSAGE);
	}
	
}
