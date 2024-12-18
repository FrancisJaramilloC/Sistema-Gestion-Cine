package controller.tda.list;
import controller.tda.list.LinkedList;
public class LinkedList<E> {
    private Node<E> header; // Nodo cabecera (el primer nodo de la lista)
    private Node<E> last;   // Nodo último (el último nodo de la lista)
    private Integer size;   // Tamaño de la lista (cuenta el número de nodos en la lista)

    // Constructor de la clase LinkedList
    public LinkedList() {
        this.header = null; // Inicialmente, la cabecera es nula (no hay nodos en la lista)
        this.last = null;   // Inicialmente, el último nodo es nulo
        this.size = 0;      // Inicialmente, el tamaño de la lista es 0
    }

    // Método para verificar si la lista está vacía
    public Boolean isEmpty() {
        // Retorna verdadero si la cabecera es nula o el tamaño es 0, es decir, si la lista está vacía
        return (this.header == null || this.size == 0);
    }

    // Método privado para agregar un elemento al principio de la lista
    private void addHeader(E dato) {
        Node<E> help; // Nodo de ayuda para insertar el nuevo dato

        // Si la lista está vacía
        if (isEmpty()) {
            help = new Node<>(dato);  // Crea un nuevo nodo con el dato
            header = help;            // El nuevo nodo se convierte en el nodo cabecera
            this.size++;              // Incrementa el tamaño de la lista
        } else {
            // Si la lista no está vacía
            Node<E> healpHeader = this.header;   // Guarda el nodo cabecera actual en una variable auxiliar
            help = new Node<>(dato, healpHeader); // Crea un nuevo nodo que apunta al nodo cabecera actual
            this.header = help;                   // El nuevo nodo se convierte en la nueva cabecera
        }
        this.size++; // Incrementa el tamaño de la lista
    }

    private void addLast(E info) {
        Node<E> help; // Nodo para ayudar a agregar el nuevo elemento
        if (isEmpty()) { // Verificar si la lista está vacía
            help = new Node<>(info); // Crear un nuevo nodo
            header = help; // Establecer el nuevo nodo como cabecera
            last = help; // Establecer el nuevo nodo como último
        } else {
            help = new Node<>(info, null); // Crear un nuevo nodo
            last.setNext(help); // Conectar el último nodo al nuevo nodo
            last = help; // Actualizar 'last' al nuevo nodo
        }
        this.size++; // Incrementar el tamaño de la lista
    }

    public void add(E info){
        addLast(info);
    }

    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException{
        if(isEmpty()){
            throw new ListEmptyException("Error, List empty");
        } else if(index.intValue() < 0 || index.intValue() >= this.size){
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == (this.size - 1)){
            return last;
        } else{
            Node<E> search = header;
            int cont = 0;
            while(cont < index.intValue()){
                cont++;
                search = search.getNext();
            }
            return search;
        }
}

private E getFirst() throws ListEmptyException{
    if(isEmpty()){
        throw new ListEmptyException("Error, lista vacia");
    }
    return last.getInfo();
}

public E getLast() throws ListEmptyException{
    if(isEmpty()){
        throw new ListEmptyException("Error, Lista Vacia");
    }
    return last.getInfo();
}

public E get (Integer index) throws ListEmptyException, IndexOutOfBoundsException{
    if(isEmpty()){
        throw new ListEmptyException("Error, list empty");        
    } else if(index.intValue() < 0 || index.intValue() >= this.size.intValue()){
        throw new IndexOutOfBoundsException("Error, fuera de rango");
    } else if (index.intValue() == 0){
        return header.getInfo();
    } else if (index.intValue() == (this.size -1)){
        return last.getInfo();
    }else {
        Node<E> search = header;
        int cont = 0;
        while (cont < index.intValue()){
            cont++;
            search = search.getNext();
        }
        return search.getInfo();
    }
}

public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException{
    if(isEmpty() || index.intValue() == 0) {
        addHeader(info);
    } else if (index.intValue() == this.size.intValue()){
        addLast(info);
    } else{
        Node<E> search_preview = getNode(index - 1);
        Node<E> search = getNode(index);
        Node<E> help = new Node<>(info, search);
        search_preview.setNext(help);
        this.size++;
    }
}

/***END BYPOSITION */
public void reset(){
    this.header = null;
    this.last = null;
    this.size = 0;
}

@Override
public String toString(){
    StringBuilder sb = new StringBuilder(  "List data");
    try{
        Node<E> help = header;
        while(help != null){
            sb.append(help.getInfo()).append(" ->");
            help = help.getNext();
        }
    }catch (Exception e){
        sb.append(e.getMessage());
    }
    return sb.toString();
}

public Integer getSize(){
    return this.size;
}
public Node<E> getHeader() {
    return header; // Devuelve el nodo cabecera
}


// esta bien
public E[] toArray(){
    E[] matrix = null;
    if(!isEmpty()){
        Class clazz = header.getInfo().getClass(); 
        matrix = (E[])java.lang.reflect.Array.newInstance(clazz, size);
        Node<E> aux = header;
        for(int i = 0; i < this.size; i++){
            matrix[i] = aux.getInfo();
            aux = aux.getNext();
        }

    }
    return matrix;
}



public LinkedList<E> toList(E[]matrix){
    reset();
for (int i = 0; i < matrix.length; i++){
    this.add(matrix[i]); 
  }
  return this;
}


}    