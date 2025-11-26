package ptit.edu.vn.bookshop.service;

import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.dto.request.PublisherRequestDTO;
import ptit.edu.vn.bookshop.dto.response.PublisherResponseDTO;
import ptit.edu.vn.bookshop.dto.response.page.PublisherPageResponseDTO;

public interface PublisherService {
    PublisherResponseDTO createPublisher(PublisherRequestDTO publisher);
    PublisherResponseDTO updatePublisher(PublisherRequestDTO publisher, Long id);
    void deletePublisher(Long id);
    PublisherResponseDTO fetchPublisher(Long id, boolean isAdmin);

    PublisherPageResponseDTO  fetchAllPublishers(Pageable pageable, String[] publisher, boolean isAdmin);

}
