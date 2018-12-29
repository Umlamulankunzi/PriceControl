package com.example.diehard.pricecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        DatabaseHelper db = new DatabaseHelper(this);

        /*
        // inserting contents into db
        db.addProduct(new Product(0, "Milk", "1ltr", 2.50f));
        db.addProduct(new Product(0, "Peanut Butter", "500g", 3.55f));
        db.addProduct(new Product(0, "Jade", "250g", 1.80f));*/







        /*
        //this.dB.createDb();
        try{
            SQLiteDatabase myDB = this.openOrCreateDatabase("products", MODE_NO_LOCALIZED_COLLATORS, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS prod_prices(name VARCHAR, qty VARCHAR, price DOUBLE)");
            myDB.execSQL("INSERT INTO prod_prices(name, qty, price) VALUES ('Meat', '1kg', 4.50)");
            myDB.execSQL("INSERT INTO prod_prices(name, qty, price) VALUES ('Bread', '1', 1.50)");

            Cursor c = myDB.rawQuery("SELECT * FROM prod_prices ", null);
            int namIndex = c.getColumnIndex("name");
            int qtyIndex = c.getColumnIndex("qty");
            int priceIndex = c.getColumnIndex("price");

            c.moveToFirst();

            while (c != null){
                Log.i("Product Name", c.getString(namIndex));
                Log.i("Product Qty", c.getString(qtyIndex));
                Log.i("Product Price", c.getString(priceIndex));
                c.moveToNext();
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }*/


    }

    public void onClick(View view){
        EditText nameEntry = findViewById(R.id.nameEntry);
        EditText qtyEntry = findViewById(R.id.qtyEntry);
        EditText priceEntry = findViewById(R.id.priceEntry);
        DatabaseHelper db = new DatabaseHelper(this);

        if(nameEntry.getText().toString().length() < 1) {
            Toast.makeText(getApplicationContext(), "Please enter \nProduct Name", Toast.LENGTH_SHORT).show();
        }else if (qtyEntry.getText().toString().length() < 1) {
            Toast.makeText(getApplicationContext(), "Please enter \nQuantity", Toast.LENGTH_SHORT).show();
        }else if (priceEntry.getText().toString().length() < 1) {
            Toast.makeText(getApplicationContext(), "Please enter \nValid Price", Toast.LENGTH_SHORT).show();
        }else {
            String name = nameEntry.getText().toString();
            String qty = qtyEntry.getText().toString();
            Float price = Float.parseFloat(priceEntry.getText().toString());

            db.addProduct(new Product(0, name, qty, price));
            nameEntry.setText("");
            qtyEntry.setText("");
            priceEntry.setText("");
            Toast.makeText(getApplicationContext(), name + " " + qty + "\nSaved", Toast.LENGTH_SHORT).show();


        }






    }





}
