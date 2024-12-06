package dev.zbib.userservice.controller;

import dev.zbib.shared.dto.ProviderDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/favorites")
@RequiredArgsConstructor
public class UserFavoriteController {

} 