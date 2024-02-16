package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.SellerDto;
import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.repository.interfaces.IUserRepository;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<SellerDto> getFollowedList(int userId) {
        List<Seller> seller =  userRepository.getFollowedList(userId);

        return null;

    }
}
