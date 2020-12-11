package doc.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import doc.service.AccountService;
import doc.service.IAccountService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IAccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		doc.entities.User appUser=accountService.loadUserByEmail(username);
		if(appUser==null) throw new UsernameNotFoundException("Invalid User");
		
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		appUser.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		System.err.println(appUser.getPassword());
		return new User(appUser.getEmail(),appUser.getPassword(),authorities);
	}

}
