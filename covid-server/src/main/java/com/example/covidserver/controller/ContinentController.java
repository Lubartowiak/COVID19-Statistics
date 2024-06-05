package com.example.covidserver.controller;

import com.example.covidserver.DTO.ContinentDTO;
import com.example.covidserver.service.ContinentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/continent")
@AllArgsConstructor
public class ContinentController {

    private ContinentService continentService;

    @GetMapping("/all")
    public ResponseEntity<List<ContinentDTO>> getAllContinents() {
        return ResponseEntity.ok(continentService.allContinents());
    }
}
