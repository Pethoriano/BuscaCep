import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Principal {
    public static void main(String[] args) throws Exception {

        Scanner leitura = new Scanner(System.in);
        List<Cep> ceps = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        var cepDesejado = " ";


        while (!cepDesejado.equalsIgnoreCase("pare")) {

            System.out.println("Por favor, digite o CEP (SEM O USO DE HÍFEN) ou digite PARE para parar a aplicação:");
            cepDesejado = leitura.nextLine();

            if (cepDesejado.equalsIgnoreCase("pare")) {
                break;
            }

            var endereco = "https://viacep.com.br/ws/" + cepDesejado.replace("-", "") +"/json/";
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, BodyHandlers.ofString());
                var json = response.body();
                
                EncaixaCep meuEncaixaCep = gson.fromJson(json, EncaixaCep.class);
                Cep meuCep = new Cep(meuEncaixaCep);
                ceps.add(meuCep);

                System.out.println(meuCep);

            } catch (Exception e) {
                System.out.println("Desculpe, mas um erro ocorreu");
            }
        }

            FileWriter escrita = new FileWriter("Cep.json");
            escrita.write(gson.toJson(ceps));
            escrita.close();

            System.out.println(ceps);

    }
}
