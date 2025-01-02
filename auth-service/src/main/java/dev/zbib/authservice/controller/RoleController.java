package dev.zbib.authservice.controller;

import dev.zbib.authservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{id}/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PutMapping("/{role}")
    public ResponseEntity<String> assignUserToRole(
            @PathVariable String id,
            @PathVariable String role) {
        roleService.assignRole(id, role);
        return ResponseEntity.ok("Role assigned");
    }
}
