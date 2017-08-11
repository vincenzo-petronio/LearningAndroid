package it.localhost.app.mobile.learningandroid.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import it.localhost.app.mobile.learningandroid.data.model.Contact;

/**
 *
 */

public class ApiService {

    public List<Contact> getContacts() {
        String resultJson = "[{\"name\":\"Henry\",\"surname\":\"Oneil\",\"sex\":\"m\",\"birthday\":\"22/08/1956\"},{\"name\":\"Candice\",\"surname\":\"Oneil\",\"sex\":\"f\",\"birthday\":\"15/01/1985\"},{\"name\":\"Rebecca\",\"surname\":\"Powel\",\"sex\":\"f\",\"birthday\":\"05/11/1966\"},{\"name\":\"Frances\",\"surname\":\"Rieker\",\"sex\":\"m\",\"birthday\":\"21/02/1996\"},{\"name\":\"Erin\",\"surname\":\"Trujillo\",\"sex\":\"m\",\"birthday\":\"26/04/1984\"}]";

        return new Gson().fromJson(resultJson, new TypeToken<ArrayList<Contact>>() {
        }.getType());
    }
}
