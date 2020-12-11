package doc.service;



import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import doc.dao.RoleRepository;
import doc.dao.UserRepository;
import doc.entities.Role;
import doc.entities.User;

@Service
@Transactional
public class AccountService implements IAccountService {

	@Autowired
	private UserRepository appUserRepository;
	@Autowired
	private RoleRepository appRoleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }

	@Override
	public User saveUser(
			String firstname,
			String lastname,
			String email,
			String password,
			String role,
			boolean active,
			Date birthdDay,
			String fonction,
			String tel
		) {
		User appUser=this.appUserRepository.findByEmail(email);
		if(appUser!=null) throw new RuntimeException("User already exists !");
		//if(!password.equals(confirmedPassword)) throw new RuntimeException("Pleas confirm your password");
		User user=new User();
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setActivated(active);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setBirthdDay(birthdDay);
		user.setFonction(fonction);

		user.setTel(tel);
	

		appUserRepository.save(user);
		
		this.addRoleToUser(email, role);
		if(role.equals("ADMIN"))
			this.addRoleToUser(email, "USER");
		return user;
	}

	@Override
	public Role save(Role role) {
		Role appRole=appRoleRepository.save(role);
		return appRole;
	}

	@Override
	public User loadUserByEmail(String email) {
		User user=this.appUserRepository.findByEmail(email);
		return user;
	}

	@Override
	public void addRoleToUser(String email, String rolename) {
		Role appRole=appRoleRepository.findByRoleName(rolename);
		User appUser=appUserRepository.findByEmail(email);
		appUser.getRoles().add(appRole);
	
		
	}

	
}
