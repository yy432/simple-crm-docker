package com.exampleyx.simplecrm;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/interactions")
public class InteractionController {

  private InteractionService interactionService;

  public InteractionController(InteractionService interactionService) {
    this.interactionService = interactionService;
  }

  // Create
  @PostMapping("")
  public ResponseEntity<Interaction> saveInteraction(@RequestBody Interaction interaction){
    Interaction newInteraction = interactionService.saveInteraction(interaction);
    return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
  }

  // Read
  @GetMapping("/{id}")
  public ResponseEntity<Interaction> getInteraction(@PathVariable int id){
    Interaction foundInteraction = interactionService.getInteraction(id);
    return new ResponseEntity<>(foundInteraction, HttpStatus.OK);
  }

  // Read All
  @GetMapping("")
  public ResponseEntity<ArrayList<Interaction>> getAllInteractions() {
    ArrayList<Interaction> allInteractions = interactionService.getAllInteractions();
    return new ResponseEntity<>(allInteractions, HttpStatus.OK);
  }

  //Update
  @PutMapping("/{id}")
  public ResponseEntity<Interaction> updateInteraction(@PathVariable int id, @RequestBody Interaction interaction) {
    Interaction updatedInteraction = interactionService.updateInteraction(id, interaction);
    return new ResponseEntity<>(updatedInteraction, HttpStatus.OK);

}

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteInteraction(@PathVariable int id) {
    interactionService.deleteInteraction(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}