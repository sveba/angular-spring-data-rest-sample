package org.guylabs.angular.spring.data.rest.sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Simple category entity.
 */
@Entity
public final class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;


    private Parent() {
    }

    private Parent(String name) {
        this.name = name;
    }

    public static Parent from(String name) {
        return new Parent(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
