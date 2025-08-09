package org.example.gamemanagement.util;

import org.example.gamemanagement.dto.GameNameDTO;
import org.example.gamemanagement.dto.GameRequestDTO;
import org.example.gamemanagement.dto.GameResponseDTO;
import org.example.gamemanagement.entities.Game;
import org.example.gamemanagement.entities.GameName;

import java.util.List;
import java.util.stream.Collectors;

public class GameUtil {
    public static GameResponseDTO mapToResponseDTO(Game game) {
        return GameResponseDTO.builder()
                .id(game.getId())
                .category(game.getCategory())
                .defaultLanguage(game.getDefaultLanguage())
                .name(game.getNames()
                        .stream().map(
                                gameName -> new GameNameDTO(
                                        gameName.getLanguage(), gameName.getValue()
                                )
                        ).collect(Collectors.toList()))
                .build();
    }

    public static Game mapToEntity(GameRequestDTO dto) {
        Game game = Game.builder()
                .id(dto.getId())
                .category(dto.getCategory())
                .defaultLanguage(dto.getDefaultLanguage())
                .build();

        // Convert DTO list to entity list
        List<GameName> gameNames = dto.getName().stream()
                .map(nameDTO -> GameName.builder()
                        .language(nameDTO.getLanguage())
                        .value(nameDTO.getValue())
                        .game(game) // link back to parent
                        .build())
                .collect(Collectors.toList());

        game.setNames(gameNames);

        return game;




    }
}
