import sqlite3
from contextlib import closing
from typing import Optional, List
from ..database.connection_factory import ConnectionFactory
from ..model.entidades import Cliente, Destino, Reserva

class ClienteDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Cliente (
                    id_cliente INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, cliente: Cliente) -> Cliente:
        sql = "INSERT INTO Cliente (nome) VALUES (?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (cliente.nome,))
                cliente.id_cliente = cursor.lastrowid
        return cliente

class DestinoDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Destino (
                    id_destino INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome_destino TEXT NOT NULL,
                    pais TEXT NOT NULL
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, destino: Destino) -> Destino:
        sql = "INSERT INTO Destino (nome_destino, pais) VALUES (?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (destino.nome_destino, destino.pais))
                destino.id_destino = cursor.lastrowid
        return destino

class ReservaDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Reserva (
                    id_reserva INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_cliente INTEGER NOT NULL,
                    id_destino INTEGER NOT NULL,
                    data_viagem TEXT NOT NULL,
                    valor REAL NOT NULL,
                    FOREIGN KEY(id_cliente) REFERENCES Cliente(id_cliente),
                    FOREIGN KEY(id_destino) REFERENCES Destino(id_destino)
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, reserva: Reserva) -> Reserva:
        sql = "INSERT INTO Reserva (id_cliente, id_destino, data_viagem, valor) VALUES (?, ?, ?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (reserva.id_cliente, reserva.id_destino, reserva.data_viagem, reserva.valor))
                reserva.id_reserva = cursor.lastrowid
        return reserva