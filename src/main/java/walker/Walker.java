package walker;

import java.io.File;
import java.util.ArrayList;

public class Walker {
    private ArrayList<File> fileList = new ArrayList<File>();

    public ArrayList<File> getListFiles(String str, ArrayList<String> extensionList) {
        File f = new File(str);
        for (File s : f.listFiles()) {
            if (s.isFile()) {
                String extension = s.getName().contains(".") ?
                        s.getName().substring(s.getName().lastIndexOf(".") + 1) :
                        s.getName();
                if(isAccepted(extension, extensionList)) {
                    fileList.add(s);
                }
            } else if (s.isDirectory()) {
                getListFiles(s.getAbsolutePath(), extensionList);
            }
        }

        return fileList;
    }

    private boolean isAccepted(String extension, ArrayList<String> extensionList){
        for(String ext : extensionList){
            if(ext.equalsIgnoreCase(extension)){
                return true;
            }
        }
        return false;
    }

}
