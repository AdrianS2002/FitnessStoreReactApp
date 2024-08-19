package com.example.ProiectMagazinSuplimenteAlimentare.service.user;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.*;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * UserService interface is used to create the methods that will be implemented in the UserServiceImpl class.
 */
@Component
public interface UserService  {
    List<User> findAll();
    User findUserById(Long id);
    User findUserByName(String name) throws ApiExceptionResponse;

    User findUserByUsername(String username) throws ApiExceptionResponse;

    UserDTO saveUser(UserCreationDTO user);
    UserDTO saveNewUser(UserCreationDTO user);

    UserDTO updateUser (UserDTO user);

    boolean deleteUser(Long id);

    SuccessfulLoginDTO updatePassword(PasswordUpdateDTO dto) throws ApiExceptionResponse;

    SuccessfulLoginDTO login (AuthDTO dto) throws ApiExceptionResponse;

    public UserDTO updateUserRoles(UserDTO user) throws ApiExceptionResponse; //

    SuccessfulLogoutDTO logout (LogoutDTO dto) throws ApiExceptionResponse;

    User assignOrder(OrderUserDTO dto);

    User assignRole(RoleUserAtributtionDTO dto);

    void updateLoginFlag(String username);

}
