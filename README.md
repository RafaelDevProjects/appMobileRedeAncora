# Documentação do Projeto RedeAncora

## Visão Geral

O RedeAncora é um aplicativo Android desenvolvido em Java que permite a exibição e compra de peças automotivas. Os usuários podem visualizar detalhes das peças, descrições, preços, imagens e outras informações relevantes. O aplicativo utiliza Firebase como banco de dados para armazenar informações das peças e categorias, garantindo uma experiência dinâmica e atualizada.

## Tecnologias Utilizadas

- **Linguagem:** Java

- **Frameworks:** Android SDK
  
- **Banco de Dados:** Firebase Realtime Database

- **Bibliotecas:** Glide (para carregamento de imagens), RecyclerView (para listagem de itens)

- **Padrões de Projeto:** MVP (Model-View-Presenter)

- **Persistência de Dados:** Serializable (para transferência de objetos entre Activities)

# Estrutura do Projeto

# Descrição das Classes

### **1. PecasDomain.java** (Pacote: br.com.redeAncora.app.Domain)

Classe que representa uma peça automotiva no sistema.

**Atributos:**

- **title**: Nome da peça

- **description**: Descrição detalhada

- **picUrl**: URL da imagem

- **detalhes**: Características específicas

- **marca**: Marca da peça

- **HighestSpeed**: Velocidade máxima suportada (caso aplicável)

- **price**: Preço

- **rating**: Avaliação dos usuários

### **2. CategoryDomain.java** (Pacote: br.com.redeAncora.app.Domain)

Classe que representa uma categoria de peças.

**Atributos:**

- **title**: Nome da categoria

- **id:** Identificador único

- **picUrl:** URL da imagem

### **3. PecasAdapter.java** (Pacote: br.com.redeAncora.app.Adapter)

Adaptador para exibição de peças em um RecyclerView.

**Principais Funções:**

- **onCreateViewHolder():** Cria e infla um novo ViewHolder

- **onBindViewHolder():** Associa os dados à View

- **getItemCount():** Retorna a quantidade de itens na lista

- **Viewholder:** Classe interna para manipulação da interface

### **4. DetailActivity.java** (Pacote: br.com.redeAncora.app.Activity)

Tela de detalhes de uma peça.

**Responsabilidades:**

- Receber o objeto PecasDomain via Intent

- Exibir os detalhes da peça selecionada

## Fluxo de Funcionamento

1. O aplicativo se conecta ao Firebase e carrega a lista de peças e categorias.

2. A lista de peças é carregada na RecyclerView.

3. O usuário pode clicar em uma peça para ver seus detalhes.

4. A DetailActivity é aberta exibindo informações completas.

Autor

Desenvolvido por Rafael Sigoli
