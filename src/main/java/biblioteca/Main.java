package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        CentroCopias centroCopias = new CentroCopias();

        List<Estudiante> estudiantes = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Estudiante estudiante = new Estudiante(centroCopias,i);
            estudiantes.add(estudiante);
            Thread estudianteThread = new Thread(estudiante);
            threads.add(estudianteThread);
            estudianteThread.start();
        }

        Thread.sleep(20000);
        centroCopias.tiempoTerminado = true;

        synchronized (centroCopias) {
            centroCopias.notifyAll();
        }
        for(Thread thread : threads){
            thread.join();
        }



        System.out.println("\n--- RESULTADOS FINALES ---");
        for (Estudiante e : estudiantes) {
            System.out.println("El estudiante " + e.getNumEstudiante() + " ha realizado " + e.getContadorCopias() + " copias");
        }

    }
}
