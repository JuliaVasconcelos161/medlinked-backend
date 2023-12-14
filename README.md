<html lang="pt-br">
    <body>
        <h1>Medlinked</h1>
        <h3>Índice:</h3>
        <ul>
            <li><p><a style="text-decoration:none; color:white;" href="#descricao_projeto">Descrição Projeto</a></p></li>
            <li><p><a style="text-decoration:none; color:white;" href="#tecnologias">Tecnologias Utilizadas</a></p></li>
            <li><p><a style="text-decoration:none; color:white;" href="#modelo">Modelo de Banco de Dados do Projeto</a></p></li>
            <li>
                <p><a style="text-decoration:none; color:white;" href="#funcionalidades">Funcionalidades implementadas</a></p>
                <ul>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#secretaria">SecretariaController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#create-secretaria">Criar Secretária</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#update-secretaria">Editar Secretária</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-secretaria">Retornar Secretária</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#delete-secretaria">Deletar Secretária</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#usuario">UsuarioController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#autenticar-usuario">Autenticar Usuário</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#alterar-senha">Alterar Senha Usuário</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#planosaude">PlanoSaudeController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#create-plano">Criar Plano de Saúde</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-planos">Retornar Planos de Saúde sem Paginação</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-planos-paginado">Retornar Planos de Saúde com Paginação</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#delete-plano">Deletar Plano de Saúde</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#paciente">PacienteController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#create-paciente">Criar Paciente</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-paciente">Retornar Paciente</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#update-paciente">Alterar Paciente</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-pacientes">Retornar Pacientes sem Paginação</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-pacientes-paginado">Retornar Pacientes com Paginação</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#delete-paciente">Deletar Paciente</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#medico">MedicoController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#create-medico">Criar Médico</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-medicos">Retornar Médicos</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-medico">Retornar Médico</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#update-medico">Alterar Médico</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#delete-medico">Deletar Médico</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#pessoa">PessoaController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#pessoa-cpf">Procurar Pessoa por CPF</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#agendamento-automatico">AgendamentoAutomaticoController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#create-agenda">Criar Agenda(Agendamentos Automáticos)</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#agendamento">AgendamentoController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#create-agendamento">Criar Agendamento Único</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#update-agendamento">Alterar Agendamento</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-agendamento">Retornar Agendamento</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-agendamentos-paginado">Retornar Agendamentos de Médicos de uma Secretária com Paginação</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-agendamentos">Retornar Agendamentos de Médicos de uma Secretária sem Paginação</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#delete-agendamento">Deletar Agendamento</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#planosaude-paciente">PlanoSaudePacienteController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#associate-plano-paciente">Associar Plano de Saúde a Paciente</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#disassociate-plano-paciente">Desassociar Plano de Saúde de Paciente</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-planos-paciente">Retornar Planos de Saúde de Paciente</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-planos-paciente-medico">Retornar Planos de Saúde de Paciente em comum com Médico</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#planosaude-medico">PlanoSaudeMedicoController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#associate-plano-medico">Associar Planos de Saúde a Médico</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#disassociate-plano-medico">Desassociar Planos de Saúde de Médico</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-planos-medico-paginado">Retornar Planos de Saúde Médico com Paginação</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#secretaria-medico">SecretariaMedicoController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#associate-secretaria-medico">Associar Secretária ao Médico</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#disassociate-secretaria-medico">Desassociar Secretária de Médico</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-secretaria-medicos-paginado">Retornar Médicos de uma Secretária com Paginação</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-secretaria-medicos">Retornar Médicos de uma Secretária sem Paginação</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#passwordresettoken">PasswordResetTokenController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#email-senha">Enviar email para redefinir senha</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#verifica-token">Verificar token para redefinir senha</a></p></li>
                            <li><p><a style="text-decoration:none; color:white;" href="#salva-senha-token">Salvar nova senha de usuário utilizando token</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#especialidade">EspecialidadeController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-especialidades">Retornar todas as Especialidades</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#estado">EstadoController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-estados">Retornar todos os Estados</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#tipo-agendamento">TipoAgendamentoController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-tipos-agendamento">Retornar todos os Tipos de Agendamento</a></p></li>
                        </ul>
                    </li>
                    <li>
                        <p><a style="text-decoration:none; color:white;" href="#tipo-plano-saude">TipoPlanoSaudeController</a></p>
                        <ul>
                            <li><p><a style="text-decoration:none; color:white;" href="#get-tipos-plano-saude">Retornar todos os Tipos de Plano de Saúde</a></p></li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
        <h3 id="descricao_projeto">Descrição do Projeto:</h3>
        <p style="text-align:justify;"> 
            Esse projeto foi desenvolvido como backend do Projeto Integrador do 4° Período de Sistemas de Informação,
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
        <p id="front-projeto">Front-end do Projeto: <a style="text-decoration:none; color:white;" href="https://github.com/LucasCCA/MedLinked-Frontend">Link para o repositório com o front-end desse projeto.</a></p>
        <h3 id="tecnologias">Tecnologias utilizadas:</h3>
        <ul>
            <li>Java</li>
            <li>Spring Boot</li>
            <li>PostgreSQL</li>
        </ul>
        <h3 id="modelo">Modelo de Banco de Dados do Projeto:</h3>
        <img src="modelo-medlinked.png" alt="Imagem Modelo Banco de Dados">
        <h3 id="funcionalidades">Funcionalidades implementadas:</h3>
        <ul>
            <li>
                <h3 id="secretaria">SecretariaController:</h3>
                <ul>
                    <li><h4 id="create-secretaria">Criar Secretária:</h4>
                    <p> Método HTTP: POST </p>
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
                    <p> Retorno: UsuarioResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="update-secretaria">Editar Secretária:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /secretaria/update/{idSecretaria}
                        </p>
                        <p>
                            RequestBody: SecretariaDto
                        </p>
                        <p style="text-align:justify;">
                            Ao editar uma secretária, será feita um busca utilizando idSecretaria informado, caso não exista
                            nenhuma secretária com aquele id será lançada uma exceção. Caso contrário, como ela não possui atributos
                            além daqueles que se relacionam a ela a partir de Pessoa, será chamado um método que atualiza as informações
                            de Pessoa no qual são verificados email, e cpf, caso já exista uma outra pessoa com email e cpf informados
                            será estourada uma exceção. Caso contrário, serão salvas as alterações de pessoa
                            e secretária.
                        </p>
                        <p> Retorno: Secretaria ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-secretaria">Retornar Secretária:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /secretaria/{idSecretaria}
                        </p>
                        <p style="text-align:justify;">
                            Nessa rota serão retornadas as informações da Secretária cujo id foi informado,
                            caso não seja encontrado será estourada uma exceção.
                        </p>
                        <p> Retorno: Secretaria ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="delete-secretaria">Deletar Secretária:</h4>
                        <p> Método HTTP: DELETE </p>
                        <p>
                            Rota: /secretaria/delete/{idSecretaria}
                        </p>
                        <p style="text-align:justify;">
                            Ao deletar uma secretária, será feita um busca utilizando idSecretaria informado, caso não exista
                            nenhuma secretária com aquele id será lançada uma exceção. Caso contrário, será verificado se ela possui
                            algum vinculo com médico, se ela possuir, será estourada uma exceção que informará a existência desse vínculo.
                            Caso contrário, será deletado o registro em tb_secretaria, e também em tb_usuario, 
                            será verificada a existência de outro tipo de cadastro de Pessoa com esses dados,
                            caso não exista, também será deletado o registro em tb_pessoa.
                        </p>
                        <p> Retorno: Mensagem de sucesso ou mensagem de MedLinkedException</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="usuario">UsuarioController:</h3>
                <ul>
                    <li><h4 id="autenticar-usuario">Autenticar Usuário:</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /usuario/authenticate
                        </p>
                        <p>
                            RequestBody: UsuarioRegisterDto
                        </p>
                        <p style="text-align:justify;">
                            Autentica um usuário, e retorna UsuarioResponseDto que carrega um JWT, o qual é construido
                            adicionando claims, nome e idUsuário, que serão obtidas através de Pessoa.
                        </p>
                        <p> Retorno: UsuarioResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="alterar-senha">Alterar Senha Usuário:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /usuario/update-senha/{idUsuario}
                        </p>
                        <p>
                            RequestBody: UpdateSenhaUsuarioDto
                        </p>
                        <p style="text-align:justify;">
                            Busca usuário a partir de idUsuario fornecido, caso não exista será estourada uma exceção,
                            verifica se a antiga senha passada no dto é compatível com a senha atual, caso não seja é lançada exceção.
                            Altera a senha do usuário criptografando-a, e retorna o Usuario como resposta da requisição.
                        </p>
                        <p> Retorno: Usuario ou mensagem de MedLinkedException</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="planosaude">PlanoSaudeController:</h3>
                <ul>
                    <li><h4 id="create-plano">Criar Plano de Saúde:</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /plano-saude/create
                        </p>
                        <p>
                            RequestBody: PlanoSaudeDto
                        </p>
                        <p style="text-align:justify;">
                            Verifica se já existe um plano de saúde com a descrição enviada no dto, caso exista lança exceção. Caso não 
                            exista o plano de saúde é salvo e retornado na requisição.
                        </p>
                        <p> Retorno: PlanoSaude ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-planos">Retornar Planos de Saúde sem Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /plano-saude
                        </p>
                        <p style="text-align:justify;">
                            Busca registros de planos de saúde e os retorna em uma lista de PlanoSaude.
                        </p>
                        <p> Retorno: List de PlanoSaude</p>
                    </li>
                    <li><h4 id="get-planos-paginado">Retornar Planos de Saúde com Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /plano-saude/paginado
                        </p>
                        <p>RequestParams:</p>
                        <ul>
                            <li>page</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "0"</li>
                            </ul>
                            <li>pageSize</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "10"</li>
                            </ul>
                        </ul>
                        <p style="text-align:justify;">
                            Busca registros de planos de saúde e os retorna em uma lista de PlanoSaude paginada.
                        </p>
                        <p> Retorno: Page de PlanoSaude</p>
                    </li>
                    <li><h4 id="delete-plano">Deletar Plano de Saúde:</h4>
                        <p> Método HTTP: DELETE </p>
                        <p>
                            Rota: /plano-saude/delete/{idPlanoSaude}
                        </p>
                        <p style="text-align:justify;">
                            Busca PlanoSaude utilizando idPlanoSaude informado, caso não seja encontrado estoura exceção, 
                            disassocia o plano de saúde a ser deletado de todos os pacientes, médicos e agendamentos
                            os quais ele possuía vínculo e deleta o plano de saúde.
                        </p>
                        <p> Retorno: Mensagem de sucesso ou mensagem de MedLinkedException</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="paciente">PacienteController:</h3>
                <ul>
                     <li><h4 id="create-paciente">Criar Paciente:</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /paciente/create
                        </p>
                        <p>
                            RequestBody: PacienteDto
                        </p>
                        <p style="text-align:justify;">
                            Ao criar um paciente será verificado o cpf informado, caso já esteja cadastrado no sistema
                            como outro tipo de pessoa, será criado um registro apenas na tabela tb_paciente, caso contrário
                            também será criado um registro na tb_pessoa, criando a pessoa no sistema. Também é verificado se existe
                            um paciente com aquele cpf, ou email, caso exista é estourada uma exceção, caso seja necessário criar
                            uma pessoa no sistema também será verificado se já existe alguma outra pessoa com cpf ou
                            email informado, caso exista estoura exceção. O paciente criado é salvo, e um Endereco para o paciente com os
                            dados informados no dto é salvo.  
                        </p>
                        <p> Retorno: PacienteResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-paciente">Retornar Paciente:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /paciente/{idPaciente}
                        </p>
                        <p style="text-align:justify;">
                            Busca o Paciente e o seu Endereco utilizando idPaciente informado, caso não encontre, será lançada exceção.
                            Retorna PacienteResponseDto que contém informações do paciente e de seu endereço.
                        </p>
                        <p> Retorno: PacienteResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="update-paciente">Alterar Paciente:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /paciente/update/{idPaciente}
                        </p>
                        <p>RequestBody: PacienteDto</p>
                        <p style="text-align:justify;">
                            Busca o Paciente utilizando idPaciente informado, caso não seja encontrado, lança exceção. Como o Paciente
                            em si não possui outras informações além das de Pessoa, é chamado um método que altera as informações de Pessoa 
                            referentes aquele paciente, essas alterações são salvas. Busca Endereco utilizando idPaciente informado,
                            caso não seja encontrado é lançada exceção. Chama método que altera as informações de endereço, salva essas
                            alterações e retorna PacienteResponseDto com dados do paciente e de seu endereço.
                        </p>
                        <p> Retorno: PacienteResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-pacientes">Retornar Pacientes sem Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /paciente
                        </p>
                        <p style="text-align:justify;">
                            Busca registros de pacientes e os retorna em uma lista de Paciente.
                        </p>
                        <p> Retorno: List de Paciente</p>
                    </li>
                    <li><h4 id="get-pacientes-paginado">Retornar Pacientes com Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /paciente/paginado
                        </p>
                        <p>RequestParams:</p>
                        <ul>
                            <li>page</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "0"</li>
                            </ul>
                            <li>pageSize</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "10"</li>
                            </ul>
                            <li>nomePaciente</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>cpf</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                        </ul>
                        <p style="text-align:justify;">
                            Busca registros de pacientes, podendo filtrar por cpf, e/ou nome do paciente,
                            e os retorna em uma lista de Paciente paginada.
                        </p>
                        <p> Retorno: Page de Paciente</p>
                    </li>
                    <li><h4 id="delete-paciente">Deletar Paciente:</h4>
                        <p> Método HTTP: DELETE </p>
                        <p>
                            Rota: /paciente/delete/{idPaciente}
                        </p>
                        <p style="text-align:justify;">
                            Busca Paciente utilizando idPaciente informado, caso não seja encontrado, lança exceção. Deleta agendamentos
                            daquele paciente, o disassocia de todos os planos de saúde vinculados a ele, deleta o seu endereco, deleta o
                            paciente, e verifica se existe algum outro tipo de pessoa no sistema com dados de Pessoa daquele paciente,
                            caso não exista o registro em tb_pessoa também é deletado.
                        </p>
                        <p> Retorno: Mensagem de sucesso ou mensagem de MedLinkedException</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="medico">MedicoController:</h3>
                <ul>
                     <li><h4 id="create-medico">Criar Médico:</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /medico/create/{idSecretaria}
                        </p>
                        <p>
                            RequestBody: MedicoDto
                        </p>
                        <p style="text-align:justify;">
                            Ao criar um médico será verificado o cpf informado, caso já esteja cadastrado no sistema
                            como outro tipo de pessoa, será criado um registro apenas na tabela tb_medico, caso contrário
                            também será criado um registro na tb_pessoa, criando a pessoa no sistema. Também é verificado se existe
                            um médico com aquele cpf, ou email, ou crm, caso exista é estourada uma exceção. Se no dto
                            tiverem sido enviadas mais de duas especialidades para serem salvas, também será estourada uma exceção.
                            Caso seja necessário criar uma pessoa no sistema será verificado se já existe alguma outra pessoa com cpf ou
                            email informado, caso exista estoura exceção. O médico criado é salvo, é criado um vínculo
                            com a secretária cujo idSecretaria foi passado na url, um registro em tb_crm com
                            dados referentes ao crm e especialidades a serem vinculadas àquele médico. Por meio de
                            constructor expression cria-se um MedicoCrmResponseDto, que é retornado na requisição.
                        </p>
                        <p> Retorno: MedicoCrmResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-medicos">Retornar Médicos:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /medico
                        </p>
                        <p style="text-align:justify;">
                            Busca registros de médicos e os retorna em uma lista de Medico.
                        </p>
                        <p> Retorno: List de Medico</p>
                    </li>
                    <li><h4 id="get-medico">Retornar Médico:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /medico/{idMedico}
                        </p>
                        <p style="text-align:justify;">
                            Utilizando constructor expression é criado um novo MedicoCrmResponseDto, que é retornado na 
                            requisição.
                        </p>
                        <p> Retorno: MedicoCrmResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="update-medico">Alterar Médico:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /medico/update/{idMedico}
                        </p>
                        <p>RequestBody: MedicoDto</p>
                        <p style="text-align:justify;">
                            Valida o CRM do médico para não permitir duplicidade, e também se existem mais de duas especialidades
                            a serem associadas. Obtém o objeto Medico correspondente ao idMedico fornecido, caso não seja
                            possível é lançada uma exceção. Atualiza as informações da Pessoa associada ao médico 
                            utilizando verificações de cpf, e email para impedir duplicidade. Atualiza o registro do 
                            médico e posteriormente as informações relacionadas a seu crm armazenadas em tb_crm.
                            Retorna MedicoCrmResponseDto construído por meio de constructor expression.
                        </p>
                        <p> Retorno: MedicoCrmResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="delete-medico">Deletar Médico:</h4>
                        <p> Método HTTP: DELETE </p>
                        <p>
                            Rota: /medico/delete/{idMedico}
                        </p>
                        <p style="text-align:justify;">
                            Busca Medico utilizando idMedico informado, caso não seja encontrado, lança exceção. 
                            Remove vínculos com todos os planos de saúde, e secretárias. Deleta agendamentos e os dados
                            relacionados ao CRM daquele médico, incluindo a informação de quais especialidades ele
                            atendia. Em seguida, o registro em tb_medico é deletado e caso não exista mais nenhum 
                            outro tipo de pessoa com as informações de Pessoa desse médico, o registro em tb_pessoa
                            também é excluido.
                        </p>
                        <p> Retorno: Mensagem de sucesso ou mensagem de MedLinkedException</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="pessoa">PessoaController:</h3> 
                <ul>
                    <li><h4 id="pessoa-cpf">Procurar Pessoa por CPF:</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /pessoa/cpf
                        </p>
                        <p>RequestBody: PessoaCpfDto</p>
                        <p style="text-align:justify;">
                            Busca Pessoa utilizando CPF informado, caso não seja encontrada retorna null.
                        </p>
                        <p> Retorno: Pessoa ou null.</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="agendamento-automatico">AgendamentoAutomaticoController:</h3> 
                <ul>
                    <li><h4 id="create-agenda">Criar Agenda(Agendamentos Automáticos):</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /agendamento-automatico/create
                        </p>
                        <p>RequestBody: AgendamentoAutomaticoDto</p>
                        <p style="text-align:justify;">
                            Inicialmente, é buscado o médico utilizando idMedico passado no dto. A dataInicio, dataFim
                            são parseadas para LocalDate, e horaInicio e horaFim da geração são parseados para LocalTime.
                            Cria-se uma variável diaHorarioAgendamento do tipo LocalDateTime que é inicializada utilizando
                            o horario e o dia de início da geração dos agendamentos. Verifica-se o tempo inicio para 
                            que seja antes do fim.
                        </p>
                        <p style="text-align:justify;">
                            Um laço de repetição inicia e se repete até que o diaHorarioAgendamento
                            seja maior ou igual que o dia e horario final de geração, dentro dele outro laço de repetição
                            atua enquanto o diaHorarioAgendamento for menor que um LocalDateTime composto pelo LocalDate do
                            diaHorarioAgendamento e o horario fim da geração menos tempo de intervalo menos um. Isso
                            ocorre para não serem gerados agendamentos os quais o horário de término ultrapasse
                            o horarioFim definido no dto.
                        </p>
                        <p style="text-align:justify;">
                            O laço interno verifica se existe conflito de horário
                            entre algum agendamento existente e o que será gerado, caso exista será lançada uma exceção
                            e no seu catch esse agendamento não gerado será adicionado a uma lista que posteriormente será
                            retornada para indicar quais agendamentos não foram gerados devido ao conflito de horários.
                            Ainda dentro do catch o diaHorarioAgendamento é incrementado com o tempoIntervalo e utiliza-se
                            o continue para que o resto do código do laço não seja executado e seja passada para a próxima
                            iteração.
                        </p>
                        <p style="text-align:justify;">
                            Caso não exista conflito de horários, o agendamento será construído e salvo. A variável 
                            diaHorarioAgendamento será incrementada com o tempoIntervalo e será passada para a próxima iteração.
                        </p>
                        <p style="text-align:justify;">
                            Ao final do laço de repetição interno, diaHorarioAgendamento é incrementado com dias, caso
                            a geração seja feita para apenas de segunda a sexta e diaHorarioAgendamento seja sexta-feira
                            são adicionados 3 dias, caso contrário, é adicionado um dia, e é passada para a próxima iteração
                            do laço de repetição externo. Após todas as iterações, é retornada uma lista de AgendamentoAutomaticoFalhoDto
                            que demonstra as informações de agendamentos que não foram criados por conflito de horários.
                        </p>
                        <p> Retorno: List de AgendamentoAutomaticoFalhoDto ou mensagem de MedLinkedException</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="agendamento">AgendamentoController</h3>
                <ul>
                    <li><h4 id="create-agendamento">Criar Agendamento Único:</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /agendamento/create
                        </p>
                        <p>RequestBody: AgendamentoDto</p>
                        <p style="text-align:justify;">
                            Valida horário de agendamento para confirmar se o horario passado no dto para aquele médico está
                            disponível, caso não esteja é lançada exceção. Busca Medico, Paciente, e PlanoSaude, utilizando
                            ids correspondentes passados no dto, caso não sejam encontrados, é lançada exceção. O Agendamento
                            é construído, com os dados fornecidos, e salvo. Após o salvamento, caso o paciente seja diferente de null,
                            é chamado um serviço de email para que seja enviado um email de confirmação com informações do agendamento para o paciente.
                        </p>
                        <p> Retorno: Agendamento ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="update-agendamento">Alterar Agendamento:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /agendamento/update/{idAgendamento}
                        </p>
                        <p>RequestBody: AgendamentoDto</p>
                        <p style="text-align:justify;">
                            Valida horário de agendamento para confirmar se o horario passado no dto para aquele médico está
                            disponível, caso não esteja é lançada exceção. O agendamento é buscado pelo idAgendamento informado,
                            se não encontrado é lançada exceção. Dados vindos do dto são vinculados ao agendamento por meio 
                            de setters. Verifica-se idMedico, idPaciente e idPlanoSaude passados no
                            dto, se diferentes daqueles já vinculados ao agendamento existente, são feitas buscas de Medico, Paciente, e PlanoSaude,
                            e os objetos encontrados são vinculados ao agendamento por meio de setters. 
                            As alterações são salvas e caso o paciente do agendamento não seja null, é chamado um serviço de email 
                            para que seja enviado um email de confirmação com informações do agendamento para o paciente.
                        </p>
                        <p> Retorno: Agendamento ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-agendamento">Retornar Agendamento:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /agendamento/detalhes/{idAgendamento}
                        </p>
                        <p style="text-align:justify;">
                            Busca Agendamento utilizando idAgendamento informado, caso não seja encontrado, lança exceção.
                        </p>
                        <p> Retorno: Agendamento ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-agendamentos-paginado">Retornar Agendamentos de Médicos de uma Secretária com Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /agendamento/paginado/{idSecretaria} 
                        </p>
                        <p>RequestParams:</p>
                        <ul>
                            <li>idMedico</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>idPaciente</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>mes</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>ano</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>dia</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>tipoAgendamento</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>page</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "0"</li>
                            </ul>
                            <li>pageSize</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "10"</li>
                            </ul>
                        </ul>
                        <p style="text-align:justify;">
                            Busca registros de agendamentos de médicos da secretária utilizando idSecretaria informado,
                            podendo ser filtrados por idMedico, idPaciente, mes, ano, dia, e tipoAgendamento,
                            e os retorna numa lista de Agendamento paginada.
                        </p>
                        <p>Retorno: Page de Agendamento</p>
                    </li>
                    <li><h4 id="get-agendamentos">Retornar Agendamentos de Médicos de uma Secretária sem Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /agendamento/{idSecretaria}
                        </p>
                        <p>RequestParams:</p>
                        <ul>
                            <li>idMedico</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>idPaciente</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>mes</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>ano</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>dia</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                            <li>tipoAgendamento</li>
                            <ul>
                                <li>required = false</li>
                            </ul>
                        </ul>
                        <p style="text-align:justify;">
                            Busca registros de agendamentos de médicos da secretária utilizando idSecretaria informado,
                            podendo ser filtrados por idMedico, idPaciente, mes, ano, dia, e tipoAgendamento,
                            e os retorna numa lista de Agendamento.
                        </p>
                        <p>Retorno: List de Agendamento</p>
                    </li>
                    <li><h4 id="delete-agendamento">Deletar Agendamento:</h4>
                       <p> Método HTTP: DELETE </p>
                        <p>
                            Rota: /agendamento/delete/{idAgendamento}
                        </p>
                        <p style="text-align:justify;">
                            Busca Agendamento utilizando idAgendamento informado, caso não encontre é lançada exceção.
                            O agendamento encontrado é deletado, retorna-se uma mensagem de sucesso.
                        </p>
                        <p>Retorno: Mensagem de sucesso ou mensagem de MedLinkedException</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="planosaude-paciente">PlanoSaudePacienteController</h3>
                <ul>
                    <li><h4 id="associate-plano-paciente">Associar Plano de Saúde a Paciente:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /plano-saude/paciente/associate/{idPaciente}/{idPlanoSaude}
                        </p>
                        <p>RequestBody: PlanoSaudePacienteDto</p>
                        <p style="text-align:justify;">
                            Busca o PlanoSaude e Paciente utilizando idPaciente e idPlanoSaude informados, caso não sejam encontrados, é lançada exceção.
                            Baseado no idTipoPlanoSaude informado no dto, é realizada uma busca no TipoPlanoSaude correspondente.
                            É construído um PlanoSaudePaciente e as informações são salvas em tb_plano_saude_paciente.
                        </p>
                        <p> Retorno: PlanoSaudePaciente ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="disassociate-plano-paciente">Desassociar Plano de Saúde de Paciente:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /plano-saude/paciente/disassociate/{idPaciente}/{idPlanoSaude}
                        </p>
                        <p style="text-align:justify;">
                            Deleta registro de PlanoSaudePaciente em tb_plano_saude_paciente.
                        </p>
                        <p> Retorno: Mensagem de sucesso</p>
                    </li>
                     <li><h4 id="get-planos-paciente">Retornar Planos de Saúde de Paciente:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /plano-saude/paciente/{idPaciente}
                        </p>
                        <p style="text-align:justify;">
                            Busca o Paciente utilizando idPaciente informado, caso não seja encontrado, é lançada exceção.
                            Constrói PacientePlanosSaudeResponseDto utilizando constructor expression e o retorna.
                        </p>
                        <p> Retorno: PacientePlanosSaudeResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-planos-paciente-medico">Retornar Planos de Saúde de Paciente em comum com Médico:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /plano-saude/paciente/medico/{idPaciente}/{idMedico}
                        </p>
                        <p style="text-align:justify;">
                            Busca planos de saúde que médico e paciente, cujos ids foram informados, possuem em comum.
                        </p>
                        <p> Retorno: List de PlanoSaude</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="planosaude-medico">PlanoSaudeMedicoController</h3>
                <ul>
                    <li><h4 id="associate-plano-medico">Associar Planos de Saúde a Médico:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /plano-saude/medico/associate/{idMedico}
                        </p>
                        <p>RequestBody: MedicoPlanoSaudeDto</p>
                        <p style="text-align:justify;">
                            Busca o Medico utilizando idMedico informado, caso não seja encontrado, é lançada exceção.
                            Para cada idPlanoSaude enviado no dto é buscado o PlanoSaude correspondente, o medico
                            anteriormente encontrado é adicionado a lista de médicos desse plano de saúde, e o plano
                            é adicionado a lista de planos de médico, essa atualização é salva, criando um novo registro
                            em tb_medico_plano_saude. É retornada a lista com planos de saúde, que foram recém-associados 
                            ao médico.
                        </p>
                        <p> Retorno: List de PlanoSaude ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="disassociate-plano-medico">Desassociar Planos de Saúde de Médico:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /plano-saude/medico/disassociate/{idMedico}/{idPlanoSaude}
                        </p>
                        <p style="text-align:justify;">
                            Busca o Medico e PlanoSaude utilizando idMedico e idPlanoSaude informados, caso não sejam 
                            encontrados, é lançada exceção. O plano de saúde encontrado é removido da lista de planos de
                            saúde do médico e o médico é removido da lista de médicos do plano de saúde, essa alteração 
                            é salva e é retornada a lista de planos de saúde do médico.
                        </p>
                        <p> Retorno: List de PlanoSaude ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-planos-medico-paginado">Retornar Planos de Saúde Médico com Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /plano-saude/medico/{idMedico}
                        </p>
                        <p>RequestParams:</p>
                        <ul>
                            <li>page</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "0"</li>
                            </ul>
                            <li>pageSize</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "10"</li>
                            </ul>
                        </ul>
                        <p style="text-align:justify;">
                            Busca registros de planos de saúde vinculados a um médico utilizando idMedico informado,
                            e os retorna numa lista de PlanoSaude paginada.
                        </p>
                        <p> Retorno: Page de PlanoSaude</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="secretaria-medico">SecretariaMedicoController</h3>
                <ul>
                    <li><h4 id="associate-secretaria-medico">Associar Secretária ao Médico:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /secretaria/medico/associate/{idSecretaria}/{idMedico}
                        </p>
                        <p style="text-align:justify;">
                            Busca o médico e a secretária que cujos ids foram passados como PathVariable, caso
                            não sejam encontrados é lançada exceção. Como a entidade Secretaria é aquela na qual
                            a relação entre médicos e secretarias foi mapeada, para criar um registro em tb_medico_secretaria
                            é necessário apenas adicionar o médico a lista de médicos da secretária e salvar a alteração.
                        </p>
                        <p> Retorno: Mensagem de sucesso ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="disassociate-secretaria-medico">Desassociar Secretária de Médico:</h4>
                        <p> Método HTTP: PUT </p>
                        <p>
                            Rota: /secretaria/medico/disassociate/{idSecretaria}/{idMedico}
                        </p>
                        <p style="text-align:justify;">
                            Busca o médico e a secretária que cujos ids foram passados como PathVariable, caso
                            não sejam encontrados é lançada exceção. Como a entidade Secretaria é aquela na qual
                            a relação entre médicos e secretarias foi mapeada, para remover um registro em tb_medico_secretaria
                            é necessário apenas remover o médico da lista de médicos da secretária e salvar a alteração.
                            Após isso, é verificado se aquele médico possui vínculo com outras secretárias, caso
                            não possua, ele é deletado.
                        </p>
                        <p> Retorno: Mensagem de sucesso ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-secretaria-medicos-paginado">Retornar Médicos de uma Secretária com Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /secretaria/medico/paginado/{idSecretaria}
                        </p>
                        <p>RequestParams:</p>
                        <ul>
                            <li>page</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "0"</li>
                            </ul>
                            <li>pageSize</li>
                            <ul>
                                <li>required = true</li>
                                <li>defaultValue = "10"</li>
                            </ul>
                        </ul>
                        <p style="text-align:justify;">
                            Busca registros de médicos vinculados a uma secretária utilizando idSecretaria informado,
                            é criado MedicoCrmResponseDto utilizando constructor expression, para cada médico são
                            populadas suas especialidades e os seus vínculos com planos de saúde. É retornado Page de
                            MedicoCrmResponseDto.
                        </p>
                        <p> Retorno: Page de MedicoCrmResponseDto ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="get-secretaria-medicos">Retornar Médicos de uma Secretária sem Paginação:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /secretaria/medico/{idSecretaria}
                        </p>
                        <p style="text-align:justify;">
                            Busca registros de médicos vinculados a uma secretária utilizando idSecretaria informado,
                            é criado MedicoCrmResponseDto utilizando constructor expression. É retornado List de
                            MedicoCrmResponseDto.
                        </p>
                        <p> Retorno: List de MedicoCrmResponseDto</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="passwordresettoken">PasswordResetTokenController</h3>
                <ul>
                    <li><h4 id="email-senha">Enviar email para redefinir senha:</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /password/reset
                        </p>
                        <p>RequestParams:</p>
                        <ul>
                            <li>username</li>
                            <ul>
                                <li>required = true</li>
                            </ul>
                        </ul>
                        <p style="text-align:justify;">
                            Busca o usuário cujo username foi informado, caso não seja encontrado é lançada exceção.
                            Um token UUID é formado, é buscado um registro em tb_password_reset para aquele usuário,
                            caso exista, o token do registro é atualizado para o que foi gerado, caso contrário é criado
                            um novo registro atribuindo o token formado ao usuário cujo username foi informado.
                            O serviço de email envia um email com uma url para que o usuário acesse e troque sua senha e na
                            requisição o token enviado é retornado.
                        </p>
                        <p> Retorno: String(Token) ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="verifica-token">Verificar token para redefinir senha:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /password/change
                        </p>
                        <p>RequestParams:</p>
                        <ul>
                            <li>token</li>
                            <ul>
                                <li>required = true</li>
                            </ul>
                        </ul>
                        <p style="text-align:justify;">
                            Busca o PasswordResetToken utilizando token informado, caso não seja encontrado é lançada exceção.
                            Verifica se o token está expirado, se estiver é lançada exceção, caso
                            contrário é devolvida mensagem de sucesso.
                        </p>
                        <p> Retorno: Mensagem de sucesso ou mensagem de MedLinkedException</p>
                    </li>
                    <li><h4 id="salva-senha-token">Salvar nova senha de usuário utilizando token:</h4>
                        <p> Método HTTP: POST </p>
                        <p>
                            Rota: /password/save
                        </p>
                        <p>RequestBody: PasswordResetDto</p>
                        <p style="text-align:justify;">
                            Verifica o token para confirmar que é válido, caso não seja lança exceção. 
                            Por meio do token informado no dto, busca-se o PasswordResetToken e através dele
                            encontra-se o usuário. A senha do usuário é atualizada e é retornado as informações de usuário.
                        </p>
                        <p> Retorno: Usuário ou mensagem de MedLinkedException</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="especialidade">EspecialidadeController</h3>
                <ul>
                    <li><h4 id="get-especialidades">Retornar todas as Especialidades:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /especialidade
                        </p>
                        <p style="text-align:justify;">
                            Busca todas as especialidades cadastradas e as retorna numa lista ordenada
                            por suas descrições.
                        </p>
                        <p> Retorno: List de Especialidade</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="estado">EstadoController</h3>
                <ul>
                    <li><h4 id="get-estados">Retornar todos os Estados:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /estado
                        </p>
                        <p style="text-align:justify;">
                            Busca todos os estados cadastrados e os retorna numa lista ordenada
                            por suas descrições.
                        </p>
                        <p> Retorno: List de Estado</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="tipo-agendamento">TipoAgendamentoController</h3>
                <ul>
                    <li><h4 id="get-tipos-agendamento">Retornar todos os Tipos de Agendamento:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /tipo-agendamento
                        </p>
                        <p style="text-align:justify;">
                            Busca todos os valores do enum TipoAgendamento e os retorna em uma lista.
                        </p>
                        <p> Retorno: List de TipoAgendamento</p>
                    </li>
                </ul>
            </li>
            <li>
                <h3 id="tipo-plano-saude">TipoPlanoSaudeController</h3>
                <ul>
                    <li><h4 id="get-tipos-plano-saude">Retornar todos os Tipos de Plano de Saúde:</h4>
                        <p> Método HTTP: GET </p>
                        <p>
                            Rota: /tipo-plano-saude
                        </p>
                        <p style="text-align:justify;">
                            Busca todos os tipos de plano de saúde cadastrados e os retorna em uma lista
                            ordenada por suas descrições.
                        </p>
                        <p> Retorno: List de TipoPlanoSaude</p>
                    </li>
                </ul>
            </li>
        </ul>
    </body>
</html>
