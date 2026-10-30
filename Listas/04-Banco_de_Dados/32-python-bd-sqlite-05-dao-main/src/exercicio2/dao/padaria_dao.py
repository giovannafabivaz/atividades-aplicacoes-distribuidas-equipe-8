import sqlite3
from contextlib import closing
from typing import Optional, List
from ..database.connection_factory import ConnectionFactory
from ..model.entidades import Cliente, Produto, Pedido, ItemPedido

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

    def read_all(self) -> List[Cliente]:
        sql = "SELECT * FROM Cliente"
        clientes = []
        with closing(self.connection_factory.create_connection()) as conn:
            conn.row_factory = sqlite3.Row
            for row in conn.execute(sql).fetchall():
                clientes.append(Cliente(**dict(row)))
        return clientes

class ProdutoDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Produto (
                    id_produto INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    preco REAL NOT NULL
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, produto: Produto) -> Produto:
        sql = "INSERT INTO Produto (nome, preco) VALUES (?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (produto.nome, produto.preco))
                produto.id_produto = cursor.lastrowid
        return produto

class PedidoDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Pedido (
                    id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_cliente INTEGER NOT NULL,
                    data_pedido TEXT NOT NULL,
                    total REAL NOT NULL,
                    FOREIGN KEY(id_cliente) REFERENCES Cliente(id_cliente)
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, pedido: Pedido) -> Pedido:
        sql = "INSERT INTO Pedido (id_cliente, data_pedido, total) VALUES (?, ?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (pedido.id_cliente, pedido.data_pedido, pedido.total))
                pedido.id_pedido = cursor.lastrowid
        return pedido

class ItemPedidoDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS ItemPedido (
                    id_pedido INTEGER NOT NULL,
                    id_produto INTEGER NOT NULL,
                    quantidade INTEGER NOT NULL,
                    PRIMARY KEY(id_pedido, id_produto),
                    FOREIGN KEY(id_pedido) REFERENCES Pedido(id_pedido),
                    FOREIGN KEY(id_produto) REFERENCES Produto(id_produto)
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, item: ItemPedido) -> ItemPedido:
        sql = "INSERT INTO ItemPedido (id_pedido, id_produto, quantidade) VALUES (?, ?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql, (item.id_pedido, item.id_produto, item.quantidade))
        return item