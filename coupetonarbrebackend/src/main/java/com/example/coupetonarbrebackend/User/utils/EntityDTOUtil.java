//package com.example.coupetonarbrebackend.User.utils;
//
//
//import com.example.coupetonarbrebackend.User.DataLayer.Client;
//
//import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
//import org.springframework.beans.BeanUtils;
//
//import java.util.UUID;
//
//public class EntityDTOUtil {
//
//    public static ClientResponseDTO toClientResponseDto(Client client){
//        ClientResponseDTO clientResponseDTO =new ClientResponseDTO();
//        BeanUtils.copyProperties(client,clientResponseDTO);
//        return clientResponseDTO;
//    }
//
//  /*  public static Client toClientEntity(ClientRequestDTO clientRequestDTO){
//        Client client = new Client();
//        BeanUtils.copyProperties(clientRequestDTO,client);
//        return client;
//    }
//
//   */
//
//    public static String generateUUIDString(){
//        return UUID.randomUUID().toString();
//    }
//
//
//}
