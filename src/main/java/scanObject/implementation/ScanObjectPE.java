package scanObject.implementation;

import scanObject.ScanObject;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ScanObjectPE implements ScanObject {
    private Integer offset;
    private Integer length;
    private byte[] fileBytes;

    public ScanObjectPE(String filePath){
        offset = 0;
        length = 1024; //TODO

        byte[] bytes = new byte[0];
        try {
            RandomAccessFile f = new RandomAccessFile(filePath, "r");
            bytes = new byte[(int) f.length()];
            f.readFully(bytes);
            //TODO: закрывать файл
        } catch (IOException e){
            System.out.println(e);
        }
        this.fileBytes = bytes;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLength() {
        return length;
    }

    public byte[] getBytes() {
        return fileBytes;
    }
}
