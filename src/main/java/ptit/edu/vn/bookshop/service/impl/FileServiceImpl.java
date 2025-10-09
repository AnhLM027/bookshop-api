package ptit.edu.vn.bookshop.service.impl;

import ptit.edu.vn.bookshop.domain.dto.response.FileResponseDTO;
import ptit.edu.vn.bookshop.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${app.upload-file.base-path}")
    private String baseURI;

    public void createUploadedFile(String folder) throws URISyntaxException {
        URI uri = new URI(folder);
        Path path = Paths.get(uri);
        File file = path.toFile();
        if (!file.isDirectory()) {
            try {
                Files.createDirectories(path);
                log.info("Created upload directory at: {}", path.toAbsolutePath());
            } catch (IOException ex) {
                log.error("Failed to create upload directory at: {}", path.toAbsolutePath(), ex);
            }
        } else {
            log.info("Upload directory already exists at: {}", path.toAbsolutePath());
        }
    }

    @Override
    public FileResponseDTO storeFile(MultipartFile file, String folder) throws IOException, URISyntaxException {
        //chuẩn hóa tên avatar
        String safeName = file.getOriginalFilename();
        if (safeName != null) {
            safeName = safeName.replaceAll(" ", "");
        }

        // create unique fileName
        String finalName = System.currentTimeMillis() + "_" + safeName;

        URI uri = new URI(baseURI + folder + "/" + finalName);
        Path path = Paths.get(uri);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }
        log.info("Stored file at: {}", path.toAbsolutePath());
        FileResponseDTO response = new FileResponseDTO();
        response.setFileName(finalName);
        response.setUrlFile(uri.toString());
        response.setUploadAt(Instant.now());
        return response;
    }
}
