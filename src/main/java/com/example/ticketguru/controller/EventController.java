package com.example.ticketguru.controller;

import com.example.ticketguru.domain.Event;
import com.example.ticketguru.dto.EventDto;
import com.example.ticketguru.repository.EventRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<EventDto> getAll() {
        return eventRepository.findAllByOrderByAikaAsc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return eventRepository.findById(id)
                .<ResponseEntity<Object>>map(event -> ResponseEntity.ok(toDto(event)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.<String, String>of("message", "Tapahtumaa ei löydy id:llä " + id)));
    }

    @PostMapping
    public ResponseEntity<EventDto> create(@Valid @RequestBody EventDto dto) {
        Event event = toEntity(dto);
        event = eventRepository.save(event);
        EventDto created = toDto(event);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody EventDto dto) {
        return eventRepository.findById(id)
                .map(existing -> {
                    existing.setKuvaus(dto.getKuvaus());
                    existing.setAika(dto.getAika());
                    existing.setKaupunki(dto.getKaupunki());
                    existing.setPaikka(dto.getPaikka());
                    existing.setKapasiteetti(dto.getKapasiteetti());
                    Event saved = eventRepository.save(existing);
                    return ResponseEntity.<Object>ok(toDto(saved));
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.<String, String>of("message", "Tapahtumaa ei löydy id:llä " + id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return eventRepository.findById(id)
                .map(event -> {
                    eventRepository.delete(event);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private EventDto toDto(Event event) {
        return new EventDto(
                event.getId(),
                event.getKuvaus(),
                event.getAika(),
                event.getKaupunki(),
                event.getPaikka(),
                event.getKapasiteetti()
        );
    }

    private Event toEntity(EventDto dto) {
        Event event = new Event();
        event.setKuvaus(dto.getKuvaus());
        event.setAika(dto.getAika());
        event.setKaupunki(dto.getKaupunki());
        event.setPaikka(dto.getPaikka());
        event.setKapasiteetti(dto.getKapasiteetti());
        return event;
    }
}
