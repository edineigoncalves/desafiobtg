# Desafio Técnico BTG

#### DADOS TÉCNICOS :

- Swagger utilizado para Documentação
- Gerenciamento de dependência: Gradle
- Realização de Testes Unitário: JUnit 4.12
- Linguagem: Java 8
- Framework: Spring Boot 2.2
- Utilizada as práticas de Clean Code : Tornando o desenvolvimento e manutenção cada vez mais simples.
- Não há utilização de banco de dados.
- Não há utilização de bibliotecas utilitárias externas.

## ENDPOINTS : 
http://localhost:8080/v1/btg/jokenpo/swagger-ui.html

###  JOGADOR

###### Exemplo de Chamada

```
http://localhost:8080/v1/btg/jokenpo/game/play'
```

###### Exemplo de Retorno de Sucesso - 200 OK

```json
    {
    "meta": {
        "timestamp": "2020-06-19T07:19:10.123+0000"
    },
    "data": []
    }
```

#####  Inserção
http://localhost:8080/v1/btg/jokenpo/player/save

```json
  {
    "player": "Maria"
  }
```

###### Exemplo de Retorno de Sucesso - 200 OK

```json
{
  "meta": {
    "timestamp": "2020-06-19T07:19:10.123+0000"
  },
  "data": {
    "player": "Maria"
  }
}
```

##### Listagem


http://localhost:8080/v1/btg/jokenpo/player/all

###### Exemplo de Retorno de Sucesso - 200 OK

```json
{
  "meta": {
    "timestamp": "2020-06-19T07:19:10.123+0000"
  },
  "data": [
    {
      "player": "Wanda"
    },
    {
      "player": "Maria"
    },
    {
      "player": "Luiz"
    }
  ]
}
```

#####  Exclusão
http://localhost:8080/v1/btg/jokenpo/player/delete
```json
    {
        "player": "Maria"
    }
```

###### Exemplo de Retorno de Sucesso - 200 OK

```json
{
  "meta": {
    "timestamp": "2020-06-19T07:19:10.123+0000"
  },
  "data": [
    {
      "player": "Wanda"
    },
    {
      "player": "Luiz"
    }
  ]
}
```



### JOGADA (ACTION)
http://localhost:8080/v1/btg/jokenpo/player/action/save
##### Inserção

```json
    {
    "player": "Luiz",
    "action": "PAPEL"
    }
```

###### Exemplo de Retorno de Sucesso - 200 OK


```json
{
  "meta": {
    "timestamp": "2020-06-19T07:19:10.123+0000"
  },
  "data": [
    {
      "player": {
        "player": "Luiz"
      },
      "action": "PAPEL"
    },
    {
      "player": {
        "player": "Wanda"
      },
      "action": "PAPEL"
    },
    {
      "player": {
        "player": "Maria"
      },
      "action": "TESOURA"
    }
  ]
}
```

#####  Exclusão
http://localhost:8080/v1/btg/jokenpo/player/action/delete

###### Exemplo de Chamada

```json
    {
        "player": "Maria"
    }
```

###### Exemplo de Retorno de Sucesso - 200 OK

```json
{
  "meta": {
    "timestamp": "2020-06-19T07:19:10.123+0000"
  },
  "data": [
    {
      "player": {
        "player": "Luiz"
      },
      "action": "TESOURA"
    },
    {
      "player": {
        "player": "Wanda"
      },
      "action": "PAPEL"
    }
  ]
}
```

#####  Listagem

######  Exemplo de Chamada

http://localhost:8080/v1/btg/jokenpo/player/action/all

######  Exemplo de Retorno de Sucesso - 200 OK

```json
{
  "meta": {
    "timestamp": "2020-06-19T07:19:10.123+0000"
  },
  "data": [
    {
      "player": {
        "player": "Luiz"
      },
      "action": "TESOURA"
    },
    {
      "player": {
        "player": "Maria"
      },
      "action": "PEDRA"
    },
    {
      "player": {
        "player": "Wanda"
      },
      "action": "PAPEL"
    }
  ]
}
```

###  RESULTADO DO JOGO

#####  Obter Resultado do Jogo

######  Exemplo de Chamada

http://localhost:8080/v1/btg/jokenpo/game

###### Exemplo de Retorno de Sucesso - 200 OK

```json
{
  "meta": {
    "timestamp": "2020-06-19T07:19:10.123+0000"
  },
  "data": {
    "historico": [
      "Luiz (TESOURA)",
      "Wanda (PAPEL)",
      "Maria (PEDRA)"
    ],
    "resultado": "Empate"
  }
}
```


