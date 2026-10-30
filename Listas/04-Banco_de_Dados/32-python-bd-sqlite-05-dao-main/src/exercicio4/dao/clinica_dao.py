import sqlite3
from contextlib import closing
from typing import Optional, List
from ..database.connection_factory import ConnectionFactory
from ..model.entidades import Paciente, Medico, Consulta

class PacienteDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Paciente (
                    id_paciente INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, paciente: Paciente) -> Paciente:
        sql = "INSERT INTO Paciente (nome) VALUES (?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (paciente.nome,))
                paciente.id_paciente = cursor.lastrowid
        return paciente

class MedicoDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Medico (
                    id_medico INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    especialidade TEXT NOT NULL
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, medico: Medico) -> Medico:
        sql = "INSERT INTO Medico (nome, especialidade) VALUES (?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (medico.nome, medico.especialidade))
                medico.id_medico = cursor.lastrowid
        return medico

class ConsultaDAO:
    def __init__(self, connection_factory: Optional[ConnectionFactory] = None) -> None:
        self.connection_factory = connection_factory or ConnectionFactory()

    def create_table(self) -> None:
        sql = """CREATE TABLE IF NOT EXISTS Consulta (
                    id_consulta INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_paciente INTEGER NOT NULL,
                    id_medico INTEGER NOT NULL,
                    data_consulta TEXT NOT NULL,
                    valor REAL NOT NULL,
                    FOREIGN KEY(id_paciente) REFERENCES Paciente(id_paciente),
                    FOREIGN KEY(id_medico) REFERENCES Medico(id_medico)
                 )"""
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                conn.execute(sql)

    def create(self, consulta: Consulta) -> Consulta:
        sql = "INSERT INTO Consulta (id_paciente, id_medico, data_consulta, valor) VALUES (?, ?, ?, ?)"
        with closing(self.connection_factory.create_connection()) as conn:
            with conn:
                cursor = conn.execute(sql, (consulta.id_paciente, consulta.id_medico, consulta.data_consulta, consulta.valor))
                consulta.id_consulta = cursor.lastrowid
        return consulta