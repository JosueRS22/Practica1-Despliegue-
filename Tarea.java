import java.io.Serializable;
import java.util.UUID;

enum Estado implements Serializable {
    PENDIENTE, COMPLETADA, ELIMINADA 
}

public class Tarea implements Serializable {
    private String id;
    private String nombre;
    private Estado estado;

    public Tarea(String nombre) {
        this.id = generarId();
        this.nombre = nombre;
        this.estado = Estado.PENDIENTE;
    }

    private String generarId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return this.estado.toString();
    }

    public void setEstado(Estado nuevoEstado) {
        if (estado == Estado.PENDIENTE && nuevoEstado == Estado.COMPLETADA) 
            this.estado = Estado.COMPLETADA;

        else if (nuevoEstado == Estado.ELIMINADA) 
            this.estado = Estado.ELIMINADA;
    }
}
