package doc.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import doc.entities.Annonce;
import doc.entities.Message;
import doc.service.IMessageService;
import doc.service.MessageService;
import lombok.Data;

@RestController
public class MessageController {
@Autowired
MessageService messageService;


@GetMapping("/messages")
public List<Message> getAll()
{
	return messageService.getAllMessages();
}
@PostMapping("/messages")
public Message sendMessage(@RequestBody MessageForm messageForm)
{
	
	//System.err.println(userForm);
	return messageService.saveMessage(new Message(null,messageForm.getNom(),messageForm.getEmail(),messageForm.getMessage()));
}

}

@Data
class MessageForm{
	private String nom;
	private String email;
	private String message;
}