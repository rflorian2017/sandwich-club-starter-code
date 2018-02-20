package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich); //add the sandwich object as a parameter
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);


        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        /**
         * Create a text view for each data that has to be filled in the UI and initialize it with the corresponding text view
         * For each data, use the get methods of the sandwich object. Where the data is a list, loop over the list and append the text to the text view
         */
        TextView mOriginTv = (TextView) findViewById(R.id.origin_tv);
        mOriginTv.setText(sandwich.getPlaceOfOrigin());

        TextView mAlsoKnownAsTv = (TextView) findViewById(R.id.also_known_tv);
        for (String alias : sandwich.getAlsoKnownAs()
                ) {
            mAlsoKnownAsTv.append(alias + "\n");
        }

        TextView mDescriptionTv = (TextView) findViewById(R.id.description_tv);
        mDescriptionTv.setText(sandwich.getDescription());

        TextView mIngredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        for (String ingredient : sandwich.getIngredients()
                ) {
            mIngredientsTv.append(ingredient + "\n");
        }

    }
}
