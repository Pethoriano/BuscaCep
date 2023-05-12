public class Cep {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public Cep(EncaixaCep meuEncaixaCep) {
        this.cep = meuEncaixaCep.cep();
        this.logradouro = meuEncaixaCep.logradouro();
        this.localidade = meuEncaixaCep.localidade();
        this.bairro = meuEncaixaCep.bairro();
        this.uf = meuEncaixaCep.uf();
    }

    @Override
    public String toString() {
        return "(CEP: " + cep + ", Logradouro: " + logradouro + ", Bairro: " + bairro
        + ", Localidade: " + localidade + "UF: " + uf + ")";
    }

}
