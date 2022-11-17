# Sistema

## Conteúdo
1. [Banco e versões utilizados](#banco)
2. [Bibliotecas utilizadas](#libs)
   1.  [Restrições aplicadas no JPA](#jpa)
3. [Estrutura de diretórios](#dir)


## Banco e versões utilizados <a name="banco"></a>

---
TBA.

## Estrutura de diretórios <a name="libs"></a>

---
TBA.

## Bibliotecas utilizadas <a name="libs"></a>
### Restrições aplicadas no JPA <a name="jpa"></a>

#### O JPA permite que você abstraia SQL nas entidades caso queira, porém, visto que isso não é permitido, não utilizamos nenhum tipo de manipulação, usando o JPA apenas para conectar com o banco.
- *NENHUMA* validação de banco (constraints, not nulls, checks, tamanhos, etc) é feita pelo JPA - isso é feito EXLCUSIVAMENTE pelo SQL, portanto, quaisquer erros são lançados pelo banco. Isso pode ser conferido rapidamente pelos seguintes fatores:
  1. Nas entidades, em @Column, e demais classes de serviço e controller, não há validação de campo (como uso de parâmetros como 'insertable', 'nullable', 'length', 'format', etc)
  2. Não há nenhuma biblioteca de validação externa;
  3. A conexão com o banco, configurada em [application.yaml](src/main/resources/application.yaml) é feita no modo validate, em que o JPA não aplica nenhuma mudança no esquema nem cria nada a partir do app, apenas busca o que já existe no banco e mapeia para as entidades;
- *NENHUM* uso de facilidades de busca é utilizada (Relações bidirecionais)
  - Relações com FK, por exemplo: a entidade Veiculo guarda a chave de Motorista, numa relação que é semanticamente N:1. O JPA oferece a possibilidade de mapear biredicionalmente, isto é, além do padrão, que é Veículo guardando a chave do seu Motorista, permite que o objeto Motorista conheça/possua uma lista de Veículos (õ que não ocorre no banco). Retiramos essa última possibilidade, afim de manter todo o controle de consultas para SQL puro.
- *NENHUM* uso de facilidades de join é utilizado (Relações bidirecionais) 
  - Ao mapear uma FK que está em uma tabela, o JPA oferece a possibilidade de fazer JOIN automaticamente. Por isso, desabilitamos isso por meio da seguinte notação nas FKs:
    ```java
      @ManyToOne(fetch = FetchType.LAZY)
      private Object foreignKey;
      //... {
      @OneToOne(fetch = FetchType.LAZY)
      private Object foreignKey;
    ```
## Estrutura de diretórios <a name="dir"></a>
````bash
├───.github
│   └───workflows
├───.idea
├───.mvn
│   └───wrapper
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───scc
│   │   │           └───campanha
│   │   │               ├───controllers
│   │   │               │   └───dtos
│   │   │               │   └───BaseController.java
│   │   │               │   └───ExceptionHandlerController.java
│   │   │               ├───database
│   │   │               │   ├───models
│   │   │               │   │   └───pks
│   │   │               │   └───repositories
│   │   │               │   └───ConsultasSQL.java
│   │   │               ├───enums
│   │   │               ├───services
│   │   │               └───utils
│   │   │                  └───SQLDicionarioViolacoes.java
│   │   └───resources
│   │       └───application.yaml
│   │       └───dicionario.yaml
│   └───test
│       └───java
│           └───com
│               └───scc
│                   └───campanha
└───target
```