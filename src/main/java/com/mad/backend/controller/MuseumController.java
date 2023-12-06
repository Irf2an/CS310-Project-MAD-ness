package com.mad.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;

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

import com.mad.backend.model.*;
import com.mad.backend.repo.*;

@RestController
@RequestMapping("/museum")
public class MuseumController {

    @Autowired
    private MuseumRepository museumRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    @PostConstruct
    public void init() {
        if (museumRepository.count() == 0) {
            System.out.println("Database is empty, initializing..");

            // ------------ Adding Address and Museum ------------
            Address a1 = new Address("1", "Stateville", "123 Main Street, Cityville", "12345");
            Museum m1 = new Museum("National History Museum",
                    "Explore the rich history of our world through fascinating exhibits and artifacts.",
                    a1, "national_history_museum.jpg", "/images/",
                    "Monday to Friday, 9 AM to 6 PM", 15.99);
            Address a2 = new Address("2", "State of Art", "456 Art Avenue, Creativity City", "56789");
            Museum m2 = new Museum("Art Gallery of Modern Masters",
                    "Immerse yourself in the world of modern art, featuring masterpieces from renowned artists.",
                    a2, "art_gallery.jpg", "/images/",
                    "Tuesday to Saturday, 10 AM to 8 PM", 20.50);
            Address a3 = new Address("3", "Cosmos State", "789 Galaxy Lane, Space City", "67890");
            Museum m3 = new Museum("Space Discovery Center",
                    "Embark on a journey to the stars and beyond, exploring the wonders of our universe.",
                    a3, "space_discovery_center.jpg", "/images/",
                    "Wednesday to Sunday, 11 AM to 7 PM", 18.75);
            Address a4 = new Address("4", "Paleoland", "101 Prehistoric Street, Fossilville", "54321");
            Museum m4 = new Museum("Dinosaur World Museum",
                    "Step back in time to the age of dinosaurs, with lifelike exhibits and fossil collections.",
                    a4, "dinosaur_world_museum.jpg", "/images/",
                    "Thursday to Monday, 10:30 AM to 5:30 PM", 14.99);
            Address a5 = new Address("5", "Innovation State", "202 Future Avenue, Tech City", "11223");
            Museum m5 = new Museum("Technology Innovations Hub",
                    "Discover the latest in technology and innovation through interactive displays and workshops.",
                    a5, "technology_innovations_hub.jpg", "/images/",
                    "Monday to Saturday, 10 AM to 6 PM", 22.00);

            museumRepository.save(m1);
            museumRepository.save(m2);
            museumRepository.save(m3);
            museumRepository.save(m4);
            museumRepository.save(m5);

            // ------------ Adding User ------------
            User u1 = new User("john123", "john123@test.com", "john", "doe", "12345");
            userRepository.save(u1);

            // ------------ Adding Comment ------------
            Comment c1 = new Comment("Highly Recommended",
                    "Immersive exhibits that seamlessly blend art, history, and technology, making each visit to this museum a captivating journey through time and culture. From interactive installations to thought-provoking artifacts, thiscultural haven sparks curiosity and fosters a deep appreciation for the richness of our heritage",
                    4,
                    u1, m1, LocalDateTime.now());
            commentRepository.save(c1);
            System.out.println("All sample data is saved!");
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