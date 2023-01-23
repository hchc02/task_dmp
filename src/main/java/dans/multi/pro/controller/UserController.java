package dans.multi.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dans.multi.pro.dto.LoginDto;
import dans.multi.pro.model.User;
import dans.multi.pro.service.UserServiceImpl;
import dans.multi.pro.util.JwsTokenProvide;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	JwsTokenProvide jwtUtils;

	@Autowired
  	PasswordEncoder encoder;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto request) {
        String result = "USER NOT FOUND";
		User user = userServiceImpl.checkIfMatched(request);

        result = jwtUtils.generateJwtToken(user.getUsername());

		return ResponseEntity.ok(result);
	}

	@PostMapping("/daftar")
	public ResponseEntity<?> registerUser(@RequestBody LoginDto request) {
		if (userServiceImpl.checkIfUsernameExist(request.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(("Error: Username already exist!"));
		}
		userServiceImpl.saveUser(request);

		return ResponseEntity.ok("User registered successfully!");
	}
}