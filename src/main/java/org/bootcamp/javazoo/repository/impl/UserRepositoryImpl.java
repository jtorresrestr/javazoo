package org.bootcamp.javazoo.repository.impl;

import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.repository.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    List<User> users = new ArrayList<>();

    public UserRepositoryImpl() {
        Seller seller1 = new Seller(1, "Seller 1");
        Seller seller2 = new Seller(2, "Seller 2");
        Seller seller3 = new Seller(3, "Seller 3");
        User user1 = new User(1, "User 1");
        User user2 = new User(7, "User 7");
        user1.addFollowed(seller1);
        user1.addFollowed(seller2);
        user1.addFollowed(seller3);
        users.add(user1);
        user2.addFollowed(seller3);
        users.add((user2));
    }

    @Override
    public User getById(Integer userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
    }

}
