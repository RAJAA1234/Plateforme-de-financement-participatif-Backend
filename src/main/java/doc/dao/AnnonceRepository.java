package doc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import doc.entities.Annonce;

@RepositoryRestResource
public interface AnnonceRepository extends JpaRepository<Annonce, Long>{

}