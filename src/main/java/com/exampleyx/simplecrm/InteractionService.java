package com.exampleyx.simplecrm;

import java.util.ArrayList;


public interface InteractionService {
  Interaction saveInteraction(Interaction interaction);

  Interaction getInteraction(int id);

  ArrayList<Interaction> getAllInteractions();

  Interaction updateInteraction(int id, Interaction interaction);

  void deleteInteraction(int id);

}
