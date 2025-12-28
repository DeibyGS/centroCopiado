package biblioteca;

public class CentroCopias {

private boolean[] maquinas = new boolean[2];
public volatile  boolean tiempoTerminado = false;

public CentroCopias() {}

    public synchronized int ocuparMaquina(int numEstudiante) throws InterruptedException {
    while (true){

        if(tiempoTerminado){
            return -1;
        }
        for (int i=0; i<maquinas.length; i++){
            if (!maquinas[i]){
                maquinas[i] = true;
                System.out.println("La máquina número " + (i+1) + " esta ocupada por el estudiante número " + numEstudiante );
                return i;
            }
        }
        wait();
    }
    }
    public synchronized void desocuparMaquina(int i, int numEstudiante) {
        if (i >= 0 && i < maquinas.length) {
            maquinas[i] = false;
            System.out.println("El estudiante " + numEstudiante + " ha liberado la máquina " + (i + 1));
            notifyAll();
        }
    }


}
