package com.topicos.backend.controller;

import com.topicos.backend.dto.StatsDTO;
import com.topicos.backend.security.JwtTokenUtil;
import com.topicos.backend.services.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@AllArgsConstructor
public class StatsController {

  private final JwtTokenUtil jwtTokenUtil;

  private final StatsService statsService;

  //GET
  @GetMapping("/stats")
  public StatsDTO getAllAreas(@RequestHeader("Authorization") String token) {
    if (this.jwtTokenUtil.getAdminFromToken(token)) {
      return this.statsService.getStats();
    }
    return this.statsService.getStatsByCompany(this.jwtTokenUtil.getCompanyFromToken(token));
  }

}
