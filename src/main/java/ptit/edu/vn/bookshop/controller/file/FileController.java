package ptit.edu.vn.bookshop.controller.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ptit.edu.vn.bookshop.domain.dto.response.FileResponseDTO;
import ptit.edu.vn.bookshop.exception.StorageException;
import ptit.edu.vn.bookshop.service.FileService;
import ptit.edu.vn.bookshop.util.anotation.ApiMessage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Value("${app.upload-file.base-path}")
    private String baseURI;

    @PostMapping("/files")
    @ApiMessage("upload single file")
    public ResponseEntity<FileResponseDTO> uploadFile(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "folder", required = false) String folder) throws URISyntaxException, IOException, StorageException {

        if (file == null || file.isEmpty()) {
            throw new StorageException("File is empty, please try again.");
        }
        String fileName = file.getOriginalFilename();
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "docx");
        boolean isValid = allowedExtensions.stream().anyMatch(extension -> fileName.toLowerCase().endsWith(extension));

        if (!isValid) {
            throw new StorageException("Invalid file allow only " + allowedExtensions.toString());
        }
        // create folder
        this.fileService.createUploadedFile(baseURI + folder);
        // store file in folder
        String uploadFile = this.fileService.storeFile(file, folder);

        FileResponseDTO fileResponseDTO = new FileResponseDTO(uploadFile, Instant.now());
        return ResponseEntity.ok().body(fileResponseDTO);
    }
}
