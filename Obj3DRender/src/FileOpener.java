import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class FileOpener {

	public static File getFileInput(FileNameExtensionFilter filter) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("File: " + chooser.getSelectedFile().getPath());
			return chooser.getSelectedFile();
        }
		return null;
	}
	
	// new FileNameExtensionFilter("OBJ Files", "obj")
	public static File getObjFile() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("OBJ Files", "obj");
		return getFileInput(filter);
	}
	
}
