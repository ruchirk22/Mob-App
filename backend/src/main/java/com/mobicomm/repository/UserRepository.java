package com.mobicomm.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mobicomm.entity.User;
// import com.mobicomm.enums.UserRole;
import com.mobicomm.enums.UserRole;

@Repository
@Component
public interface UserRepository extends JpaRepository<User, Long>{

	User findFirstByEmail(String email);
// 
	// User findByUserRole(UserRole userRole);

    User findByUserRole(UserRole admin);
 
	
}
