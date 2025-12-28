package biblioteca;

public class Estudiante implements Runnable{

    private CentroCopias centroCopias;
    private int numEstudiante;
    private int contadorCopias;


    public Estudiante(CentroCopias cc,int numEstudiante) {
        this.centroCopias = cc;
        this.numEstudiante = numEstudiante;
    }


    private void estudia() throws InterruptedException {
        System.out.println("El estudiante " + numEstudiante + " está estudiando...");
        Thread.sleep((long)(Math.random()*2000));
    }

    private void irAlCentroDeCopiado() {
        System.out.println("El estudiante " + numEstudiante + " se dirige al centro de copias...");
    }

    private void hacerCopias() throws InterruptedException {
        System.out.println("El estudiante " + numEstudiante + " esta haciendo copias...");
        Thread.sleep(1000);
        contadorCopias++;
    }

    @Override
    public void run() {
        try {
            while (!centroCopias.tiempoTerminado) {
                estudia();
                irAlCentroDeCopiado();

                int maquina = centroCopias.ocuparMaquina(numEstudiante);
                if (maquina == -1) {
                    break;
                }
                hacerCopias();
                centroCopias.desocuparMaquina(maquina,numEstudiante);
            }
            }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public int getContadorCopias() {
        return contadorCopias;
    }

    public int getNumEstudiante() {
        return numEstudiante;
    }
}
