package org.example.gamemanagement.service;

import org.example.gamemanagement.dto.GameRequestDTO;
import org.example.gamemanagement.dto.GameResponseDTO;
import org.example.gamemanagement.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {
    Page<GameResponseDTO> search(Category category, String keyword, Pageable pageable);
    GameResponseDTO getById(String id);
    GameResponseDTO create(GameRequestDTO dto);
    GameResponseDTO update(String id, GameRequestDTO dto);
    void deleteBatch(List<String> ids);
}
