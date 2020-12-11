package doc.service;

import java.util.List;

import doc.entities.Message;

public interface IMessageService {
	public Message saveMessage(Message message);
	
	public List<Message> getAllMessages();
}
