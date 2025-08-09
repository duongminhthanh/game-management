package org.example.gamemanagement.repository;

import org.example.gamemanagement.entities.Category;
import org.example.gamemanagement.entities.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    @Query("""
      SELECT DISTINCT g FROM Game g
      LEFT JOIN g.names n
      WHERE (:category IS NULL OR g.category = :category)
        AND (:keyword IS NULL OR (
            LOWER(n.value) LIKE CONCAT('%', LOWER(:keyword), '%')
            OR LOWER(g.id) LIKE CONCAT('%', LOWER(:keyword), '%')
        ))
      """)
    Page<Game> search(@Param("category") Category category,
                      @Param("keyword") String keyword,
                      Pageable pageable);
}
