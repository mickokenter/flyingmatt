package cs442.xqiu12.foodmenu;

import java.io.Serializable;

public class meal implements Serializable{
    String name = "";
    double price = 0.;
    int spicy = 0;
    String ingredients = "";
    public meal(){
        name = "";
        price = 0.00;
        spicy = 0;
        ingredients = "";
    }
    public meal(String n, double p, int s, String i){
        setName(n);
        setPrice(p);
        setSpicy(s);
        setIngredients(i);
    }
    public void setName(String n){
        name = n;
    }
    public void setPrice(double p){
        price = p;
    }
    public void setSpicy(int s){
        if(s < 6 && s >= 0) {
            spicy = s;
        }
        else spicy = 5;
    }
    public void setIngredients(String i){
        ingredients = i;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getSpicy(){
        return spicy;
    }
    public String getIngredients(){
        return ingredients;
    }
    public boolean equals(meal sample){
        return this.getName().equals(sample.getName());
    }
}
