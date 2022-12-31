package com.jcarter.jsmms.controllers;

import com.jcarter.jsmms.models.Material;
import com.jcarter.jsmms.repositories.MaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private final MaterialRepository materialsRepository;

    public MaterialController(MaterialRepository repo) {
        materialsRepository = repo;
    }

    private static final Logger logger = Logger.getLogger(MaterialController.class.getName());

    @GetMapping
    public List<Material> getAllMaterials(@RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit) {
        List<Material> materials = materialsRepository.getAllMaterials();
        return materials;
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<Material> getSpecificMaterial(@PathVariable("materialId") int materialId) {
        Material m = materialsRepository.getMaterialForId(materialId);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<Material> putSpecificMaterial(@PathVariable("materialId") int materialId, @RequestBody Material newMaterial) {
        if (materialsRepository.updateMaterialForId(materialId, newMaterial) <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<Material> deleteSpecificMaterial(@PathVariable("materialId") int materialId) {
        materialsRepository.deleteMaterialForId(materialId);
        return ResponseEntity.status(HttpStatus.GONE).body(null);
    }

    @PostMapping
    public ResponseEntity<Material> addMaterial(
            @RequestBody Material newMaterial) {
        materialsRepository.storeMaterial(newMaterial);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


}
