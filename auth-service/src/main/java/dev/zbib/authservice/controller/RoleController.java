package dev.zbib.authservice.controller;

import dev.zbib.authservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PutMapping("/assign/users/{id}/")
    public ResponseEntity<String> assignUserToRole(
            @PathVariable String id,
            @RequestParam String role) {
        roleService.assignRole(id, role);
        return ResponseEntity.ok("Role assigned");
    }
}
