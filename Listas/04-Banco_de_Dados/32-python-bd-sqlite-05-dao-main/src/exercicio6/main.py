from .dao.create_table import CreateTableUsuario
from .dao.create_usuario import CreateUsuario
from .dao.read_usuario import ReadUsuario
from .dao.update_usuario import UpdateUsuario
from .dao.delete_usuario import DeleteUsuario
from .model.usuario import Usuario

def main() -> None:
    # 1. Criar Tabela
    CreateTableUsuario().execute()

    # 2. Inserir
    usuario = Usuario(nome="Ana", nascimento="2000-06-07")
    usuario = CreateUsuario().execute(usuario)
    print(f"Inserido: {usuario}")

    # 3. Ler
    usuario_lido = ReadUsuario().execute(usuario.id)
    print(f"Lido: {usuario_lido}")

    # 4. Atualizar
    usuario_lido.nome = "Ana Silva"
    UpdateUsuario().execute(usuario_lido)
    print(f"Atualizado: {ReadUsuario().execute(usuario_lido.id)}")

    # 5. Eliminar
    DeleteUsuario().execute(usuario_lido)
    print(f"Lido após eliminação: {ReadUsuario().execute(usuario_lido.id)}")

if __name__ == "__main__":
    main()