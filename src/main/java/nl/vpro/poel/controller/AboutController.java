package nl.vpro.poel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;
import org.json.JSONObject;
import java.util.Iterator;

@Controller
public class AboutController {

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    String about(Model model) throws Exception{

        try {

//            String url = "https://us3.api.mailchimp.com/3.0/campaigns?status=sent&count=1?fields=send_time,archive_url,settings.title";
//
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//            // optional default is GET
//            con.setRequestMethod("GET");
//
//            String userCredentials = "";
//            String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
//            con.setRequestProperty ("Authorization", basicAuth);
//
//            //add request header
//            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//            int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'GET' request to URL : " + url);
//            System.out.println("Response Code : " + responseCode);
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            HashMap<String, String> map = new HashMap<String, String>();
//            JSONObject jObject = new JSONObject(response.toString());

//            model.addAttribute("mailings", jObject);
            model.addAttribute("mailings", "");
            return "about";

        } catch (Exception e) {
            e.printStackTrace();
            return "about";
        }

    }
}