  
package doc.service;

import java.util.List;

import doc.entities.Annonce;

public interface IAnnonceService {

	public List<Annonce> getAllAnnonces();
	public Annonce saveAnnonce(Annonce annonce);
	
}