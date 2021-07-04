package project.controller;

import project.entity.dto.WriterDto;
import project.service.UserService;
import project.service.implementation.JdbcUserServiceImpl;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new JdbcUserServiceImpl();
    }

    public WriterDto save(WriterDto writerDto){
        return userService.save(writerDto);
    }

    public WriterDto update(WriterDto writerDto){
        return userService.update(writerDto);
    }

    public WriterDto get(Long id){
        return userService.get(id);
    }

    public WriterDto getByFirstName(String firstName){

        return userService.getByFirstName(firstName);
    }

    public WriterDto getByLastName(String lastName){

        return userService.getByLastName(lastName);
    }

    public void remove(Long id){
        userService.remove(id);
    }
}
