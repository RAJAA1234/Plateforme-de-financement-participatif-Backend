package doc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doc.dao.MessageRepository;
import doc.entities.Message;

@Service
@Transactional
public class MessageService {
	@Autowired
	MessageRepository messageRepository;
	
	public Message saveMessage(Message message)
	{
		return this.messageRepository.save(message);
	}
	
	public List<Message> getAllMessages()
	{
		return this.messageRepository.findAll();
	}

}
