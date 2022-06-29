package hw225.Entity;

public class FileEntity {
    private boolean isDirectory;
    private String name;
    private String size;
    private String dateCreate;
    private String dateUpdate;

    public FileEntity(boolean isDirectory, String name, String size, String dateCreate, String dateUpdate) {
        this.isDirectory = isDirectory;
        this.name = name;
        this.size = size;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}