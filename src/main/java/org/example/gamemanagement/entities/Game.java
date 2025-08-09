package org.example.gamemanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Game {
    @Id
    private String id; // e.g. "UNCHARTED4"

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<GameName> names = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Language defaultLanguage;

}

