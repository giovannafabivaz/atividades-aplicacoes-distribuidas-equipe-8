class PerfilUsuario {

    private String biografia;
    private String status;

    public void atualizarPerfil(
        String novaBio,
        String novoStatus,
        String dispositivo
    ) {

        System.out.println(
            dispositivo + " está validando os dados..."
        );

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        synchronized (this) {

            biografia = novaBio;
            status = novoStatus;

            System.out.println(
                "[" + dispositivo + "] Perfil atualizado:"
            );

            System.out.println("Biografia: " + biografia);
            System.out.println("Status: " + status);

            System.out.flush();
        }
    }
}

public class Ex2_PerfilUsuario {

    public static void main(String[] args) {

        PerfilUsuario perfil = new PerfilUsuario();

        Thread dispositivoMovel = new Thread(() -> {
            perfil.atualizarPerfil(
                "Atualização feita pelo celular",
                "Online pelo celular",
                "Dispositivo Móvel"
            );
        });

        Thread computador = new Thread(() -> {
            perfil.atualizarPerfil(
                "Atualização feita pelo computador",
                "Online pelo computador",
                "Computador"
            );
        });

        dispositivoMovel.start();
        computador.start();
    }
}