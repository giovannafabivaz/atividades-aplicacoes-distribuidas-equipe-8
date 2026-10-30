from dataclasses import dataclass
from typing import Optional

@dataclass
class Paciente:
    id_paciente: Optional[int] = None
    nome: str = ""

@dataclass
class Medico:
    id_medico: Optional[int] = None
    nome: str = ""
    especialidade: str = ""

@dataclass
class Consulta:
    id_consulta: Optional[int] = None
    id_paciente: int = 0
    id_medico: int = 0
    data_consulta: str = ""
    valor: float = 0.0