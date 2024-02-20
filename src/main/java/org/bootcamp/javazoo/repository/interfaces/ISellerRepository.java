package org.bootcamp.javazoo.repository.interfaces;

import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;

public interface ISellerRepository {
    Seller findById(Integer id);
    void addFollower(User user, Seller seller);
    void removeFollower(Seller seller);
}
