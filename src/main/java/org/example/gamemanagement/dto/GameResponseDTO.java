package org.example.gamemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gamemanagement.entities.Category;
import org.example.gamemanagement.entities.Language;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GameResponseDTO {
    private String id;
    private Category category;
    private List<GameNameDTO> name;
    private Language defaultLanguage;
}
