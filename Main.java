import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> tareas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

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

    public static void agregarTarea(ArrayList<String> lista, Scanner sc) {
        System.out.print("Escriba la descripción de la tarea: ");
        String nuevaTarea = sc.nextLine();
        lista.add(nuevaTarea);
        System.out.println("¡Tarea agregada con éxito!");
    }

    public static void listarTareas(ArrayList<String> lista) {
        System.out.println("\n--- LISTA DE TAREAS ---");
        if (lista.isEmpty()) {
            System.out.println("No hay tareas pendientes.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + ". " + lista.get(i));
            }
        }
    }

    public static void marcarCompletada(ArrayList<String> lista, Scanner sc) {
    }

    public static void eliminarTarea(ArrayList<String> lista, Scanner sc) {
        if (lista.isEmpty()) {
            System.out.println("No hay tareas para eliminar.");
            return;
        }

        listarTareas(lista);
        System.out.print("Ingrese el número de la tarea que desea eliminar: ");
        
        try {
            int indice = Integer.parseInt(sc.nextLine()) - 1;

            if (indice >= 0 && indice < lista.size()) {
                String eliminada = lista.remove(indice);
                System.out.println("Tarea '" + eliminada + "' eliminada correctamente.");
            } else {
                System.out.println("Error: El número de tarea no existe.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número.");
        }
    }
}