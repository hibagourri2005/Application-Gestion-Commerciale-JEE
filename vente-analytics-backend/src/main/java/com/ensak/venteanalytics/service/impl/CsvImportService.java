/**package com.ensak.venteanalytics.service.impl;

import com.ensak.venteanalytics.entity.*;
import com.ensak.venteanalytics.enumtype.RoleUtilisateur;
import com.ensak.venteanalytics.enumtype.TypeAlerte;
import com.ensak.venteanalytics.repository.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CsvImportService {

    private final UtilisateurRepository utilisateurRepository;
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final AvisRepository avisRepository;
    private final AlerteRepository alerteRepository;
    private final FavoriRepository favoriRepository;
    private final VenteRepository venteRepository;

    public CsvImportService(UtilisateurRepository utilisateurRepository,
                            ProduitRepository produitRepository,
                            CategorieRepository categorieRepository,
                            AvisRepository avisRepository,
                            AlerteRepository alerteRepository,
                            FavoriRepository favoriRepository,
                            VenteRepository venteRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.avisRepository = avisRepository;
        this.alerteRepository = alerteRepository;
        this.favoriRepository = favoriRepository;
        this.venteRepository = venteRepository;
    }

    @Transactional
    public void importAllData() throws IOException, CsvValidationException {

        Map<Long, Utilisateur> utilisateurMap = new HashMap<>();
        Map<Long, Categorie> categorieMap = new HashMap<>();
        Map<String, Produit> produitMap = new HashMap<>();

        // 1️⃣ IMPORT UTILISATEURS
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/utilisateur.csv"))) {
            String[] line;
            reader.readNext(); // skip header

            while ((line = reader.readNext()) != null) {
                // line = [id, nom, email, mot_de_passe, role]
                RoleUtilisateur role = RoleUtilisateur.valueOf(line[4]);
                Utilisateur user = switch (role) {
                    case ADMIN -> new Administrateur(line[1], line[2], line[3]);
                    case VENDEUR -> new Vendeur(line[1], line[2], line[3]);
                    case ANALYSTE -> new Analyste(line[1], line[2], line[3]);
                    case ACHETEUR -> new Acheteur(line[1], line[2], line[3]);
                };

                utilisateurRepository.save(user);

                if (user.getId() == null)
                    throw new RuntimeException("Utilisateur non sauvegardé");

                utilisateurMap.put(Long.parseLong(line[0]), user);
            }
        }

        // 2️⃣ IMPORT CATEGORIES
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/categorie.csv"))) {
            String[] line;
            reader.readNext(); // skip header

            while ((line = reader.readNext()) != null) {
                // line = [id, nom, description]
                Categorie c = new Categorie();
                c.setNom(line[1]);
                c.setDescription(line[2]);

                categorieRepository.save(c);

                if (c.getId() == null)
                    throw new RuntimeException("Categorie non sauvegardée");

                categorieMap.put(Long.parseLong(line[0]), c);
            }
        }

        // 3️⃣ IMPORT PRODUITS
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/produit.csv"))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                // line = [id(Pxxx), nom, prix, stock, vendeur_id, categorie_id]
                Vendeur vendeur = (Vendeur) utilisateurMap.get(Long.parseLong(line[4]));
                Categorie categorie = categorieMap.get(Long.parseLong(line[5]));

                if (vendeur == null || categorie == null)
                    throw new RuntimeException("Vendeur ou catégorie manquant pour produit " + line[0]);

                Produit p = new Produit();
                p.setNom(line[1]);
                p.setPrix(Double.parseDouble(line[2]));
                p.setStock(Integer.parseInt(line[3]));
                p.setVendeur(vendeur); // il manque setVendeur dans ta classe Produit, il faut l'ajouter si nécessaire !
                p.setCategorie(categorie);

                produitRepository.save(p);

                if (p.getId() == null)
                    throw new RuntimeException("Produit non sauvegardé : " + line[0]);

                produitMap.put(line[0], p); // clé = ID CSV (ex: P001)
            }
        }

        // 4️⃣ IMPORT AVIS
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/avis.csv"))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                // line = [id, note, commentaire, date_avis, produit_id, acheteur_id]
                Produit produit = produitMap.get(line[4]);
                Utilisateur user = utilisateurMap.get(Long.parseLong(line[5]));

                if (produit == null) {
                    throw new RuntimeException("Produit introuvable (avis.csv) : " + line[4]);
                }
                if (user == null) {
                    throw new RuntimeException("Utilisateur introuvable (avis.csv) : " + line[5]);
                }
                if (!(user instanceof Acheteur)) {
                    throw new RuntimeException("Utilisateur " + line[5] + " n'est pas un ACHETEUR");
                }

                Avis avis = new Avis();
                avis.setNote(Integer.parseInt(line[1]));
                avis.setCommentaire(line[2]);
                // Convertir LocalDate en LocalDateTime (date.atStartOfDay)
                avis.setDateCreation(LocalDate.parse(line[3]).atStartOfDay());
                avis.setProduit(produit);
                avis.setAcheteur((Acheteur) user);

                avisRepository.save(avis);
            }
        }

     // 5 Import alertes
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/alerte.csv"))) {
            String[] line;
            reader.readNext(); // skip header

            while ((line = reader.readNext()) != null) {
                Utilisateur user = utilisateurMap.get(Long.parseLong(line[4]));

                if (user == null) {
                    throw new RuntimeException("Utilisateur introuvable alerte");
                }

                if (!(user instanceof Vendeur)) {
                    System.err.println("Alerte ignorée : utilisateur id=" + user.getId() + " n'est pas un vendeur");
                    continue; // passe à la ligne suivante sans créer d'alerte
                }

                Alerte a = new Alerte();
                a.setTypeAlerte(TypeAlerte.valueOf(line[1]));
                a.setMessage(line[2]);
                a.setDateCreation(LocalDate.parse(line[3]));
                a.setVendeur((Vendeur) user);

                alerteRepository.save(a);
            }
        }


        // 6️⃣ IMPORT FAVORIS
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/favori.csv"))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                // line = [id, date_ajout, acheteur_id, produit_id]
                Acheteur acheteur = (Acheteur) utilisateurMap.get(Long.parseLong(line[2]));
                Produit produit = produitMap.get(line[3]);

                if (acheteur == null)
                    throw new RuntimeException("Acheteur introuvable : " + line[2]);
                if (produit == null)
                    throw new RuntimeException("Produit introuvable (favori.csv) : " + line[3]);

                Favori f = new Favori();
                f.setDateAjout(LocalDate.parse(line[1]));
                f.setAcheteur(acheteur);
                f.setProduit(produit);

                favoriRepository.save(f);
            }
        }

        // 7️⃣ IMPORT VENTES
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/vente.csv"))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                // line = [id, quantite, prix_total, date_vente, produit_id, vendeur_id]
                Produit produit = produitMap.get(line[4]);
                Vendeur vendeur = (Vendeur) utilisateurMap.get(Long.parseLong(line[5]));

                if (produit == null || vendeur == null)
                    throw new RuntimeException("Vente invalide (produit ou vendeur manquant)");

                Vente v = new Vente();
                v.setQuantite(Integer.parseInt(line[1]));
                v.setPrixTotal(Double.parseDouble(line[2]));
                v.setDateVente(LocalDate.parse(line[3]));
                v.setProduit(produit);
                v.setVendeur(vendeur);

                venteRepository.save(v);
            }
        }
    }
}
**/
