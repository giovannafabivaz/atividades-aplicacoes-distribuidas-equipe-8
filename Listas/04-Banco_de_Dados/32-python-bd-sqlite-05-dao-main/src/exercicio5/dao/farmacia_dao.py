import sqlite3
from contextlib import closing
from typing import Optional, List
from ..database.connection_factory import ConnectionFactory
from ..model.entidades import Medicamento, Fornecedor, Compra

class MedicamentoDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Medicamento (
                    id_medicamento INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    preco REAL NOT NULL
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, medicamento: Medicamento) -> Medicamento:
        sql = "INSERT INTO Medicamento (nome, preco) VALUES (?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (medicamento.nome, medicamento.preco))
                medicamento.id_medicamento = cursor.lastrowid
        return medicamento

class FornecedorDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Fornecedor (
                    id_fornecedor INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    cidade TEXT NOT NULL
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, fornecedor: Fornecedor) -> Fornecedor:
        sql = "INSERT INTO Fornecedor (nome, cidade) VALUES (?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (fornecedor.nome, fornecedor.cidade))
                fornecedor.id_fornecedor = cursor.lastrowid
        return fornecedor

class CompraDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Compra (
                    id_compra INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_medicamento INTEGER NOT NULL,
                    id_fornecedor INTEGER NOT NULL,
                    quantidade INTEGER NOT NULL,
                    data_compra TEXT NOT NULL,
                    FOREIGN KEY(id_medicamento) REFERENCES Medicamento(id_medicamento),
                    FOREIGN KEY(id_fornecedor) REFERENCES Fornecedor(id_fornecedor)
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, compra: Compra) -> Compra:
        sql = "INSERT INTO Compra (id_medicamento, id_fornecedor, quantidade, data_compra) VALUES (?, ?, ?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (compra.id_medicamento, compra.id_fornecedor, compra.quantidade, compra.data_compra))
                compra.id_compra = cursor.lastrowid
        return compra