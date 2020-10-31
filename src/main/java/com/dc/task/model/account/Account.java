package com.dc.task.model.account;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "account_types")
public class Account {

    @Id
    public ObjectId _id = ObjectId.get();
    @Indexed
    @Field("account_type")
    public int type;
    public String name;
}
