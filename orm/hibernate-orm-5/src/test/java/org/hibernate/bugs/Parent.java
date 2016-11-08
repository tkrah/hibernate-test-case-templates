package org.hibernate.bugs;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parent {
  
  @Id
  @GeneratedValue
  private Long id;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", orphanRemoval = true)
  // next works with PERSIST cascade
  //@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private Collection<Child> childs = new ArrayList<Child>();
  
  
  /**
   * @return the id
   */
  protected Long getId() {
    return id;
  }
  
  
  /**
   * @return the childs
   */
  protected Collection<Child> getChilds() {
    return childs;
  }
  
  
}
