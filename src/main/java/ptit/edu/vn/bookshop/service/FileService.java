package ptit.edu.vn.bookshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface FileService {
    void createUploadedFile(String folder) throws URISyntaxException;

    String storeFile(MultipartFile file, String folder) throws IOException, URISyntaxException;
}
