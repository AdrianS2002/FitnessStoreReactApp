package com.example.ProiectMagazinSuplimenteAlimentare.service.user;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.*;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.UserMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.UserToLoginMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.UserToLogoutMapper;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Order;
import com.example.ProiectMagazinSuplimenteAlimentare.model.Role;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.OrderRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.RoleRepository;
import com.example.ProiectMagazinSuplimenteAlimentare.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserServiceImpl class implements the methods defined in the UserService interface.

 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findUserByName(String name) throws ApiExceptionResponse {
        User user =  userRepository.findFirstByName(name);
        if (user == null) {
            throw ApiExceptionResponse.builder()
                    .errors(List.of("No user with name " + name))
                    .message("Entity not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return user;
    }

    @Override
    public User findUserByUsername(String username) throws ApiExceptionResponse {
        User user = userRepository.findFirstByUsername(username);
        if (user == null) {
            throw ApiExceptionResponse.builder()
                    .errors(List.of("No user with username " + username))
                    .message("Entity not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return user;
    }

    @Override
    public UserDTO saveUser(UserCreationDTO dto) {
        User entity = UserMapper.toCreationEntity(dto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity = userRepository.save(entity);
        return UserMapper.toDto(entity);
    }

    @Override
    public UserDTO saveNewUser(UserCreationDTO dto) {
        User entity = UserMapper.toCreationEntity(dto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        // Inițializați lista de roluri dacă este null
        if (entity.getRoles() == null) {
            entity.setRoles(new ArrayList<>());
        }

        // Adăugați rolul de utilizator
        Role userRole = roleRepository.findByRole("USER");
        entity.getRoles().add(userRole);

        entity = userRepository.save(entity);
        return UserMapper.toDto(entity);
    }

//    @Override
//    public UserDTO updateUser(UserDTO dto) {
//        User user = UserMapper.toEntity(dto);
//        boolean exists = userRepository.findById(user.getId()).isPresent();
//        if(exists){
//            user = userRepository.save(user);
//            return UserMapper.toDto(user);
//        }
//        return null;
//    }

    public UserDTO updateUser(UserDTO dto) {
        User existingUser = userRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setPassword(passwordEncoder.encode(dto.getPassword())); // exemplu de actualizare a parolei
        existingUser.setName(dto.getName());
        existingUser.setTelephone(dto.getTelephone());

        userRepository.save(existingUser);
        return UserMapper.toDto(existingUser);
    }

    @Override
    public UserDTO updateUserRoles(UserDTO dto) {
        User existingUser = userRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        // Actualizează câmpurile utilizatorului
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        existingUser.setName(dto.getName());
        existingUser.setTelephone(dto.getTelephone());

        // Actualizează rolurile
        List<Role> newRoles = dto.getRoles().stream()
                .map(role -> roleRepository.findByRole(role.getRole()))
                .collect(Collectors.toList());
        existingUser.setRoles(newRoles);

        userRepository.save(existingUser);
        return UserMapper.toDto(existingUser);
    }
    @Override
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return userRepository.findById(id).isEmpty();
    }

    @Override
    public SuccessfulLoginDTO updatePassword(PasswordUpdateDTO dto) throws ApiExceptionResponse {
        User user = userRepository.findFirstByUsername(dto.getUsername());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);

        }
        return UserToLoginMapper.mapUserToDTO(user);
    }

    @Override
    public SuccessfulLoginDTO login(AuthDTO dto) throws ApiExceptionResponse {
        User user = userRepository.findFirstByUsername(dto.getUsername());
        if (user == null ||   !passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            ArrayList<String> errors = new ArrayList<>();
            errors.add("Invalid username or password.");

            throw ApiExceptionResponse.builder()
                    .errors(errors)
                    .message("Authentication failed")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        user.setFlag(true);
        userRepository.save(user);
        // Mapping user to SuccessfulLoginDTO
        return UserToLoginMapper.mapUserToDTO(user);
    }

    @Override
    public SuccessfulLogoutDTO logout(LogoutDTO dto) throws ApiExceptionResponse {
        User user = userRepository.findFirstByUsername(dto.getUsername());
        if (user == null) {
            throw ApiExceptionResponse.builder()
                    .errors(List.of("No user with username " + dto.getUsername()))
                    .message("Entity not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        user.setFlag(false);
        userRepository.save(user);
        return UserToLogoutMapper.mapUserToDTO(user);
    }
    @Override
    public User assignOrder(OrderUserDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow() ;
        user.getOrders().add(order);
        userRepository.save(user);
        return user;
    }

    @Override
    public User assignRole(RoleUserAtributtionDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Role role = roleRepository.findById(dto.getRoleId()).orElseThrow();
        user.getRoles().add(role);
        userRepository.save(user);
        return user;
    }


    @Override
    public void updateLoginFlag(String username) {
        userRepository.updateLoginFlag(username);
    }
}
