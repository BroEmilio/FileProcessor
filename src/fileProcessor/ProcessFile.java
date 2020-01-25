package fileProcessor;
import java.io.*;

public class ProcessFile {
	File loadedFile;
	void processLineOfFile(String currentLine) {
		// TODO implement here algorithm of process line
	}
	
	public ProcessFile(File file) {
		this.loadedFile = file;
	}
		
	boolean loadAndProcessFile() {
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new FileReader(loadedFile));
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				processLineOfFile(currentLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}  finally {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
		}
		return true;
	}
	
}
