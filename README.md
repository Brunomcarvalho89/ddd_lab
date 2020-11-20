# DDD_lab

Implementação do contexto de Identificação e Autenticação de usuários usando o conhecimentos de Domain Driven Design em Java.

Descrição dos projetos:
  
  # AuthenticationIdentity
  
    Projeto AuthenticationIdentity é o projeto principal com api para cadastro de usuarios e geração do token para acesso as funcionalidades.    
    
    - Tecnologias e Frameworks:
        * Spring boot
        * Spring boot JPA
        * Spring boot Security
        * Spring AMQP para integração com o sistema de fila RabbitMQ
        * Mysql
        * Flyway
        * JWT
        * Swagger
        * Tom cat 9
        * JUnit 5
        * H2
  
  # EmailSender
  
    Projeto EmailSender responsável por consumir mensagens do RabbitMQ e realizar a notificação via email para o usuário.
    
    - Tecnologias e Frameworks:
        * Spring boot
        * Spring AMQP para integração com o sistema de fila RabbitMQ
        * JUnit 5
        
  # ResowCommom
  
    Projeto ResowCommom responsável com classes utilizadas nos outros projetos.
        
        
        



