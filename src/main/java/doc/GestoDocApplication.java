package doc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import doc.dao.RoleRepository;
import doc.entities.Annonce;
import doc.entities.Message;
import doc.entities.Role;
import doc.service.AccountService;
import doc.service.AnnonceService;
import doc.service.MessageService;

@SpringBootApplication
public class GestoDocApplication implements CommandLineRunner{

	@Autowired
	AccountService accountService;
	@Autowired
	AnnonceService annonceService;
	@Autowired
	MessageService messageService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestoDocApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("===============================START=======================================");
		accountService.save(new Role(null,"ADMIN"));
		accountService.save(new Role(null,"USER"));
		accountService.saveUser("user","user","user@mail.com", "1234","USER",true,new Date(),"Prof","0655443322");
		accountService.saveUser("admin","admin","admin@gmail.com","1234","ADMIN",true,new Date(),"devloppeur","0655443322");
		for (int i = 1; i < 11; i++) {
			accountService.saveUser("user"+i,"user"+i,"user"+i+"@gmail.com","1234","USER",true,new Date(),"hh","Grade");
		}
	
		for (int i = 1; i < 11; i++) {
			messageService.saveMessage(new Message(null,"Nom "+i,"nom"+i+"@mail.com","Message de nom"+i));
		}
		
	}

}
