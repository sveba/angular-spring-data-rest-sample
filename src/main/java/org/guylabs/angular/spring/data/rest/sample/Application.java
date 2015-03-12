package org.guylabs.angular.spring.data.rest.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Main application class which is auto configured by Spring. It adds default values in the main method
 * and sets the base URI of the REST endpoint to "/rest".
 */
@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
public class Application extends RepositoryRestMvcConfiguration {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        ItemRepository itemRepository = context.getBean(ItemRepository.class);
        ParentRepository parentRepository = context.getBean(ParentRepository.class);

        Parent parent1 = Parent.from("Parent 1");
        Parent parent2 = Parent.from("Parent 2");
        Parent parent3 = Parent.from("Parent 3");
        parentRepository.save(parent1);
        parentRepository.save(parent2);
        parentRepository.save(parent3);
        
        // save a couple of categories
        Category firstCategory = Category.from("Child Category 1", parent1, new ArrayList<Item>());
		categoryRepository.save(firstCategory);
        Category secondCategory = Category.from("Child Category 2", parent1, new ArrayList<Item>());
		categoryRepository.save(secondCategory);
        categoryRepository.save(Category.from("Child Category 3", parent1, new ArrayList<Item>()));
        categoryRepository.save(Category.from("Child Category 4", parent2, new ArrayList<Item>()));
        categoryRepository.save(Category.from("Child Category 5", parent3, new ArrayList<Item>()));
        
        // save a couple of items
        itemRepository.save(Item.from("Item 1", null));
        itemRepository.save(Item.from("Item 2", firstCategory));
        itemRepository.save(Item.from("Item 3", firstCategory));
        itemRepository.save(Item.from("Item 4", secondCategory));
        itemRepository.save(Item.from("Item 5", secondCategory));
        itemRepository.save(Item.from("Item 6", secondCategory));
    }

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        try {
            config.setBaseUri(new URI("/rest"));
        } catch (URISyntaxException exception) {
            throw new RuntimeException("Cannot set base uri on REST configuration", exception);
        }
    }

}