// ====================
// LISTA DE LIVROS
// ====================

const livros = [

  {
    id: 1,
    titulo: "Dom Casmurro",
    autor: "Machado de Assis",
    isbn: "123",
    quantidadeTotal: 5,
    quantidadeDisponivel: 3
  },

  {
    id: 2,
    titulo: "Harry Potter",
    autor: "J.K Rowling",
    isbn: "456",
    quantidadeTotal: 10,
    quantidadeDisponivel: 7
  },
 {
  id: 3,
  titulo: "Percy Jackson",
  autor: "Rick Riordan",
  isbn: "789",
  quantidadeTotal: 8,
  quantidadeDisponivel: 8
}

];
function mostrarCadastroLivro() {
  document.getElementById("conteudo").innerHTML = `
    <h1> Cadastrar Livro</h1>

    <input id="titulo" placeholder="Título do livro">
    <input id="autor" placeholder="Autor">
    <input id="isbn" placeholder="ISBN">
    <input id="quantidadeTotal" type="number" placeholder="Quantidade total">

    <button onclick="cadastrarLivro()">Salvar livro</button>
    <button onclick="mostrarLivros()">Cancelar</button>
  `;
}


// ====================
// HOME
// ====================

function mostrarHome() {

  document.getElementById("conteudo").innerHTML = `

    <h1> Home</h1>

    <p>
      Bem-vinda à Biblioteca Virtual!
    </p>

  `;
}


// ====================
// FAVORITOS
// ====================

function mostrarFavoritos() {

  document.getElementById("conteudo").innerHTML = `

    <h1> Favoritos</h1>

    <p>
      Seus livros favoritos aparecerão aqui.
    </p>

  `;
}


// ====================
// MOSTRAR LIVROS
// ====================

function mostrarLivros() {

  let html = `

    <h1> Livros</h1>

    <input
      id="buscaLivro"
      placeholder="Buscar livro"
      oninput="filtrarLivros()"
    >

    <div id="listaLivros"></div>

  `;

  document.getElementById("conteudo").innerHTML = html;

  renderizarLivros(livros);
}


// ====================
// RENDERIZAR LIVROS
// ====================

function renderizarLivros(lista) {

  let cards = "";

  lista.forEach(livro => {

    cards += `

      <div class="card">

        <h2>${livro.titulo}</h2>

        <p><b>Autor:</b> ${livro.autor}</p>

        <p><b>ISBN:</b> ${livro.isbn}</p>

        <p><b>Total:</b> ${livro.quantidadeTotal}</p>

        <p><b>Disponível:</b> ${livro.quantidadeDisponivel}</p>

        <button onclick="verDetalhesLivro(${livro.id})">
          Ver detalhes
        </button>

      </div>

    `;
  });

  document.getElementById("listaLivros").innerHTML = cards;
}


// ====================
// FILTRAR LIVROS
// ====================

function filtrarLivros() {

  const busca = document
    .getElementById("buscaLivro")
    .value
    .toLowerCase();

  const filtrados = livros.filter(livro => {

    return (

      livro.titulo.toLowerCase().includes(busca)

      ||

      livro.isbn.includes(busca)

    );

  });

  renderizarLivros(filtrados);
}


// ====================
// DETALHES
// ====================

function verDetalhesLivro(id) {

  let livros = livros.find(l => l.id === id);

  document.getElementById("conteudo").innerHTML = `

    <h1> Detalhes</h1>

    <div class="card">

      <h2>${livro.titulo}</h2>

      <p><b>Autor:</b> ${livro.autor}</p>

      <p><b>ISBN:</b> ${livro.isbn}</p>

      <p><b>Total:</b> ${livro.quantidadeTotal}</p>

      <p><b>Disponível:</b> ${livro.quantidadeDisponivel}</p>

      <button onclick="mostrarLivros()">
        Voltar
      </button>

    </div>

  `;
}


// ====================
// INICIAR APP
// ====================

function cadastrarLivro() {
  const titulo = document.getElementById("titulo").value;
  const autor = document.getElementById("autor").value;
  const isbn = document.getElementById("isbn").value;
  const quantidadeTotal = Number(document.getElementById("quantidadeTotal").value);

  if (!titulo || !autor || !isbn || !quantidadeTotal) {
    alert("Preencha todos os campos!");
    return;
  }

  const isbnExiste = livros.some(livro => livro.isbn === isbn);

  if (isbnExiste) {
    alert("Esse ISBN já está cadastrado!");
    return;
  }

  const novoLivro = {
    id: livros.length + 1,
    titulo: titulo,
    autor: autor,
    isbn: isbn,
    quantidadeTotal: quantidadeTotal,
    quantidadeDisponivel: quantidadeTotal
  };

  livros.push(novoLivro);

  alert("Livro cadastrado com sucesso!");
  mostrarLivros();
}
mostrarHome();