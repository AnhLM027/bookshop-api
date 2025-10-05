package ptit.edu.vn.bookshop.service;

import org.springframework.data.domain.Pageable;
import ptit.edu.vn.bookshop.domain.dto.request.PublisherCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.PublisherUpdateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.PublisherResponseDTO;
import ptit.edu.vn.bookshop.domain.dto.response.page.PublisherPageResponseDTO;

public interface PublisherService {
    PublisherResponseDTO createPublisher(PublisherCreateRequestDTO publisher);
    PublisherResponseDTO updatePublisher(PublisherUpdateRequestDTO publisher, Long id);
    void deletePublisher(Long id);
    PublisherResponseDTO fetchPublisher(Long id, boolean isAdmin);

    PublisherPageResponseDTO  fetchAllPublishers(Pageable pageable, String[] publisher, boolean isAdmin);

}
