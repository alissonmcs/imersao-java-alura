import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //Abrir conexão HTTP (fazer um get) com o endereço da API e guardar a resposta dentro de uma string (buscar os top 250 filmes)

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        //String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //Exibir e manipular os dados.....
        List<Conteudo> conteudos = extrator.extraiConteudo(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++){
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            //System.out.println("\u001b[30;1m \u001b[43;1m ▶ \u001b[1m" + conteudo.get("title") + " " + "\u001b[m");
            System.out.println("\u001b[30;1m \u001b[43;1m ▶ \u001b[1m" + conteudo.getTitulo() + " " + "\u001b[m");
            //System.out.println("\u001b[30;1m \u001b[41;1m" + "Poster (ctrl + clique): " + "\u001b[m" + conteudo.get("image") + "\u001b[m");
            //System.out.println("\u001b[30;1m \u001b[46;1m ⭐ " + "Nota: " + conteudo.get("imDbRating") + "" + "\u001b[m");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
        }
    }
}