package com.exampleyx.simplecrm;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "interaction")
public class Interaction {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "remarks")
  private String remarks;
  @Column(name = "interaction_date")
  private LocalDate interactionDate;

  // Many interactions can be associated with one customer
  @ManyToOne(optional = false)
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public LocalDate getInteractionDate() {
    return this.interactionDate;
  }

  public void setInteractionDate(LocalDate interactionDate) {
    this.interactionDate = interactionDate;
  }

}
