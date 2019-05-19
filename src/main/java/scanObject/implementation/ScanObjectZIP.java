package scanObject.implementation;

import com.google.common.io.ByteStreams;
import com.google.common.primitives.Bytes;
import scanObject.ScanObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ScanObjectZIP implements ScanObject {
    private Integer offset;
    private Integer length;
    private byte[] fileBytes;

    public ScanObjectZIP(String filePath){
        offset = 0;
        length = 1024; //TODO

        byte[] bytes = new byte[0];
        try {

            ZipFile zipFile = new ZipFile(filePath);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while(entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                InputStream stream = zipFile.getInputStream(entry);
                bytes = Bytes.concat(bytes, ByteStreams.toByteArray(stream));
            }

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
