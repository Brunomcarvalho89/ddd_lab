# DDD_lab

Implementação do contexto de Identificação e Autenticação de usuários usando o conhecimentos de Domain Driven Design em Java.

Os projetos AuthenticationIdentity e EmailSender foram desenvolvidos usando a arquitetura Hexagonal/PortsAndAdapters para encapsulamento do Dominio.

# Descrição dos projetos:
  
  ## AuthenticationIdentity
  
    Projeto AuthenticationIdentity é o projeto principal com api para cadastro de usuarios e geração do token para acesso as funcionalidades. Como exercício estou consumindo duas apis de cep para validação do cep informado(Chamada no código esta comentada, porém esta funcionando). Após o registro do usuário uma mensagem é enviada para uma fila no rabbitmq com o objetivo de notificar o usuário.
    
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
  
    Projeto EmailSender responsável por consumir mensagens do RabbitMQ e realizar a notificação via email para o usuário. Quando o usuário é cadastrado pela api consumo a mensagem adicionada na fila com os dados do usuários para notificação via Email.
    
    - Tecnologias e Frameworks:
        * Spring boot
        * Spring AMQP para integração com o sistema de fila RabbitMQ
        * JUnit 5
        
  ## ResowCommom
  
    Projeto ResowCommom com classes utilizadas nos outros projetos.
        
        
Os projetos tem cobertura de testes, porém existem algumas funcionalidades que ainda nao foram implementadas.



