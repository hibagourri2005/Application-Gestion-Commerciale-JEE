package com.ensak.venteanalytics.repository;

import com.ensak.venteanalytics.dto.VendeurDTO;
import com.ensak.venteanalytics.entity.Utilisateur;
import com.ensak.venteanalytics.enumtype.RoleUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

    boolean existsByEmail(String email);

    long countByRole(RoleUtilisateur role);

   // 🔹 IDs des vendeurs triés par ventes
    @Query("""
        SELECT v.id
        FROM Utilisateur v
        LEFT JOIN v.produits p
        LEFT JOIN p.ventes s
        WHERE v.role = com.ensak.venteanalytics.enumtype.RoleUtilisateur.VENDEUR
        GROUP BY v.id
        ORDER BY COALESCE(SUM(s.quantite), 0) DESC
    """)
    List<Long> findVendeurIdsOrdreParVentesDesc(); 

    // 🔹 Top vendeurs avec total ventes
    @Query("""
        SELECT new com.ensak.venteanalytics.dto.VendeurDTO(u.nom, COALESCE(SUM(v.prixTotal), 0))
        FROM Utilisateur u
        JOIN u.produits p
        JOIN p.ventes v
        WHERE u.role = com.ensak.venteanalytics.enumtype.RoleUtilisateur.VENDEUR
        GROUP BY u.id, u.nom
        ORDER BY COALESCE(SUM(v.prixTotal), 0) DESC
    """)
    List<VendeurDTO> findTopVendeurs();
}