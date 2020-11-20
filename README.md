# DDD_lab

Implementação do contexto de Identificação e Autenticação de usuários usando o conhecimentos de Domain Driven Design em Java.

Os projetos AuthenticationIdentity e EmailSender foram desenvolvidos usando a arquitetura Hexagonal/PortsAndAdapters para encapsulamento do Dominio.

# Descrição dos projetos:
  
  ## AuthenticationIdentity
  
    Projeto AuthenticationIdentity é o projeto principal com api para cadastro de usuarios e geração do token para acesso as funcionalidades. Como exercício estou consumindo duas apis de cep para validação do cep informado(Chamada no código esta comentada, porém esta funcionando).
    
    - Tecnologias e Frameworks:
        * Spring Boot
        * Spring Boot JPA
        * Spring Boot Security
        * Spring Boot Actuator
        * Spring AMQP para integração com o sistema de fila RabbitMQ
        * Mysql
        * Flyway
        * Autenticação com JWT
        * Swagger
        * Tom cat 9
        * JUnit 5
        * H2
  
  ## EmailSender
  
    Projeto EmailSender responsável por consumir mensagens do RabbitMQ e realizar a notificação via email para o usuário.
    
    - Tecnologias e Frameworks:
        * Spring boot
        * Spring AMQP para integração com o sistema de fila RabbitMQ
        * JUnit 5
        
  ## ResowCommom
  
    Projeto ResowCommom com classes utilizadas nos outros projetos.
        
        
        



