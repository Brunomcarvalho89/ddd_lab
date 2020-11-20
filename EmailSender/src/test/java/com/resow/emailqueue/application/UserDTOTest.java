package com.resow.emailqueue.application;

import com.resow.emailqueue.application.dto.UserDTO;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class UserDTOTest {

    @Test
    public void testConstructor() {

        String fullname = "fullname";
        String nickname = "nickname";
        
        Set<String> email = new HashSet<>();
        email.add("teste@teste.com");
        
        Set<String> phones = new HashSet<>();
        phones.add("992256545");
        
        String addressDescription = "address description";
        String addressComplement = "address complement";
        long number = 1;
        String zipCode = "26545002";
        long city = 2;

        UserDTO userDTO = new UserDTO(fullname, nickname, email, phones, addressDescription, addressComplement, number, zipCode, city);

        Assertions.assertEquals(fullname, userDTO.getFullName());
        Assertions.assertEquals(nickname, userDTO.getNickname());
        Assertions.assertEquals(email, userDTO.getEmail());
        Assertions.assertEquals(phones, userDTO.getPhones());
        Assertions.assertEquals(addressDescription, userDTO.getAddressDescription());
        Assertions.assertEquals(addressComplement, userDTO.getAddressComplement());
        Assertions.assertEquals(number, userDTO.getNumber());
        Assertions.assertEquals(zipCode, userDTO.getZipCode());
        Assertions.assertEquals(city, userDTO.getCityID());

    }

    @Test
    public void testSettersAndGetters() {

        String fullname = "fullname";
        String nickname = "nickname";
        Set<String> email = null;
        Set<String> phones = null;
        String addressDescription = "address description";
        String addressComplement = "address complement";
        long number = 1;
        String zipCode = "26545002";
        long city = 2;

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(fullname);
        userDTO.setNickname(nickname);
        userDTO.setEmail(email);
        userDTO.setPhones(phones);
        userDTO.setAddressDescription(addressDescription);
        userDTO.setAddressComplement(addressComplement);
        userDTO.setNumber(number);
        userDTO.setZipCode(zipCode);
        userDTO.setCityID(city);

        Assertions.assertEquals(fullname, userDTO.getFullName());
        Assertions.assertEquals(nickname, userDTO.getNickname());
        Assertions.assertEquals(email, userDTO.getEmail());
        Assertions.assertEquals(phones, userDTO.getPhones());
        Assertions.assertEquals(addressDescription, userDTO.getAddressDescription());
        Assertions.assertEquals(addressComplement, userDTO.getAddressComplement());
        Assertions.assertEquals(number, userDTO.getNumber());
        Assertions.assertEquals(zipCode, userDTO.getZipCode());
        Assertions.assertEquals(city, userDTO.getCityID());

    }
}
