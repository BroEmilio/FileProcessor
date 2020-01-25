package fileProcessor;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class LoadingFileProfile {
	public LoadingFileProfile() {
		loadChooser = setLoadChooser(loadFilter);
	}
	
	File loadedFile;
	JFileChooser loadChooser;
	
	//set filter for files 
	FileFilter loadFilter = new FileFilter() {
		@Override
		public String getDescription() {
		  return "plik tekstowy (.txt)";
		}
		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) {
	            return true;
	        } else {
	            return f.getName().toLowerCase().endsWith(".txt");
	        }
		}
	};
	
	JFileChooser setLoadChooser(FileFilter filter) {
		String userDir = System.getProperty("user.home");
		JFileChooser chooser= new JFileChooser(userDir +"/Desktop");
		chooser.setFileFilter(filter);
    	chooser.setDialogTitle("Wybierz plik do przetworzenia");
    	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	chooser.setAcceptAllFileFilterUsed(false);
    	chooser.showOpenDialog(null);
    	loadedFile = chooser.getSelectedFile();
    	
    	return chooser;
	}
	
	public File getFile() {
		return loadedFile;
	}
	
	

}
