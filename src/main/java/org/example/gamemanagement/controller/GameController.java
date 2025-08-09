package org.example.gamemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.gamemanagement.dto.GameRequestDTO;
import org.example.gamemanagement.dto.GameResponseDTO;
import org.example.gamemanagement.entities.Category;
import org.example.gamemanagement.service.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<Page<GameResponseDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) String keyword) {

        Page<GameResponseDTO> result = gameService.search(category, keyword, PageRequest.of(page, size));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> get(@PathVariable String id) {
        GameResponseDTO dto= gameService.getById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> create(@Valid @RequestBody GameRequestDTO dto) {
        GameResponseDTO created = gameService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> update(@PathVariable String id, @Valid @RequestBody GameRequestDTO dto) {
        GameResponseDTO updated = gameService.update(id, dto);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBatch(@RequestBody List<String> ids) {
        gameService.deleteBatch(ids);
        return ResponseEntity.noContent().build();
    }
}
