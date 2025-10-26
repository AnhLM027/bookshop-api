package ptit.edu.vn.bookshop.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ptit.edu.vn.bookshop.service.CloudinaryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // single file
    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File không được rỗng!");
        }
        Map<String, Object> options = ObjectUtils.asMap(
                "resource_type", "auto"
        );
        Map<?, ?> data = cloudinary.uploader().upload(file.getBytes(), options);
        return data.get("secure_url").toString();
    }

    // Upload multi file
    public List<String> uploadMultipleFiles(MultipartFile[] files) throws IOException {
        List<String> results = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Map<String, Object> options = ObjectUtils.asMap(
                        "resource_type", "auto"
                );
                Map<?, ?> data = cloudinary.uploader().upload(file.getBytes(), options);
                results.add(data.get("secure_url").toString());
            }
        }
        return results;
    }


//    public String uploadFile(MultipartFile file) throws IOException {
//        Map data = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//        return data.get("secure_url").toString();
//    }
//
//    public List<String> uploadMultipleFiles(MultipartFile[] files) throws IOException {
//        List<String> results = new ArrayList<>();
//        for (MultipartFile file : files) {
//            Map data = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//            results.add(data.get("secure_url").toString());
//        }
//        return results;
//    }

}
