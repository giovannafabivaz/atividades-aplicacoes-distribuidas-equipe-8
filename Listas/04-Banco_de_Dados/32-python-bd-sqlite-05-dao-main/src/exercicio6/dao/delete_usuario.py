from contextlib import closing
from typing import Optional
from ..database.connection_factory import ConnectionFactory
from ..model.usuario import Usuario

class DeleteUsuario:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def execute(self, usuario: Usuario) -> None:
        sql = "DELETE FROM usuario WHERE id = ?"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql, (usuario.id,))