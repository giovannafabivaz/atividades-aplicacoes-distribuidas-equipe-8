from .dao.clinica_dao import PacienteDAO, MedicoDAO, ConsultaDAO
from .model.entidades import Paciente, Medico, Consulta

def main() -> None:
    # 1. Inicializar DAOs e criar tabelas
    paciente_dao = PacienteDAO()
    medico_dao = MedicoDAO()
    consulta_dao = ConsultaDAO()

    paciente_dao.create_table()
    medico_dao.create_table()
    consulta_dao.create_table()

    # 2. Inserir Paciente
    paciente = Paciente(nome="João Silva")
    paciente = paciente_dao.create(paciente)
    print(f"Paciente registado: {paciente}")

    # 3. Inserir Médico
    medico = Medico(nome="Dra. Ana Rita", especialidade="Cardiologia")
    medico = medico_dao.create(medico)
    print(f"Médico registado: {medico}")

    # 4. Registar Consulta
    consulta = Consulta(id_paciente=paciente.id_paciente, id_medico=medico.id_medico, data_consulta="2026-11-05", valor=80.00)
    consulta = consulta_dao.create(consulta)
    print(f"Consulta registada: {consulta}")

if __name__ == "__main__":
    main()