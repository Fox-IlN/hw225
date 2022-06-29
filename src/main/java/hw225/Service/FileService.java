package hw225.Service;

import hw225.Entity.FileEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService implements IFileService {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public List<FileEntity> getInfo(Path path) {

        List<FileEntity> arrFiles = new ArrayList<>();

        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
            for (Path paths : directoryStream) {
                if (Files.isDirectory(paths)) {
                    arrFiles.add(0, new FileEntity(true, paths.getFileName().toString(), "", getDateCreate(paths), getDateUpdate(paths)));
                } else {
                    arrFiles.add(new FileEntity(false, paths.getFileName().toString(), getSize(paths), getDateCreate(paths), getDateUpdate(paths)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return arrFiles;
    }

    @Override
    public List<FileEntity> getRoots() {
        File[] paths = File.listRoots();
        List<FileEntity> fileInfo = new ArrayList<>();

        for (File path : paths) {
            fileInfo.add(new FileEntity(true, path.toString(), "", "", ""));
        }
        return fileInfo;
    }
    private String getSize(Path path) throws IOException {
        long size = Files.size(path);

        if (size < 1024) return size + " B";
        int unitIdx = (63 - Long.numberOfLeadingZeros(size)) / 10;

        return String.format("%.2f %sB", (double) size / (1L << (unitIdx * 10)), " KMGTPE".charAt(unitIdx));
    }
    private String getDateCreate(Path path) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        return dateTimeFormatter.format(attr.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    private String getDateUpdate(Path path) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        return dateTimeFormatter.format(attr.lastModifiedTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

}