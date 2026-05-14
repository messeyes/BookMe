// ====================
// VARIÁVEL GLOBAL
// ====================
let livros = []; 
let paginaAtual = 1;

// ====================
// INICIALIZAÇÃO 
// ====================
window.onload = function() {
    atualizarMenuLateral();
    mostrarHome();
};

// ====================
// HOME 
// ====================
function mostrarHome() {
  document.getElementById("conteudo").innerHTML = `
    <h1>Bem-vindo à Biblioteca Virtual</h1>
    <p>Escolha uma opção no menu ao lado para começar.</p>
  `;
}

// ====================
// ATUALIZAR MENU LATERAL
// ====================
function atualizarMenuLateral() {
    const dadosSessao = localStorage.getItem("usuarioLogado");
    const areaPerfil = document.getElementById("perfil-usuario");

    if (!areaPerfil) return; 

    if (dadosSessao) {
        const usuario = JSON.parse(dadosSessao);
        const primeiroNome = usuario.nome.split(" ")[0];

        areaPerfil.innerHTML = `
            <div class="mini-perfil">
                <div class="avatar-circulo">${primeiroNome.charAt(0)}</div>
                <div class="mini-perfil-info">
                    <span>Olá, ${primeiroNome}!</span>
                    <small>${usuario.cargo}</small>
                </div>
            </div>
            <button class="btn-perfil-acesso" onclick="mostrarPainelUsuario()">⚙️ Editar Perfil</button>
        `;
    } else {
        areaPerfil.innerHTML = `
            <div class="mini-perfil">
                <div class="avatar-circulo">?</div>
                <div class="mini-perfil-info">
                    <span>Visitante</span>
                    <small>Não autenticado</small>
                </div>
            </div>
            <button class="btn-perfil-acesso" onclick="mostrarCadastroUsuario()">Acesso perfil / Login</button>
        `;
    }
}

/// ====================
// MOSTRAR LIVROS (GET)
// ====================
async function mostrarLivros() {
    const dadosSessao = localStorage.getItem("usuarioLogado");
    let btnNovoLivroHTML = "";

    if (dadosSessao) {
        const usuario = JSON.parse(dadosSessao);
        if (usuario.cargo === "ADMINISTRADOR" || usuario.cargo === "BIBLIOTECARIO") {
            btnNovoLivroHTML = `<button class="btn-novo" onclick="mostrarCadastroLivro()">+ Cadastrar Livro</button>`;
        }
    } 

    let html = `
        <div class="cabecalho-tela">
            <h1>Livros</h1>
            ${btnNovoLivroHTML} 
        </div>
        <input id="buscaLivro" placeholder="Buscar livro..." oninput="filtrarLivros()">
        <div id="listaLivros"><i>Conectando com o servidor Java...</i></div>
    `;
    
    const conteudo = document.getElementById("conteudo");
    if (conteudo) conteudo.innerHTML = html;

    try {
        const resposta = await fetch('http://localhost:8080/livros');
        if (resposta.ok) {
            livros = await resposta.json();
            renderizarLivros(livros);
        } else {
            document.getElementById("listaLivros").innerHTML = "<p>Nenhum livro encontrado.</p>";
        }
    } catch (erro) {
        console.error("Erro na requisição:", erro);
        document.getElementById("listaLivros").innerHTML = "<p class='erro-conexao'>Erro: Não foi possível conectar com o Backend (Porta 8080).</p>";
    }
}

/// ====================
// RENDERIZAR LIVROS 
// ====================
function renderizarLivros(lista) {
  const itensPorPagina = window.innerWidth <= 768 ? 6 : 12;
  
  if(lista.length === 0) {
      document.getElementById("listaLivros").innerHTML = "<p>Nenhum livro encontrado.</p>";
      return;
  }

  const totalPaginas = Math.ceil(lista.length / itensPorPagina);
  if (paginaAtual > totalPaginas) paginaAtual = totalPaginas;
  if (paginaAtual < 1) paginaAtual = 1;

  const inicio = (paginaAtual - 1) * itensPorPagina;
  const fim = inicio + itensPorPagina;
  const livrosDaPagina = lista.slice(inicio, fim);

  let cards = "";
  livrosDaPagina.forEach(livro => {
    cards += `
      <div class="card">
        <h2>${livro.titulo}</h2>
        <p><b>Autor:</b> ${livro.autor}</p>
        <p><b>ISBN:</b> ${livro.isbn}</p>
        <p><b>Total:</b> ${livro.quantTotal}</p>
        <button onclick="mostrarDetalhesLivro(${livro.id})">Ver detalhes</button>
      </div>
    `;
  });
  
  
  const paginacaoHTML = `
      <div class="paginacao">
          <button class="btn-paginacao" ${paginaAtual === 1 ? 'disabled' : `onclick="mudarPagina(${paginaAtual - 1})"`}>Anterior</button>
          <span>Página ${paginaAtual} de ${totalPaginas}</span>
          <button class="btn-paginacao" ${paginaAtual === totalPaginas ? 'disabled' : `onclick="mudarPagina(${paginaAtual + 1})"`}>Próxima</button>
      </div>
  `;

  document.getElementById("listaLivros").innerHTML = cards + paginacaoHTML;
}

// ====================
// MUDAR PÁGINA
// ====================
function mudarPagina(novaPagina) {
    paginaAtual = novaPagina;
    filtrarLivros(); 
}

// ====================
// FILTRAR LIVROS (BARRA DE PESQUISA)
// ====================
function filtrarLivros() {
    const termo = document.getElementById("buscaLivro").value.toLowerCase();
    
    const livrosFiltrados = livros.filter(livro => 
        livro.titulo.toLowerCase().includes(termo)
    );

    if (event && event.type === 'input') {
        paginaAtual = 1; 
    }

    renderizarLivros(livrosFiltrados);
}

// ====================
// CADASTRAR LIVRO (TELA)
// ====================
function mostrarCadastroLivro() {
  document.getElementById("conteudo").innerHTML = `
    <h1>Cadastrar Livro</h1>
    <input id="titulo" placeholder="Título do livro">
    <input id="autor" placeholder="Autor">
    <input id="isbn" placeholder="ISBN">
    <input id="quantidadeTotal" type="number" placeholder="Quantidade total">
    
    <div class="botoes-form">
        <button onclick="cadastrarLivro()">Salvar livro</button>
        <button class="btn-cancelar" onclick="mostrarLivros()">Cancelar</button>
    </div>
  `;
}

// ====================
// CADASTRAR LIVRO (POST)
// ====================
async function cadastrarLivro() {
  const titulo = document.getElementById("titulo").value;
  const autor = document.getElementById("autor").value;
  const isbn = document.getElementById("isbn").value;
  const quantidadeTotal = Number(document.getElementById("quantidadeTotal").value);

  if (!titulo || !autor || !isbn || !quantidadeTotal) {
    alert("Preencha todos os campos!");
    return;
  }

  const novoLivro = {
      titulo: titulo,
      autor: autor,
      isbn: isbn,
      quantTotal: quantidadeTotal,
      quantDisponivel: quantidadeTotal
  };

  try {
      
      const resposta = await fetch('http://localhost:8080/livros', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(novoLivro)
      });

      if (resposta.ok) {
          alert("Livro salvo com sucesso no banco de dados!");
          mostrarLivros(); 
      } else {
          alert("O servidor recusou o cadastro. Verifique os dados.");
      }
      
  } catch (erro) {
      console.error("Erro ao salvar:", erro);
      alert("Falha na comunicação com o backend.");
  }
}

// ====================
// VER DETALHES DO LIVRO
// ====================
function mostrarDetalhesLivro(idClicado) {
  const livro = livros.find(l => l.id === idClicado);
  const dadosSessao = localStorage.getItem("usuarioLogado"); 

  if (!livro) {
    alert("Livro não encontrado!");
    return;
  }

  let botoesAcaoHTML = "";

  if (dadosSessao) {
      if (livro.quantDisponivel > 0) {
          botoesAcaoHTML = `<button class="btn-emprestar" onclick="realizarEmprestimo(${livro.id})">Pegar Emprestado</button>`;
      } else {
          botoesAcaoHTML = `<button class="btn-reservar" onclick="realizarReserva(${livro.id})">Reservar Livro</button>`;
      }
  } else {
      botoesAcaoHTML = `<p class="aviso-login">Faça login ou cadastre-se para realizar empréstimos.</p>`;
  }

  document.getElementById("conteudo").innerHTML = `
    <div class="cabecalho-tela">
        <h1>${livro.titulo}</h1>
        <button class="btn-voltar" onclick="mostrarLivros()">Voltar para lista</button>
    </div>
    
    <div class="card-detalhes">
        <h3>Informações da Obra</h3>
        <p><b>Autor:</b> ${livro.autor}</p>
        <p><b>Código ISBN:</b> ${livro.isbn}</p>
        <br>
        <h3>Estoque</h3>
        <p><b>Disponível:</b> ${livro.quantDisponivel} de ${livro.quantTotal}</p>
        
        <div class="botoes-acao">
            ${botoesAcaoHTML}
        </div>
    </div>
  `;
}

// ====================
// REALIZAR EMPRÉSTIMO
// ====================
async function realizarEmprestimo(idDoLivro) {
    const dadosSessao = localStorage.getItem("usuarioLogado");
    
    if (!dadosSessao) {
        alert("Você precisa estar logado para pegar um livro!");
        return;
    }

    const usuario = JSON.parse(dadosSessao);

    
    const pacoteDeDados = {
        idUsuario: usuario.id,
        idLivro: idDoLivro
    };

    try {
        const resposta = await fetch('http://localhost:8080/emprestimos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(pacoteDeDados)
        });

        if (resposta.ok) {
            alert("Empréstimo realizado com sucesso! Verifique seu perfil.");
            mostrarLivros(); 
        } else {
            const erro = await resposta.text();
            alert("Erro: " + (erro || "O servidor recusou o empréstimo. Verifique o estoque."));
        }
    } catch (erro) {
        console.error("Erro na conexão:", erro);
        alert("Não foi possível conectar ao servidor Java.");
    }
}

// ====================
// REALIZAR RESERVA
// ====================
async function realizarReserva(idDoLivro) {
    const dadosSessao = localStorage.getItem("usuarioLogado");
    
    if (!dadosSessao) {
        alert("Você precisa estar logado para reservar um livro!");
        return;
    }
    const usuario = JSON.parse(dadosSessao);

    const pacoteDeDados = {
        idUsuario: usuario.id,
        idLivro: idDoLivro
    };

    try {
        const resposta = await fetch('http://localhost:8080/reservas', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(pacoteDeDados)
        });

        if (resposta.ok) {
            alert("Livro reservado com sucesso! Avisaremos quando chegar.");
            mostrarLivros();
        } else {
            alert("O servidor recusou a reserva. Verifique se o banco de dados está rodando.");
        }
    } catch (erro) {
        console.error("Erro ao reservar:", erro);
        alert("Erro de conexão com o servidor Java.");
    }
}

// ====================
// TELA DE CADASTRO DE USUÁRIO
// ====================
function mostrarCadastroUsuario() {
  document.getElementById("conteudo").innerHTML = `
    <div class="cabecalho-tela">
        <h1 id="titulo-autenticacao">Criar Nova Conta</h1>
    </div>
    
    <div class="card-detalhes" id="card-auth">
        <div id="campos-registro">
            <label>Nome Completo</label>
            <input id="nomeUsuario" placeholder="Como quer ser chamado?">
            
            <label>E-mail Institucional</label>
            <input id="emailUsuario" placeholder="seu@email.com">
            
            <label>Senha Numérica (Mínimo 4 Digitos)</label>
            <input id="senhaUsuario" type="password" placeholder="Ex: 1234">

            <label>Tipo de Perfil</label>
            <select id="cargoUsuario">
                <option value="USUARIO">Usuário</option>
                <option value="ADMINISTRADOR">Administrador</option>
                <option value="BIBLIOTECARIO">Bibliotecário</option>
            </select>
        </div>
        
        <div class="botoes-acao">
            <button class="btn-emprestar" id="btn-principal" onclick="cadastrarUsuario()">Finalizar Cadastro</button>
            <button class="btn-voltar" onclick="mostrarHome()">Cancelar</button>
        </div>
       
    </div>
  `;
}

// ====================
// CADASTRAR USUÁRIO (POST)
// ====================
async function cadastrarUsuario() {
    const nome = document.getElementById("nomeUsuario").value.trim();
    const email = document.getElementById("emailUsuario").value.trim();
    const senha = document.getElementById("senhaUsuario").value;
    const cargo = document.getElementById("cargoUsuario").value;

    if (!nome || !email || !senha) {
        alert("Ops! Todos os campos são obrigatórios.");
        return;
    }

    if (!email.includes("@") || !email.includes(".")) {
        alert("Por favor, insira um e-mail válido (ex: nome@email.com)");
        return;
    }

    const novoUsuario = {
        nome: nome,
        email: email,
        senha: senha,
        cargo: cargo
    };

    try {
        const resposta = await fetch('http://localhost:8080/usuarios', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novoUsuario)
        });

        if (resposta.ok) {
            const usuarioCriado = await resposta.json();
            localStorage.setItem("usuarioLogado", JSON.stringify(usuarioCriado));
            
            alert(`Conta criada com sucesso! Bem-vinda, ${usuarioCriado.nome}`);
            atualizarMenuLateral();
            mostrarHome();
        } else {
            alert("Erro no cadastro. Verifique se a senha é um número maior que 4 Digitos.");
        }
    } catch (erro) {
        alert("Erro de conexão.");
    }
}

// ====================
// FAZER LOGOUT
// ====================
function logout() {
    localStorage.removeItem("usuarioLogado");
    alert("Você saiu da conta!");
    atualizarMenuLateral();
    mostrarCadastroUsuario();
}


// ====================
// PAINEL DO USUÁRIO 
// ====================
async function mostrarPainelUsuario() {
    const dadosSessao = localStorage.getItem("usuarioLogado");
    if (!dadosSessao) return mostrarCadastroUsuario();

    const usuario = JSON.parse(dadosSessao);

    document.getElementById("conteudo").innerHTML = `
        <div class="cabecalho-tela">
            <h1>Meu Perfil</h1>
            <button class="btn-voltar" onclick="logout()">Sair da Conta</button>
        </div>
        
        <div class="card-detalhes">
            <p><b>Nome:</b> ${usuario.nome}</p>
            <p><b>Email:</b> ${usuario.email}</p>
            <p><b>Cargo:</b> <span class="tag-status">${usuario.cargo}</span></p>
        </div>

        <div class="secoes-perfil">
            <button class="btn-emprestar btn-perfil-acao" onclick="mostrarMeusEmprestimos()">
                Ver Meus Empréstimos
            </button>
            
            <button class="btn-reservar btn-perfil-acao btn-acao-reservas" onclick="mostrarMinhasReservas()">
                Minhas Reservas
            </button>
            
            <button class="btn-reservar btn-perfil-acao btn-acao-multas" onclick="mostrarMinhasMultas()">
                Gerenciar Multas
            </button>
        </div>
    `;
}

async function devolverLivro(idEmprestimo) {
    try {
        const resposta = await fetch(`http://localhost:8080/emprestimos/${idEmprestimo}/devolver`, {
            method: 'PATCH' 
        });

        if (resposta.ok) {
            alert("Livro devolvido com sucesso!");
            mostrarMeusEmprestimos(); 
        }
    } catch (erro) {
        alert("Erro ao processar devolução.");
    }
}

// ====================
// ABA: MEUS EMPRÉSTIMOS
// ====================
async function mostrarMeusEmprestimos() {
    const dadosSessao = localStorage.getItem("usuarioLogado");
    const usuario = JSON.parse(dadosSessao);

    document.getElementById("conteudo").innerHTML = `<h1>Meus Empréstimos</h1><div id="lista-emp"></div>`;

    try {
        const resposta = await fetch('http://localhost:8080/emprestimos');
        const todos = await resposta.json();

        const meus = todos.filter(e => e.usuario.id === usuario.id);

        let html = '<table class="tabela-atividades"><tr><th>Livro</th><th>Devolução</th><th>Ação</th></tr>';
        meus.forEach(e => {
            html += `
                <tr>
                    <td>${e.livro.titulo}</td>
                    <td>${e.dataDevolPrevista}</td>
                    <td>${!e.dataDevolReal ? `<button onclick="devolverLivro(${e.id})">Devolver</button>` : 'Entregue'}</td>
                </tr>`;
        });
        document.getElementById("lista-emp").innerHTML = html + '</table>';
    } catch (e) {
        alert("Erro ao buscar empréstimos.");
    }
}

// ====================
// ABA: MINHAS RESERVAS
// ====================
async function mostrarMinhasReservas() {
    const dadosSessao = localStorage.getItem("usuarioLogado");
    if (!dadosSessao) return;
    const usuario = JSON.parse(dadosSessao);

    document.getElementById("conteudo").innerHTML = `
        <div class="cabecalho-tela">
            <h1>⏳ Minhas Reservas</h1>
            <button class="btn-voltar" onclick="mostrarPainelUsuario()">Voltar ao Perfil</button>
        </div>
        <div id="lista-atividades" class="card-detalhes">Verificando reservas...</div>
    `;

    try {
        const resposta = await fetch('http://localhost:8080/reservas');
        const todasReservas = await resposta.json();

        const minhasReservas = todasReservas.filter(res => res.usuario.id === usuario.id);

        if (minhasReservas.length === 0) {
            document.getElementById("lista-atividades").innerHTML = "<p>Nenhuma reserva encontrada.</p>";
            return;
        }

        let html = '<div class="grid-reservas">';
        minhasReservas.forEach(res => {
            html += `
                <div class="item-reserva">
                    <h3>${res.livro.titulo}</h3> 
                    <p><b>Data:</b> ${new Date(res.dataReserva).toLocaleDateString('pt-BR')}</p>
                    <p><b>Posição na fila:</b> ${res.ordemFila}º lugar</p>
                    <p><b>Status:</b> <span class="tag-status">${res.status}</span></p>
                    
                    ${res.status === 'PENDENTE' ? `<button class="btn-voltar btn-cancelar-reserva" onclick="cancelarReserva(${res.id})">Cancelar Reserva</button>` : ''}
                </div>
            `;
        });
        html += '</div>';
        document.getElementById("lista-atividades").innerHTML = html;
    } catch (erro) {
        document.getElementById("lista-atividades").innerHTML = "<p>Erro ao conectar com as reservas.</p>";
    }
}

// ====================
// CANCELAR RESERVA
// ====================
async function cancelarReserva(idReserva) {
    try {
        const resposta = await fetch(`http://localhost:8080/reservas/${idReserva}/cancelar`, {
            method: 'PATCH' 
        });

        if (resposta.ok || resposta.status === 204) { 
            alert("Sua reserva foi cancelada com sucesso!");
            mostrarMinhasReservas(); 
        }
    } catch (erro) {
        alert("Erro ao tentar cancelar a reserva.");
    }
}

// ====================
// ABA: GERENCIAMENTO DE MULTAS
// ====================
async function mostrarMinhasMultas() {
    const dadosSessao = localStorage.getItem("usuarioLogado");
    const usuario = JSON.parse(dadosSessao);

    document.getElementById("conteudo").innerHTML = `
        <div class="cabecalho-tela">
            <h1>Minhas Multas</h1>
            <button class="btn-voltar" onclick="mostrarPainelUsuario()">Voltar</button>
        </div>
        <div id="lista-multas">Carregando...</div>
    `;

    try {
        const resposta = await fetch('http://localhost:8080/multas');
        const todasMultas = await resposta.json();

        const minhasMultas = todasMultas.filter(m => m.emprestimo.usuario.id === usuario.id);

        if (minhasMultas.length === 0) {
            document.getElementById("lista-multas").innerHTML = "<p>Você não tem multas pendentes.</p>";
            return;
        }

        let html = '<table class="tabela-atividades"><tr><th>Livro</th><th>Valor</th><th>Status</th><th>Ação</th></tr>';
        minhasMultas.forEach(m => {
            html += `
                <tr>
                    <td>${m.emprestimo.livro.titulo}</td>
                    <td>R$ ${m.valor.toFixed(2)}</td>
                    <td>${m.pago ? 'Pago' : 'Pendente'}</td>
                    <td>${!m.pago ? `<button onclick="pagarMulta(${m.id})">Pagar</button>` : '-'}</td>
                </tr>`;
        });
        document.getElementById("lista-multas").innerHTML = html + '</table>';
    } catch (e) {
        document.getElementById("lista-multas").innerHTML = "Erro ao conectar com o banco.";
    }
}


async function pagarMulta(idMulta) {
    try {
        const resposta = await fetch(`http://localhost:8080/multas/${idMulta}/pagar`, {
            method: 'PATCH' 
        });

        if (resposta.ok) {
            alert("Pagamento registrado! Sua conta está regularizada.");
            mostrarMinhasMultas();
        }
    } catch (erro) {
        alert("Erro ao processar pagamento.");
    }
}

// ====================
// REGRAS DA BIBLIOTECA
// ====================
function mostrarRegras() {
  document.getElementById("conteudo").innerHTML = `
    <div class="cabecalho-tela">
        <h1>Regras da Biblioteca 📜</h1>
    </div>

        <h3 class="titulo-regra">1. Empréstimos e Prazos</h3>
        <ul class="lista-regras">
            <li>O prazo padrão para devolução de qualquer obra é de <b>14 dias</b> corridos.</li>
            <li>Para garantir que todos tenham acesso, você só pode pegar <b>um exemplar</b> de um mesmo livro por vez.</li>
        </ul>
        
        <h3 class="titulo-regra">2. Sistema de Reservas</h3>
        <ul class="lista-regras">
            <li>Se o livro que você deseja estiver sem estoque (0 disponíveis), você pode entrar na <b>fila de reserva</b>.</li>
            <li>Acompanhe sua posição na fila pela aba "Minhas Reservas" no seu Perfil.</li>
            <li>Você pode cancelar sua reserva a qualquer momento se desistir de esperar.</li>
        </ul>

        <h3 class="titulo-regra">3. Atrasos e Multas</h3>
        <ul class="lista-regras">
            <li>Devoluções feitas após a data prevista gerarão multas no sistema.</li>
            <li>Para regularizar sua situação, acesse "Gerenciar Multas" no seu Perfil e realize o pagamento simbólico.</li>
        </ul>
    </div>
  `;
}
