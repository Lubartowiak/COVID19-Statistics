package com.example.covidserver.controller;

import com.example.covidserver.DTO.CountryStatDTO;
import com.example.covidserver.DTO.DataBaseXMLObjectDTO;
import com.example.covidserver.DTO.DataBaseObjectDTO;
import com.example.covidserver.DTO.ImportDatabaseResponse;
import com.example.covidserver.service.CountryStatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
@Transactional(isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
public class StatisticsController {

    private final CountryStatService statService;

    @GetMapping("/all")
    public ResponseEntity<List<CountryStatDTO>> allStatistics() {
        return ResponseEntity.ok(statService.allStats());
    }

    @PostMapping("/import")
    public ResponseEntity<ImportDatabaseResponse> importJSONDatabase(@RequestBody List<DataBaseObjectDTO> database) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return this.statService.importJSONDatabase(database);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ImportDatabaseResponse("Only admin can do that"));
    }

    @PostMapping(value = "/importXML", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ImportDatabaseResponse> importXMLDatabase(@RequestBody List<DataBaseXMLObjectDTO> database) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return this.statService.importXMLDatabase(database);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ImportDatabaseResponse("Only admin can do that"));
    }
}
