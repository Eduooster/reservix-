
O **Reservix** é uma aplicação web robusta voltada para o gerenciamento de reservas de salas e recursos compartilhados. O sistema foi concebido para simplificar a coordenação de espaços comuns, permitindo a consulta ágil de disponibilidade, criação de reservas, visualização centralizada de agendamentos e o gerenciamento completo de salas e usuários.

---



## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com foco arquitetural e educacional, servindo como portfólio técnico avançado. O principal objetivo é aplicar conceitos rigorosos de engenharia de software e desenvolvimento backend, destacando-se:

*   **Arquitetura em Camadas & Clean Architecture:** Divisão clara de responsabilidades isolando o núcleo de negócio de detalhes técnicos e externos.
*   **Separação de Preocupações:** Segregação estrita entre as camadas de *Domínio*, *Aplicação*, *Infraestrutura* e *Apresentação*.
*   **Casos de Uso Orientados a Regras de Negócio:** Centralização da lógica operacional do sistema em componentes dedicados e testáveis.
*   **Inversão de Dependência (DIP):** Garantia de que o núcleo do sistema dependa apenas de abstrações, facilitando a testabilidade e a flexibilidade.
*   **Controle Rigoroso de Conflitos:** Implementação de algoritmos para validação e prevenção de sobreposição de horários em reservas simultâneas.

---

## ✨ Funcionalidades do MVP

O Produto Mínimo Viável (MVP) contempla o fluxo completo de agendamento e governança do sistema:

*   **Cadastro e autenticação de usuários:** Fluxo seguro de registro e login com controle de sessões.
*   **Gerenciamento de salas ou recursos:** Operações de CRUD (Criação, Leitura, Atualização e Exclusão) para a administração de espaços.
*   **Consulta de disponibilidade por data e horário:** Mecanismo de busca para identificar janelas livres em salas específicas.
*   **Criação de reservas:** Agendamento de salas por usuários autenticados para intervalos de tempo determinados.
*   **Visualização de reservas do usuário:** Painel centralizado onde o usuário consulta seu histórico e agendamentos futuros.
*   **Cancelamento de reservas:** Possibilidade de revogar agendamentos ativos respeitando as travas de segurança.
*   **Controle de permissões (RBAC):** Diferenciação explícita de acessos e ações entre usuários comuns e administradores.

---

## 🛡️ Regras de Negócio

Para garantir a integridade dos dados e a consistência das operações, o Reservix segue as seguintes premissas de negócio:

1.  **Sem Sobreposições:** Uma sala não pode ter duas ou mais reservas ativas com horários sobrepostos.
2.  **Consistência Temporal:** O horário de término de uma reserva deve ser obrigatoriamente posterior ao seu horário de início.
3.  **Restrição de Autenticação:** Apenas usuários autenticados e com tokens válidos podem criar reservas.
4.  **Isolamento de Escopo (Usuário Comum):** Usuários comuns podem visualizar e gerenciar (cancelar) estritamente as suas próprias reservas.
5.  **Acesso Total (Administrador):** Administradores possuem privilégios elevados para gerenciar todas as reservas, salas e usuários do ecossistema.
6.  **Bloqueio de Inativos:** Salas marcadas como desativadas no sistema ficam indisponíveis para novas reservas.

---

## 🏗️ Arquitetura do Sistema

A organização do código-fonte é inspirada nos princípios da **Clean Architecture**, sendo estruturada em quatro camadas concêntricas independentes:

```
  ┌─────────────────────────────────────────────────────────┐
  │                      Presentation                       │ (Controllers, Rest API)
  │   ┌─────────────────────────────────────────────────┐   │
  │   │                  Infrastructure                 │   │ (Persistência, DB, Segurança)
  │   │   ┌─────────────────────────────────────────┐   │   │
  │   │   │               Application               │   │   │ (Casos de Uso, DTOs, Contratos)
  │   │   │   ┌─────────────────────────────────┐   │   │   │
  │   │   │   │             Domain              │   │   │   │ (Entidades, Regras Centrais)
  │   │   │   └─────────────────────────────────┘   │   │   │
  │   │   └─────────────────────────────────────────┘   │   │
  │   └─────────────────────────────────────────────────┘   │
  └─────────────────────────────────────────────────────────┘
```

*   **Domain:** O coração da aplicação. Contém as entidades de negócio puras, agregados e as regras de negócio essenciais, totalmente livre de dependências de frameworks ou bibliotecas externas.
*   **Application:** Define os fluxos da aplicação por meio de Casos de Uso (*Use Cases*). Contém os comandos, DTOs (*Data Transfer Objects*) e as interfaces de contratos (portas) que serão implementadas pelas camadas externas.
*   **Infrastructure:** Responsável pelo suporte tecnológico. Inclui a persistência de dados (repositórios), configurações globais, integrações de serviços e componentes de infraestrutura técnica.
*   **Presentation:** Porta de entrada da aplicação. Hospeda os controladores REST responsáveis por receber as requisições HTTP, validar os payloads de entrada e traduzir as respostas da API para o cliente.

---

## 🔗 Principais Endpoints da API

### Autenticação (`/auth`)
*   `POST /auth/login` - Autentica um usuário e retorna o token de acesso (JWT).
*   `POST /auth/register` - Realiza o cadastro de um novo usuário comum.
*   `GET /auth/me` - Retorna o perfil e informações do usuário atualmente logado.

### Salas (`/rooms`)
*   `GET /rooms` - Lista todas as salas ativas disponíveis.
*   `GET /rooms/{id}` - Retorna os detalhes específicos de uma sala por ID.
*   `POST /rooms` - Cria uma nova sala *(Apenas Admin)*.
*   `PUT /rooms/{id}` - Atualiza os dados de uma sala existente *(Apenas Admin)*.
*   `DELETE /rooms/{id}` - Remove ou desativa uma sala do sistema *(Apenas Admin)*.
*   `GET /rooms/{id}/availability` - Consulta os horários disponíveis de uma sala em um período.

### Reservas (`/reservations`)
*   `GET /reservations` - Lista as reservas cadastradas (filtradas por contexto de usuário ou gerais para Admin).
*   `GET /reservations/{id}` - Detalha uma reserva específica.
*   `POST /reservations` - Cria uma nova reserva de sala baseado no payload informado.
*   `DELETE /reservations/{id}` - Cancela uma reserva existente.

### Usuários (`/users`)
*   `GET /users/{id}/reservations` - Lista o histórico completo de reservas associado a um usuário específico.

---

## 📊 Modelo de Domínio

O núcleo do sistema é modelado com base em três entidades principais enriquecidas:

### 👤 User (Usuário)
*   `id` (UUID / Long): Identificador único do usuário.
*   `name` (String): Nome completo.
*   `email` (String): Endereço de e-mail institucional ou pessoal (usado para login).
*   `password` (String): Hash seguro da senha do usuário.
*   `role` (Enum): Nível de acesso no sistema (`COMMON`, `ADMIN`).
*   `active` (Boolean): Flag indicativa se o usuário está ativo.
*   `createdAt` / `updatedAt` (Timestamp): Registros de auditoria temporal.

### 🚪 Room (Sala/Recurso)
*   `id` (UUID / Long): Identificador único do recurso.
*   `name` (String): Nome identificador da sala (ex: "Sala de Reuniões A").
*   `description` (String): Detalhes complementares e recursos disponíveis (ex: "Projetor, Ar Condicionado").
*   `capacity` (Integer): Capacidade máxima de ocupantes permitida.
*   `active` (Boolean): Status de ativação do espaço para locação.
*   `createdAt` / `updatedAt` (Timestamp): Registros de auditoria temporal.

### 📅 Reservation (Reserva)
*   `id` (UUID / Long): Identificador único do agendamento.
*   `room` (Room): Referência direta à entidade da Sala reservada.
*   `user` (User): Referência direta à entidade do Usuário proprietário da reserva.
*   `startDateTime` (LocalDateTime): Data e hora exatas de início da reserva.
*   `endDateTime` (LocalDateTime): Data e hora exatas do término da reserva.
*   `status` (Enum): Estado atual da reserva (`CONFIRMED`, `CANCELED`).
*   `createdAt` / `updatedAt` (Timestamp): Registros de auditoria temporal.

---

## 🔄 Fluxo de Criação de Reserva

O diagrama abaixo exemplifica a sequência lógica executada pelo sistema ao registrar um novo agendamento:

```
[ Usuário ] ───(1. Autentica no Sistema)───> [ JWT Gerado ]
    │
    ├───(2. Consulta Disponibilidade)──────> [ Filtra Salas & Horários Vagos ]
    │
    ├───(3. Informa Data e Horário)────────> [ Envia Requisição POST ]
                                                     │
                                            [ Validação do Sistema ]
                                            ├── Verifica Token JWT Ativo
                                            ├── Valida Consistência de Horários
                                            └── Executa Algoritmo de Conflitos
                                                     │
[ Reserva Registrada ] <───(5. Vincula ao Usuário)───┴─── (4. Se Válida e Sem Conflito)
    │
    └───(6. Usuário visualiza ou cancela o agendamento a qualquer momento)
```

---

## 🛠️ Tecnologias Utilizadas

A stack tecnológica foi selecionada de forma a fornecer robustez empresarial, segurança e facilidade de teste:

*   **Linguagem Principal:** Java (versão 21 ou superior)
*   **Framework Core:** Spring Boot (Gerenciamento de inversão de controle e ecossistema backend)
*   **Segurança & Autenticação:** Spring Security & JWT (Json Web Token)
*   **Banco de Dados Relacional:** PostgreSQL e H2
*   **Gerenciador de Dependências:** Maven
*   **Testes Automatizados:** JUnit 5 (Testes unitários e de integração) & Mockito (Simulação de dependências e isolamento)

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
Certifique-se de possuir instalado em sua máquina local:
*   **Java 17** Development Kit (JDK) ou superior.
*   **Apache Maven** instalado e configurado nas variáveis de ambiente.
*   Instância do **PostgreSQL** ativa e rodando.

### Passos para Execução

1.  **Clonar o repositório:**
    ```bash
    git clone https://github.com/Eduooster/reservix.git
    ```

2.  **Acessar o diretório do projeto:**
    ```bash
    cd reservix
    ```

3.  **Configurar as variáveis de ambiente:**
    Ajuste as credenciais de acesso ao banco de dados no arquivo `src/main/resources/application.properties` (ou configure via variáveis de ambiente do sistema):
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/reservix_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    jwt.secret=sua_chave_secreta_super_segura
    ```

4.  **Compilar e Executar a aplicação:**
    Utilize o wrapper do Maven ou o comando padrão:
    ```bash
    mvn spring-boot:run
    ```

5.  **Acessar a API:**
    Assim que a inicialização do Spring Boot for concluída com sucesso, a API REST estará disponível para consumo local em:
    👉 **`http://localhost:8080`**

---

## 📈 Próximos Passos (Backlog)

Como parte da evolução contínua da aplicação, as seguintes features mapeadas serão implementadas futuramente:

*   🔄 **Reservas Recorrentes:** Possibilidade de replicar um agendamento automaticamente de forma diária, semanal ou mensal.
*   ⏳ **Fila de Espera:** Mecanismo para permitir que usuários entrem em uma fila caso uma sala desejada já esteja ocupada no horário, sendo notificados em caso de desistência.
*   📧 **Notificações por E-mail:** Envio automatizado de confirmações, alertas de proximidade de horários e avisos de cancelamento.
*   📊 **Relatórios de Ocupação:** Painel administrativo com métricas e insights sobre quais salas possuem maior taxa de utilização e horários de pico.
*   🗓️ **Integração com Calendários Externos:** Sincronização bidirecional de agendamentos com ferramentas populares como Google Calendar e Microsoft Outlook.
*   📱 **Aplicativo Mobile:** Desenvolvimento de um aplicativo móvel (front-end independente) que consumirá esta mesma API REST para permitir reservas rápidas via smartphones.

---

## 📄 Notas Finais e Licença

Este projeto foi integralmente desenvolvido para fins estritamente didáticos, de estudo e composição de portfólio de engenharia de software backend.

A licença padrão do projeto pode ser livremente editada, adaptada ou customizada de acordo com as diretrizes e necessidades de governança do repositório final.
README.md
Displaying README.md.
