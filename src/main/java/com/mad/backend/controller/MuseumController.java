<<<<<<< Updated upstream
=======
package com.mad.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

import com.mad.backend.model.*;
import com.mad.backend.repo.*;

@RestController
@RequestMapping("/museum")
public class MuseumController {

    @Autowired
    private MuseumRepository museumRepository;

    @PostConstruct
    public void init() {

        if (museumRepository.count() == 0) {
            System.out.println("Database is empty, initializing..");
            Address a1 = new Address("Stateville", "123 Main Street, Cityville", "12345");
            Museum m1 = new Museum("National History Museum",
                    "Explore the rich history of our world through fascinating exhibits and artifacts.",
                    a1, "[Link to Image 1]", "/images/national_history_museum.jpg",
                    "Monday to Friday, 9 AM to 6 PM", 15.99);
            museumRepository.save(m1);

            // User u1 = new User("...");
            // userRepository.save(u1);

            // Comment c1 = new Comment("..", m1, u1);
            // commentRepository.save(c1);

            System.out.println("Museum and Address sample data is saved!");
        }
    }

    @GetMapping("/allMuseums")
    public List<Museum> getAllMuseums() {

        return museumRepository.findAll();

    }

    @PostMapping("/search")
    public List<Museum> searchBooks(@RequestBody Museum museum) {

        List<Museum> museums = museumRepository.findByName(museum.getName());

        return museums;
    }

    @GetMapping("/{address}")
    public List<Museum> getAllMuseumsByAddress(Address address) {

        List<Museum> museums = museumRepository.findByAddress(address);
        return museums;
    }

    @PostMapping("/save")
    public Museum saveMuseum(@RequestBody Museum museum) {

        Museum museumSaved = museumRepository.save(museum);

        return museumSaved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Museum> updateMuseum(@PathVariable String id, @RequestBody Museum updatedMuseum) {
        Optional<Museum> optionalMuseum = museumRepository.findById(id);

        if (optionalMuseum.isPresent()) {
            Museum existingMuseum = optionalMuseum.get();

            // Update fields with non-null values from updatedMuseum
            if (updatedMuseum.getName() != null) {
                existingMuseum.setName(updatedMuseum.getName());
            }
            if (updatedMuseum.getDescription() != null) {
                existingMuseum.setDescription(updatedMuseum.getDescription());
            }
            if (updatedMuseum.getAddress() != null) {
                existingMuseum.setAddress(updatedMuseum.getAddress());
            }
            if (updatedMuseum.getImage() != null) {
                existingMuseum.setImage(updatedMuseum.getImage());
            }
            if (updatedMuseum.getImagePath() != null) {
                existingMuseum.setImagePath(updatedMuseum.getImagePath());
            }
            if (updatedMuseum.getOpeningHours() != null) {
                existingMuseum.setOpeningHours(updatedMuseum.getOpeningHours());
            }
            if (updatedMuseum.getPrice() != 0.0) {
                existingMuseum.setPrice(updatedMuseum.getPrice());
            }

            Museum updated = museumRepository.save(existingMuseum);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeMuseum(@PathVariable String id) {
        Optional<Museum> optionalMuseum = museumRepository.findById(id);

        if (optionalMuseum.isPresent()) {
            museumRepository.deleteById(id);
            return ResponseEntity.ok("Museum with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Museum with ID " + id + " not found.");
        }
    }

}
>>>>>>> Stashed changes
