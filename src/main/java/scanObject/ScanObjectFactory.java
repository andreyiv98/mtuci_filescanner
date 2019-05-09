package scanObject;

import scanObject.implementation.ScanObjectPE;

public class ScanObjectFactory {
    public ScanObject getScanObject(String filePath){

        //...

        return new ScanObjectPE(filePath);
    }
}
