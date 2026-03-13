
##  - Checklist:

- [x] Fazer as migrações corretas do banco, ou seja, os campos que foram adicionados ou removidos precisam estar nas migrations.
- [x] Para dropar a tabela de Category, é necessário excluir a chaves estrangeiras que ela possui em outras tabelas
- [ ] Aplicar autenticação no front-end e fazer tela de login.
- [x] Fazer com que somente seja mostrado dados daquele usuário logado.
- [ ] Aplicar mudanças de usuário logado para os outros services.
- [x] Corrigir consultas "UserCategoryRepository", provavelmente o erro esta no inner join
- [ ] Mudar nomes das colunas que guardam as FKs das tabelas, esta muito confuso
- [x] Na consulta do "getCategoriesWithExpenseByUserId", você pode fazer um método que puxa a contagem e o valor total gasto naquela categoria sem depender do sql.
- [ ] Implementar a Query que vai alimentar o relatório com o JPA Criteria API utilizando Query dinâmicas com Predicate, Specification e SubQuery.
- [ ] Concertar campos que podem ser nulos na tabela do banco de dados para poder usar o @Nullable com certeza nos DTO's.
- [ ] Concertar campos que não podem ser nulos na tabela do banco de dados para poder usar o @NonNull com certeza nos DTO's.
- [ ] Fazer endpoint parecido com o "getCategoriesWithExpenseByUserId" mas que puxa todas as categorias. O filtro vai receber uma lista.
- [ ] Procurar algum cenário com o mesmo comportamento de Super com Agência do BNB, ou seja, para cada Super, o campo de Agência retorna somente as agências pertencentes àquela Super.
- [ ] Retirar rotas redundantes, ex: POST(/create)
- [ ] Calcular porcentagem com base no total das categorias.
- [ ] Criar regra de perfil de adm para determinados endpoints - Com base na role e no usuário logado, o usuário poderá ou não acessar aquele endpoint.
- [ ] Ache um endpoint que você possa usar Request Param nele.
 ----
### Remodelar
- [x] Relação entre Usuário e Categoria do Usuário é 1:N
- [x] Relação entre Categoria do Usuário para Despesa também é 1:N (Dentro da Categoria de Usuário terá uma lista que guardará as despesas, se por acaso a categoria não tiver nome, a despesa será adicionada numa categoria padrão)
- [x] Relação de Despesa para Categoria do Produto é de N:N sem dado extra da relação
- [x] Refazer o banco com a modelagem correta e refazer também as migrations.
- [x] A seed está dando errado, corrigir.

-------------------
## - Observações:

- Uma categoria do usuário tem o id do próprio usuário. Essa categoria por sua vez, é referenciada pela tabela de tb_user_category_expenses que contém o id dessa categoria com o id das despesas. Então, se eu quero puxar as categorias do usuário com as suas despesas de cada categoria, eu devo fazer um inner join entre categoria de usuario e o usuario e categoria de usuario 