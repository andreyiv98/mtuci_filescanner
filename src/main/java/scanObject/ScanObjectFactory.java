package scanObject;

import scanObject.implementation.ScanObjectPE;
import scanObject.implementation.ScanObjectZIP;

public class ScanObjectFactory {
    public ScanObject getScanObject(String filePath){

        //...
        if(filePath.split("\\.").length > 0 ? filePath.split("\\.")[filePath.split("\\.").length-1].equalsIgnoreCase("zip") : false){
            return new ScanObjectZIP(filePath);
        }

        return new ScanObjectPE(filePath);
    }
}
