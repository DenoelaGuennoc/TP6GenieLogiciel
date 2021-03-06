package representation;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRepresentant {
	// Quelques constantes
	private static final float FIXE_BASTIDE = 1000f;
	private static final float INDEMNITE_OCCITANIE = 200f;
	
	private Representant r; // L'objet à tester
	private ZoneGeographique occitanie;
	
	@BeforeEach
	public void setUp() {
		// Initialiser les objets utilisés dans les tests
		occitanie = new ZoneGeographique(1, "Occitanie");
		occitanie.setIndemniteRepas(INDEMNITE_OCCITANIE);

		r = new Representant(36, "Bastide", "Rémi", occitanie);	
		r.setSalaireFixe(FIXE_BASTIDE);				
	}
	
	@Test
	public void testSalaireMensuel() {
		float CA = 50000f;
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		// On enregistre un CA pour le mois 0 (janvier)
		r.enregistrerCA(0, CA);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		
		assertEquals(// Comparaison de "float"
			// valeur attendue
			FIXE_BASTIDE + INDEMNITE_OCCITANIE + CA * POURCENTAGE,
			// Valeur calculée
			salaire,
			// Marge d'erreur tolérée
			0.001,
			// Message si erreur
			"Le salaire mensuel est incorrect"
		); 
	}

	@Test
	public void testCAParDefaut() {
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		
		// On n'enregistre aucun CA
		//r.enregistrerCA(0, 10000f);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		// Le CA du mois doit avoir été initialisé à 0
		
		assertEquals(
			FIXE_BASTIDE + INDEMNITE_OCCITANIE, 
			salaire, 
			0.001,
			"Le CA n'est pas correctement initialisé"
		);
	}

	@Test
	public void testCANegatifImpossible() {
		
		try {
			// On enregistre un CA négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(0, -10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un CA négatif doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
	
        @Test
	public void testMoisNegatif() {
            try {
                //On donne un mois négatif
                //On s'attend à une exception
                r.checkMois(-3);
                fail("Un mois négatif doit gérérer une exception");
            }
            catch (IllegalArgumentException e) {
            }
        }
        
        @Test
	public void testMoisTropGrand() {
            try {
                //On donne un mois >11
                //On s'attend à une exception
                r.checkMois(15);
                fail("Un mois supérieur à 11 doit gérérer une exception");
            }
            catch (IllegalArgumentException e) {
            }
        }
        
        @Test
	public void testPourcentageNegatif() {
            try {
                //On donne un pourcentage négatif
                //On s'attend à une exception
                r.salaireMensuel(0, -3);
                fail("Un pourcentage négatif doit gérérer une exception");
            }
            catch (IllegalArgumentException e) {
            }
        }
        
        @Test
	public void testPourcentageTropGrand() {
            try {
                //On donne un pourcentage >1
                //On s'attend à une exception
                r.salaireMensuel(0, 1.2f);
                fail("Un pourcentage >1 doit gérérer une exception");
            }
            catch (IllegalArgumentException e) {
            }
        }
        
        @Test
        public void testToString() {
            String attendu = "Representant{numero=36, nom=Bastide, prenom=Rémi}";
            assertEquals(attendu, r.toString());
        }
        
        @Test
        public void testGetNumero() {
            assertEquals(36, r.getNumero(), "getNumero() ne retourne pas le bon numéro de représentant");
        }
        
        @Test
        public void testGetNom() {
            assertEquals("Bastide", r.getNom(), "getNom() ne retourne pas le bon nom de représentant");
        }
        
        @Test
        public void testGetPrenom() {
            assertEquals("Rémi", r.getPrenom(), "getPrenom() ne retourne pas le bon prénom de représentant");
        }
        
        @Test
        public void testGetSalaireFixe() {
            assertEquals(FIXE_BASTIDE, r.getSalaireFixe(), "getSalaireFixe() ne retourne pas le bon salaire fixe de représentant");
        }
        
        @Test
        public void testGetSecteur() {
            assertEquals(occitanie, r.getSecteur(), "getSecteur() ne retourne pas le bon secteur pour le représentant");
        }
        
        @Test
        public void testSetSecteur() {
            //création d'un nouveau secteur
            ZoneGeographique bretagne = new ZoneGeographique(29, "Bretagne");
            //attribution du nouveau secteur au représentant
            r.setSecteur(bretagne);
            
            assertEquals(bretagne, r.getSecteur(), "Le secteur n'a pas été mis à jour par setSecteur(newSecteur)");
            
        }
        
        @Test
        public void testSetGetAdresse() {
            //attribution d'une nouvelle adresse au représentant
            r.setAdresse("1 rue de chez moi");
            
            assertEquals("1 rue de chez moi", r.getAdresse(), "getAdresse() ou setAdresse() ne retourne pas la bonne adresse pour le représentant");
            //Si l'adresse retournée est la bonne c'est quelle a bien été modifiée par setAdresse
            //et quelle est bien retournée par getAdresse.
        }
}
