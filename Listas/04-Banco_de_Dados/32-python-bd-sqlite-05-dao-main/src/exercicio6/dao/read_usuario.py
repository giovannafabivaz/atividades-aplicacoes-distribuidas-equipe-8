import sqlite3
from contextlib import closing
from typing import Optional
from ..database.connection_factory import ConnectionFactory
from ..model.usuario import Usuario

class ReadUsuario:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def execute(self, usuario_id: int) -> Optional[Usuario]:
        sql = "SELECT id, nome, nascimento FROM usuario WHERE id = ?"
        with closing(self.connection_factory.create_connection()) as conn:
            conn.row_factory = sqlite3.Row
            row = conn.execute(sql, (usuario_id,)).fetchone()
        
        if row is None:
            return None
        return Usuario(id=row["id"], nome=row["nome"], nascimento=row["nascimento"])