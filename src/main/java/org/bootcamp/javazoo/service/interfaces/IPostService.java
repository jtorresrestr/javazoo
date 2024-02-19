package org.bootcamp.javazoo.service.interfaces;

import org.bootcamp.javazoo.dto.PostDto;
import org.bootcamp.javazoo.dto.response.MessageDto;

public interface IPostService {

    MessageDto addNewPost(PostDto postDto);

}
