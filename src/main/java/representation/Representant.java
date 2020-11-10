package representation;
import java.util.*;

public class Representant {

	private final int numero;
	private final String nom;
	private final String prenom;
	private String adresse;
	private float salaireFixe;
        private ZoneGeographique zoneRepresentant;
        private HashMap<Integer, Float> CAMensuel = new HashMap<Integer, Float>(); //archive des CA sur les 12 derniers mois

	public Representant(int numero, String nom, String prenom, ZoneGeographique secteur) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
                this.zoneRepresentant =  secteur;
	}

	public int getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public float getSalaireFixe() {
		return salaireFixe;
	}

	public void setSalaireFixe(float salaireFixe) {
		this.salaireFixe = salaireFixe;
	}

	public ZoneGeographique getSecteur() {
		// TODO: Implémenter cette méthode
                return zoneRepresentant;
		//throw new UnsupportedOperationException("Pas encore implémenté");
	}

	public void setSecteur(ZoneGeographique secteur) {
		// TODO: Implémenter cette méthode
                this.zoneRepresentant = secteur;
		//throw new UnsupportedOperationException("Pas encore implémenté");
	}

        //Vérifie la validité du paramètre mois (0 <= mois <= 11)
        public void checkMois(int mois){
            if (mois < 0 || mois > 11) {
                throw new IllegalArgumentException("Le mois doit être compris entre 0 et 11 (0 = janvier)");
            }
        }
        
	/**
	 * Enregistre le CA de ce représentant pour un mois donné. 
	 * @param mois le numéro du mois (de 0 à 11)
	 * @param montant le CA réalisé pour ce mois (positif ou nul)
	 **/
	public void enregistrerCA(int mois, float montant) {
		// vérifier les paramètres
		checkMois(mois);
		if (montant < 0) {
			throw new IllegalArgumentException("Le montant doit être positif ou null");
		}
		// TODO: Implémenter cette méthode
                //throw new UnsupportedOperationException("Pas encore implémenté");
                this.CAMensuel.put(mois, montant);
	}

	/**
	 * Calcule le salaire mensuel de ce répresentant pour un mois donné
	 * @param mois le numéro du mois (de 0 à 11)
	 * @param pourcentage le pourcentage (>= 0 ) à appliquer sur le CA réalisé pour ce mois
	 * @return le salaire pour ce mois, tenant compte du salaire fixe, de l'indemnité repas, et du pourcentage sur CA
	 */
	public float salaireMensuel(int mois, float pourcentage) {
		// TODO: Implémenter cette méthode
		//throw new UnsupportedOperationException("Pas encore implémenté");
                checkMois(mois);
                if (pourcentage < 0 || pourcentage > 1){
                    throw new IllegalArgumentException("Le pourcentage doit être compris en 0 et 1 (1=100%)");
                }
                // Calcul du salaire mensuel = salaire fixe + pourcentage du CA + indemnité repas
                float sMensuel = this.salaireFixe + pourcentage * this.CAMensuel.getOrDefault(mois, 0f) + this.zoneRepresentant.getIndemniteRepas();
                return sMensuel;
	}

	@Override
	public String toString() {
		return "Representant{" + "numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + '}';
	}

}
