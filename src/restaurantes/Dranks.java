/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantes;

/**
 *
 * @author Sarwat
 */
public class Dranks {

    int id, cost;
    String name, type;

    public Dranks(int id, String name, String type, int cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
