package com.mobicomm.services.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.mobicomm.entity.User;
import com.mobicomm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			public User loadUserByUsername(String email) {
				return userRepository.findFirstByEmail(email);
						// .orElseThrow(() ->new UsernameNotFoundException("User not found !"));
			}

		};
	}

}
