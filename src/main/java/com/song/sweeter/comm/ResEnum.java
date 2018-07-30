package com.song.sweeter.comm;

import com.song.sweeter.comm.dto.ResDto;
import com.song.sweeter.comm.dto.UserDto;
import com.song.sweeter.entity.User;
import com.song.sweeter.service.ResourceService;

/**
 * 资源
 */
public enum ResEnum {
    XING(1, "杏"){
        @Override
        public boolean reward(UserDto userDto, int num, ResourceService resourceService) {
            resourceService.getUserService().changeXing(userDto.getId(), num);
            return true;
        }

        @Override
        public boolean consume(UserDto userDto, int num, ResourceService resourceService) {
            resourceService.getUserService().changeXing(userDto.getId(), -num);
            return true;
        }

        @Override
        public ResDto genResDto(User user) {
            return new ResDto(this, user.getXing());
        }
    },
    DONG(2, "冬"){
        @Override
        public boolean reward(UserDto userDto, int num, ResourceService resourceService) {
            resourceService.getUserService().changeDong(userDto.getId(), num);
            return true;
        }
        @Override
        public boolean consume(UserDto userDto, int num, ResourceService resourceService) {
            resourceService.getUserService().changeDong(userDto.getId(), -num);
            return true;
        }
        @Override
        public ResDto genResDto(User user) {
            return new ResDto(this, user.getDong());
        }
    };

    private int type;
    private String name;

    ResEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean reward(UserDto userDto, int num, ResourceService resourceService){
        return false;
    }

    public boolean consume(UserDto userDto, int num, ResourceService resourceService){
        return false;
    }

    public ResDto genResDto(User user){
        return new ResDto(null, 0);
    }
}
