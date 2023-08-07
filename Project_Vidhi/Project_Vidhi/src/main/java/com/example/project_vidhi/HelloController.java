package com.example.project_vidhi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloController {

    @FXML
    private Label recipeLabel;

    @FXML
    private TextField searchField;

    // Replace "6cad067da2e14e1aa85fb9169f105067" with your actual API key
    private final String apiKey = "6cad067da2e14e1aa85fb9169f105067";

    @FXML
    public void searchRecipe() {
        String recipeName = searchField.getText();
        String apiUrl = "https://api.spoonacular.com/recipes/complexSearch?query=" + recipeName + "&apiKey=" + apiKey;

        try {
            // Send an HTTP GET request to the API and read the response
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray resultsArray = jsonResponse.getJSONArray("results");

            // Display the recipe information on the label
            StringBuilder displayText = new StringBuilder();
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject recipeObject = resultsArray.getJSONObject(i);
                String title = recipeObject.getString("title");
                String imageUrl = recipeObject.getString("image");

                displayText.append(title).append("\n");
                displayText.append("Image URL: ").append(imageUrl).append("\n\n");
            }

            recipeLabel.setText(displayText.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
