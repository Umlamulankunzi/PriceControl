package com.example.diehard.pricecontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DieHard on 16/12/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productPrice";
    private static final String TABLE_PRODUCTS = "Products";
    private static final String PRODUCT_ID = "prod_id";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_QTY = "qty";
    private static final String PRODUCT_PRICE = "price";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + "( " +
                PRODUCT_ID + " INT PRIMARY KEY, " +
                PRODUCT_NAME + " TEXT, " +
                PRODUCT_QTY + " TEXT, " +
                PRODUCT_PRICE + " FLOAT )";
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db );
    }

    void addProduct (Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_QTY, product.getQty());
        values.put(PRODUCT_PRICE, product.getPrice());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();

    }

     Product getProduct(String name, String qty){
        SQLiteDatabase db = this.getReadableDatabase();

         Cursor cursor = db.query(TABLE_PRODUCTS, new String[]{PRODUCT_NAME, PRODUCT_QTY, PRODUCT_PRICE},
                 PRODUCT_NAME + "=? AND " + PRODUCT_QTY + "=? ", new String[]{String.valueOf(name)},
                 null, qty, null, "1");

         if (cursor != null){
             cursor.moveToNext();
         }

         Product product = new Product(cursor.getInt(0), cursor.getString(1),
                 cursor.getString(2), cursor.getFloat(3));

         return product;

     }

     public List<Product> getAllProucts(){
         List<Product>  productList = new ArrayList<>();
         String selectionQuery = "SELECT * FROM " + TABLE_PRODUCTS;

         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectionQuery, null);

         if (cursor.moveToNext()){
             do {
                 Product product = new Product();
                 product.setProd_id(cursor.getInt(0));
                 product.setName(cursor.getString(1));
                 product.setQty(cursor.getString(2));
                 product.setPrice(cursor.getFloat(3));

                 productList.add(product);

             }while(cursor.moveToNext());
         }
         return productList;
     }

     public int updateProduct(Product product){
         SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(PRODUCT_ID, product.getProd_id());
         values.put(PRODUCT_NAME, product.getName());
         values.put(PRODUCT_QTY, product.getQty());
         values.put(PRODUCT_PRICE, product.getPrice());

         return db.update(TABLE_PRODUCTS, values, PRODUCT_ID + " =?"
                 , new String[]{String.valueOf(product.getProd_id())});

     }

     public void deleteProduct(Product product){
         SQLiteDatabase db = this.getWritableDatabase();
         db.delete(TABLE_PRODUCTS, PRODUCT_ID + " =?",
                 new String[]{String.valueOf(product.getProd_id())});

         db.close();
     }

     public int getProductCount (){
         String countQuery  = "SELECT * FROM " + TABLE_PRODUCTS;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(countQuery, null);
         cursor.close();

         return cursor.getCount();

     }



}
