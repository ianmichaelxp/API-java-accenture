# Projeto Accademia Accenture - Bankline Java API

API de um Bankline com cadastro de usuário, contas e lançamentos.
A documentação da API pode ser acessada em [https://banklineaccenture.herokuapp.com/swagger-ui.html](https://accountbank-api.herokuapp.com/swagger-ui.html)

## Time Javangers Assembly
- [Ian Michel](https://github.com/ianmichel)
  Responsável pela implementação das rotinas de Usuário e Login, além da configuração do Spring Security e utilização de JWT
- [Nicholas Alves](https://github.com/Nicholas-Alves)
  Responsável pela implementação das rotinas de Dashboard, além a implementação e configuração do Swagger-UI
- [Nailson Ferreira](https://github.com/NailsonFerreira)
  Responsável pela implementação das rotinas de Conta e Planos de Conta
- [Leonardo Januário](https://github.com/leojrs)
  Responsável pela implementação das rotinas de Lançamento

## Estrutura do Projeto
Cada pacote possui um conjuntos de classes que distribui as responsabilidades do projeto:  
- Model: onde definimos os modelos ou seja as classes dos objetos que usamos no sistema, que são entidade mapeada para o Banco de Dados
- Repository: onde definimos os métodos JPA para acessar os dados no Banco de Dados
- Service: onde definimos as regras de negócio para manipulação dos Models
- DTO: onde definimos as classes em que serão consumidas e enviadas pelo frontend
- Controller: onde configuramos os recursos / endpoints a serem consumidos pelo frontend
- Enums: onde definimos nossas enumerações
- Exception: onde definimos as exceções personalizadas a serem tratadas em todo o sistema
- config.config: onde definimos as configurações de segurança do Spring Security e JWT
- config.swagger: onde definimos as configurações do Swagger para documentar a API

##Atributos gerais para as classes de Modelo
Nós utilizamos uma biblioteca chamada Lombok, que nos permite usar anotações para deixar implícita escrita de métodos
como Get, Set e construtores (com e sem parâmetros). Essas anotações foram usadas em todas as classes do pacote model.

## Usuário
O model tem os atributos:
- Id
- Nome
- CPF
- Login
- Senha

O service faz as validações para criação do usuário.
- O nome, cpf, login e senha não podem ser nulos.
- O login não pode passar de 20 caracteres.
- O cpf não pode passar de 11 caracteres.
- Não pode cadastrar um usuário com um login já existente no sistema.
- No cadastro de um novo usuário é criada uma Corrente além de um plano de conta (categoria) "Salário" do tipo "Receita"

O Controller mapeia as rotas da API, que podem ser vistas com mais detalhes na [documentação](https://accountbank-api.herokuapp.com/swagger-ui.html) feita com o Swagger

## Conta
O model tem os atributos:
- Id
- Número (é igual ao login do usuário)
- Tipo ([TipoConta Enum](#tipoconta-enum))
- Saldo
- idMinhaConta (id da conta do usuário logado)

Os atributos Número e Tipo são uma chave única.
A conta é criada com o saldo 0.
Tem os getters e setter dos atributos.

O service faz as buscas necessárias como:
- buscarPorId(Integer id)
- buscaPorNumeroETipoConta(String numero, TipoConta tipoConta)
- buscarPorUsuarioId(Integer usuarioId)

O Controller mapeia as rotas da API, que podem ser vistas com mais detalhes na [documentação](https://accountbank-api.herokuapp.com/swagger-ui.html) feita com o Swagger

## Lançamento
O model tem os atributos:
- Id
- Id da Conta do Usuário
- Número da conta do usuário
- Data
- Valor
- Descrição
- Tipo ([TipoLancamento Enum](#tipolancamento-enum))
- Número da conta de destino (para o caso de transferência)

Tem os getters e setter dos atributos.

O service faz as validações para criação do lançamento.
- O Id da conta do Usuário, número da conta, valor, descricao, tipo e plano de conta (categoria) não podem ser nulos.
- O valor não pode ser negativo.
- Se for transferência a conta de destino não pode ser nula.
- O número da conta do usuário e de destino devem ser de contas já cadastradas no sistema.
- Caso seja um débito ou uma transferência o valor não deve deixar o saldo negativo.
- No caso de um crédtio, o valor passado será adicionado ao saldo da conta.
- No caso de um débito, o valor passado será subtraido do saldo da conta.
- No caso de uma transferência, o valor passado será subtraido do saldo da conta do usuário e adicionado no saldo da conta do destinatário, e será criado dois lançamentos um em cada conta.

O Controller mapeia as rotas da API, que podem ser vistas com mais detalhes na [documentação](https://accountbank-api.herokuapp.com/swagger-ui.html) feita com o Swagger

## Plano Conta (Categoria)
O model tem os atributos:
- Id
- Tipo ([TipoPlanoConta Enum](#tipoplanoconta-enum))
- Descrição
- Id Usuário

Tem os getters e setter dos atributos.

O service faz as validações para criação do plano conta.
- A descricao, o tipo e o Id do usuário não podem ser nulos.

O Controller mapeia as rotas da API, que podem ser vistas com mais detalhes na [documentação](https://accountbank-api.herokuapp.com/swagger-ui.html) feita com o Swagger

## Login
O service faz a validação do login do usuário no sistema.
- O login e a senha não podem ser nulos.
- Busca o usuário pelo login e compara a senha passada com a cadastrada.

O Controller mapeia as rotas da API, que podem ser vistas com mais detalhes na [documentação](https://accountbank-api.herokuapp.com/swagger-ui.html) feita com o Swagger

## Dashboard
O service faz a busca dos dados necessário no dashboard do sistema.
Também faz a busca dos lançamentos para o extrato por data de início e fim.

O Controller mapeia as rotas da API, que podem ser vistas com mais detalhes na [documentação](https://accountbank-api.herokuapp.com/swagger-ui.html) feita com o Swagger

## TipoConta Enum
- CORRENTE

## TipoPlanoConta Enum
- R: Receita
- D: Despesa
- TC: Transferência entre Contas

## TipoLancamento Enum
- DEBITO
- TRANSFERENCIA


# SPRING BOOT
- A API será um Bankline com funcionalidades específicas, hospedada no Swagger
para apresentação do projeto final e posteriores checagens realizadas por terceiros.

## Funcionalidades da API
### Usuário
- Criar
- Logar
- Listar

## Banco
### Transações bancárias
- Pagamento
- Depósito
- Listagem de transações

# Implementações de segurança
A api faz uso de Jason Web Token (JWT) para autenticação de endpoints durante o uso
da mesma além do Spring Security para configurações internas de BackEnd.

# Como funciona?

Ao criar o usuário, o sistema de login fica disponível, habilitando o acesso pelo
endpoint

Após o login o endpoint retornará uma resposta contendo os dados de autenticação,
contendo um token bearer que será filtrado pela implemetação do JWT no Java, o que
permitirá o acesso as demais funcionalidades.

## Utilizando as transações

Para realizar uma transação, a requisição deverá ser feita no endpoint "/lancamentos".

Para se alternar entre os diferentes tipos de transações foi implementado um
sistema de Enum em que fica disponível para o usuário as opções de: Débito, Crédito
e Transferência. Para alternar, o usuário deve mudar o valor do campo "tipo". Dependendo
da opção, a conta destino terá o saldo alterado.
