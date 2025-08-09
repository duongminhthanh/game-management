package org.example.gamemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.gamemanagement.entities.Language;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GameNameDTO {
    @NotNull
    private Language language;
    @NotBlank
    private String value;
}
