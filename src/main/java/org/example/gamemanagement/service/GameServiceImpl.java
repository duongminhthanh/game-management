package org.example.gamemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.gamemanagement.dto.GameRequestDTO;
import org.example.gamemanagement.dto.GameResponseDTO;
import org.example.gamemanagement.entities.Category;
import org.example.gamemanagement.entities.Game;
import org.example.gamemanagement.entities.GameName;
import org.example.gamemanagement.repository.GameRepository;
import org.example.gamemanagement.util.GameUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.gamemanagement.util.GameUtil.mapToEntity;
import static org.example.gamemanagement.util.GameUtil.mapToResponseDTO;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Page<GameResponseDTO> search(Category category, String keyword, Pageable pageable) {
        Page<Game> page = gameRepository.search(category, keyword, pageable);
        return page.map(GameUtil::mapToResponseDTO);
    }

    @Override
    public GameResponseDTO getById(String id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found: " + id));
        return mapToResponseDTO(game);
    }

    @Override
    public GameResponseDTO create(GameRequestDTO dto) {
        if (gameRepository.existsById(dto.getId())) {
            throw new RuntimeException("Game with ID already exists: " + dto.getId());
        }
        Game game = mapToEntity(dto);
        Game saved = gameRepository.save(game);
        return mapToResponseDTO(saved);
    }

    @Override
    public GameResponseDTO update(String id, GameRequestDTO dto) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found: " + id));

        // Clear old names
        existingGame.getNames().clear();

        // Update main fields
        existingGame.setCategory(dto.getCategory());
        existingGame.setDefaultLanguage(dto.getDefaultLanguage());

        // Add new names
        dto.getName().forEach(nameDTO -> {
            GameName gn = GameName.builder()
                    .language(nameDTO.getLanguage())
                    .value(nameDTO.getValue())
                    .build();
            existingGame.getNames().add(gn);
        });

        Game updated = gameRepository.save(existingGame);
        return mapToResponseDTO(updated);
    }

    @Override
    public void deleteBatch(List<String> ids) {
        ids.forEach(gameRepository::deleteById);
    }



}
