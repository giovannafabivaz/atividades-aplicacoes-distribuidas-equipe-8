import { createContext, useContext, useState, ReactNode } from 'react';

interface ContadorContextType {
  valor: number;
  historico: number[];
  incrementar: () => void;
  decrementar: () => void;
  definirValor: (novoValor: number) => void;
  resetar: () => void;
  limparHistorico: () => void;
}

const ContadorContext = createContext<ContadorContextType | undefined>(undefined);

export function ContadorProvider({ children }: { children: ReactNode }) {
  const [valor, setValor] = useState(0);
  const [historico, setHistorico] = useState<number[]>([]);
  const adicionarAoHistorico = (novoValor: number) => {
    setHistorico((histAtual) => {
      const novoHistorico = [novoValor, ...histAtual];
      return novoHistorico.slice(0, 10);
    });
  };

  const incrementar = () => setValor((v) => {
    const novoValor = v + 1;
    adicionarAoHistorico(novoValor);
    return novoValor;
  });
  
  const decrementar = () => setValor((v) => {
    if (v > 0) {
      const novoValor = v - 1;
      adicionarAoHistorico(novoValor);
      return novoValor;
    }
    return 0;
  });

  const definirValor = (novoValor: number) => {
    setValor(novoValor);
    adicionarAoHistorico(novoValor);
  };

  const resetar = () => {
    setValor(0);
    adicionarAoHistorico(0);
  };

  const limparHistorico = () => setHistorico([]);

  return (
    <ContadorContext.Provider value={{ 
      valor, historico, incrementar, decrementar, definirValor, resetar, limparHistorico 
    }}>
      {children}
    </ContadorContext.Provider>
  );
}

export function useContador() {
  const context = useContext(ContadorContext);
  if (!context)
    throw new Error('useContador deve ser usado dentro de ContadorProvider');
  return context;
}