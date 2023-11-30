package com.example.coupetonarbrebackend.User.DataMapperLayer;

import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper {

    @Mapping(target = "clientId", ignore = true)
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "address", target = "address")


    Client requestModelToEntity(ClientRequestDTO clientRequestDTO);
}
