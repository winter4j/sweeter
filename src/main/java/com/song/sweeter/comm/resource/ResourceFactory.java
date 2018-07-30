package com.song.sweeter.comm.resource;

import com.song.sweeter.comm.dto.ResDto;
import com.song.sweeter.comm.dto.UserDto;
import com.song.sweeter.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceFactory {

//    private static final ResourceFactory instance = new ResourceFactory();
//    public static ResourceFactory getInstance(){
//        return instance;
//    }
//    private ResourceFactory(){}

    @Autowired
    private ResourceService resourceService;

    public boolean reward(UserDto userDto, ResDto resDto, String log){
        if(userDto == null || resDto == null || resDto.getNum() <= 0){
            return false;
        }
        boolean succ = resDto.getResEnum().reward(userDto, resDto.getNum(), resourceService);
        return succ;
    }

    public boolean consume(UserDto userDto, ResDto resDto, String log){
        if(userDto == null || resDto == null || resDto.getNum() <= 0){
            return false;
        }
        boolean succ = resDto.getResEnum().consume(userDto, resDto.getNum(), resourceService);
        return succ;
    }
}
