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
    @Override
    public List<Seller> getFollowedList(Integer userId) {
        var user =  users.stream().filter(u -> u.getId().equals(userId)).findFirst();
        if(user.isEmpty()) {
            return new ArrayList<>();
        }else {
            return user.get().getFollowed();
        }
    }
}
