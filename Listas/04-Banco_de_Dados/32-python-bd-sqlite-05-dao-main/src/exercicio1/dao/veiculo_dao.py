import sqlite3
from contextlib import closing
from typing import Optional, List
from ..database.connection_factory import ConnectionFactory
from ..model.veiculo import Veiculo

class VeiculoDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """
            CREATE TABLE IF NOT EXISTS veiculo (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                marca TEXT NOT NULL,
                modelo TEXT NOT NULL,
                chassi TEXT NOT NULL UNIQUE,
                placa TEXT NOT NULL UNIQUE,
                cor TEXT NOT NULL
            )
        """
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, veiculo: Veiculo) -> Veiculo:
        sql = "INSERT INTO veiculo (marca, modelo, chassi, placa, cor) VALUES (?, ?, ?, ?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (veiculo.marca, veiculo.modelo, veiculo.chassi, veiculo.placa, veiculo.cor))
                veiculo.id = cursor.lastrowid
        return veiculo

    def read_all(self) -> List[Veiculo]:
        sql = "SELECT * FROM veiculo"
        veiculos = []
        with closing(self.connection_factory.create_connection()) as conn:
            conn.row_factory = sqlite3.Row
            rows = conn.execute(sql).fetchall()
            for row in rows:
                veiculos.append(Veiculo(**dict(row)))
        return veiculos

    def update(self, veiculo: Veiculo) -> None:
        sql = "UPDATE veiculo SET marca=?, modelo=?, chassi=?, placa=?, cor=? WHERE id=?"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql, (veiculo.marca, veiculo.modelo, veiculo.chassi, veiculo.placa, veiculo.cor, veiculo.id))

    def delete(self, id_veiculo: int) -> None:
        sql = "DELETE FROM veiculo WHERE id = ?"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql, (id_veiculo,))