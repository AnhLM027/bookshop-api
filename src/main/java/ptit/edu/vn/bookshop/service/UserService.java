package ptit.edu.vn.bookshop.service;

import ptit.edu.vn.bookshop.dto.request.UserUpdateRequestDTO;
import ptit.edu.vn.bookshop.dto.request.auth.PasswordChangeRequestDTO;
import ptit.edu.vn.bookshop.dto.request.UserCreateRequestDTO;
import ptit.edu.vn.bookshop.dto.response.LoginResponseDTO;
import ptit.edu.vn.bookshop.dto.response.page.UserPageResponseDTO;
import ptit.edu.vn.bookshop.dto.response.UserResponseDTO;
import ptit.edu.vn.bookshop.domain.entity.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO);
    UserResponseDTO updateUser(UserUpdateRequestDTO userRequestDTO, Long id);
    void deleteUser(Long id);
    UserResponseDTO fetchUser(Long id);
    UserPageResponseDTO fetchAllUsers(Pageable pageable, String[] user) ;
    UserResponseDTO getUserByEmail(String email);
//    void updateUserToken(String token, String email);
    UserResponseDTO getUserByRefreshTokenAndEmail(String refreshToken, String email);
    User getUserByUsername(String email);
    LoginResponseDTO.UserLogin getCurrentUserAccount();
    void passwordChange(PasswordChangeRequestDTO passwordChangeRequestDTO, String email);

}
