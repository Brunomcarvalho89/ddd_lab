package com.resow.authenticationidentity.infrastructure.api.exception;

import com.resow.authenticationidentity.domain.model.identity.exception.AddContactUserException;
import com.resow.authenticationidentity.domain.model.identity.exception.AddressDescriptionException;
import com.resow.authenticationidentity.domain.model.identity.exception.CityException;
import com.resow.authenticationidentity.domain.model.identity.exception.InvalidPasswordException;
import com.resow.authenticationidentity.domain.model.identity.exception.NameInvalidException;
import com.resow.authenticationidentity.domain.model.identity.exception.NicknameAlreadyExistsException;
import com.resow.authenticationidentity.domain.model.identity.exception.NumberAddressException;
import com.resow.authenticationidentity.domain.model.identity.exception.RoleNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.exception.UserNotFoundException;
import com.resow.authenticationidentity.domain.model.identity.exception.ZipCodeException;
import com.resow.common.exception.GenerateHashException;
import com.resow.common.exception.InvalidTokenSecretException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author home
 */
@ControllerAdvice
public class CentralPointExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("Message not readable")
                .withDetails(ex.getMessage())
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("UserNotFoundException")
                .withDetails(userNotFoundException.getMessage())
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressDescriptionException.class)
    public ResponseEntity<?> handleAddressDescritionException(AddressDescriptionException addressDescriptionException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("AddressDescriptionException")
                .withDetails(addressDescriptionException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ZipCodeException.class)
    public ResponseEntity<?> handleZipCodeException(ZipCodeException zipCodeException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("ZipCodeException")
                .withDetails(zipCodeException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CityException.class)
    public ResponseEntity<?> handleCityException(CityException cityException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("CityException")
                .withDetails(cityException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberAddressException.class)
    public ResponseEntity<?> handleNumberAddressException(NumberAddressException numberAddressException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("NumberAddressException")
                .withDetails(numberAddressException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NameInvalidException.class)
    public ResponseEntity<?> handleNameInvalidException(NameInvalidException nameInvalidException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("NameInvalidException")
                .withDetails(nameInvalidException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AddContactUserException.class)
    public ResponseEntity<?> handleAddContactUserException(AddContactUserException addContactUserException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("AddContactUserException")
                .withDetails(addContactUserException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenerateHashException.class)
    public ResponseEntity<?> handleGenerateHashException(GenerateHashException generateHashException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("GenerateHashException")
                .withDetails(generateHashException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NicknameAlreadyExistsException.class)
    public ResponseEntity<?> handleNicknameAlreadyExistsException(NicknameAlreadyExistsException nicknameAlreadyExistsException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("NicknameAlreadyExistsException")
                .withDetails(nicknameAlreadyExistsException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleRoleNotfoundException(RoleNotFoundException roleNotFoundException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("RoleNotFoundException")
                .withDetails(roleNotFoundException.getMessage())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(InvalidTokenSecretException.class)
    public ResponseEntity<?> handleRoleNotfoundException(InvalidTokenSecretException invalidTokenSecretException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("InvalidTokenSecretException")
                .withDetails(invalidTokenSecretException.getMessage())
                .withStatus(HttpStatus.UNAUTHORIZED.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handleInvalidPasswordException(InvalidPasswordException invalidPasswordException) {

        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .withTitle("InvalidPasswordException")
                .withDetails(invalidPasswordException.getMessage())
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withTimestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);

    }

}
