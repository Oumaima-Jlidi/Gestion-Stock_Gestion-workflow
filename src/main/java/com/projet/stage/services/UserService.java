package com.projet.stage.services;

import com.projet.stage.DTO.UserDTO;
import com.projet.stage.DTO.LoginDTO;
import com.projet.stage.DTO.LoginMesage;

public interface UserService {
String addUser(UserDTO userdto);
LoginMesage loginUser(LoginDTO logindto);
}
