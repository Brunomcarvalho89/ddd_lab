package com.resow.common.application.dto;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class LinkDTO extends AbstractBaseDTO {

    private String rel;
    private String link;

    public LinkDTO() {
    }

    public LinkDTO(String rel, String link) {
        this.rel = rel;
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
