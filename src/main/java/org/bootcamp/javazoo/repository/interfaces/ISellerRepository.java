package org.bootcamp.javazoo.repository.interfaces;

import org.bootcamp.javazoo.entity.Seller;

public interface ISellerRepository {
    Seller findById(Integer id);
}
