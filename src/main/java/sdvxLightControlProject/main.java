package sdvxLightControlProject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

class jsonAddress{
    String address;
    String encrypted_address;
}

public class main {
    public static String FindSSE3Port() {

        // Open coreProps.json to parse what port SteelSeries Engine 3 is listening on.
        String jsonAddressStr = "";
        String corePropsFileName;
        // Check if we should be using the Windows path to coreProps.json
        if(System.getProperty("os.name").startsWith("Windows")) {
            corePropsFileName = System.getenv("PROGRAMDATA") +
                    "\\SteelSeries\\SteelSeries Engine 3\\coreProps.json";
        } else {
            // Mac path to coreProps.json
            corePropsFileName = "/Library/Application Support/" +
                    "SteelSeries Engine 3/coreProps.json";
        }

        try {
            BufferedReader coreProps = new BufferedReader(new FileReader(corePropsFileName));
            jsonAddressStr = coreProps.readLine();
            // System.out.println("Opened coreProps.json and read: " + jsonAddressStr);
            coreProps.close();
        } catch (FileNotFoundException e) {
            System.out.println("coreProps.json not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unhandled exception.");
        }

        Gson reader = new Gson();
        jsonAddress address = reader.fromJson(jsonAddressStr, jsonAddress.class);
        return address.address;
    }

    public static void main(String[] args){
        jsonBuilder jb = new jsonBuilder();

        String version = "0.1 alpha";
        String gamesenseIP = "";


        // Readme here
        System.out.println("SDVX Light Effect Controller by KonmaiLightEffectTeam");
        System.out.println("powered by Steelseries' GameSense");
        System.out.println("version " + version);

        // Get server address
        gamesenseIP = FindSSE3Port();
        String metadataUrl = gamesenseIP + "/game_metadata";
        String removeUrl = gamesenseIP + "/remove_game";
        String bindingUrl = gamesenseIP + "/bind_game_event";
        System.out.println("Current Gamesense IP: " + gamesenseIP);
        System.out.println("Connecting to Gamesense...");

        // Send binding request
        HttpClient sender = HttpClientBuilder.create().build();


        // Test JSON
        /*
        System.out.println(jb.getButtonBindingPackage());
        System.out.println(jb.getFXBindingPackage());
        System.out.println(jb.getIdleBindingPackage());
        */
    }


}
