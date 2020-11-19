package com.resow.common.application.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class AbstractHateoasBaseDTO extends AbstractBaseDTO {

    protected List<LinkDTO> listLinkDTO;

    public AbstractHateoasBaseDTO() {
        this.listLinkDTO = new ArrayList<>();
    }

    public void add(String rel, String link) {
        this.listLinkDTO.add(new LinkDTO(rel, link));
    }

    public List<LinkDTO> getListLinkDTO() {
        return listLinkDTO;
    }

    public void setListLinkDTO(List<LinkDTO> listLinkDTO) {
        this.listLinkDTO = listLinkDTO;
    }

}
