public class Sala{

    private String nome;
    private String local;
    private int capacidadeMaxima;
    private String descricao;

    public Sala(String nome, int capacidadeMaxima, String descricao){
        this.capacidadeMaxima = capacidadeMaxima;
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getLocal(){
        return local;
    }
    
    public void setLocal(){
        this.local = local;
    }

    public int getCapacidadeMaxima(){
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima){
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

}
