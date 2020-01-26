package fileProcessor;

import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadingFileProfile {
	public LoadingFileProfile() {
		loadChooser = configureLoadChooser(loadFilter);
	}
	
	Path loadedFile;
	JFileChooser loadChooser;
	
	//set filter for files 
	FileNameExtensionFilter loadFilter = new FileNameExtensionFilter("Pliki tekstowe", "acs");

	
	JFileChooser configureLoadChooser(FileNameExtensionFilter filter) {
		String userDir = System.getProperty("user.home");
		JFileChooser chooser= new JFileChooser(userDir +"/Desktop");
		chooser.setFileFilter(filter);
    	chooser.setDialogTitle("Wybierz plik do przetworzenia");
    	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	chooser.setAcceptAllFileFilterUsed(false);
    	chooser.showOpenDialog(null);
    	loadedFile = chooser.getSelectedFile().toPath();
    	
    	return chooser;
	}
	
	public Path getPath() {
		return loadedFile;
	}
	
	

}
