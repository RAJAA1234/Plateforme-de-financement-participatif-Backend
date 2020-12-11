package doc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import doc.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
