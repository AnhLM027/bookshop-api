package ptit.edu.vn.bookshop.service.mapper;

import org.springframework.stereotype.Component;
import ptit.edu.vn.bookshop.domain.dto.request.PublisherRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.PublisherResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Publisher;

@Component
public class PublisherMapper {
    public Publisher mapPublisherRequestDTOToPublisher(PublisherRequestDTO requestDTO) {
        Publisher publisher = new Publisher();
        publisher.setName(requestDTO.getName());
        publisher.setAddress(requestDTO.getAddress());
        publisher.setEmail(requestDTO.getEmail());
        publisher.setPhone(requestDTO.getPhone());
        return publisher;
    }

    public PublisherResponseDTO mapPublisherToPublisherResponseDTO(Publisher publisher) {
        PublisherResponseDTO publisherResponseDTO = new PublisherResponseDTO();
        publisherResponseDTO.setId(publisher.getId());
        publisherResponseDTO.setName(publisher.getName());
        publisherResponseDTO.setAddress(publisher.getAddress());
        publisherResponseDTO.setEmail(publisher.getEmail());
        publisherResponseDTO.setPhone(publisher.getPhone());
        publisherResponseDTO.setStatus(publisher.getStatus());
        publisherResponseDTO.setCreatedAt(publisher.getCreatedAt());
        publisherResponseDTO.setUpdateAt(publisher.getUpdatedAt());
        return publisherResponseDTO;
    }
}
