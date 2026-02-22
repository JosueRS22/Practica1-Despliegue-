import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tarea> tareas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        cargarTareasDesdeArchivo(tareas);
        System.out.println("--- GESTOR DE TAREAS PENDIENTES ---");

        while (opcion != 5) {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    agregarTarea(tareas, scanner);
                    break;
                case 2:
                    listarTareas(tareas);
                    break;
                case 3:
                    marcarCompletada(tareas, scanner);
                    break;
                case 4:
                    eliminarTarea(tareas, scanner);
                    break;
                case 5:
                    System.out.println("Saliendo de la aplicación... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no reconocida.");
                    break;
            }
        }
        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\nMenú de opciones:");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void agregarTarea(ArrayList<Tarea> lista, Scanner sc) {
        System.out.print("Escriba la descripción de la tarea: ");
        String nuevaTarea = sc.nextLine();
        
        try {
            FileOutputStream file = new FileOutputStream("tareas.dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(file);

            Tarea tarea = new Tarea(nuevaTarea);
            lista.add(tarea);

            outputStream.writeObject(lista);
            
            file.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("¡Tarea agregada con éxito!");
    }

    public static void listarTareas(ArrayList<Tarea> lista) {
        System.out.println("\n--- LISTA DE TAREAS ---");

        try {
            for (int i = 0; i < lista.size(); i++) {
                Tarea tarea = lista.get(i);
                System.out.println((i + 1) + ". " + tarea.getNombre() + " - Estado: " + tarea.getEstado());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void marcarCompletada(ArrayList<Tarea> lista, Scanner sc) {
    }

    public static void eliminarTarea(ArrayList<Tarea> lista, Scanner sc) {
        if (lista.isEmpty()) {
            System.out.println("No hay tareas para eliminar.");
            return;
        }

        listarTareas(lista);
        System.out.print("Ingrese el número de la tarea que desea eliminar: ");
        
        try {
            int indice = Integer.parseInt(sc.nextLine()) - 1;

            if (indice >= 0 && indice < lista.size()) {
                Tarea eliminada = lista.remove(indice);
                System.out.println("Tarea '" + eliminada.getNombre() + "' eliminada correctamente.");

                FileOutputStream file = new FileOutputStream("tareas.dat");
                ObjectOutputStream outputStream = new ObjectOutputStream(file);

                outputStream.writeObject(lista);
                
                file.close();
                outputStream.close();
                
            } else {
                System.out.println("Error: El número de tarea no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cargarTareasDesdeArchivo(ArrayList<Tarea> lista) {
        try {
            FileInputStream file = new FileInputStream("tareas.dat");
            ObjectInputStream inputStream = new ObjectInputStream(file);

            ArrayList<Tarea> tareas = (ArrayList<Tarea>) inputStream.readObject();

            System.out.println("Cargando tareas desde el archivo..." + tareas.size() + " tareas");

            lista.addAll(tareas);

            file.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}