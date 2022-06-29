package hw225.Service;

import hw225.Entity.FileEntity;

import java.nio.file.Path;
import java.util.List;

public interface IFileService {
    List<FileEntity> getInfo(Path path);

    List<FileEntity> getRoots();
}