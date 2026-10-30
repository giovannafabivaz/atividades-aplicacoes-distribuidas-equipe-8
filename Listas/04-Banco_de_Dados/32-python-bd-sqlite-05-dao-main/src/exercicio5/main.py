from .dao.farmacia_dao import MedicamentoDAO, FornecedorDAO, CompraDAO
from .model.entidades import Medicamento, Fornecedor, Compra

def main() -> None:
    # 1. Inicializar DAOs e criar tabelas
    medicamento_dao = MedicamentoDAO()
    fornecedor_dao = FornecedorDAO()
    compra_dao = CompraDAO()

    medicamento_dao.create_table()
    fornecedor_dao.create_table()
    compra_dao.create_table()

    # 2. Inserir Medicamento
    medicamento = Medicamento(nome="Paracetamol 500mg", preco=3.50)
    medicamento = medicamento_dao.create(medicamento)
    print(f"Medicamento registado: {medicamento}")

    # 3. Inserir Fornecedor
    fornecedor = Fornecedor(nome="Farmacêutica Lda", cidade="Porto")
    fornecedor = fornecedor_dao.create(fornecedor)
    print(f"Fornecedor registado: {fornecedor}")

    # 4. Registar Compra
    compra = Compra(id_medicamento=medicamento.id_medicamento, id_fornecedor=fornecedor.id_fornecedor, quantidade=50, data_compra="2026-10-30")
    compra = compra_dao.create(compra)
    print(f"Compra registada: {compra}")

if __name__ == "__main__":
    main()