package org.hibernate.bugs;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Child {
  
  @Id
  @GeneratedValue
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "parent_id")
  private Parent parent;
  
  
  
  public Child(Parent parent) {
    super();
    this.parent = parent;
  }
  
  
  public Child() {
    
  }


  @Override
  public String toString() {
    return "Child [id=" + id + "]";
  }
  
  
}
