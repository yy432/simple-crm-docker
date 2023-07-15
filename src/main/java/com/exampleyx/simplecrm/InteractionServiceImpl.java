package com.exampleyx.simplecrm;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl implements InteractionService{

  private InteractionRepository interactionRepository;

  public InteractionServiceImpl(InteractionRepository interactionRepository) {
    this.interactionRepository = interactionRepository;
  }

  @Override
  public Interaction saveInteraction(Interaction interaction) {
    Interaction newInteraction = interactionRepository.save(interaction);
    return newInteraction;
  }

  //get one
  @Override
  public Interaction getInteraction(int id) {
    //Interaction foundInteraction = interactionRepository.findById(id).get();
    Optional<Interaction> wrappedFoundInteraction = interactionRepository.findById(id);
    if(!wrappedFoundInteraction.isPresent()){
      throw new InteractionNotFoundException(id);
    }
    Interaction foundInteraction = wrappedFoundInteraction.get();
    //if(!wrappedFoundInteraction.isPresent()){
    return foundInteraction; 
  }

  //all 
  @Override
  public ArrayList<Interaction> getAllInteractions() {
    ArrayList<Interaction> allInteractions = (ArrayList<Interaction>) interactionRepository.findAll();
    return allInteractions;
  }

  @Override
  public Interaction updateInteraction(int id, Interaction interaction) {
    //Interaction interactionToUpdate = interactionRepository.findById(id).get();
    Optional<Interaction> wrappedinteractionToUpdate = interactionRepository.findById(id);

    if(!wrappedinteractionToUpdate.isPresent()){
      throw new InteractionNotFoundException(id);
    }
    Interaction interactionToUpdate = wrappedinteractionToUpdate.get();
    interactionToUpdate.setRemarks(interaction.getRemarks());
    interactionToUpdate.setInteractionDate(interaction.getInteractionDate());
    return interactionRepository.save(interactionToUpdate);
  }

  @Override
  public void deleteInteraction(int id) {
    interactionRepository.deleteById(id);
  }

}
