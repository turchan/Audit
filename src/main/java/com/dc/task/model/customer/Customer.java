package com.dc.task.model.customer;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customers")
public class Customer {

    @Id
    public ObjectId _id = ObjectId.get();
    @Indexed
    public Integer id;
    public String first_name;
    public String last_name;
    public String last_login_balance;

}
