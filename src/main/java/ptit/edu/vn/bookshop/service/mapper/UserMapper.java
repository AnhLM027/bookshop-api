package ptit.edu.vn.bookshop.service.mapper;

import ptit.edu.vn.bookshop.domain.dto.request.auth.RegisterRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.request.UserCreateRequestDTO;
import ptit.edu.vn.bookshop.domain.dto.response.UserResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.Role;
import ptit.edu.vn.bookshop.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapperUserCreateDtoToUser(UserCreateRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());
        user.setStatus(dto.getStatus());
        user.setAvatar(dto.getAvatar());
        if (dto.getRole() != null) {
            Role role = new Role();
            role.setId(dto.getRole().getId());
            user.setRole(role);
        }
        return user;
    }

    public UserResponseDTO mapperUserToUserResponseDTO(User userEntity) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userEntity.getId());
        userResponseDTO.setName(userEntity.getName());
        userResponseDTO.setEmail(userEntity.getEmail());
        userResponseDTO.setDateOfBirth(userEntity.getDateOfBirth());
        userResponseDTO.setPhone(userEntity.getPhone());
        userResponseDTO.setGender(userEntity.getGender());
        userResponseDTO.setStatus(userEntity.getStatus());
        userResponseDTO.setAvatarUrl(userEntity.getAvatar());
        userResponseDTO.setCreatedBy(userEntity.getCreatedBy());
        userResponseDTO.setCreatedAt(userEntity.getCreatedAt());
        if (userEntity.getRole() != null) {
            UserResponseDTO.UserRoleResponseDTO role = new UserResponseDTO.UserRoleResponseDTO();
            role.setId(userEntity.getRole().getId());
            role.setName(userEntity.getRole().getName());
            userResponseDTO.setRole(role);
        }
        return userResponseDTO;
    }

    public User mapperRegisterRequestDtoToUser(RegisterRequestDTO registerRequestDTO){
        User user = new User();
        user.setName(registerRequestDTO.getName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(registerRequestDTO.getPassword());
        user.setPhone(registerRequestDTO.getPhone());
        return user;
    }
}
