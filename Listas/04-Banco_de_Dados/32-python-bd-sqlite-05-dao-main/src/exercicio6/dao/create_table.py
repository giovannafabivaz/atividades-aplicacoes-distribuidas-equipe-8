from contextlib import closing
from typing import Optional
from ..database.connection_factory import ConnectionFactory

class CreateTableUsuario:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def execute(self) -> None:
        sql = """
            CREATE TABLE IF NOT EXISTS usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome VARCHAR(256) NOT NULL,
                nascimento TEXT
            )
        """
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)