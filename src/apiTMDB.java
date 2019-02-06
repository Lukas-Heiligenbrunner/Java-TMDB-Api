import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class apiTMDB {
    private static final String apikey = "9fd90530b11447f5646f8e6fb4733fb4";

    public apiTMDB(){
        String json="";
        try {
                URL apiurl = new URL("https://api.themoviedb.org/3/movie/550?api_key=9fd90530b11447f5646f8e6fb4733fb4");
                BufferedReader myreader = new BufferedReader(new InputStreamReader(apiurl.openStream(), Charset.forName("UTF-8")));
                json = readAll(myreader);

            JSONParser pars = new JSONParser();

            JSONObject jsonobj = (JSONObject) pars.parse(json);
            System.out.println(jsonobj.get("id"));

            System.out.println(json);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }




}
