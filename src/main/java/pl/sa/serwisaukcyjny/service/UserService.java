package pl.sa.serwisaukcyjny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sa.serwisaukcyjny.model.User;
import pl.sa.serwisaukcyjny.model.dto.UserDto;
import pl.sa.serwisaukcyjny.repository.RoleRepository;
import pl.sa.serwisaukcyjny.repository.UserRepository;

import pl.sa.serwisaukcyjny.model.Role;


@Service
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User addUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCity(userDto.getCity());
        String encodePassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
        user.setPassword(encodePassword);
        Role role = roleRepository.getOne((long) 1);
        user.addRole(role);
        return userRepository.save(user);
    }
    public User getUserNameByEmail(String email)
    {

        return userRepository.findByEmail(email);
    }
}
