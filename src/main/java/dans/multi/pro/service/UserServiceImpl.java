package dans.multi.pro.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dans.multi.pro.dto.LoginDto;
import dans.multi.pro.model.User;
import dans.multi.pro.repository.UserRepo;

@Service
public class UserServiceImpl implements UserDetailsService{
  @Autowired
  UserRepo userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Transactional
  public User checkIfMatched(LoginDto dto) throws UsernameNotFoundException {
    return userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword())
        .orElseThrow(() -> new UsernameNotFoundException("Please input correct username and password"));
  }

  @Transactional
  public boolean checkIfUsernameExist(String username){
    return userRepository.existsByUsername(username);
  }

  @Transactional
  public void saveUser(LoginDto dto){
    User user = new User(dto.getUsername(),dto.getPassword());
                             
	userRepository.save(user);
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserService.build(user);
  }
}
