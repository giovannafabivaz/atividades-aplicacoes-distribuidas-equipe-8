from contextlib import closing
from typing import Optional
from ..database.connection_factory import ConnectionFactory
from ..model.usuario import Usuario

class CreateUsuario:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def execute(self, usuario: Usuario) -> Usuario:
        sql = "INSERT INTO usuario (nome, nascimento) VALUES (?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (usuario.nome, usuario.nascimento))
                usuario.id = cursor.lastrowid
        return usuario