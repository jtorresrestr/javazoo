package org.bootcamp.javazoo.service.interfaces;

import org.bootcamp.javazoo.dto.MessageDTO;
import org.bootcamp.javazoo.dto.PostDto;

public interface IPostService {

    MessageDTO addNewPost(PostDto postDto);

}
