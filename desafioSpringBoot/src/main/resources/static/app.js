const API = 'http://localhost:8080/usuarios';
let deleteModal;
let deletePendingId = null;


document.addEventListener('DOMContentLoaded', () => {
    deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
    carregarUsuarios();

    document.getElementById('formCriar').addEventListener('submit', async (e) => {
        e.preventDefault();
        await criarUsuario();
    });

    document.getElementById('formEditar').addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = document.getElementById('editId').value;
        await atualizarUsuario(id);
    });

    document.getElementById('confirmDeleteBtn').addEventListener('click', async () => {
        if (deletePendingId !== null) await deletarUsuario(deletePendingId);
    });
});


function trocarAba(aba) {
    const painelCriar  = document.getElementById('painelCriar');
    const painelEditar = document.getElementById('painelEditar');
    const tabCriar     = document.getElementById('tabCriar');
    const tabEditar    = document.getElementById('tabEditar');

    if (aba === 'criar') {
        painelCriar.style.display  = 'block';
        painelEditar.style.display = 'none';
        tabCriar.classList.add('active');
        tabEditar.classList.remove('active');
    } else {
        painelCriar.style.display  = 'none';
        painelEditar.style.display = 'block';
        tabEditar.classList.add('active');
        tabCriar.classList.remove('active');
    }

    ocultarAlertas();
}


function toggleSenha(inputId, iconId) {
    const input = document.getElementById(inputId);
    const icon  = document.getElementById(iconId);
    input.type  = input.type === 'password' ? 'text' : 'password';
    icon.className = input.type === 'password' ? 'bi bi-eye' : 'bi bi-eye-slash';
}


async function carregarUsuarios() {
    try {
        const res = await fetch(API);
        if (!res.ok) {
            mostrarVazio();
            atualizarContador(0);
            return;
        }
        const usuarios = await res.json();
        renderTabela(usuarios);
        atualizarContador(usuarios.length);
    } catch {
        mostrarVazio();
        atualizarContador(0);
    }
}

function renderTabela(usuarios) {
    const tbody = document.getElementById('usuariosTableBody');
    document.getElementById('emptyState').classList.add('d-none');
    document.getElementById('tableWrapper').classList.remove('d-none');

    if (!usuarios || usuarios.length === 0) {
        mostrarVazio();
        return;
    }

    tbody.innerHTML = usuarios.map(u => `
        <tr>
            <td><span class="id-badge">${u.id}</span></td>
            <td style="font-weight:500">${escHtml(u.nome)}</td>
            <td class="td-muted">${escHtml(u.email)}</td>
            <td class="td-muted">${escHtml(u.cpf ?? '—')}</td>
            <td class="td-muted">${escHtml(u.telefone ?? '—')}</td>
            <td style="text-align:right">
                <button class="btn-edit me-1"
                    data-id="${u.id}"
                    data-nome="${escHtml(u.nome)}"
                    data-email="${escHtml(u.email)}"
                    data-cpf="${escHtml(u.cpf ?? '')}"
                    data-telefone="${escHtml(u.telefone ?? '')}"
                    onclick="handleEditar(this)">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn-delete"
                    data-id="${u.id}"
                    data-nome="${escHtml(u.nome)}"
                    onclick="handleDeletar(this)">
                    <i class="bi bi-trash3"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

function mostrarVazio() {
    document.getElementById('emptyState').classList.remove('d-none');
    document.getElementById('usuariosTableBody').innerHTML = '';
}

function atualizarContador(n) {
    document.getElementById('userCount').textContent = `${n} registro${n !== 1 ? 's' : ''}`;
}


async function criarUsuario() {
    ocultarAlertas();
    try {
        const res = await fetch(API, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                nome:      document.getElementById('criarNome').value,
                email:     document.getElementById('criarEmail').value,
                senha:     document.getElementById('criarSenha').value,
                cpf:       document.getElementById('criarCpf').value,
                telefone:  document.getElementById('criarTelefone').value
            })
        });
        if (res.ok) {
            document.getElementById('formCriar').reset();
            mostrarSucesso('Usuário cadastrado com sucesso!');
            carregarUsuarios();
        } else {
            mostrarErro(await res.text());
        }
    } catch {
        mostrarErro('Erro ao conectar com o servidor.');
    }
}


function handleEditar(btn) {
    const id       = btn.dataset.id;
    const nome     = btn.dataset.nome;
    const email    = btn.dataset.email;
    const cpf      = btn.dataset.cpf;
    const telefone = btn.dataset.telefone;

    document.getElementById('editId').value          = id;
    document.getElementById('editNome').value         = nome;
    document.getElementById('editEmail').value        = email;
    document.getElementById('editSenha').value        = '';
    document.getElementById('editCpf').value          = cpf;
    document.getElementById('editTelefone').value     = telefone;

    document.getElementById('editIndicator').style.display = 'flex';
    document.getElementById('editingName').textContent     = `#${id} ${nome}`;
    document.getElementById('editPlaceholder').style.display = 'none';
    document.getElementById('formEditar').style.display      = 'block';

    trocarAba('editar');
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

async function atualizarUsuario(id) {
    ocultarAlertas();
    try {
        const body = {
            nome:     document.getElementById('editNome').value,
            email:    document.getElementById('editEmail').value,
            senha:    document.getElementById('editSenha').value,
            cpf:      document.getElementById('editCpf').value,
            telefone: document.getElementById('editTelefone').value
        };


        Object.keys(body).forEach(key => {
            if (!body[key] || body[key].trim() === '') delete body[key];
        });

        const res = await fetch(`${API}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });
        if (res.ok) {
            cancelarEdicao();
            mostrarSucesso('Usuário atualizado com sucesso!');
            carregarUsuarios();
        } else {
            mostrarErro(await res.text());
        }
    } catch {
        mostrarErro('Erro ao conectar com o servidor.');
    }
}

function cancelarEdicao() {
    document.getElementById('formEditar').reset();
    document.getElementById('editId').value                  = '';
    document.getElementById('editIndicator').style.display   = 'none';
    document.getElementById('editPlaceholder').style.display = 'block';
    document.getElementById('formEditar').style.display      = 'none';
    trocarAba('criar');
}


function handleDeletar(btn) {
    deletePendingId = btn.dataset.id;
    document.getElementById('deleteUserName').textContent = `#${btn.dataset.id} — ${btn.dataset.nome}`;
    deleteModal.show();
}

function confirmarDelete(id, nome) {
    deletePendingId = id;
    document.getElementById('deleteUserName').textContent = `#${id} — ${nome}`;
    deleteModal.show();
}

async function deletarUsuario(id) {
    try {
        const res = await fetch(`${API}/${id}`, { method: 'DELETE' });
        deleteModal.hide();
        deletePendingId = null;
        if (res.ok) {
            mostrarSucesso('Usuário deletado com sucesso!');
            carregarUsuarios();
        } else {
            mostrarErro(await res.text());
        }
    } catch {
        mostrarErro('Erro ao conectar com o servidor.');
    }
}


function mostrarErro(msg) {
    document.getElementById('alertMessage').textContent = msg;
    document.getElementById('alertError').classList.remove('d-none');
    document.getElementById('alertSuccess').classList.add('d-none');
}

function mostrarSucesso(msg) {
    document.getElementById('alertSuccessMessage').textContent = msg;
    document.getElementById('alertSuccess').classList.remove('d-none');
    document.getElementById('alertError').classList.add('d-none');
    setTimeout(() => document.getElementById('alertSuccess').classList.add('d-none'), 3000);
}

function ocultarAlertas() {
    document.getElementById('alertError').classList.add('d-none');
    document.getElementById('alertSuccess').classList.add('d-none');
}

function escHtml(str) {
    return String(str)
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;');
}