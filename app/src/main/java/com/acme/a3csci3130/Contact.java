package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public String uid;
    public String businessNumber;
    public String name;
    public String primaryBusiness;
    public String address;
    public String province;


    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Constructor that sets the parameters of the Contact.
     * @param uid the UID pulled from DB
     * @param businessNumber the business number String pulled from DB
     * @param name the name String pulled from the DB
     * @param primaryBusiness the primary business String pulled from the DB
     * @param address the address String pulled from the DB
     * @param province the province String pulled from the DB
     */

    public Contact(String uid, String businessNumber, String name, String primaryBusiness, String address,
                   String province){
        this.uid = uid;
        this.businessNumber = businessNumber;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    /**
     * The following toMap method takes the Contact information from the database and maps it
     * to the Contact object.
     * @param result result from Database
     * @param businessNumber the business number String from the DB
     * @param uid the UID String from the db
     * @param name the name of the business String from the DB
     * @param primaryBusiness the primary business String from the DB
     * @param province the province String from the DB
     * @param address the address string from the DB
     * @return returns a hashmap called result containing all the parameters of the business
     * contact.
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("businessNumber", businessNumber);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
