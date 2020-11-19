package com.resow.common.application.dto;

/**
 *
 * @author Bruno Carvalho - brunomcarvalho89@gmail.com
 */
public class ResponseDTO extends AbstractBaseDTO {

    private String mensagem;

    public ResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    
    
}
