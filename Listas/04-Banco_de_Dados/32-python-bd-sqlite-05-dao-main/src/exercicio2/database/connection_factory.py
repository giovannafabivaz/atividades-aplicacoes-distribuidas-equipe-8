import os
import sqlite3
from pathlib import Path
from typing import Optional, Union

class ConnectionFactory:
    def __init__(self, database_path: Optional[Union[str, Path]] = None) -> None:
        self.database_path = database_path or os.getenv("SQLITE_DB_PATH", "./src/exercicio2/database/padaria.db")
        os.makedirs(os.path.dirname(self.database_path), exist_ok=True)

    def create_connection(self) -> sqlite3.Connection:
        return sqlite3.connect(self.database_path)