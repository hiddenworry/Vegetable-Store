/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package google;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author ADMIN
 */
public class GoogleAccountDAO {

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String accessToken = null;
        String response = Request.Post(GoogleLoginConstants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GoogleLoginConstants.GOOGLE_CLIENT_ID)
                        .add("client_secret", GoogleLoginConstants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GoogleLoginConstants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", GoogleLoginConstants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

        return accessToken;
    }

    public static GoogleAccountDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = GoogleLoginConstants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GoogleAccountDTO googleDTO = new Gson().fromJson(response, GoogleAccountDTO.class);
        return googleDTO;
    }

}
