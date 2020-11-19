package com.resow.authenticationidentity.domain.model.identity.event;

import com.resow.common.event.BaseEvent;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class CityChanged implements BaseEvent {

    private String userUUID;
    private Long newCity;
    private Long oldCity;

    public CityChanged(String userUUID, Long newCity, Long oldCity) {
        this.userUUID = userUUID;
        this.newCity = newCity;
        this.oldCity = oldCity;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public Long getNewCity() {
        return newCity;
    }

    public Long getOldCity() {
        return oldCity;
    }
}
