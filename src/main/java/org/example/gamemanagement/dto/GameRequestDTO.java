package org.example.gamemanagement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class GameRequestDTO {
    @NotBlank
    private String id;
    @NotNull
    private Category category;
    @NotNull
    @Size(min = 1)
    @Valid
    private List<GameNameDTO> name;
    @NotNull
    private Language defaultLanguage;
}
