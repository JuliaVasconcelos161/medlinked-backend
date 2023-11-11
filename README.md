<h1>Medlinked</h1> 

<h3>Descrição do Projeto:</h3>

<p style="text-align:justify;"> 
    Esse projeto foi desenvolvido como backend do Projeto Integrador do 4° Período de Sistemas de Infomação,
    cujo objetivo é fornecer ao usuário uma aplicação que possibilite o controle de agenda de um ou vários médicos
    para os quais uma secretária trabalha.
</p>
<p style="text-align:justify;"> 
    A secretária é o usuário do sistema e poderá cadastrar médicos, pacientes, convênios e agendamentos, que seriam as consultas.
    Também é possível que a secretária se desvincule dos médicos que ela atende e se vincule a outros, caso seja necessário, para que com o vinculo
    seja possível verificar a agenda do médico vinculado, tendo em vista que os agendamentos exibidos na agenda da secretária são apenas aqueles 
    pertencentes a médicos vinculados a ela.
</p>
<p style="text-align:justify;"> 
    Pode-se gerar uma agenda para vários dias, em formato
    de agendamento automático, para que a secretária preencha posteriormente as informações adicionais, mas
    caso haja algum imprevisto ou um agendamento tenha ocorrido de forma mais rápida que o esperado
    também é possível editar o agendamento, alterando suas informações e também incluir um agendamento único.
</p>

<h3>Tecnologias utilizadas:</h3>

<ul>
    <li>Java</li>
    <li>Spring Boot</li>
    <li>PostgreSQL</li>
</ul>

<h3>Modelo de banco de dados do projeto:</h3>

<h3>Funcionalidades implementadas:</h3>

<ul>
    <li><h4>Criar secretária:</h4></li>
        <p> Método http: POST </p>
        <p>
            Rota: /secretaria/create
        </p>
        <p>
            RequestBody: SecretariaUsuarioDto
        </p>
        <p style="text-align:justify;">
            Ao criar uma secretária será verificado o cpf informado, caso já esteja cadastrado no sistema
            como outro tipo de pessoa, será criado um registro apenas na tabela tb_secretaria, caso contrário
            também será criado um registro na tb_pessoa, criando a pessoa no sistema. Também é verificado se existe
            uma secretária com aquele cpf, ou email, caso exista é estourada uma exceção, caso seja necessário criar
            uma pessoa no sistema também será verificado se já existe alguma outra pessoa com cpf ou
            email informado, caso exista estoura exceção. Como a secretária é o usuário do sistema, ao criá-la também
            é criado um registro em tb_usuario, contendo username e senha informados em SecretariaUsuarioDto. A senha
            é criptografada para ser salva no banco de dados utilizando BCrypt. No fim é retornado um token, que informa
            o idUsuario, seu nome e seu username.
        </p>
    <p> Retorno: UsuarioResponseDto ou mensagem retornada por MedLinkedException </p>

<li><h4>Editar secretária:</h4></li>
    <p> Método http: PUT </p>
    <p>
        Rota: /secretaria/update/{idSecretaria}
    </p>
    <p>
        RequestBody: SecretariaDto
    </p>
    <p style="text-align:justify;">
        Ao editar uma secretária, como ela não possui atributos além daqueles que se relacionam a ela
        a partir de Pessoa, será chamado um método que atualiza as informações de Pessoa no qual
        são verificados email, e cpf, caso já exista uma outra pessoa com email e cpf informados
        será estourada uma exceção. Caso contrário, serão salvas as novas caracteristicas de pessoa
        e secretária.
    </p>
    <p> Retorno: Secretaria ou mensagem retornada por MedLinkedException</p>

<li><h4>Retornar secretária:</h4></li>
    <p> Método http: GET </p>
    <p>
        Rota: /secretaria/{idSecretaria}
    </p>
    <p style="text-align:justify;">
        Nessa rota serão retornadas as informações da Secretária cujo id foi informado,
        caso não seja encontrado será estourada uma exceção.
    </p>
    <p> Retorno: Secretaria ou mensagem retornada por MedLinkedException</p>

<li><h4>Deletar secretária:</h4></li>
    <p> Método http: DELETE </p>
    <p>
        Rota: secretaria/delete/{idSecretaria}
    </p>
    <p style="text-align:justify;">
        Ao deletar uma secretária, será verificado se ela possui algum vinculo com médico,
        caso ela possua, será estourada uma exceção que informará a existência desse vínculo.
        Caso contrário, será deletado o registro em tb_secretaria, e também em tb_usuario, 
        será verificada a existência de outro tipo de cadastro de Pessoa com esses dados,
        caso não exista, também será deletado o registro em tb_pessoa.
    </p>
    <p> Retorno: Não possui retorno ou retorna mensagem de MedLinkedException</p>


</ul>