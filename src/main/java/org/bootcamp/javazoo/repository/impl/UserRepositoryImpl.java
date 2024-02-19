package org.bootcamp.javazoo.repository.impl;

import org.bootcamp.javazoo.entity.Post;
import org.bootcamp.javazoo.entity.Product;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.repository.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    List<User> users = new ArrayList<>();

    public UserRepositoryImpl() {
        Product product1 = new Product(1, "Laptop", "Electronics", "BrandX", "Silver", "Buen estado");
        Product product2 = new Product(2, "Smartphone", "Electronics", "BrandY", "Negro", "Usado");

        Seller seller1 = new Seller(1, "Seller 1");
        Seller seller2 = new Seller(2, "Seller 2");
        Seller seller3 = new Seller(3, "Seller 3");
        User user1 = new User(1, "User 1");
        user1.setFollowed(List.of(seller1, seller2, seller3));

        Post post1 = new Post(1, seller1, LocalDate.now(), 1, 500.0, product1);
        Post post2 = new Post(2, seller2, LocalDate.now().minusDays(5), 2, 300.0, product2);
        Post post3 = new Post(3, seller1, LocalDate.now().minusWeeks(2), 1, 250.0, product1);

        seller1.getPosts().add(post1);
        seller2.getPosts().add(post2);
        seller1.getPosts().add(post3);

        seller1.getFollowers().add(user1);
        seller2.getFollowers().add(user1);
        seller3.getFollowers().add(user1);

        users.add(user1);
    }

    @Override
    public User getById(Integer userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
    }
}
