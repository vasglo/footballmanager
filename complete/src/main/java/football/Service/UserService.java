package football.Service;

import football.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto update(UserDto userDto);

    void  delete(String email);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();
}
