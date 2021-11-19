package com.topicos.backend.controller;

import com.topicos.backend.dto.UserDTO;
import com.topicos.backend.dto.request.NewUserDTO;
import com.topicos.backend.dto.request.UserCredentialDTO;
import com.topicos.backend.services.UserService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/user/register")
  public UserDTO saveUser(@Valid @RequestBody NewUserDTO user, @RequestHeader("Authorization") String token) {
    return this.userService.saveAndSendMail(user, token);
  }

  @PostMapping(value = "/user/activate")
  public String activateUser(@Valid @RequestBody UserCredentialDTO user, @RequestHeader("Authorization") String token) {
    return this.userService.activate(user, token);
  }

  @PostMapping(value = "/user/login")
  public String createAuthenticationToken(@RequestBody UserCredentialDTO user) throws Exception {
    return this.userService.loginAndGenerateToken(user);
  }

  @PostMapping(value = "/user/admin")
  public String newAdmin(@RequestBody UserCredentialDTO user) throws Exception {
    return this.userService.newAdmin(user);
  }

}
