package recordManager;

import java.io.Serializable;

public class AVRecord implements Serializable {
    private String name;
    private Integer offsetStart;
    private Integer offsetEnd;
    private Integer lenght;
    private byte[] firstBytes;
    private byte[] hash;
    private String fileType;

    public AVRecord(String name, Integer offsetStart, Integer offsetEnd, Integer lenght, byte[] firstBytes, byte[] hash, String fileType) {
        this.name = name;
        this.offsetStart = offsetStart;
        this.offsetEnd = offsetEnd;
        this.lenght = lenght;
        this.firstBytes = firstBytes;
        this.hash = hash;
        this.fileType = fileType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOffsetStart() {
        return offsetStart;
    }

    public void setOffsetStart(Integer offsetStart) {
        this.offsetStart = offsetStart;
    }

    public Integer getOffsetEnd() {
        return offsetEnd;
    }

    public void setOffsetEnd(Integer offsetEnd) {
        this.offsetEnd = offsetEnd;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public byte[] getFirstBytes() {
        return firstBytes;
    }

    public void setFirstBytes(byte[] firstBytes) {
        this.firstBytes = firstBytes;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Name: ").append(this.name)
                .append("; OffsetStart: ").append(this.offsetStart)
                .append("; OffsetEnd: ").append(this.offsetEnd)
                .append("; Lenght: ").append(this.lenght)
                .append("; FirstBytes: ").append(this.firstBytes)
                .append("; Hash: ").append(this.hash)
                .append("; FileType: ").append(this.fileType)
                .append(";").toString();
    }
}
