package com.dev.fitbooking.controller;

import com.dev.fitbooking.dto.request.FitnessRoomRequestDto;
import com.dev.fitbooking.dto.response.FitnessRoomResponseDto;
import com.dev.fitbooking.model.FitnessRoom;
import com.dev.fitbooking.service.FitnessRoomService;
import com.dev.fitbooking.service.mapper.FitnessRoomMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fitness-rooms")
public class FitnessRoomController {
    private final FitnessRoomService fitnessRoomService;
    private final FitnessRoomMapper fitnessRoomMapper;

    @Autowired
    public FitnessRoomController(FitnessRoomService fitnessRoomService,
                                 FitnessRoomMapper fitnessRoomMapper) {
        this.fitnessRoomService = fitnessRoomService;
        this.fitnessRoomMapper = fitnessRoomMapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid FitnessRoomRequestDto requestDto) {
        FitnessRoom fitnessRoom = fitnessRoomMapper.toEntity(requestDto);
        fitnessRoomService.add(fitnessRoom);
    }

    @GetMapping
    public List<FitnessRoomResponseDto> getAll() {
        return fitnessRoomService
                .getAll()
                .stream()
                .map(fitnessRoomMapper::toDto)
                .collect(Collectors.toList());
    }
}
