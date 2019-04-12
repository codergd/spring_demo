package customdata.fileUpload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    /**
     * 初始化
     */
    void init();

    /**
     * 存储文件
     * @param file
     */
    void store(MultipartFile file);

    /**
     * 加载所有上传文件
     * @return
     */
    Stream<Path> loadAll();

    /**
     * 加载单个文件
     * @param fileName
     * @return
     */
    Path load(String fileName);

    Resource loadAsResource(String fileName);

    /**
     * 删除所有上传文件
     */
    void deleteAll();
}
