package org.bootcamp.javazoo.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Seller extends User {
    private List<User> followers = new ArrayList<>();

    public Seller(Integer id, String name) {
        super(id, name);
    }
}


