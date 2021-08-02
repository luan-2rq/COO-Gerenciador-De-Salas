public class IntervaloDeData<T>{

    private T inicio;
    private T fim;

    public IntervaloDeData(T inicio, T fim){

        this.inicio = inicio;
        this.fim = fim;
    }

    public T getInicio(){

        return inicio;
    }

    public T getFim(){
        
        return fim;
    }

}