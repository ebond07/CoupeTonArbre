package com.example.coupetonarbrebackend.User.DataMapperLayer;

import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ClientResponseMapper {

    ClientResponseMapper INSTANCE = Mappers.getMapper( ClientResponseMapper.class );

    @Mapping(source = "clientId", target = "clientId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "address", target = "address")
    ClientResponseDTO entityToResponseModel(Client client);

    List<ClientResponseDTO> entityListToResponseModelList(List<Client> clients);
}
